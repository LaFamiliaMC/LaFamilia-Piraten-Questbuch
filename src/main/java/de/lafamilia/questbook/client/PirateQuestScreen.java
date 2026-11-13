package de.lafamilia.questbook.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.List;

/**
 * Fabric 1.21.1 alpha client screen, based on the pixel-exact approved 1671×941 art.
 * Rendering-only prototype: buttons change local sample selection; no quest progress is modified.
 * The optional read-only FTB Quests bridge requires exact mapped FTB IDs and runtime tests.
 */
public final class PirateQuestScreen extends Screen {
    private static final int W = 1671, H = 941;
    private static final String MOD = "lafamilia_questbook";
    private static final Identifier BACKGROUND = tex("textures/gui/approved_reference_1671x941.png");
    private static final Identifier DETAIL_BLANK = tex("textures/gui/right_details_blank.png");
    private static final Identifier OTHER_BOARD = tex("textures/gui/alternate_quest_board.png");
    private static final Identifier EMPTY_FRAME = tex("textures/gui/quest_frame_empty.png");
    private static final Identifier PORTRAIT_THARION = tex("textures/gui/portrait_tharion.png");
    private final QuestCatalog catalog = QuestCatalog.load();
    private String category = "piraten_hauptgeschichte";
    private QuestCatalog.Quest selected;
    private boolean tracked = false;
    private QuestCatalog.Quest cachedForQuest;
    private long lastFtbCheckNanos = 0L;
    private FtbQuestBridge.Status cachedFtb = FtbQuestBridge.Status.absent("Demo");
    private float scale;
    private float x0, y0;

    public PirateQuestScreen() {
        super(Text.literal("La Familia – Piraten-Questbuch [DEMO]"));
        selected = catalog.find("kapitaen_tharion");
    }

