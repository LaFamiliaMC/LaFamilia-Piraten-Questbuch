package de.lafamilia.questbook.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
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
    private FtbEditorBridge.Snapshot editorData = FtbEditorBridge.Snapshot.unavailable("Noch nicht geladen");
    private long lastEditorPoll;
    private boolean editMode = false;
    private int chapterIndex = 0;
    private long cameraChapter = -1;
    private double cameraX, cameraY, pixelsPerUnit = 64.0;
    private FtbEditorBridge.QuestView selectedReal;
    private FtbEditorBridge.QuestView draggingReal;
    private double draggingStartX, draggingStartY, draggingOffsetX, draggingOffsetY;
    private long linkingFrom = -1L;
    private boolean renaming;
    private TextFieldWidget nameField;
    private String editorMessage = "";
    private long messageUntil;


    public PirateQuestScreen() {
        super(Text.literal("La Familia Piraten-Questbuch"));
        selected = catalog.find("kapitaen_tharion");
        if (selected == null) selected = catalog.forCategory(category).stream().findFirst().orElse(null);
    }


    @Override protected void init() {
        super.init();
        layout();
        nameField = new TextFieldWidget(textRenderer,
            Math.round(x0 + 1328 * scale), Math.round(y0 + 699 * scale),
            Math.max(110, Math.round(270 * scale)), 22, Text.literal("Neuer Questname"));
        nameField.setMaxLength(90);
        nameField.setVisible(false);
        addDrawableChild(nameField);
    }

    private void notifyEditor(String msg) {
        editorMessage = msg;
        messageUntil = System.nanoTime() + 5_000_000_000L;
    }

    private void refreshEditor(boolean force) {
        long now = System.nanoTime();
        if (!force && now - lastEditorPoll < 900_000_000L) return;
        lastEditorPoll = now;
        long oldChapterId = currentChapter() == null ? -1 : currentChapter().id();
        long selectedId = selectedReal == null ? -1 : selectedReal.id();
        FtbEditorBridge.Snapshot data = FtbEditorBridge.read();
        editorData = data;
        if (!data.editor() && editMode) {
            editMode = false;
            renaming = false;
            if (nameField != null) nameField.setVisible(false);
            notifyEditor("FTB-Editorberechtigung ist nicht mehr aktiv");
        }
        if (data.chapters().isEmpty()) {
            chapterIndex = 0;
            selectedReal = null;
            return;
        }
        int restored = -1;
        for (int i = 0; i < data.chapters().size(); i++) {
            if (data.chapters().get(i).id() == oldChapterId) { restored = i; break; }
        }
        if (restored >= 0) chapterIndex = restored;
        chapterIndex = Math.max(0, Math.min(chapterIndex, data.chapters().size()-1));
        FtbEditorBridge.ChapterView chapter = data.chapters().get(chapterIndex);
        selectedReal = chapter.quests().stream().filter(q -> q.id() == selectedId).findFirst()
            .orElseGet(() -> chapter.quests().stream().findFirst().orElse(null));
        if (cameraChapter != chapter.id()) cameraChapter = -1;
    }

    private FtbEditorBridge.ChapterView currentChapter() {
        if (editorData.chapters().isEmpty()) return null;
        int idx = Math.max(0, Math.min(chapterIndex, editorData.chapters().size()-1));
        return editorData.chapters().get(idx);
    }

    private void fitCamera() {
        FtbEditorBridge.ChapterView chapter = currentChapter();
        if (chapter == null || cameraChapter == chapter.id()) return;
        cameraChapter = chapter.id();
        if (chapter.quests().isEmpty()) {
            cameraX = cameraY = 0;
            pixelsPerUnit = 75;
            return;
        }
        double minX=Double.POSITIVE_INFINITY, maxX=Double.NEGATIVE_INFINITY;
        double minY=Double.POSITIVE_INFINITY, maxY=Double.NEGATIVE_INFINITY;
        for (FtbEditorBridge.QuestView quest: chapter.quests()) {
            minX = Math.min(minX, quest.x()); maxX = Math.max(maxX, quest.x());
            minY = Math.min(minY, quest.y()); maxY = Math.max(maxY, quest.y());
        }
        cameraX=(minX+maxX)*.5;
        cameraY=(minY+maxY)*.5;
        pixelsPerUnit = Math.max(7.5, Math.min(88,
            Math.min(735/(maxX-minX+3), 465/(maxY-minY+3))));
    }

    private double nodeX(FtbEditorBridge.QuestView q) { return 841+(q.x()-cameraX)*pixelsPerUnit; }
    private double nodeY(FtbEditorBridge.QuestView q) { return 500+(q.y()-cameraY)*pixelsPerUnit; }
    private QuestCatalog.Rect realRect(FtbEditorBridge.QuestView q) {
        int x=(int)Math.round(nodeX(q) - 64), y=(int)Math.round(nodeY(q) - 65);
        if (draggingReal != null && q.id()==draggingReal.id()) {
            x+=(int)Math.round(draggingOffsetX);
            y+=(int)Math.round(draggingOffsetY);
        }
        return new QuestCatalog.Rect(x,y,128,130);
    }

    private void editorButton(DrawContext dc,String title,int x,int y,int w,int h,boolean active) {
        fill(dc,x,y,w,h,active?0xF0442D1D:0xDD271E1B);
        edge(dc,x,y,w,h,2,active?0xFFF5C56B:0xFFAA783B);
        center(dc,title,x+w/2,y+7,0xFFFFE7B0,1.0f);
    }

    private void beginRename() {
        if (selectedReal==null || nameField==null) return;
        renaming = true;
        nameField.setText(selectedReal.title());
        nameField.setVisible(true);
        setFocused(nameField);
        nameField.setFocused(true);
    }

    private void finishRename(boolean submit) {
        if (!renaming) return;
        if (submit && selectedReal != null) {
            String error=FtbEditorBridge.rename(selectedReal,nameField.getText());
            notifyEditor(error.isBlank()?"Questtitel an FTB Quests gesendet":error);
            if (error.isBlank()) refreshEditor(true);
        }
        renaming = false;
        nameField.setVisible(false);
        nameField.setFocused(false);
        setFocused(null);
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
        refreshEditor(false);
        dc.getMatrices().push();
        dc.getMatrices().translate(x0, y0, 0);
        dc.getMatrices().scale(scale, scale, 1f);
        picture(dc, BACKGROUND, 0, 0, W, H);
        float mx = (mouseX - x0) / scale, my = (mouseY - y0) / scale;
        renderCategoryMenu(dc, mx, my);
        renderHeader(dc);
        if (editMode) {
            fitCamera();
            renderEditorTree(dc, mx, my);
            renderEditorToolbar(dc);
        } else {
            renderTree(dc, mx, my);
        }
        renderDetails(dc);
        if (editorData.editor() && !editMode) {
            editorButton(dc,"FTB Bearbeiten",1061,209,186,30,true);
        }
        if (!editorMessage.isBlank() && System.nanoTime() < messageUntil) {
            fill(dc,470,897,755,24,0xDD241811);
            center(dc,editorMessage,848,901,0xFFFFDF9B,1.15f);
        }
        dc.getMatrices().pop();
        super.render(dc,mouseX,mouseY,delta);
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

    private void renderEditorToolbar(DrawContext dc) {
        FtbEditorBridge.ChapterView chapter=currentChapter();
        fill(dc,424,199,822,44,0xD827201B);
        edge(dc,424,199,822,44,2,0xFFB98A4A);
        String t=chapter==null?"Kein FTB Kapitel":chapter.title();
        if (t.length()>43) t=t.substring(0,40)+"...";
        text(dc,"FTB "+(chapterIndex+1)+"/"+editorData.chapters().size()+": "+t,445,213,0xFFFFE8BE,1.3f);
        editorButton(dc,"<",948,206,35,30,false);
        editorButton(dc,">",988,206,35,30,false);
        editorButton(dc,"+ Quest",1028,206,95,30,false);
        editorButton(dc,"Speichern",1129,206,110,30,false);
    }

    private void renderEditorTree(DrawContext dc,float mx,float my) {
        FtbEditorBridge.ChapterView chapter=currentChapter();
        if (chapter==null) {
            center(dc,editorData.message().isBlank()?
                "Noch keine FTB-Kapitel vorhanden – im FTB-Editor anlegen":editorData.message(),
                835,475,0xFFFFE9B0,1.7f);
            return;
        }
        List<FtbEditorBridge.QuestView> quests=chapter.quests();
        if (quests.isEmpty()) {
            center(dc,"Dieses FTB-Kapitel ist leer. Klicke auf + Quest.",838,492,0xFFFFE9B0,1.8f);
        }
        for (FtbEditorBridge.QuestView dependent:quests) for(long depId:dependent.dependencies()) {
            FtbEditorBridge.QuestView prerequisite=quests.stream().filter(q->q.id()==depId).findFirst().orElse(null);
            if(prerequisite!=null) {
                QuestCatalog.Rect a=realRect(prerequisite),b=realRect(dependent);
                line(dc,a.x()+a.w()/2,a.y()+a.h()/2,b.x()+b.w()/2,b.y()+b.h()/2);
            }
        }
        List<QuestCatalog.Quest> demoIcons=catalog.forCategory(category);
        int index=0;
        for (FtbEditorBridge.QuestView q: quests) {
            QuestCatalog.Rect r=realRect(q);
            if(r.x()+r.w()<405||r.x()>1261||r.y()+r.h()<247||r.y()>864) {index++;continue;}
            picture(dc,QUEST_FRAME,r.x(),r.y(),r.w(),r.h());
            if(!demoIcons.isEmpty()) {
                picture(dc,tex(demoIcons.get(index%demoIcons.size()).icon()),
                    r.x()+31,r.y()+9,66,65);
            }
            paraCentered(dc,q.title(),r.x()+r.w()/2,r.y()+88,r.w()-12,2,0xFFFFDFA6,1.2f,16);
            if(q==selectedReal||r.contains(mx,my)) {
                edge(dc,r.x()+2,r.y()+2,r.w()-4,r.h()-4,3,0xFFFFD36C);
            }
            if(linkingFrom==q.id()) center(dc,"Quelle",r.x()+r.w()/2,r.y()-15,0xFFFFF1A1,1.1f);
            index++;
        }
    }

    private void renderRealDetails(DrawContext dc) {
        if(selectedReal==null) {
            text(dc,"FTB-Quest bearbeiten",1324,227,0xFF422915,1.5f);
            para(dc,"Wähle links eine echte FTB-Quest aus. Benutze + Quest zum Erstellen.",1324,349,274,8,0xFF563417,1.4f,21);
        } else {
            FtbQuestBridge.Status status=FtbQuestBridge.read(Long.toUnsignedString(selectedReal.id(),16));
            para(dc,selectedReal.title(),1324,229,278,3,0xFF392213,1.72f,23);
            text(dc,"FTB-ID: "+Long.toUnsignedString(selectedReal.id(),16).toUpperCase(),
                1325,295,0xFF76502E,1.05f);
            fill(dc,1322,324,292,2,0xB8884B2E);
            if(status.connected()) {
                para(dc,status.description().isBlank()?"Keine Beschreibung":status.description(),
                    1325,344,275,5,0xFF503018,1.18f,19);
                text(dc,"Aufgaben",1325,452,0xFF4A2C15,1.8f);
                int taskY=491;
                for(int i=0;i<Math.min(3,status.tasks().size());i++) {
                    para(dc,"- "+status.tasks().get(i),1325,taskY,269,2,0xFF54361D,1.12f,16);
                    taskY+=37;
                }
                text(dc,"Belohnungen",1325,616,0xFF4A2C15,1.8f);
                int rewardY=646;
                for(int i=0;i<Math.min(2,status.rewards().size());i++) {
                    para(dc,"- "+status.rewards().get(i),1325,rewardY,270,1,0xFF54361D,1.1f,17);
                    rewardY+=21;
                }
            } else para(dc,status.label(),1325,352,280,4,0xFF76502E,1.18f,20);
        }
        if(renaming) {
            editorButton(dc,"OK",1474,701,59,28,true);
            editorButton(dc,"X",1540,701,53,28,false);
        } else {
            editorButton(dc,"Titel ändern",1325,698,132,31,false);
            editorButton(dc,linkingFrom>=0?"Ziel wählen...":"Verbinden",1465,698,139,31,false);
        }
        editorButton(dc,"FTB-Editor öffnen",1344,753,252,37,false);
    }

    private void renderDetails(DrawContext dc) {
        if(editMode) {renderRealDetails(dc);return;}
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
        layout();
        double mx=(mouseX-x0)/scale,my=(mouseY-y0)/scale;

        if (button == 1 && editMode && linkingFrom >= 0) {
            linkingFrom = -1;
            notifyEditor("Verknüpfung abgebrochen");
            return true;
        }

        if (button == 0 && renaming) {
            if(mx>=1474&&mx<1533&&my>=701&&my<729) { finishRename(true);return true; }
            if(mx>=1540&&mx<1593&&my>=701&&my<729) { finishRename(false);return true; }
            return super.mouseClicked(mouseX,mouseY,button);
        }

        if (button==0 && editorData.editor() && !editMode &&
                mx>=1061&&mx<1247&&my>=209&&my<239) {
            editMode=true;
            linkingFrom=-1;
            refreshEditor(true);
            cameraChapter=-1;
            notifyEditor("FTB-Editor aktiv – echte Kapitel, Quests und Serverrechte");
            return true;
        }

        if (editMode) {
            if(button==0 && mx>=948&&mx<1023&&my>=206&&my<236) {
                if(!editorData.chapters().isEmpty()) {
                    int delta=mx<983?-1:1;
                    chapterIndex=Math.floorMod(chapterIndex+delta,editorData.chapters().size());
                    selectedReal=null;
                    draggingReal=null;
                    cameraChapter=-1;
                    linkingFrom=-1;
                }
                return true;
            }
            if(button==0 && mx>=1028&&mx<1123&&my>=206&&my<236) {
                FtbEditorBridge.ChapterView c=currentChapter();
                if(c==null) notifyEditor("Bitte zuerst im FTB-Editor ein Kapitel anlegen");
                else {
                    fitCamera();
                    String result=FtbEditorBridge.create(c,cameraX,cameraY);
                    notifyEditor(result.isBlank()?"Neue FTB-Quest an den Server gesendet (Checkmark-Aufgabe)":result);
                    if(result.isBlank()) lastEditorPoll=0;
                }
                return true;
            }
            if(button==0 && mx>=1129&&mx<1239&&my>=206&&my<236) {
                String result=FtbEditorBridge.save();
                notifyEditor(result.isBlank()?"Speichern beim FTB-Server angefordert":result);
                return true;
            }
            if(button==0 && mx>=1325&&mx<1457&&my>=698&&my<729) {
                beginRename();return true;
            }
            if(button==0 && mx>=1465&&mx<1604&&my>=698&&my<729) {
                if(selectedReal==null) notifyEditor("Bitte zuerst eine Quest auswählen");
                else {
                    linkingFrom=linkingFrom<0?selectedReal.id():-1;
                    notifyEditor(linkingFrom>=0?"Jetzt die Folgequest anklicken":"Verknüpfung abgebrochen");
                }
                return true;
            }
            if(button==0 && mx>=1344&&mx<1596&&my>=753&&my<790) {
                openOriginalFtb();return true;
            }
            if(button==0) {
                FtbEditorBridge.ChapterView ch=currentChapter();
                if(ch!=null) for(FtbEditorBridge.QuestView quest:ch.quests()) {
                    if(realRect(quest).contains(mx,my)) {
                        if(linkingFrom>=0) {
                            FtbEditorBridge.QuestView source=ch.quests().stream()
                                .filter(v->v.id()==linkingFrom).findFirst().orElse(null);
                            if(source!=null) {
                                String result=FtbEditorBridge.connect(source,quest,ch);
                                notifyEditor(result.isBlank()?"Questabhängigkeit an FTB-Server gesendet":result);
                            } else notifyEditor("Quellquest nicht mehr vorhanden");
                            linkingFrom=-1;
                            lastEditorPoll=0;
                        } else {
                            selectedReal=quest;
                            draggingReal=quest;
                            draggingStartX=mx;
                            draggingStartY=my;
                            draggingOffsetX=draggingOffsetY=0;
                        }
                        return true;
                    }
                }
            }
            for(QuestCatalog.Category c:catalog.categories)if(button==0&&c.rect().contains(mx,my)) {
                category=c.id();
                linkingFrom=-1;
                notifyEditor("Kategorie: "+c.label()+" – FTB-Kapitel über < > auswählen");
                return true;
            }
            return super.mouseClicked(mouseX,mouseY,button);
        }

        if (button != 0) return super.mouseClicked(mouseX,mouseY,button);
        for (QuestCatalog.Category c:catalog.categories) if(c.rect().contains(mx,my)) {
            category=c.id(); selected=catalog.forCategory(category).stream().findFirst().orElse(null);
            lastFtbQuest=null;return true;
        }
        for (QuestCatalog.Quest quest:catalog.forCategory(category)) if(quest.rect().contains(mx,my)) {
            selected=quest;lastFtbQuest=null;return true;
        }
        if(mx>=1352&&mx<1591&&my>=755&&my<789) {openOriginalFtb();return true;}
        return super.mouseClicked(mouseX,mouseY,button);
    }

    @Override public boolean mouseDragged(double mouseX,double mouseY,int button,double deltaX,double deltaY) {
        if(editMode && button==0 && draggingReal!=null) {
            draggingOffsetX=(mouseX-x0)/scale-draggingStartX;
            draggingOffsetY=(mouseY-y0)/scale-draggingStartY;
            return true;
        }
        return super.mouseDragged(mouseX,mouseY,button,deltaX,deltaY);
    }

    @Override public boolean mouseReleased(double mouseX,double mouseY,int button) {
        if(editMode && button==0 && draggingReal!=null) {
            double dx=draggingOffsetX,dy=draggingOffsetY;
            if(Math.abs(dx)+Math.abs(dy)>5) {
                FtbEditorBridge.ChapterView c=currentChapter();
                if(c!=null) {
                    String result=FtbEditorBridge.move(draggingReal,c,
                        draggingReal.x()+dx/pixelsPerUnit,draggingReal.y()+dy/pixelsPerUnit);
                    notifyEditor(result.isBlank()?"Neue Questposition an FTB-Server gesendet":result);
                    lastEditorPoll=0;
                }
            }
            draggingReal=null;
            draggingOffsetX=draggingOffsetY=0;
            return true;
        }
        return super.mouseReleased(mouseX,mouseY,button);
    }

    @Override public boolean shouldPause() { return false; }
    @Override public boolean keyPressed(int keyCode,int scanCode,int modifiers) {
        if(renaming) {
            if(keyCode==257 || keyCode==335) {finishRename(true);return true;}
            if(keyCode==256) {finishRename(false);return true;}
            return super.keyPressed(keyCode,scanCode,modifiers);
        }
        if(keyCode==256) {
            if(linkingFrom>=0) {linkingFrom=-1;return true;}
            if(editMode) {editMode=false;draggingReal=null;return true;}
            close();return true;
        }
        return super.keyPressed(keyCode,scanCode,modifiers);
    }
}
