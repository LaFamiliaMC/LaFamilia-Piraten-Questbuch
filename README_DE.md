# La Familia Piraten-Questbuch – Alpha 0.4.0 (Fabric 1.21.1)

## Tatsächlich geändert
- **Neue leere Grundgrafik** statt eines Bildes mit fest eingezeichneten Quests oder Texten. PNG: `pirate_empty_background.png`.
- **Alle neun Kategorien** sind grafische Buttons mit dynamischen Texten/Icons.
- Alle Questfelder, Icons, Verbindungslinien, Überschriften, Beschreibungen und Belohnungen werden **getrennt gezeichnet**, nicht über die alte GUI überklebt.
- Rechts ist **nur ein einziges leeres Pergament** in der Grundgrafik. Die Auswahl wird darauf dynamisch gerendert.
- **Die zusätzliche Taste P wurde vollständig entfernt.** Die GUI ersetzt die FTB-Quests-Öffnung mittels clientseitigem Mixin `ClientQuestFile.openQuestGui()` bei FTB Quests 1.21.1.
- Für Administration/Notfälle: **Shift gedrückt halten**, während man das FTB-Questbuch öffnet, oder in unserem Bildschirm rechts unten **FTB-Questbuch öffnen** anklicken.
- Das Questmodell bleibt serverseitig bei FTB Quests; die 52 Questkarten sind weiterhin **Demo-Daten**, solange keine 16-stelligen FTB-Quest-IDs zugeordnet wurden. Nichts wird beim Anklicken verliehen oder serverseitig verändert.

## GitHub / Test
Der Mod benötigt Minecraft 1.21.1, Java 21, Fabric Loader, Fabric API und **FTB Quests** (mit dessen üblichen Abhängigkeiten).
Installiere nur **eine** Version der La-Familia-Questbuch-JAR im Client. Die Alpha 0.4 ist noch nicht im Spiel getestet.
Der bereitgestellte GitHub-Workflow baut die ZIP automatisch, sofern das Archiv in das Repository hochgeladen wird.

## Hinweise
Die ältere `approved_reference_1671x941.png`, `alternate_quest_board.png` und die vorgerenderten Kartenausschnitte sind **bewusst nicht mehr in der neuen Mod enthalten**.
Die 762 Quests / 31 Kapitel sind das weitere Inhaltsziel – sie sind **noch nicht** in dieser Demo-JAR implementiert.