    private static Identifier tex(String path) { return Identifier.of(MOD, path); }
    private void layout() {
        scale = Math.min((float)width / W, (float)height / H);
        x0 = (width - W*scale)*.5f;
        y0 = (height - H*scale)*.5f;
    }
    private void image(DrawContext dc, Identifier texture, int x, int y, int w, int h) {
        dc.drawTexture(texture,x,y,0f,0f,w,h,w,h);
    }
    private void box(DrawContext dc, int x, int y, int w, int h, int color) {
        dc.fill(x,y,x+w,y+h,color);
    }
    private void outline(DrawContext dc, QuestCatalog.Rect r, int color, int width) {
        box(dc,r.x()-width,r.y()-width,r.w()+width*2,width,color);
        box(dc,r.x()-width,r.y()+r.h(),r.w()+width*2,width,color);
        box(dc,r.x()-width,r.y(),width,r.h(),color);
        box(dc,r.x()+r.w(),r.y(),width,r.h(),color);
    }
    private void text(DrawContext dc, String content, int x, int y, int color, float factor) {
        dc.getMatrices().push();
        dc.getMatrices().translate(x,y,0);
        dc.getMatrices().scale(factor,factor,1);
        dc.drawText(client.textRenderer,content,0,0,color,false);
        dc.getMatrices().pop();
    }
    private int textWidth(String content) { return client.textRenderer.getWidth(content); }
    private int paragraph(DrawContext dc,String s,int x,int y,int maxWidth,int maxRows,int color,float factor,int lineHeight) {
        String[] words = s.trim().split("\\s+");
        StringBuilder line = new StringBuilder();
        int row=0;
        for (String word:words) {
            String next=line.isEmpty()?word:line+" "+word;
            if (textWidth(next)*factor>maxWidth && !line.isEmpty()) {
                text(dc,line.toString(),x,y+row*lineHeight,color,factor);
                if (++row>=maxRows)return y+row*lineHeight;
                line=new StringBuilder(word);
            } else line=new StringBuilder(next);
        }
        if (!line.isEmpty() && row<maxRows) text(dc,line.toString(),x,y+row*lineHeight,color,factor);
        return y+(row+1)*lineHeight;
    }
    private void wrapLabel(DrawContext dc,String s,int cx,int y,int maxWidth,int lines,float factor) {
        String[] words = s.split("\\s+");
        StringBuilder line=new StringBuilder();
        int row=0;
        for (String word:words) {
            String n=line.isEmpty()?word:line+" "+word;
            if(textWidth(n)*factor>maxWidth && !line.isEmpty()) {
                text(dc,line.toString(),cx-(int)(textWidth(line.toString())*factor/2),y+row*14,0xFFFFEDC4,factor);
                row++;line=new StringBuilder(word);
                if(row>=lines)return;
            } else line=new StringBuilder(n);
        }
        if(row<lines&&!line.isEmpty())text(dc,line.toString(),cx-(int)(textWidth(line.toString())*factor/2),y+row*14,0xFFFFEDC4,factor);
    }
    @Override
    public void render(DrawContext dc, int mouseX, int mouseY, float delta) {
        layout();
        // Deliberately do not call renderBackground(): our decorative approved art is the backdrop.
        dc.getMatrices().push();
        dc.getMatrices().translate(x0,y0,0);
        dc.getMatrices().scale(scale,scale,1);
        image(dc,BACKGROUND,0,0,W,H);
        double mx=(mouseX-x0)/scale, my=(mouseY-y0)/scale;
        for (QuestCatalog.Category c:catalog.categories) {
            if(c.id().equals(category)) {
                QuestCatalog.Rect r=c.rect();
                outline(dc,new QuestCatalog.Rect(r.x()+3,r.y()+4,r.w()-6,r.h()-8),0xE9F7C653,2);
            }
        }
        List<QuestCatalog.Quest> questList=catalog.forCategory(category);
        if (!category.equals("piraten_hauptgeschichte")) {
            image(dc,OTHER_BOARD,411,235,848,646);
            // In non-main categories replace only the header lettering, not the ornate frame.
            box(dc,697,82,366,55,0xFFDEB47C);
            String catTitle=catalog.categories.stream().filter(c->c.id().equals(category)).map(QuestCatalog.Category::label).findFirst().orElse("");
            text(dc,catTitle,717,96,0xFF442715,2.08f);
            text(dc,catTitle,455,276,0xFFFFD984,2.25f);
            text(dc,"Beispielaufgaben · Inhalt noch nicht mit FTB synchronisiert",460,328,0xFFD1C8B8,1.25f);
            for(int i=0;i<questList.size();i++) {
                QuestCatalog.Quest q=questList.get(i);QuestCatalog.Rect r=q.rect();
                if(i>0) {
                    QuestCatalog.Rect prev=questList.get(i-1).rect();
                    int lx1=prev.x()+prev.w()/2, ly1=prev.y()+prev.h()/2;
                    int lx2=r.x()+r.w()/2, ly2=r.y()+r.h()/2;
                    if(i==4) {
                        box(dc,lx1-3,ly1,6,Math.max(1,ly2-ly1),0xFFDDAB4C);
                    } else {
                        box(dc,Math.min(lx1,lx2),ly1,Math.max(2,Math.abs(lx2-lx1)),5,0xFFDDAB4C);
                    }
                }
            }
            for(QuestCatalog.Quest q:questList) {
                QuestCatalog.Rect r=q.rect();
                image(dc,EMPTY_FRAME,r.x(),r.y(),r.w(),r.h());
                image(dc,tex(q.icon()),r.x()+41,r.y()+14,62,64);
                wrapLabel(dc,q.title(),r.x()+r.w()/2,r.y()+104,110,2,1.15f);
            }
        }
        for(QuestCatalog.Quest q:questList) {
            QuestCatalog.Rect r=q.rect();
            boolean hover=r.contains(mx,my);
            if(q==selected||hover)outline(dc,new QuestCatalog.Rect(r.x()+4,r.y()+5,r.w()-8,r.h()-10),
                    q==selected?0xFFFFD659:0xAAF8C86C,q==selected?4:2);
        }
        renderDetails(dc);
        // Always visible; ensures no player mistakes sample UI for connected quest status.
        box(dc,1215,10,154,32,0xDB27191B);
        text(dc,"GUI-DEMO · ohne FTB",1223,20,0xFFFFCF82,1.16f);
        dc.getMatrices().pop();
    }
    private FtbQuestBridge.Status ftbStatus() {
        long now = System.nanoTime();
        if (selected != cachedForQuest || now - lastFtbCheckNanos > 1_000_000_000L) {
            cachedForQuest = selected;
            cachedFtb = FtbQuestBridge.read(selected == null ? "" : selected.ftbQuestId());
            lastFtbCheckNanos = now;
        }
        return cachedFtb;
    }
    private void renderDetails(DrawContext dc) {
        image(dc,DETAIL_BLANK,1282,168,367,678);
        if(selected==null)return;
        // The approved frame is retained. All text below comes from selected quest data.
        if (selected.category().equals("piraten_hauptgeschichte")) {
            image(dc,PORTRAIT_THARION,1306,202,111,132);
        } else {
            image(dc,EMPTY_FRAME,1306,204,112,125);
            image(dc,tex(selected.icon()),1328,222,67,73);
        }
        FtbQuestBridge.Status info = ftbStatus();
        String title = info.connected() && !info.title().isBlank() ? info.title() : selected.title();
        String description = info.connected() && !info.description().isBlank() ? info.description() : selected.description();
        List<String> tasks = info.connected() ? info.tasks() : selected.tasks();
        List<String> rewards = info.connected() ? info.rewards() : selected.rewards();
        text(dc,"Quest-Info",1421,212,0xFF57341C,1.55f);
        paragraph(dc,title,1421,250,187,2,0xFF3A2119,1.28f,21);
        paragraph(dc,description,1310,341,300,3,0xFF4C2C19,1.24f,20);
        text(dc,"Aufgaben",1312,414,0xFF4C2C19,1.75f);
        int y=454;
        for(int i=0;i<Math.min(3,tasks.size());i++) {
            box(dc,1314,y+2,17,17,0xFFB77A30);box(dc,1316,y+4,13,13,0xFFF4D99C);
            paragraph(dc,tasks.get(i),1339,y+1,267,2,0xFF4E321F,1.15f,16);
            y+=34;
        }
        text(dc,"Belohnungen",1312,543,0xFF4C2C19,1.75f);
        y=581;
        for(int i=0;i<Math.min(3,rewards.size());i++) {
            box(dc,1314,y+2,18,18,0xFFE7BD58);
            box(dc,1318,y+6,10,10,0xFF96602F);
            paragraph(dc,rewards.get(i),1341,y,267,2,0xFF4E321F,1.27f,17);
            y+=31;
        }
        text(dc,info.label(),1312,691,0xFF614029,.95f);
        box(dc,1311,711,304,64,tracked?0xFF2C5D42:0xFF315B36);
        outline(dc,new QuestCatalog.Rect(1311,711,304,64),0xFFDDBA6B,4);
        String buttonLabel = tracked ? "Markiert (nur Demo)" : "Quest merken (Demo)";
        text(dc,buttonLabel,1335,735,0xFFFBECD5,1.78f);
    }
    @Override
    public boolean mouseClicked(double mouseX,double mouseY,int button) {
        if(button!=0)return super.mouseClicked(mouseX,mouseY,button);
        layout();
        double mx=(mouseX-x0)/scale,my=(mouseY-y0)/scale;
        for(QuestCatalog.Category c:catalog.categories) {
            if(c.rect().contains(mx,my)) {
                category=c.id();
                selected=catalog.forCategory(category).stream().findFirst().orElse(null);
                tracked=false;return true;
            }
        }
        for(QuestCatalog.Quest q:catalog.forCategory(category)) {
            if(q.rect().contains(mx,my)) {
                selected=q;tracked=false;return true;
            }
        }
        if(mx>=1311&&mx<1615&&my>=711&&my<775) { tracked=!tracked;return true; }
        return super.mouseClicked(mouseX,mouseY,button);
    }
    @Override public boolean shouldPause() { return false; }
    @Override public boolean keyPressed(int keyCode,int scanCode,int modifiers) {
        if(keyCode==256) { close();return true; }
        return super.keyPressed(keyCode,scanCode,modifiers);
    }
}
