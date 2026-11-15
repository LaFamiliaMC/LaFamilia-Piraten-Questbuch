# La Familia – Kapitän „Ein neuer Seefahrer“ (Quest 01)

**NPC UUID:** `8c658830-313e-4f63-8dc2-f7ff4a51b669`  
**FTB Quest ID:** `FA10010300010000`  
**Rangstage:** `lafamilia_rank_01_landratte`  
**Dialog-ID:** `addaae3c-bd31-37a4-8e70-188d7680f404`  
**Status:** UUID und Dialog-ID fest zugeordnet. Automatische Questaufgabe noch im FTB-Quests-Editor einrichten und testen.

## Dialog

**Kapitän:** Ahoy, Fremder! Willkommen im Hafen von La Familia. Ich bin der Kapitän dieser Mannschaft. Jeder große Pirat hat einmal klein angefangen – und du siehst aus, als könntest du hier deinen Platz finden.

**Spieler:** Ich möchte mich eurer Mannschaft anschließen!

**Kapitän:** Bei uns zählen Mut, Zusammenhalt und ein ehrliches Wort. Lerne unseren Hafen kennen und beweise, dass du zur Mannschaft gehörst. Ich ernenne dich zur **Landratte** – deinem ersten Piratenrang.

**Spieler:** Aye, Kapitän! Wo soll ich anfangen?

**Kapitän:** Geh zum Hafenmeister! Er führt unser Hafenbuch und zeigt dir den Weg. Und vergiss nie: Eine gute Mannschaft lässt niemanden zurück.

**Spieler:** Ich mache mich auf den Weg!

**Optionaler Folgedialog nach Abschluss:** Ahoy, Landratte! Deine Reise beginnt beim Hafenmeister.

## Technische Zuordnung

Die bisher ausgelieferte Testkapiteldatei verwendet noch die Checkmark-Aufgabe (`FA10010400010001`). **Die korrekte Dialog-ID lautet `addaae3c-bd31-37a4-8e70-188d7680f404`, NICHT `dialog_831`.** Im FTB-Quests-Editor bei Quest `FA10010300010000` die Checkmark-Aufgabe durch den Task **EasyNPC Task** ersetzen, als Dialog-ID `addaae3c-bd31-37a4-8e70-188d7680f404` hinterlegen und, sofern das Feld angeboten wird, die Kapitäns-NPC-UUID `8c658830-313e-4f63-8dc2-f7ff4a51b669` setzen. Die Belohnung `gamestage` auf `lafamilia_rank_01_landratte` belassen. Anschließend in einer Testwelt mit nicht abgeschlossenem Questfortschritt prüfen.

Für die Stage-Belohnung in FTB Quests ist **`type: "gamestage"`** zu verwenden, dazu `stage: "lafamilia_rank_01_landratte"` und `auto: "invisible"`. Der Rang ist ein internes Fortschrittsflag und kein sichtbares Namenspräfix. Shop-NPCs bleiben unberührt.

**Testhinweis:** Keine automatische Gesprächserkennung behaupten, solange die ID fehlt; kein globales Zurücksetzen. Erst im Testserver testen, besonders bei Teamfortschritt.
