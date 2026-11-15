# La Familia – Hafenherbergsvater: Quest 11 „Ein Bett für die Nacht“

**NPC:** Hafenherbergsvater (eigener Quest-NPC ohne Shopfunktion)  
**NPC-UUID:** `a20fafc8-1146-4855-a55d-be29bad42ef1`  
**Quest-ID:** `LF-L02-Q05` / FTB `FA10010300110000`  
**Questtyp:** Optionale Nebenquest  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“  
**Dialogname:** `q11_hafenherbergsvater`  
**Dialog-ID:** Noch aus EasyNPC zu übermitteln  
**FTB-Status:** Noch keine aktive automatische FTB-Questaufgabe im Testbuild.

## Einziger Dialog

**Hafenherbergsvater:**

Ahoy, Landratte! Du siehst aus, als hättest du schon drei Stürme hinter dir!

Ich bin der Hafenherbergsvater von La Familia und sorge dafür, dass unsere Seeleute nach einer langen Reise ein warmes, trockenes Plätzchen zum Ausruhen finden.

Denn merk dir eines: Selbst der tapferste Pirat braucht irgendwann ein ordentliches Bett! Wer die ganze Nacht auf harten Planken schläft, taugt am Morgen kaum zum Segelsetzen.

Wenn du dir im Hafen ein eigenes Zuhause einrichtest, schau bei unserem Händler für **Betten und Schränke** vorbei. Dort findest du die passende Einrichtung, damit dein Quartier nicht wie ein leerer Laderaum aussieht!

Ein Bett gehört in jede vernünftige Unterkunft, und ein guter Schrank sorgt dafür, dass deine Habseligkeiten nicht kreuz und quer herumliegen.

Nun weißt du, wo unsere Seeleute ihre Ruhe finden und wo du deine eigene Unterkunft gemütlich ausstatten kannst.

Ruh dich gut aus, Landratte – denn das nächste Abenteuer wartet bestimmt schon!

**Spieler – einzige Antwort:**

Aye, Herbergsvater! Ich werde mir merken, wo ich Betten und Schränke finde!

**EasyNPC-Aktion:** `Close Dialog`

**Quest 11:** Soll später beim **Öffnen** dieses einen Dialogs einmalig abgeschlossen werden, nicht erst durch Klicken auf den Antwortknopf.

## Einrichtung

1. Den Hafenherbergsvater mit NPC-UUID `a20fafc8-1146-4855-a55d-be29bad42ef1` in EasyNPC bearbeiten.
2. Genau **einen Standarddialog** mit dem Namen `q11_hafenherbergsvater` anlegen.
3. Obigen Text eingeben und einen Antwortknopf mit Aktion `Close Dialog` hinzufügen.
4. EasyNPC-Dialog-ID kopieren und für Quest 11 übermitteln.
5. Die FTB-Questaufgabe erst dann an den Dialog binden, wenn alle NPCs mit IDs erfasst sind. Quest 07 bleibt die Voraussetzung; eine etwaige Belohnung erfolgt einmalig über FTB Quests.

**Wichtig:** Der bestehende Händler für **Betten & Schränke** mit UUID `b0c6014c-28f4-46f8-840c-cd6fa958d7d9` bleibt ein reiner Shop. Für Quest 11 muss der Spieler weder ein Bett kaufen noch schlafen oder Gegenstände abgeben. Kein Scoreboard und keine technischen Shopkontrollen.
