package de.lafamilia.questbook.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.fabricmc.loader.api.FabricLoader;
import java.util.List;

/**
 * Alpha 0.4: dynamically rendered questbook on a truly EMPTY pirate background.
 * All categories, headers, quest frames, links, text, task checks and rewards are separate
 * render elements. This screen is opened by FTB Quests, not by a separate P key.
 * Quest contents are demo until an exact FTB quest ID is mapped (no fake progress).
 */
public final class PirateQuestScreen extends Screen {
    private static final int W = 1671, H = 941;
    private static final String MOD = "lafamilia_questbook";
    private static final Identifier BACKGROUND = tex("textures/gui/pirate_empty_background.png");
    private static final Identifier CATEGORY_FRAME = tex("textures/gui/category_empty_button.png");
    private static final Identifier QUEST_FRAME = tex("textures/gui/quest_frame_empty.png");
    private final QuestCatalog catalog = QuestCatalog.load();
    private String category = "piraten_hauptgeschichte";
    private QuestCatalog.Quest selected;
    private QuestCatalog.Quest lastFtbQuest;
    private FtbQuestBridge.Status cachedFtb = FtbQuestBridge.Status.absent("Noch nicht verbunden");
    private long lastFtbCheckNanos;
    private float scale, x0, y0;

    public PirateQuestScreen() {
        super(Text.literal("La Familia Piraten-Questbuch"));
        selected = catalog.find("kapitaen_tharion");
        if (selected == null) selected = catalog.forCategory(category).stream().findFirst().orElse(null);
    }

