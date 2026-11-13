# La Familia – Piraten-Questbuch, Schritt 3 / GitHub-Buildvorbereitung (Fabric 1.21.1)

**Status: Quellcode einer frühen Client-GUI-Alpha, nicht im Spiel kompiliert oder getestet.**

Das Projekt verwendet die am 13. November 2026 bestätigte originale Piraten-Questbuch-Grafik (1671×941 Pixel), die neun **unveränderten Kategorien** und 162 vorbereitete Piraten-Icons. 52 Quest-Datensätze dienen **ausschließlich der GUI-Demonstration** (12 in der Hauptgeschichte, je fünf in den acht übrigen Kategorien). Sie sind **nicht** das vollständige 762-Quest-Projekt.

## Bereits im Quellcode umgesetzt

- Fabric 1.21.1 / Java 21 / Fabric Loader mindestens 0.19.5, clientseitiger Initializer.
- `P` öffnet und schließt das eigene Questbuch; `Esc` schließt es.
- Bildschirm skaliert die Grafik proportional auf die jeweils verfügbare Minecraft-GUI-Größe.
- Alle neun Kategorien besitzen anklickbare, exakt vermessene Flächen.
- Der mittlere Questbaum zeigt in der Hauptgeschichte die originalen Questfelder; die acht anderen Kategorien nutzen ein stilistisch ähnliches, dynamisches Questfeld mit Icons und Verbindungen.
- Das rechte Pergamentfeld zeigt das Bild, den Titel, die Beschreibung, Aufgaben und Belohnungen **abhängig von der ausgewählten Demo-Quest**.
- Der grüne Button markiert nur lokal eine Demo-Quest. Er löst **keine Serveraktion** aus und vergibt **keine Belohnung**.
- Quest-Daten und Grafiken liegen in Ressourcenordnern; Positionen sind in `gui_layout.json` dokumentiert.
- `preview/index.html` ist ein unabhängiger lokaler Anschauungstest mit anklickbaren Kategorien und Questfeldern.
- Eine GitHub-Actions-Konfiguration zur Erstellung einer Test-JAR ist enthalten.

## Noch NICHT enthalten

- Noch keine vollständig getestete FTB-Quests-Anbindung; die neue optionale Lesebrücke zeigt echte synchronisierte Daten nur nach Zuordnung konkreter FTB-IDs. Es werden keine Serverfortschritte geschrieben.
- Keine echte EasyNPC-/ExtraQuests-/MiguelEconomy-Anbindung.
- Keine vollständigen 762 Quests / 31 Kapitel, keine vollständigen Aufgaben, keine richtigen Belohnungen.
- Die in der ursprünglichen einzelnen Grafik eingebrannten Elemente sind nur zum Teil durch dynamische Elemente ersetzt. Eine vollständige pixelgenaue Freistellung eines „leeren“ Hafens ist noch offen.
- Die Mod-JAR wurde in dieser Ausführungsumgebung nicht als vollständiges Fabric-Projekt kompiliert (kein Gradle und kein externer Downloadzugriff für Build-Abhängigkeiten). Die GitHub-Actions-Build-Datei ermöglicht den **ersten echten Kompilierungsversuch**, ersetzt aber keinen erfolgreich bestandenen Build-Test.

## Bau als echte Fabric-JAR auf GitHub

1. Dateien und Unterordner dieses Projekts in **ein eigenes Repo für das Questbuch** auf GitHub hochladen (nicht in das Launcher-Repo mischen).
2. Unter `Actions` den Workflow `Build La Familia Piraten-Questbuch (Fabric 1.21.1)` starten.
3. Erst nach einem **grünen Build** die von `Actions` bereitgestellte Test-JAR als Artefakt herunterladen.
4. Test-JAR **nur auf einem Test-Client** mit Minecraft 1.21.1, Java 21, Fabric Loader >=0.19.5 und passender Fabric API ausprobieren; zuvor die laufende Welt sichern.
5. `P` öffnen, die neun Kategorien anklicken, Demo-Questfelder anklicken, die rechte Quest-Info prüfen und `Esc` schließen.
6. Buildfehler gegebenenfalls anhand der Actions-Logs korrigieren; noch kein automatisches Launcher-Update aktivieren.

Alternative lokal mit installierter Gradle-Version 8.10.2 und Internetzugriff: `gradle clean build`.

## Dateiorganisation

