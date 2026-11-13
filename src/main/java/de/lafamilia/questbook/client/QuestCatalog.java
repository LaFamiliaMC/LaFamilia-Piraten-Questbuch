package de.lafamilia.questbook.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Model/data boundary for future FTB Quests adapter. Values are sample UI content, NOT server state. */
final class QuestCatalog {
    record Rect(int x, int y, int w, int h) {
        boolean contains(double px, double py) { return px >= x && px < x+w && py >= y && py < y+h; }
    }
    record Category(String id, String label, Rect rect) {}
    record Quest(String id, String category, String title, String description,
                 List<String> tasks, List<String> rewards, Rect rect,
                 String icon, boolean renderReferenceCard, String ftbQuestId) {}
    final List<Category> categories;
    final List<Quest> quests;

    private QuestCatalog(List<Category> categories, List<Quest> quests) {
        this.categories = Collections.unmodifiableList(categories);
        this.quests = Collections.unmodifiableList(quests);
    }

    List<Quest> forCategory(String categoryId) {
        return quests.stream().filter(q -> q.category.equals(categoryId)).toList();
    }
    Quest find(String id) {
        return quests.stream().filter(q -> q.id.equals(id)).findFirst().orElse(null);
    }

    private static String str(JsonObject obj, String key) { return obj.get(key).getAsString(); }
    private static String optionalStr(JsonObject obj, String key) { return obj.has(key) && !obj.get(key).isJsonNull() ? obj.get(key).getAsString() : ""; }
    private static Rect rect(JsonObject obj) {
        return new Rect(obj.get("x").getAsInt(),obj.get("y").getAsInt(),
                obj.get("w").getAsInt(),obj.get("h").getAsInt());
    }
    private static List<String> strings(JsonArray arr) {
        ArrayList<String> out = new ArrayList<>();
        for (JsonElement value : arr) out.add(value.getAsString());
        return out;
    }

    static QuestCatalog load() {
        try (InputStream in = QuestCatalog.class.getClassLoader().getResourceAsStream(
                "assets/lafamilia_questbook/demo_quests.json")) {
            Objects.requireNonNull(in, "Bundled demo_quests.json not found");
            JsonObject root = JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
            ArrayList<Category> categories = new ArrayList<>();
            for (JsonElement e : root.getAsJsonArray("categories")) {
                JsonObject c=e.getAsJsonObject();
                categories.add(new Category(str(c,"id"),str(c,"label"),rect(c.getAsJsonObject("rect"))));
            }
            ArrayList<Quest> quests = new ArrayList<>();
            for (JsonElement e : root.getAsJsonArray("quests")) {
                JsonObject q=e.getAsJsonObject();
                quests.add(new Quest(str(q,"id"),str(q,"category"),str(q,"title"),
                    str(q,"description"),strings(q.getAsJsonArray("tasks")),strings(q.getAsJsonArray("rewards")),
                    rect(q.getAsJsonObject("rect")),str(q,"icon"),q.get("render_reference_card").getAsBoolean(),optionalStr(q,"ftb_quest_id")));
            }
            return new QuestCatalog(categories,quests);
        } catch (Exception ex) {
            throw new IllegalStateException("La Familia: Could not read bundled UI sample data", ex);
        }
    }
}
