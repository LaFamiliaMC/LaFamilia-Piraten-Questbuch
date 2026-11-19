# AKTUELLER SERVERSTAND – WICHTIG (19.11.2026)

Die ursprüngliche GitHub-Quelldatei `ftb-import/lafamilia_willkommen_16.snbt` stammt noch aus der VORLAGE und enthält **alte FA... Quest-IDs**. **Nicht mehr direkt auf den Server kopieren!**

Der Nutzer hat seine tatsächlich laufende Datei `quests(1).zip` bereitgestellt. Die aktuelle **Kapitel-ID `62DAD70B515151F8`**, die 16 tatsächlichen Quest-/Task-IDs und alle unveränderten EasyNPC-/Dialog-UUIDs stehen nun unter [Server-ID-Abgleich](server_id_abgleich_2026-11-19.md). Die geprüfte, bereinigte **vollständige** `quests/`-ZIP wurde im zugehörigen Chat erstellt und ist die gültige Importfassung. Die dortigen deutschen Sprachdateien enthalten für alle 277 aktiven Quests Titel und Beschreibungen.

In der laufenden ZIP fehlten 15 direkte Abhängigkeiten, die Textschlüssel zeigten auf alte Quest-IDs und zwei Kapitel waren leer. Außerdem lag eine alte `chapter_groups.snbt` fälschlich im Kapitelordner. Diese Fehler sind in der Reparatur-ZIP behoben; **kein Nachweis eines erfolgreichen Serverstarts**. Die komplette Questkonfiguration muss ersetzt statt bloß überkopiert werden, damit die Altdateien verschwinden. Vorher eine Sicherung anlegen.

---

# La Familia – 16 NPC-Quests, neuer Import (19.11.2026)

Aktueller Stand für Fabric 1.21.1: **16 Quests in einem einzelnen FTB-Kapitel** mit einer einzigen linearen Hauptfolge (`01 → 02 → ... → 16`), einem 4×4-Schlangenlayout und exakt 16 EasyNPC-Dialogaufgaben. Keine Checkmark-Testaufgaben mehr. Die Quest- und Reward-IDs entsprechen dem bisherigen Einführungsimport (keine Duplikate anlegen). Die Quelle ist `ftb-import/lafamilia_willkommen_16.snbt`.

**NPC-Aufgaben:** `type: "easynpc_ftbq_compat:easy_npc_task"` plus `dialogId` und `npcUuid`. Die Zuordnungen stammen aus `planung/quest-layout-willkommen-v1.json`. Diese SNBT-Task-Kennung ist anhand der Mod-Dokumentation und Ressourcen abgeleitet, aber noch **nicht auf dem tatsächlichen Server** mit der dort installierten Kompatibilitätsmod getestet. Ein Live-Server-Test mit Quest 01 ist erforderlich.

**WICHTIG: QUESTTEXTE** liegen bei FTB Quests 1.21.1 in `quests/lang/de_de.snbt` beziehungsweise `en_us.snbt`, **nicht nur** in der Questdatei. Eine reine Kapiteldatei von GitHub reicht daher nicht, um Texte anzuzeigen. Das vollständige Installationspaket wurde im Chat als `LaFamilia_16_NPC_Quests_NEUSTART.zip` erstellt und enthält 66 neue deutsche Übersetzungsschlüssel, die englische Fallback-Datei sowie ein PowerShell-Skript, das die aktuellen Sprachdateien zusammenführt und vorher sichert. Verwende dafür das ZIP statt nur die Kapiteldatei.

**Rang und Reise:** Die je Quest festgelegten Item- und XP-Belohnungen sind enthalten. `Landratte` und `Deckjunge` sind in den Texten angekündigt, aber die serverseitige Rangvergabe ist noch nicht programmiert. Beim letzten Kapitän kann der Welt-Teleport erst aktiviert werden, wenn Spielwelt-ID, sichere Koordinaten und ausführender Serverbefehl bekannt sind. Bestehende Nether-/End-Spendenportale bleiben unverändert.

**Bitte keine früheren Komplettpakete mit leeren Kapiteldateien erneut importieren.**
