# La Familia – Kapitän „Ein neuer Seefahrer“ (Quest 01)

**NPC UUID:** `8c658830-313e-4f63-8dc2-f7ff4a51b669`  
**FTB Quest ID:** `FA10010300010000`  
**Rangstage:** `lafamilia_rank_01_landratte`  
**Status:** Die UUID ist fest zugeordnet, die EasyNPC-Dialog-ID fehlt noch.

## Dialog

**Kapitän:** Ahoy, Fremder! Willkommen im Hafen von La Familia. Ich bin der Kapitän dieser Mannschaft. Jeder große Pirat hat einmal klein angefangen – und du siehst aus, als könntest du hier deinen Platz finden.

**Spieler:** Ich möchte mich eurer Mannschaft anschließen!

**Kapitän:** Bei uns zählen Mut, Zusammenhalt und ein ehrliches Wort. Lerne unseren Hafen kennen und beweise, dass du zur Mannschaft gehörst. Ich ernenne dich zur **Landratte** – deinem ersten Piratenrang.

**Spieler:** Aye, Kapitän! Wo soll ich anfangen?

**Kapitän:** Geh zum Hafenmeister! Er führt unser Hafenbuch und zeigt dir den Weg. Und vergiss nie: Eine gute Mannschaft lässt niemanden zurück.

**Spieler:** Ich mache mich auf den Weg!

**Optionaler Folgedialog nach Abschluss:** Ahoy, Landratte! Deine Reise beginnt beim Hafenmeister.

## Technische Zuordnung

Die aktuelle Testkapiteldatei enthält noch eine Checkmark-Aufgabe (`FA10010400010001`). Die echte EasyNPC-Dialog-ID wird im Editor über **Erweiterte Dialogkonfiguration → Q → Copy Dialog ID** kopiert. Wenn diese ID bekannt ist, ersetzen wir den Checkmark-Task durch die EasyNPC-Dialog-Aufgabe mit dem bekannten Kapitäns-UUID-Filter; Quest-ID und Belohnungs-IDs bleiben unverändert.

Für die Stage-Belohnung in FTB Quests ist **`type: "gamestage"`** zu verwenden, dazu `stage: "lafamilia_rank_01_landratte"` und `auto: "invisible"`. Der Rang ist ein internes Fortschrittsflag und kein sichtbares Namenspräfix. Shop-NPCs bleiben unberührt.

**Testhinweis:** Keine automatische Gesprächserkennung behaupten, solange die ID fehlt; kein globales Zurücksetzen. Erst im Testserver testen, besonders bei Teamfortschritt.
