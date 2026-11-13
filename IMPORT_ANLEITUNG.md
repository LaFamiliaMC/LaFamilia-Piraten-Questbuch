# La Familia – Piraten-Questbuch (Fabric 1.21.1)

Dieses Repository ist für die **eigene Piraten-Questbuch-GUI** bestimmt, unabhängig vom Launcher und Modpack.

## Aktueller Stand
- Originalvorlage: festgelegtes La-Familia-Piraten-Questbuch
- 9 unveränderte Quest-Kategorien, 162 vorhandene PNG-Icons
- Alpha-0.3.0-Quellcode: 52 **Demo**-Questkarten; keine fertigen 762 echten Quests
- Optionale, nur lesende FTB-Quests-Brücke (noch nicht im Spiel getestet)
- Keine automatische Installation an Spieler, solange der Build/Spieltest aussteht

## Einmaliger Import der Alpha 0.3.0
1. Lade das Projekt-ZIP aus dem Chat herunter:
   `LaFamilia_PiratenQuestbuch_Fabric1211_GitHubTest_V03_Quellcode.zip`
2. Im GitHub-Repository **Add file → Upload files** öffnen.
3. **Das ZIP selbst** (ungeöffnet, nicht entpacken) in das Repository hochladen und mit **Commit changes** bestätigen.
4. Der Workflow **Import + Build La Familia Piraten-Questbuch (Fabric 1.21.1)** startet automatisch.
5. Der Workflow entpackt das ZIP, prüft Grafikdateien und Beispieldaten, übernimmt nach Möglichkeit die Quelldateien in `main`, kompiliert mit Java 21/Gradle und veröffentlicht erst bei Erfolg die JAR unter **Actions → Artifacts**.

Wenn das automatische Übernehmen der Quelldateien wegen GitHub-Berechtigungen fehlschlägt, kann das erste Kompilieren trotzdem stattfinden. Der Workflow meldet dies im Log.

## Testhinweis
Diese frühe Alpha darf **noch nicht als automatisches Launcher-Update** an Spieler verteilt werden. Zuerst muss der Build fehlerfrei durchlaufen und die GUI in einem separaten Minecraft-1.21.1-Fabric-Testclient geprüft werden.

Das Questbuch ist **keine zweite Quest-Engine**: FTB Quests soll Fortschritt und Belohnungen verwalten; unsere Client-Mod die besondere Oberfläche. EasyNPC und ExtraQuests werden anschließend angebunden.
