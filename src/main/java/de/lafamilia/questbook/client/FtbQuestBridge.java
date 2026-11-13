package de.lafamilia.questbook.client;

import net.fabricmc.loader.api.FabricLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Optional, read-only FTB Quests 1.21.1 status bridge. No FTB classes are linked at compile time.
 * Never changes quest progress, rewards, or server state. The server's FTB Quests team data is authoritative.
 * An exact FTB 16-digit hex quest ID must be mapped in demo_quests.json per card; never infer by title.
 * Runtime behavior must be tested with the user's FTB Quests 2101.1.37 client and server.
 */
final class FtbQuestBridge {
    record Status(String label, boolean connected, boolean completed, long progress, long maximum,
                  String title, String description, List<String> tasks, List<String> rewards) {
        static Status absent(String label) {
            return new Status(label, false, false, 0, 0, "", "", List.of(), List.of());
        }
    }

    private static String text(Object component) throws ReflectiveOperationException {
        if (component == null) return "";
        return String.valueOf(component.getClass().getMethod("getString").invoke(component));
    }


    static Status read(String id) {
        if (id == null || id.isBlank())
            return Status.absent("Demo · noch keine FTB-ID zugeordnet");
        if (!id.matches("(?i)[0-9a-f]{1,16}"))
            return Status.absent("Ungültige FTB-Quest-ID");
        if (!FabricLoader.getInstance().isModLoaded("ftbquests"))
            return Status.absent("FTB Quests nicht installiert");
        try {
            Class<?> clientFileClass = Class.forName("dev.ftb.mods.ftbquests.client.ClientQuestFile");
            Field instanceField = clientFileClass.getField("INSTANCE");
            Object file = instanceField.get(null);
            if (file == null) return Status.absent("Warte auf Server-Synchronisierung");
            Object team = clientFileClass.getField("selfTeamData").get(file);
            if (team == null) return Status.absent("Warte auf Team-Fortschritt");
            long questId = Long.parseUnsignedLong(id, 16);
            Object quest = clientFileClass.getMethod("getQuest", long.class).invoke(file, questId);
            if (quest == null) return Status.absent("Quest-ID fehlt im geladenen FTB-Questbuch");
            Class<?> questObjectClass = Class.forName("dev.ftb.mods.ftbquests.quest.QuestObject");
            Method isCompleted = team.getClass().getMethod("isCompleted", questObjectClass);
            boolean completed = (Boolean) isCompleted.invoke(team, quest);
            String title = text(quest.getClass().getMethod("getTitle").invoke(quest));
            List<String> descriptionLines = new ArrayList<>();
            for (Object line : (List<?>) quest.getClass().getMethod("getDescription").invoke(quest)) {
                descriptionLines.add(text(line));
            }
            String description = String.join(" ", descriptionLines);
            List<String> realTasks = new ArrayList<>();
            List<String> realRewards = new ArrayList<>();
            for (Object reward : (Collection<?>) quest.getClass().getMethod("getRewards").invoke(quest)) {
                realRewards.add(text(reward.getClass().getMethod("getTitle").invoke(reward)));
            }


            Method getTasks = quest.getClass().getMethod("getTasks");
            Collection<?> tasks = (Collection<?>) getTasks.invoke(quest);
            long total = 0, done = 0;
            for (Object task : tasks) {
                realTasks.add(text(task.getClass().getMethod("getTitle").invoke(task)));
                long max = (Long) task.getClass().getMethod("getMaxProgress").invoke(task);
                long taskId = (Long) task.getClass().getMethod("getId").invoke(task);
                long curr = (Long) team.getClass().getMethod("getProgress", long.class).invoke(team, taskId);
                total += Math.max(1, max);
                done += Math.min(Math.max(0, curr), Math.max(1, max));
            }
            String label = completed ? "FTB: Abgeschlossen" : total == 0 ? "FTB: Quest offen" :
                    "FTB: Fortschritt " + done + "/" + total;
            return new Status(label, true, completed, done, total,
                    title, description, List.copyOf(realTasks), List.copyOf(realRewards));
        } catch (ReflectiveOperationException | RuntimeException | LinkageError ex) {
            // Keep the questbook usable even if FTB updates its internal classes.
            return Status.absent("FTB-Adapter nicht kompatibel");
        }
    }
}
