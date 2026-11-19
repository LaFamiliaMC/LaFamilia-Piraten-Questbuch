# La Familia – Willkommen in La Familia (16 Quests)

Minecraft Fabric 1.21.1 / FTB Quests.

Hier liegen **die neue FTB-Kapiteldatei mit 16 Quests** und eine Zuordnung aller EasyNPC-NPC- und Dialog-UUIDs. Die Questreihe gehört zur bestehenden Kapitelgruppe `Piraten-Hauptgeschichte` (Gruppe `0F230595FEF55D6F`).

Die tatsächliche Serverdatei liegt nach dem Import unter `config/ftbquests/quests/chapters/lafamilia_willkommen_16.snbt`. Die vorhandenen Sprachdateien `de_de.snbt` und `en_us.snbt` müssen **um die Questtexte erweitert werden**, sonst fehlen Titel/Beschreibungen.

**Wichtig:** Dieses Repository enthält die neue Kapitelquelle und ID-Zuordnung. Das vollständige **zusammengeführte ZIP** aus der aktuell vom Nutzer bereitgestellten `quests.zip` wurde separat im Chat erzeugt. Es lässt alle Originalkapitel unangetastet und erweitert nur die Lokalisierungsdateien. Dieses GitHub-Verzeichnis ersetzt **nicht** das vollständige ZIP.

**Test-Stand:** Aufgaben in der neuen Kapiteldatei sind `checkmark`-Platzhalter. Erst im FTB-Editor durch `EasyNPC Task` ersetzen und die Dialog-ID der CSV zuordnen. Erst danach ist ein NPC-Gespräch die echte Abschlussbedingung. Einmalige Item-/XP-Belohnungen sind angelegt; die Rang-Stages `Landratte` und `Deckjunge` werden erst nach Prüfung der serverseitigen Stage-Unterstützung integriert. Der Kapitäns-Teleport ist weiterhin **nicht konfiguriert**, da Weltname und sichere Zielkoordinaten noch nicht vorliegen. Nicht ohne Test auf Live-Server übernehmen.

## Reihenfolge und Layout – Korrektur 19.11.2026

**Ab sofort alle 16 Quests zwingend nacheinander:** `01 → 02 → 03 → ... → 16`. Es gibt keine Nebenquest-Abzweigungen mehr. Jede Quest verlangt die **vorige** Quest als einzige Voraussetzung, besitzt `progression_mode: "linear"`, `hide_until_deps_complete: 1b` und `hide_details_until_startable: 1b`. Die Anordnung ist eine übersichtliche 4×4-Schlangenlinie (oben nach rechts, in der nächsten Zeile zurück nach links). Außerdem wurden die angezeigten Vanilla-Icons an den jeweiligen NPC angepasst.

**Installation:** Die empfohlene Korrektur ist ein separates kleines Patch-ZIP aus diesem Chat: nur `quests/chapters/lafamilia_willkommen_16.snbt` ersetzen, nachdem der Server gestoppt und `config/ftbquests/quests` gesichert wurde. Das alternative Komplettpaket ergänzt auch die Sprachdatei-Texte, kann aber seitdem geänderte Übersetzungen überschreiben. Quest-IDs und Reward-IDs bleiben bestehen; bereits gespeicherte Fortschritte werden nicht automatisch gelöscht. Ohne OP-/FTB-Editorrechte mit frischem Testspieler prüfen.

**Noch offen:** Der Aufgabentyp `checkmark` bleibt ein manuell anklickbarer TEST-Platzhalter. Für tatsächlichen Abschluss durch NPC-Gespräch diese Aufgaben im FTB-Editor durch `EasyNPC Task` der installierten Compat-Mod ersetzen und die Dialog-IDs aus der CSV eintragen. Der Teleport und die Rang-Stage-Rewards sind noch nicht aktiv.
