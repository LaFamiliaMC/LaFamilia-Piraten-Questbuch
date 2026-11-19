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
- **Quest 13:** NPC-UUID `b0269d82-742e-422b-b730-dbffa19e423c` und Dialog-ID `7edc2203-9e3c-3d8c-9011-055ccb05b05c` erfasst; die echte FTB-Quest-ID und der serverseitige FTB-Task fehlen noch. Für Quest 14 sind NPC-UUID `f13b074e-ef9b-4710-a1ed-51c1b1260330` und Dialog-ID `55daa677-e99b-3859-932c-7b3ed2853f97` erfasst (FTB-Quest-ID und serverseitige Aufgabe fehlen noch); für Quest 15 sind NPC-UUID `c23edf20-1e79-4a98-8680-b0417f8e4f26` und Dialog-ID `1ecb05cb-dbcd-340d-a4ae-18e662d7b12d` vollständig dokumentiert (echte FTB-Quest-ID und Task-Bindung noch ausstehend), für Quest 16 fehlen NPC-UUID, Dialog-ID und echte FTB-Quest-ID. Alle Dialogvorlagen liegen unter `dialoge/` und sind nicht in FTB Quests aktiviert.
- Quest 12: NPC `cf2887c7-a332-4bf0-8ebb-03ef60bcc486` und EasyNPC-Dialog-ID `c636022c-fb3e-3b71-b5c1-d4d0be8eda9a` vollständig dokumentiert; die FTB-Aufgabenverknüpfung erfolgt erst zum Schluss.
- Die bestehenden Quests 01–12 sind in der früheren Testbuild-Planung als Kapitel 1 und 2 dokumentiert. Das Testbuild wurde **nicht** um neue Quests ergänzt und die EasyNPC-FTB-Task-Umstellung bleibt bis zum Abschluss aller NPCs ausstehend.
- **Quest 14 und 15 dürfen nicht an das tatsächliche Aktivieren der Portale oder die Münzspenden gebunden werden.** Diese technischen Mechanismen bleiben unabhängig und sind separat zu testen.
- **Quest 16 darf nicht beim bloßen Ansprechen teleportieren.** Der Spieler muss `In die Spielwelt segeln` aktiv auswählen. Der Button soll auch nach einmaligem Questabschluss für weitere Reisen verfügbar bleiben.
- Für die tatsächliche Teleport-Konfiguration werden der **exakte Spielweltname und die sicheren Zielkoordinaten** benötigt. Diese wurden hier bewusst nicht erfunden.
- Eine FTB-Stage-Belohnung `Deckjunge` ist als einmaliger Quest-16-Meilenstein geplant. Die früher verwendete Abschluss-ID `LF-L08-Q02` ist durch `LF-L03-Q04` ersetzt worden. Wochenquests schalten künftig nach Quest 16 frei.

## Noch zu erfassen

1. Quest 12: NPC-UUID und EasyNPC-Dialog-ID erfasst; spätere FTB-Verknüpfung und Test noch offen.
2. Quest 13: NPC-UUID `b0269d82-742e-422b-b730-dbffa19e423c` und Dialog-ID `7edc2203-9e3c-3d8c-9011-055ccb05b05c` erfasst; spätere FTB-Verknüpfung offen.
3. Quest 14: Netherportal-Wächter-NPC `f13b074e-ef9b-4710-a1ed-51c1b1260330` und Dialog-ID `55daa677-e99b-3859-932c-7b3ed2853f97` erfasst; spätere FTB-Verknüpfung offen.
4. Quest 15: Endportal-Wächter-NPC `c23edf20-1e79-4a98-8680-b0417f8e4f26` und Dialog-ID `1ecb05cb-dbcd-340d-a4ae-18e662d7b12d` erfasst; spätere FTB-Verknüpfung ausstehend.
5. NPC-UUID und anschließend Dialog-ID für Quest 16 (Kapitän auf dem kleinen Schiff).
6. Spielweltname, Spawn und Transportweg für den finalen Kapitän; danach Funktionstest auf dem Testserver.

**Keine automatische FTB-Aufgabenanbindung und keine Server-Weltaktion vor Abschluss der Sammlung und technischer Prüfung.**
