package de.lafamilia.questbook.client;

import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Server-authoritative FTB Quests editor bridge for Minecraft 1.21.1.
 * All writes use the original FTB Quests networking and are guarded by FTB edit permissions.
 * Reflection avoids shipping or linking to a potentially incompatible FTB jar at compile time.
 * Never edits player progress or grants rewards.
 */
final class FtbEditorBridge {
    record QuestView(long id, String title, double x, double y, Object handle, List<Long> dependencies) {}
    record ChapterView(long id, String title, Object handle, List<QuestView> quests) {}
    record Snapshot(boolean available, boolean editor, List<ChapterView> chapters, String message) {
        static Snapshot unavailable(String msg) { return new Snapshot(false, false, List.of(), msg); }
    }
    private FtbEditorBridge() {}

    private static Object call(Object target, String method) throws ReflectiveOperationException {
        return target.getClass().getMethod(method).invoke(target);
    }
    private static String label(Object obj) throws ReflectiveOperationException {
        if (obj == null) return "";
        Object title = call(obj, "getTitle");
        return title == null ? "" : String.valueOf(title.getClass().getMethod("getString").invoke(title));
    }
    private static long id(Object obj) throws ReflectiveOperationException {
        return ((Number) call(obj, "getId")).longValue();
    }

    static Snapshot read() {
        if (!FabricLoader.getInstance().isModLoaded("ftbquests")) {
            return Snapshot.unavailable("FTB Quests ist nicht installiert");
        }
        try {
            Class<?> clazz = Class.forName("dev.ftb.mods.ftbquests.client.ClientQuestFile");
            Object file = clazz.getField("INSTANCE").get(null);
            if (file == null) return Snapshot.unavailable("Warte auf FTB-Questbuch-Synchronisierung");
            boolean editor = (boolean) clazz.getMethod("canEdit").invoke(file);
            Collection<?> groups = (Collection<?>) clazz.getMethod("getChapterGroups").invoke(file);
            List<ChapterView> chapters = new ArrayList<>();
            for (Object group : groups) {
                for (Object chapter : (Collection<?>) call(group, "getChapters")) {
                    List<QuestView> quests = new ArrayList<>();
                    for (Object quest : (Collection<?>) call(chapter, "getQuests")) {
                        List<Long> dependencies = new ArrayList<>();
                        try (Stream<?> stream = (Stream<?>) call(quest, "streamDependencies")) {
                            for (Object dependency : stream.toList()) {
                                dependencies.add(id(dependency));
                            }
                        }
                        quests.add(new QuestView(id(quest), label(quest),
                            ((Number)call(quest,"getX")).doubleValue(),
                            ((Number)call(quest,"getY")).doubleValue(),
                            quest, List.copyOf(dependencies)));
                    }
                    chapters.add(new ChapterView(id(chapter), label(chapter), chapter, List.copyOf(quests)));
                }
            }
            return new Snapshot(true, editor, List.copyOf(chapters), "");
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return Snapshot.unavailable("FTB-Editor-Anbindung nicht bereit: " + e.getClass().getSimpleName());
        }
    }

    private static boolean permitted() throws ReflectiveOperationException {
        Class<?> clientFile = Class.forName("dev.ftb.mods.ftbquests.client.ClientQuestFile");
        Object file = clientFile.getField("INSTANCE").get(null);
        return file != null && Boolean.TRUE.equals(clientFile.getMethod("canEdit").invoke(file));
    }
    private static String error(Exception e) {
        Throwable cause = e;
        if (e instanceof java.lang.reflect.InvocationTargetException it && it.getCause() != null) cause = it.getCause();
        return cause.getClass().getSimpleName() + ": " + String.valueOf(cause.getMessage());
    }
    private static void send(Object packet) throws ReflectiveOperationException {
        Class<?> networkManager = Class.forName("dev.architectury.networking.NetworkManager");
        for (Method method : networkManager.getMethods()) {
            if (method.getName().equals("sendToServer") && Modifier.isStatic(method.getModifiers())
                    && method.getParameterCount() == 1 && method.getParameterTypes()[0].isInstance(packet)) {
                method.invoke(null, packet);
                return;
            }
        }
        throw new NoSuchMethodException("Architectury NetworkManager.sendToServer(CustomPacketPayload)");
    }
    /** Move via FTB Quests' own MoveMovableMessage, checked again on server. */
    static String move(QuestView quest, ChapterView chapter, double x, double y) {
        try {
            if (!permitted()) return "Keine FTB-Editorberechtigung";
            Method move = quest.handle().getClass().getMethod("requestMove",
                Class.forName("dev.ftb.mods.ftbquests.quest.Chapter"), double.class, double.class);
            move.invoke(quest.handle(), chapter.handle(), x, y);
            return "";
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return error(e instanceof Exception ex ? ex : new RuntimeException(e));
        }
    }

