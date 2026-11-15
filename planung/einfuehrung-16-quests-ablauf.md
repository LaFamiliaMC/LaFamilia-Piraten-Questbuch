# La Familia – Verkürzte Lobby-Einführung mit 16 Quests

**Stand:** 15. November 2026  
**Minecraft:** Fabric 1.21.1  
**Aktiver Plan:** [quest-layout-willkommen-v1.json](quest-layout-willkommen-v1.json)  
**Archivierter 48-Quest-Entwurf:** [archiv/quest-layout-willkommen-48-quests-stand-2026-11-15.json](archiv/quest-layout-willkommen-48-quests-stand-2026-11-15.json)

## Entscheidung

Die ursprüngliche 48-Quest-Einführung wird auf **16 Quests** verkürzt. Die Quests 01–11 bleiben in ihrem bisherigen Umfang; Quest 12 wird zum verbindlichen Übergang zu vier abschließenden Stationen. Es werden **keine weiteren 32 Lobby-Einführungsquests** benötigt. Die übrigen großen La-Familia-Questkategorien sind davon unabhängig und können später als Spielweltinhalte entwickelt werden.

## Hauptpfad

`01 → 02 → 07 → 08 → 12 → 13 → 14 → 15 → 16 → Spielwelt`

Die Quests 03–06 und 09–11 bleiben **freiwillige Nebenquests**. Sie dürfen die Spielweltreise nicht blockieren.

## Neue vier Abschlussstationen

| Quest | Inhalt | Sprecher | Abhängigkeit | FTB-Abschluss |
|---|---|---|---|---|
| 13 Das Auktionshaus | Auktionshaus erklären, ohne Handelspflicht | Neuer Auktionsmeister neben dem Auktionshaus | 12 | Später beim Öffnen eines Dialogs |
| 14 Das Tor zum Nether | Spendenfreischaltung des Netherportals erklären | Neuer Netherportal-Wächter, kein Eingriff in Spenden-NPC | 13 | Später beim Öffnen eines Dialogs |
| 15 Das Tor zum End | Spendenfreischaltung des Endportals erklären | Neuer Endportal-Wächter, kein Eingriff in Spenden-NPC | 14 | Später beim Öffnen eines Dialogs |
| 16 Leinen los – Aufbruch in die Spielwelt | Kapitän auf dem **kleinen Schiff**; einmalig Rang Deckjunge, expliziter Reisebutton zur Spielwelt | Neuer Abschlusskapitän | 15 | Questabschluss beim Öffnen des Dialogs, Teleport nur auf bewusst gewählte Antwort |

## Wichtig für die Implementierung

- Alle vier neuen Sprecher sollen je **einen eigenen EasyNPC-Standarddialog** erhalten. Auktionshaus, Portal-Spenden-NPCs und bestehende Händler bleiben unverändert.
- Für Quests 13–16 fehlen **NPC-UUIDs, EasyNPC-Dialog-IDs und tatsächliche FTB-Quest-IDs**. Die Dialogvorlagen liegen unter `dialoge/` und sind noch nicht auf dem Server eingerichtet.
- Für Quest 12 fehlt noch die EasyNPC-**Dialog-ID** des NPC `cf2887c7-a332-4bf0-8ebb-03ef60bcc486`.
- Die bestehenden Quests 01–12 sind in der früheren Testbuild-Planung als Kapitel 1 und 2 dokumentiert. Das Testbuild wurde **nicht** um neue Quests ergänzt und die EasyNPC-FTB-Task-Umstellung bleibt bis zum Abschluss aller NPCs ausstehend.
- **Quest 14 und 15 dürfen nicht an das tatsächliche Aktivieren der Portale oder die Münzspenden gebunden werden.** Diese technischen Mechanismen bleiben unabhängig und sind separat zu testen.
- **Quest 16 darf nicht beim bloßen Ansprechen teleportieren.** Der Spieler muss `In die Spielwelt segeln` aktiv auswählen. Der Button soll auch nach einmaligem Questabschluss für weitere Reisen verfügbar bleiben.
- Für die tatsächliche Teleport-Konfiguration werden der **exakte Spielweltname und die sicheren Zielkoordinaten** benötigt. Diese wurden hier bewusst nicht erfunden.
- Eine FTB-Stage-Belohnung `Deckjunge` ist als einmaliger Quest-16-Meilenstein geplant. Die früher verwendete Abschluss-ID `LF-L08-Q02` ist durch `LF-L03-Q04` ersetzt worden. Wochenquests schalten künftig nach Quest 16 frei.

## Noch zu erfassen

1. EasyNPC-Dialog-ID für Quest 12.
2. NPC-UUID und anschließend Dialog-ID für Quest 13 (Auktionsmeister).
3. NPC-UUID und anschließend Dialog-ID für Quest 14 (Netherportal-Wächter).
4. NPC-UUID und anschließend Dialog-ID für Quest 15 (Endportal-Wächter).
5. NPC-UUID und anschließend Dialog-ID für Quest 16 (Kapitän auf dem kleinen Schiff).
6. Spielweltname, Spawn und Transportweg für den finalen Kapitän; danach Funktionstest auf dem Testserver.

**Keine automatische FTB-Aufgabenanbindung und keine Server-Weltaktion vor Abschluss der Sammlung und technischer Prüfung.**
