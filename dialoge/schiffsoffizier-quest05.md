# La Familia – Schiffsoffizier: Ein Blick auf das große Schiff (Quest 05)

**NPC:** Schiffsoffizier  
**NPC-UUID:** `f5e8ef08-d548-47c2-88c0-d473843b9c65`  
**Quest:** `LF-L01-Q05` / `FA10010300050000`  
**Questart:** optionale Nebenquest, verfügbar nach „Ein neuer Seefahrer“ (Quest 01).  
**Dialog B Abschluss-ID:** `fc757df3-d218-3b94-892e-29f55513e646` (vom Nutzer angegeben).  
**Geplante einmalige Belohnung:** 25 XP, 1 Kompass (noch nicht im Server-Testbuild aktiviert).  
**Technik:** Dialog B ist zugeordnet; EasyNPC-Aufgabe und Dialog-Automatik werden erst nach Platzierung aller NPCs aktiviert und danach einzeln getestet.

## Dialog A – Vor der Aufnahme beim Kapitän

**Schiffsoffizier:** „Halt dort! Das Schiff der La Familia ist kein Ausflugsboot. Wer an Deck will, meldet sich zuerst beim Kapitän.“

**Spieler:** „Ich wollte mir das Schiff nur ansehen.“

**Schiffsoffizier:** „Bewundern darfst du es natürlich. Doch ein Platz in der Mannschaft muss verdient werden. Such den Kapitän im Hafen auf!“

**Spieler:** „Aye! Ich komme wieder.“

**Ende:** Kein Questabschluss, keine Belohnung.

## Dialog B – Erster Besuch als Landratte (Quest 05)

**Schiffsoffizier:** „Ahoy, Landratte! Du musst der Neue sein. Willkommen an Bord! Ich bin der Schiffsoffizier und halte hier die Mannschaft auf Kurs.“

**Spieler (Antwort 1):** „Was ist das für ein Schiff?“

**Schiffsoffizier:** „Dies ist das stolze Schiff von La Familia. Es trägt unsere Flagge und ist das Herz unserer Seefahrergeschichten. Schau dir Deck, Masten, Segel und die Arbeit der Mannschaft gut an.“

**Spieler (Antwort 2):** „Was macht ein guter Seefahrer an Bord?“

**Schiffsoffizier:** „Er kennt seine Aufgaben, hält seine Ausrüstung in Ordnung und passt auf seine Kameraden auf. Eine Reise gelingt nur, wenn jeder mit anpackt.“

**Spieler (Antwort 3):** „Darf ich später auf große Fahrt gehen?“

**Schiffsoffizier:** „Mit Fleiß und Erfahrung, ganz gewiss! Zuerst aber musst du unseren Hafen kennenlernen, Vorräte sichern und beweisen, dass du Verantwortung tragen kannst.“

**Spieler (Abschlussantwort):** „Aye, Offizier! Ich werde mir alles merken!“

**Schiffsoffizier:** „Sehr gut, Landratte! Nimm diesen Kompass. Ein Pirat sollte immer wissen, wohin sein Kurs führt. Sieh dich weiter im Hafen um – wir sehen uns auf Deck!“

**Questabschluss:** Erst die Abschlussantwort soll später den EasyNPC-Questtask für `FA10010300050000` auslösen. Keine Ränge und keine wiederholbaren Belohnungen durch diesen Dialog.

## Dialog C – Nach Abschluss von Quest 05

**Schiffsoffizier:** „Ahoy, Landratte! Na, hast du dich mit unserem Schiff schon vertraut gemacht?“

**Spieler (Antwort 1):** „Was brauche ich für eine Reise?“

**Schiffsoffizier:** „Proviant, Werkzeug, einen klaren Kurs – und Kameraden, auf die du dich verlassen kannst. Unsere Händler am Kai helfen dir bei den Vorräten.“

**Spieler (Antwort 2):** „Wer hält hier alles in Ordnung?“

**Schiffsoffizier:** „Jeder an Bord trägt seinen Teil dazu bei. Von den Wachen bis zu den Handwerkern – ohne Teamarbeit bleibt jedes Schiff im Hafen liegen.“

**Spieler:** „Danke, Offizier!“

**Schiffsoffizier:** „Gute Winde und sichere Fahrt!“

**Ende:** Kein erneuter Questabschluss, keine Belohnungswiederholung.

## Spätere Einrichtung

1. NPC mit UUID `f5e8ef08-d548-47c2-88c0-d473843b9c65` vorhanden.
2. Dialog A, B und C in EasyNPC anlegen, abhängig von Fortschritt in Quest 01/05.
3. **Abschluss-Dialog-ID von Dialog B ist bereits gespeichert:** `fc757df3-d218-3b94-892e-29f55513e646`. Andere Dialoge A/C dürfen keinen Questabschluss auslösen.
4. Erst am Ende aller NPC-Platzierungen den FTB-Questtask von Checkmark auf EasyNPC-Aufgabe umstellen.
5. Quest einzeln testen; bestehende Händler-Shops und andere NPCs unverändert lassen.