    private static Identifier tex(String name) { return Identifier.of(MOD, name); }
    private void layout() {
        scale = Math.min((float)width / W, (float)height / H);
        x0 = (width - W * scale) / 2f;
        y0 = (height - H * scale) / 2f;
    }
    private void picture(DrawContext dc, Identifier id, int x, int y, int w, int h) {
        dc.drawTexture(id, x, y, 0f, 0f, w, h, w, h);
    }
    private void fill(DrawContext dc, int x, int y, int w, int h, int color) {
        dc.fill(x, y, x + w, y + h, color);
    }
    private void edge(DrawContext dc, int x, int y, int w, int h, int size, int color) {
        fill(dc, x, y, w, size, color);
        fill(dc, x, y + h - size, w, size, color);
        fill(dc, x, y, size, h, color);
        fill(dc, x + w - size, y, size, h, color);
    }
    private int strWidth(String content) { return client.textRenderer.getWidth(content); }
    private void text(DrawContext dc, String s, int x, int y, int color, float zoom) {
        dc.getMatrices().push();
        dc.getMatrices().translate(x, y, 0);
        dc.getMatrices().scale(zoom, zoom, 1);
        dc.drawText(client.textRenderer, s, 0, 0, color, true);
        dc.getMatrices().pop();
    }
    private void center(DrawContext dc, String s, int x, int y, int color, float zoom) {
        text(dc, s, x - Math.round(strWidth(s) * zoom / 2f), y, color, zoom);
    }
    private void para(DrawContext dc, String s, int x, int y, int maxW, int maxLines, int color, float zoom, int spacing) {
        if (s == null || s.isBlank()) return;
        int line = 0; StringBuilder current = new StringBuilder();
        for (String word : s.split("\\s+")) {
            String next = current.isEmpty() ? word : current + " " + word;
            if (strWidth(next) * zoom > maxW && !current.isEmpty()) {
                text(dc, current.toString(), x, y + line * spacing, color, zoom);
                if (++line >= maxLines) return;
                current = new StringBuilder(word);
            } else current = new StringBuilder(next);
        }
        if (!current.isEmpty() && line < maxLines) text(dc, current.toString(), x, y + line * spacing, color, zoom);
    }
    private void line(DrawContext dc, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        int steps = Math.max(1, Math.max(Math.abs(dx), Math.abs(dy)));
        for (int step = 0; step <= steps; step += 3) {
            int x = x1 + dx * step / steps, y = y1 + dy * step / steps;
            fill(dc, x-4, y-4, 9, 9, 0x666D3A04);
            fill(dc, x-2, y-2, 5, 5, 0xFFFFC551);
        }
    }
    @Override
    public void render(DrawContext dc, int mouseX, int mouseY, float delta) {
        layout();
        // NEVER use the legacy composite: this new bitmap has only scene and empty decorations.
        dc.getMatrices().push();
        dc.getMatrices().translate(x0, y0, 0);
        dc.getMatrices().scale(scale, scale, 1f);
        picture(dc, BACKGROUND, 0, 0, W, H);
        float mx = (mouseX - x0) / scale, my = (mouseY - y0) / scale;
        renderCategoryMenu(dc, mx, my);
        renderHeader(dc);
        renderTree(dc, mx, my);
        renderDetails(dc);
        dc.getMatrices().pop();
    }
    private void renderHeader(DrawContext dc) {
        String label = catalog.categories.stream().filter(c -> c.id().equals(category))
            .map(QuestCatalog.Category::label).findFirst().orElse("Piraten-Hauptgeschichte");
        float size = Math.min(2.7f, 470f / Math.max(1, strWidth(label)));
        center(dc, label, 846, 103, 0xFF45240C, size);
        center(dc, category.equals("piraten_hauptgeschichte") ? "Die Legende von La Familia" :
            "La Familia Abenteuer", 846, 173, 0xFFFFE3A5, 1.75f);
    }
    private void renderCategoryMenu(DrawContext dc, float mx, float my) {
        for (int i = 0; i < catalog.categories.size(); i++) {
            QuestCatalog.Category c = catalog.categories.get(i);
            QuestCatalog.Rect r = c.rect();
            // The gold button is an independent menu asset; label+icon are live.
            picture(dc, CATEGORY_FRAME, r.x()+1, r.y(), r.w()-2, r.h());
            if (c.id().equals(category) || r.contains(mx, my)) {
                edge(dc, r.x()+4, r.y()+5, r.w()-8, r.h()-10,
                    c.id().equals(category) ? 3 : 2,
                    c.id().equals(category) ? 0xFFF7D364 : 0x88F7D364);
            }
            QuestCatalog.Quest first = catalog.forCategory(c.id()).stream().findFirst().orElse(null);
            if (first != null) picture(dc, tex(first.icon()), r.x()+14, r.y()+7, 43, 39);
            float z = Math.min(1.83f, 257f / Math.max(1, strWidth(c.label())));
            text(dc, c.label(), r.x()+76, r.y()+19, 0xFFFFF0CB, z);
        }
    }
    private void renderTree(DrawContext dc, float mx, float my) {
        List<QuestCatalog.Quest> items = catalog.forCategory(category);
        if (category.equals("piraten_hauptgeschichte")) {
            int[][] edges = {{0,1},{1,2},{2,3},{3,4},{2,6},{5,6},{6,7},{5,8},{6,9},{7,10},{8,9},{9,10},{9,11}};
            for (int[] pair : edges) if (pair[0] < items.size() && pair[1] < items.size()) {
                QuestCatalog.Rect a=items.get(pair[0]).rect(), b=items.get(pair[1]).rect();
                line(dc, a.x()+a.w()/2, a.y()+a.h()/2, b.x()+b.w()/2, b.y()+b.h()/2);
            }
        } else {
            // Five demo nodes per category, with a visible branching connection.
            for (int i=1;i<items.size();i++) {
                QuestCatalog.Rect a=items.get(i-1).rect(), b=items.get(i).rect();
                line(dc, a.x()+a.w()/2,a.y()+a.h()/2,b.x()+b.w()/2,b.y()+b.h()/2);
            }
        }
        for (QuestCatalog.Quest q : items) {
            QuestCatalog.Rect r=q.rect();
            picture(dc, QUEST_FRAME, r.x(), r.y(), r.w(), r.h());
            int iconSize = Math.min(83, Math.min(r.w()-34, r.h()-54));
            picture(dc, tex(q.icon()), r.x()+(r.w()-iconSize)/2, r.y()+9, iconSize, iconSize);
            String label = q.title();
            paraCentered(dc, label, r.x()+r.w()/2, r.y()+r.h()-43, r.w()-19,
                2, 0xFFFFE6AA, 1.05f, 15);
            FtbQuestBridge.Status status = (q==selected) ? ftbStatus() : null;
            if (status != null && status.connected() && status.completed())
                center(dc, "✓", r.x()+r.w()-19, r.y()+8, 0xFF42FF72, 2.2f);
            if (q==selected || r.contains(mx,my)) {
                edge(dc, r.x()+2, r.y()+2, r.w()-4, r.h()-4, q==selected?3:2,
                    q==selected?0xFFFFD852:0x99FFD852);
            }
        }
    }
    private void paraCentered(DrawContext dc,String s,int cx,int y,int maxW,int limit,int col,float z,int step) {
        StringBuilder row=new StringBuilder(); int n=0;
        for (String word:s.split("\\s+")) {
            String next=row.isEmpty()?word:row+" "+word;
            if(strWidth(next)*z>maxW&&!row.isEmpty()) {
                center(dc,row.toString(),cx,y+n*step,col,z);
                if(++n>=limit)return; row=new StringBuilder(word);
            }else row=new StringBuilder(next);
        }
        if(!row.isEmpty()&&n<limit)center(dc,row.toString(),cx,y+n*step,col,z);
    }
    private FtbQuestBridge.Status ftbStatus() {
        long now=System.nanoTime();
        if (selected!=lastFtbQuest || now-lastFtbCheckNanos>1_000_000_000L) {
            cachedFtb=FtbQuestBridge.read(selected==null?"":selected.ftbQuestId());
            lastFtbQuest=selected; lastFtbCheckNanos=now;
        }
        return cachedFtb;
    }
    private void renderDetails(DrawContext dc) {
        if(selected==null)return;
        // Right parchment is part of BACKGROUND and deliberately contains NO baked content.
        FtbQuestBridge.Status status=ftbStatus();
        String name=status.connected()&&!status.title().isBlank()?status.title():selected.title();
        String description=status.connected()&&!status.description().isBlank()?status.description():selected.description();
        List<String> tasks=status.connected()?status.tasks():selected.tasks();
        List<String> rewards=status.connected()?status.rewards():selected.rewards();
        picture(dc, tex(selected.icon()), 1332, 225, 78, 79);
        para(dc, name, 1418, 230, 198, 3, 0xFF38210F, 1.9f, 24);
        fill(dc,1323,324,292,2,0xD38C572E);
        para(dc,description,1324,342,285,5,0xFF4A2918,1.26f,20);
        text(dc,"Aufgaben",1324,456,0xFF41220F,2.0f);
        int y=490;
        for(int i=0;i<Math.min(4,tasks.size());i++) {
            edge(dc,1327,y+1,15,15,2,0xFFA36B2B);
            para(dc,tasks.get(i),1350,y+1,260,2,0xFF4A2918,1.1f,15);
            y+=38;
        }
        fill(dc,1323,645,292,2,0xD38C572E);
        text(dc,"Belohnungen",1324,659,0xFF41220F,1.9f);
        y=691;
        for(int i=0;i<Math.min(2,rewards.size());i++) {
            fill(dc,1329,y+2,14,14,0xFFB9852C);
            para(dc,rewards.get(i),1352,y+2,252,1,0xFF4A2918,1.14f,18);
            y+=26;
        }
        // Native FTB editor/reward screen stays available using this real, clickable fallback.
        fill(dc,1352,755,239,34,0xDE224B32);
        edge(dc,1352,755,239,34,3,0xFFD4B36D);
        center(dc,"FTB-Questbuch öffnen",1471,766,0xFFFFEAC8,1.42f);
        if(status.connected()) text(dc,status.label(),1323,738,0xFF4F371F,0.93f);
        else text(dc,"Demo · FTB-Quest-ID noch nicht zugeordnet",1323,739,0xFF62442A,0.82f);
    }
    private void openOriginalFtb() {
        if (!FabricLoader.getInstance().isModLoaded("ftbquests")) return;
        FtbNativeOpen.requestNative();
        try {
            Class.forName("dev.ftb.mods.ftbquests.client.ClientQuestFile")
                .getMethod("openGui").invoke(null);
        } catch (ReflectiveOperationException | LinkageError ex) {
            // Safe fallback: do not mark quests complete or grant rewards here.
        }
    }
    @Override public boolean mouseClicked(double mouseX,double mouseY,int button) {
        if (button != 0) return super.mouseClicked(mouseX,mouseY,button);
        layout();
        double mx=(mouseX-x0)/scale,my=(mouseY-y0)/scale;
        for (QuestCatalog.Category c:catalog.categories) if(c.rect().contains(mx,my)) {
            category=c.id(); selected=catalog.forCategory(category).stream().findFirst().orElse(null);
            lastFtbQuest=null;return true;
        }
        for (QuestCatalog.Quest q:catalog.forCategory(category)) if(q.rect().contains(mx,my)) {
            selected=q;lastFtbQuest=null;return true;
        }
        if (mx>=1352&&mx<1591&&my>=755&&my<789) { openOriginalFtb();return true; }
        return super.mouseClicked(mouseX,mouseY,button);
    }
    @Override public boolean shouldPause() { return false; }
    @Override public boolean keyPressed(int keyCode,int scanCode,int modifiers) {
        if(keyCode==256) {close();return true;}
        return super.keyPressed(keyCode,scanCode,modifiers);
    }
}
