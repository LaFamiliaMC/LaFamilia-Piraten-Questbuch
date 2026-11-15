# La Familia – Quartiermeister (Quest 07)

**NPC:** Quartiermeister vor dem Handelshaus  
**NPC-UUID:** `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**FTB-Quest-ID:** `FA10010300070000`  
**Status:** NPC erfasst. FTB-EasyNPC-Anbindung noch **nicht** aktiv.  
**Gesuchte Dialog-ID:** `q07_abschluss` (Dialog D), **nicht** `q07_rueckkehr`.

> **Technischer Hinweis:** EasyNPC x FTB Quests Compat schließt eine passende Aufgabe **beim Öffnen des Dialogs** ab. Darum muss erst die Bestätigung des Spielers den eigentlichen Abschlussdialog öffnen. Diese Ergänzung präzisiert die frühere Dialogvorlage.

## Dialog A – Vor Aufnahme beim Hafenmeister (optional, spätere Questbedingung)

**Quartiermeister:** „Halt, Fremder! Erst wenn du dich beim Kapitän und beim Hafenmeister gemeldet hast, beginnen deine Aufgaben im Hafen.“

**Spieler:** „Aye, ich werde mich zuerst dort melden.“

**Ergebnis:** Kein Abschluss. FTB-Abhängigkeit zu Quest 02 später einrichten.

## Dialog B – `q07_start`: Die drei Läden kennenlernen

**Quartiermeister:** „Ahoy, Landratte! Willkommen bei der Hafenversorgung von La Familia! Ich bin der Quartiermeister und sorge dafür, dass die Mannschaft versorgt ist. Direkt hinter dieser Tür findest du Fässer und Schiffsvorräte, Geschirr und Küchenwaren und unseren Naturdeko-Laden.“

**Spieler (Frage):** „Muss ich dafür etwas kaufen?“

**Quartiermeister:** „Nein! Schau dich einfach in Ruhe um. Du sollst die Läden kennenlernen, mehr nicht. Komm anschließend zu mir zurück!“

**Spieler (Startantwort):** „Aye! Ich schaue mich in den drei Geschäften um!“

**EasyNPC-Aktion auf dieser Startantwort:** `Scoreboard` → `set:lf_q07:1`, danach `Close Dialog`.

**Ergebnis:** Nach diesem Gespräch erscheint beim nächsten Anklicken der Rückkehrdialog. Kein FTB-Questabschluss.

## Dialog C – `q07_rueckkehr`: Spieler meldet sich zurück

**Anzeige-Bedingung:** Scoreboard `lf_q07 == 1`; Priorität 10.

**Quartiermeister:** „Na, Landratte? Hast du unsere drei Geschäfte kennengelernt?“

**Spieler (Abschlussantwort):** „Aye, ich habe mir die drei Geschäfte angesehen!“

**EasyNPC-Aktionen auf genau dieser Abschlussantwort (in Reihenfolge):**
1. `Scoreboard` → `set:lf_q07:2`
2. `Open Dialog` → `q07_abschluss`

**Wichtig:** Die Quest soll **nicht** beim bloßen Öffnen von Dialog C abschließen. Es ist keine Shopbesuchsprüfung vorgesehen.

## Dialog D – `q07_abschluss`: Separates Abschlusssignal

**Anzeige:** Priorität `Manual Only (-1)`, nicht als Standarddialog. Öffnet **nur**, wenn die Antwort aus Dialog C ihn aufruft.

**Quartiermeister:** „Ausgezeichnet! Jetzt weißt du, wo unsere Mannschaft Vorräte, Küchenwaren und Hafenausstattung bekommt. Nun wird Holz für den Schiffsbau gebraucht. Schau in dein Questbuch, Landratte!“

**Spieler:** „Aye, Quartiermeister!“

**Späterer FTB-Task:** Hier die **Dialog-ID von `q07_abschluss`** hinterlegen. **Bei Öffnung dieses Dialogs** wird dann die Quest 07 abgeschlossen (sofern freigeschaltet). Ein eigener Itemkauf oder Shopcheck wird nicht benötigt.

## Dialog E – `q07_nachher`: Nach der Bestätigung

**Anzeige-Bedingung:** Scoreboard `lf_q07 == 2`; Priorität 100.

**Quartiermeister:** „Ahoy, Landratte! Unser Handelshaus kennst du nun. Der Schiffsbau kann deine Hilfe gebrauchen!“

**Spieler:** „Aye! Auf zum nächsten Auftrag!“

**Ergebnis:** Keine erneute Questbelohnung.

## Dialogsteuerung in EasyNPC

1. Einmalig durch Server-OP: `/scoreboard objectives add lf_q07 dummy`.
2. Im NPC-Menü **Erweiterter Dialog / Advanced Dialog** öffnen und Einträge wie oben benennen.
3. `q07_start`: bedingungsloser Fallback (Priorität 0). Startknopf mit Scoreboard-Aktion `set:lf_q07:1`.
4. `q07_rueckkehr`: Bedingung Scoreboard `lf_q07 == 1` (Priorität 10). Bestätigungsknopf `set:lf_q07:2` und dann Aktion **Open Dialog** `q07_abschluss`.
5. `q07_abschluss`: **Manual Only -1** und keine Bedingungen. **Nur seine Dialog-ID** für die spätere EasyNPC-FTB-Aufgabe kopieren.
6. `q07_nachher`: Bedingung `lf_q07 == 2` und Priorität 100.
7. FTB-Quest-02-Voraussetzung und FTB Task/Belohnung wie mit dem Besitzer vereinbart **erst später** integrieren.
8. Spieler können diese Dialoge im Test bereits sehen, doch die **FTB-Quest 07 wird dabei noch nicht real abgeschlossen**. Zum Zurücksetzen von Tests: `/scoreboard players reset <Spielername> lf_q07`.
