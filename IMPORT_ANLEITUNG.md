# La Familia Piraten-Questbuch – Alpha 0.4.0 (Fabric 1.21.1)

Das bisherige, komplett beschriftete GUI-Bild wird **nicht mehr** als Hintergrund verwendet.
Die neue Alpha enthält eine eigene **leere Piraten-Grundgrafik**. Menübeschriftungen, Quest-Icons, Verbindungen und Questinformationen werden separat gerendert.

## Einmaliger Import der neuen Alpha

1. Lade `LaFamilia_PiratenQuestbuch_Fabric1211_Alpha04_LeereGUI_FTB.zip` aus dem Chat herunter.
2. Öffne in GitHub **Add file → Upload files** auf der Hauptbranch `main`.
3. Lade **das vollständige ZIP unverändert** in das Repository hoch und bestätige mit **Commit changes**.
4. Der Workflow **Import + Build La Familia Piraten-Questbuch (Fabric 1.21.1)** verarbeitet das ZIP, entfernt die alten Grafik-Ressourcen, übernimmt den Quellcode und baut mit Java 21.
5. Prüfe **Actions**. Nur wenn der Build erfolgreich ist, erscheint die Datei `lafamilia-piratenquestbuch-0.4.0-alpha.1.jar` im Artifact **LaFamilia-PiratenQuestbuch-Fabric1211-Alpha04**.

## Was 0.4.0 technisch ändern soll

- **Kein extra P-Hotkey**: Die Mod klinkt sich in die normale FTB-Quests-Öffnung ein.
- Rechts nur ein **leeres Pergament**, darauf dynamische Infos zur gewählten Quest.
- In der Mitte **dynamische** Rahmen, Icons, Namen, leuchtende Verbindungen; kein altes Questbaum-Bild.
- Neun ursprünglich bestätigte Kategorien unverändert.
- Für die echte FTB-Questverwaltung bleibt FTB Quests verantwortlich. Halte **Shift** beim Öffnen gedrückt, oder wähle rechts **FTB-Questbuch öffnen**, um die native Ansicht für Admin-Aufgaben zu verwenden.

## Test- und Sicherheitsstand

Dies ist **noch kein im Minecraft-Spiel verifizierter Build**. Die modifizierte FTB-Öffnungsroutine (Mixin) muss auf dem Fabric-1.21.1-Client mit den installierten FTB-Quests-Mods getestet werden.
Die 52 enthaltenen Questkarten sind Demo-Daten: 0 reale FTB-Quest-IDs sind gemappt, keine 762 Quests sind bisher implementiert.
Bitte vorerst **nicht als automatisches Launcher-Update an Spieler verteilen**.
