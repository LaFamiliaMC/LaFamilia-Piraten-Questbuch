# Quest 07 – Beim Quartiermeister melden: Nur ein Dialog

**Quest-ID:** `LF-L02-Q01` / FTB `FA10010300070000`  
**NPC:** Quartiermeister vor dem Handelshaus, UUID `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**Voraussetzung:** Quest 02 „Der Hafenmeister“ – wird bei der gemeinsamen späteren FTB-Integration gesetzt.  
**Dialog:** `q07_quartiermeister` (einziger Erklär- und Abschlussdialog; ID wird aus EasyNPC nachgereicht).

## Questablauf

1. Der Spieler spricht den Quartiermeister außerhalb des Handelshauses an.
2. **Ein einzelner Dialog** erklärt die drei Shops.
3. Der Spieler klickt „Aye, Quartiermeister! Vielen Dank für die Einweisung!“ und schließt das Gespräch.
4. Quest 07 soll bei der späteren Einrichtung der EasyNPC-FTB-Aufgabe bereits **beim Öffnen** dieses einen Dialogs abgeschlossen werden – **nicht** erst beim Klick auf den Schließen-Knopf. Die Questbelohnung ist einmalig und Quest 08 folgt über die FTB-Quest-Abhängigkeit.

## Erklärte Shops (nur erzählerisch)

- **Fässer & Schiffsvorräte**, existierender Shop-NPC `a092c754-26c6-458e-8c42-de3ad3e26c1d`
- **Geschirr & Küchenwaren**, existierender Shop-NPC `b9150d76-1221-40eb-9736-867be277fdef`
- **Naturdeko**, vermutlich „Decocraft Nature“, existierender Shop-NPC `c9f6d74e-35ba-4083-8688-013d7ecbd161` (exakte Zuordnung bei Bedarf später bestätigen)

Diese Shop-NPCs behalten vollständig ihre Shop-GUI und erhalten keine Questdialoge.

## Was **nicht** nötig ist

- Keine Shop-Interaktion als Voraussetzung.
- Kein Rückkehrgespräch und keine zweite Dialog-ID.
- Keine Scoreboards, Spieler-Tags, Aktionen wie „Open Dialog“ oder besondere Befehle.
- Kein Kauf und keine zusätzliche Mod.

**Zum Einrichten:** Im EasyNPC-Dialogeditor einen einzigen Standarddialog mit dem Text aus [Quartiermeister-Dialog Quest 07](../dialoge/quartiermeister-quest07.md) erstellen. Als Antwort lediglich einen Knopf mit Aktion „Close Dialog“ vorsehen. Danach **die Dialog-ID dieses einen Dialogs** kopieren und uns mitteilen.

**Status:** Aktuelle FTB-Testdatei ist weiter auf Checkmark. Automatische Verknüpfung mit Dialog-Öffnen erst am Ende, wenn alle NPCs gesammelt und geprüft sind.
