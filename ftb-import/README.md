# La Familia – 16 NPC-Quests, neuer Import (19.11.2026)

Aktueller Stand für Fabric 1.21.1: **16 Quests in einem einzelnen FTB-Kapitel** mit einer einzigen linearen Hauptfolge (`01 → 02 → ... → 16`), einem 4×4-Schlangenlayout und exakt 16 EasyNPC-Dialogaufgaben. Keine Checkmark-Testaufgaben mehr. Die Quest- und Reward-IDs entsprechen dem bisherigen Einführungsimport (keine Duplikate anlegen). Die Quelle ist `ftb-import/lafamilia_willkommen_16.snbt`.

**NPC-Aufgaben:** `type: "easynpc_ftbq_compat:easy_npc_task"` plus `dialogId` und `npcUuid`. Die Zuordnungen stammen aus `planung/quest-layout-willkommen-v1.json`. Diese SNBT-Task-Kennung ist anhand der Mod-Dokumentation und Ressourcen abgeleitet, aber noch **nicht auf dem tatsächlichen Server** mit der dort installierten Kompatibilitätsmod getestet. Ein Live-Server-Test mit Quest 01 ist erforderlich.

**WICHTIG: QUESTTEXTE** liegen bei FTB Quests 1.21.1 in `quests/lang/de_de.snbt` beziehungsweise `en_us.snbt`, **nicht nur** in der Questdatei. Eine reine Kapiteldatei von GitHub reicht daher nicht, um Texte anzuzeigen. Das vollständige Installationspaket wurde im Chat als `LaFamilia_16_NPC_Quests_NEUSTART.zip` erstellt und enthält 66 neue deutsche Übersetzungsschlüssel, die englische Fallback-Datei sowie ein PowerShell-Skript, das die aktuellen Sprachdateien zusammenführt und vorher sichert. Verwende dafür das ZIP statt nur die Kapiteldatei.

**Rang und Reise:** Die je Quest festgelegten Item- und XP-Belohnungen sind enthalten. `Landratte` und `Deckjunge` sind in den Texten angekündigt, aber die serverseitige Rangvergabe ist noch nicht programmiert. Beim letzten Kapitän kann der Welt-Teleport erst aktiviert werden, wenn Spielwelt-ID, sichere Koordinaten und ausführender Serverbefehl bekannt sind. Bestehende Nether-/End-Spendenportale bleiben unverändert.

**Bitte keine früheren Komplettpakete mit leeren Kapiteldateien erneut importieren.**