- `src/main/java/de/lafamilia/questbook/client/` – Client-Initializer, GUI, Datenmodell.
- `src/main/resources/assets/lafamilia_questbook/demo_quests.json` – Beispieldaten für GUI-Test.
- `src/main/resources/assets/lafamilia_questbook/textures/gui/` – Originalbild und ergänzende GUI-Grafiken.
- `src/main/resources/assets/lafamilia_questbook/textures/icons/` – 162 Questicons.
- `gui_layout.json` – Pixelpositionen der Kategorien, zentralen Questkarten und rechten Info-Box.
- `preview/index.html` – Browser-Prototyp (keine Minecraft-JAR).
- `preview/VORSCHAU_*.png` – statische Vorschau der Hauptgeschichte und der Landwirtschaft.
- `tests/validate_assets.py` – statische Strukturprüfung.

## Nach der ersten grünen GitHub-Test-JAR

Die vorhandene optionale Read-only-Brücke mit tatsächlichen Quest-IDs unter `FTB Quests 2101.1.37` im Spiel testen und danach die stabile direkte Integration ausbauen. **FTB Quests bleibt einzige Quelle für echte Quests, Fortschritt und Belohnungen.** Erst danach Schritt für Schritt die gesamte Hauptgeschichte, die acht übrigen Kategorien sowie EasyNPC- und ExtraQuests-Funktionen verbinden.

## Änderung Alpha 0.3.0 – FTB-Lesebrücke und verbesserter CI-Test

- Der Quellcode hat jetzt `FtbQuestBridge.java`: optionale, ausschließlich lesende Anbindung an die
  tatsächlichen FTB-Quests-Clientdaten. Sie verwendet die auf dem offiziellen
  `1.21.1/main`-Branch belegten Klassen `ClientQuestFile`, `TeamData`, `QuestObject`
  und `Task`; per Reflection, um eine zusätzliche direkte Mod-Build-Abhängigkeit zu vermeiden.
- Im rechten Questinfo-Fenster erscheint nur bei **exakt zugeordneter** echter FTB-Quest-ID
  der tatsächliche Abschlusstatus beziehungsweise Aufgabenfortschritt.
- In der JSON-Datei besitzt jedes der 52 **Demo-Kärtchen** das leere Feld `ftb_quest_id`.
  Die echte ID muss aus `config/ftbquests/quests/chapters/*.snbt` übernommen werden;
  bitte nicht aus einem Questnamen erraten. Die Zuordnungstabelle liegt als
  `FTB_QUEST_ID_ZUORDNUNG.csv` vor. Solange die IDs fehlen, zeigt die GUI korrekt Demo an.
- Änderungen an Fortschritt, Belohnungen und NPCs werden **nicht** durch diese GUI ausgeführt.
- GitHub Actions testet erst Assets, dann Gradle-Compile und prüft explizit die remappte JAR;
  beim Fehler wird kein vermeintlich gültiger Build hochgeladen.

**Buildstatus 13.11.2026:** Statische Tests lokal bestanden. Kein erfolgreicher Gradle-/Minecraft-Test
in dieser Umgebung: der Gradle-Download ist hier nicht erreichbar, und es gibt noch kein
Questbuch-Repository in den verbundenen GitHub-Konten. Keine JAR vorhanden, keine
GitHub-Veröffentlichung erfolgt. Die neue Brücke ist deshalb weiterhin **unverifiziert im Spiel**.

### GitHub hochladen

Für die erste eigene Questbuch-Mod ein separates GitHub-Repository anlegen, z. B.
`LaFamilia-Piraten-Questbuch`. Das Projektverzeichnis **inhaltlich** in die Repo-Wurzel
hochladen. GitHub Actions sollte bei Push auf `main` automatisch starten. Anschließend
im Actions-Run unter `Artifacts` nach `LaFamilia-PiratenQuestbuch-Fabric1211-Alpha03`
suchen. Den ersten Build nicht als automatisches Launcher-Update ausrollen, bevor
JAR-Start und GUI im Test-Client geprüft sind.

### FTB-Quest-ID-Zuordnung

Die ersten tatsächlichen FTB-Quest-ID-Paare per SNBT ergänzen, bevor echte Fortschritte
erscheinen: `ftb_quest_id` für die passende Karte in
`src/main/resources/assets/lafamilia_questbook/demo_quests.json` setzen.
Die Ids sind 1–16 Hexzeichen (`2B3C4D5E6F7A8B9C`). Nicht nummerische GUI-IDs
wie `kapitaen_tharion` sind **keine** FTB-IDs. Die Adapterklasse liest nur den
Status der lokal synchronisierten Teamdaten.

Hinweis: Texte aus echten FTB-Quests werden in dieser Alpha im rechten Feld auf die sichtbaren Zeilen begrenzt; Scrollen und Abgabe-Buttons folgen erst nach dem getesteten Build.
