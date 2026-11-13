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


## Alpha 0.5.0 – FTB Quests im Piraten-Questbuch bearbeiten

**Teststatus:** Die Java-Kompilierung wurde geprüft. Die Bedienung mit deinem tatsächlichen FTB-Quests-Modpack und einem Multiplayer-Server muss im Spiel verifiziert werden.

### Bearbeitungsmodus
1. Minecraft Fabric 1.21.1 mit **FTB Quests** und **FTB Teams** starten.
2. Der Spieler braucht die **FTB-Quests-Editorberechtigung** *und* muss in FTB Quests den Bearbeitungsmodus eingeschaltet haben. Anderenfalls wird **FTB Bearbeiten** nicht eingeblendet.
3. FTB-Questbuch wie gewohnt öffnen (keine gesonderte Taste P). Wenn nötig, zuerst unten **FTB-Questbuch öffnen** und dort den normalen Bearbeitungsmodus aktivieren.
4. Auf **FTB Bearbeiten** oben im Piraten-Questbuch klicken.
5. Mit **< / >** zwischen echten FTB-Kapiteln wechseln. Die **neun linken Quest-Kategorien** bleiben unverändert.
6. Questkarten anklicken oder bei gedrückter linker Maustaste **verschieben**. Nach dem Loslassen wird der FTB-Server um die neue Position gebeten.
7. **+ Quest** erstellt im gewählten Kapitel eine echte Quest mit einer vorläufigen **Checkmark-Aufgabe**. Diese Aufgabe danach im normalen FTB-Editor konfigurieren oder ersetzen.
8. **Titel ändern**, **Verbinden** (Quelle auswählen, danach Folgequest anklicken) oder **Speichern** verwenden.
9. **FTB-Editor öffnen** öffnet den normalen Editor für Aufgaben, NPC-Bezüge, Belohnungen, Eigenschaften und weitere Funktionen, die in unserer eigenen Oberfläche noch nicht umgesetzt sind.

### Wichtig: Was noch fehlt
- Der **Spielermodus zeigt weiterhin Demo-Questkarten**, solange die 52 Demo-Quests nicht mit echten FTB-Quest-IDs und Kapiteln verbunden sind. Der Editor zeigt dagegen die **echten FTB-Kapitel** an.
- Die Kategorien sind noch **nicht serverweit mit FTB-Chapter-Gruppen verknüpft**; die Pfeile dienen der Kapitelauswahl.
- Die Anzeige eigener Quest-Icons, der endgültige Rahmen- und Schriftstil und die vollständige Aufgaben-/Belohnungsbearbeitung in unserem Pergament sind noch offen.
- Serverrechte sind maßgeblich. Der Client darf allein keine Quests ändern, abschließen oder Belohnungen ausgeben.
- Diese Alpha darf vor dem Spieltest nicht ungeprüft als automatisches Launcher-Update an alle Spieler verteilt werden.