    /** Create a real new quest with a checkmark task (never an automatically completed empty quest). */
    static String create(ChapterView chapter, double x, double y) {
        try {
            if (!permitted()) return "Keine FTB-Editorberechtigung";
            Class<?> chapterType = Class.forName("dev.ftb.mods.ftbquests.quest.Chapter");
            Class<?> questType = Class.forName("dev.ftb.mods.ftbquests.quest.Quest");
            Class<?> taskType = Class.forName("dev.ftb.mods.ftbquests.quest.task.Task");
            Class<?> factory = Class.forName("dev.ftb.mods.ftbquests.quest.task.TaskType");
            Object temporary = questType.getConstructor(long.class, chapterType).newInstance(0L, chapter.handle());
            Object task = factory.getMethod("createTask", long.class, questType, String.class)
                    .invoke(null, 0L, temporary, "ftbquests:checkmark");
            if (task == null) return "FTB-Tasktyp 'checkmark' nicht verfügbar";
            Class<?> message = Class.forName("dev.ftb.mods.ftbquests.net.CreateQuestAndTaskMessage");
            Object packet = message.getMethod("requestCreation", chapterType, double.class, double.class, taskType)
                    .invoke(null, chapter.handle(), x, y, task);
            send(packet);
            return "";
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return error(e instanceof Exception ex ? ex : new RuntimeException(e));
        }
    }

    private static void writeQuest(QuestView quest) throws ReflectiveOperationException {
        Class<?> base = Class.forName("dev.ftb.mods.ftbquests.quest.QuestObjectBase");
        Class<?> edit = Class.forName("dev.ftb.mods.ftbquests.net.EditObjectMessage");
        edit.getMethod("sendToServer", base).invoke(null, quest.handle());
    }

    /** Edit original FTB quest metadata; server validates permissions. */
    static String rename(QuestView quest, String newTitle) {
        try {
            if (!permitted()) return "Keine FTB-Editorberechtigung";
            String value = newTitle == null ? "" : newTitle.strip();
            if (value.isEmpty() || value.length() > 90) return "Titel muss zwischen 1 und 90 Zeichen enthalten";
            quest.handle().getClass().getMethod("setRawTitle", String.class).invoke(quest.handle(), value);
            writeQuest(quest);
            return "";
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return error(e instanceof Exception ex ? ex : new RuntimeException(e));
        }
    }

    /** Add prerequisite -> dependent, rejecting duplicates and graph cycles locally. */
    static String connect(QuestView prerequisite, QuestView dependent, ChapterView chapter) {
        try {
            if (!permitted()) return "Keine FTB-Editorberechtigung";
            if (prerequisite.id() == dependent.id()) return "Eine Quest kann nicht von sich selbst abhängen";
            if (dependent.dependencies().contains(prerequisite.id())) return "Diese Verbindung existiert bereits";
            if (pathFromTo(dependent.id(), prerequisite.id(), chapter, new java.util.HashSet<>())) {
                return "Verbindung würde einen Quest-Zyklus erzeugen";
            }
            Class<?> obj = Class.forName("dev.ftb.mods.ftbquests.quest.QuestObject");
            dependent.handle().getClass().getMethod("addDependency", obj)
                    .invoke(dependent.handle(), prerequisite.handle());
            writeQuest(dependent);
            return "";
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return error(e instanceof Exception ex ? ex : new RuntimeException(e));
        }
    }
    private static boolean pathFromTo(long source, long target, ChapterView chapter, java.util.Set<Long> seen) {
        if (source == target) return true;
        if (!seen.add(source)) return false;
        for (QuestView q : chapter.quests()) {
            if (q.id() == source) for (long p : q.dependencies()) {
                if (pathFromTo(p, target, chapter, seen)) return true;
            }
        }
        return false;
    }

    static String save() {
        try {
            if (!permitted()) return "Keine FTB-Editorberechtigung";
            Class<?> message = Class.forName("dev.ftb.mods.ftbquests.net.ForceSaveMessage");
            send(message.getField("INSTANCE").get(null));
            return "";
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return error(e instanceof Exception ex ? ex : new RuntimeException(e));
        }
    }
}
