# La Familia – Quest 13 „Das Auktionshaus“

**Plan-ID:** `LF-L03-Q01`  
**Questtyp:** Hauptquest, Einführungsreihe (16 Quests)  
**Voraussetzung:** Quest 12 „Licht für den Hafen“  
**NPC:** Auktionsmeister, eigenständiger Dialog-NPC direkt beim Auktionshaus  
**NPC-UUID:** `b0269d82-742e-422b-b730-dbffa19e423c`  
**Dialogname:** `q13_auktionsmeister`  
**Dialog-ID:** `7edc2203-9e3c-3d8c-9011-055ccb05b05c` (vom Nutzer übermittelt)  
**FTB-Quest-ID:** wird beim Anlegen des echten FTB-Kapitels vergeben  
**Status:** NPC-UUID und Dialog-ID dokumentiert. FTB-Quest und Aufgabenverknüpfung auf dem Server noch nicht eingerichtet.

## Einziger Dialog

**Auktionsmeister:**

Ahoy, Landratte! Willkommen am Umschlagplatz der großen Geschäfte von La Familia!

Ich bin der Auktionsmeister dieses Hafens. Bei uns wechseln allerlei Schätze, Werkzeuge und seltene Fundstücke ihren Besitzer!

Hier im Auktionshaus können Piraten ihre Waren anbieten, und andere Seefahrer können nach nützlichen Dingen oder echten Schnäppchen suchen.

Hast du auf deinen Reisen etwas Wertvolles gefunden, das du nicht selbst brauchst? Dann merk dir diesen Ort gut!

Doch denk daran: Ein kluger Händler prüft sein Angebot und den Preis, bevor er seine Münzen auf den Tisch legt.

Nun kennst du unser Auktionshaus. Mögen dir die Geschäfte ebenso gut gelingen wie die Abenteuer!

**Spieler – einzige Antwort:**

Aye, Auktionsmeister! Ich werde im Auktionshaus die Augen offen halten!

**EasyNPC-Aktion:** `Close Dialog`

**FTB-Aufgabe später:** Quest 13 einmalig bei **Öffnung** dieses Dialogs abschließen. Kein Kauf, Gebot oder tatsächliches Einstellen eines Angebots notwendig. Bestehende Auktionshausfunktion/Mod nicht verändern.

**Nächste Quest:** 14 „Das Tor zum Nether“.

**EasyNPC-Einrichtung:** Beim NPC mit obiger UUID genau einen Standarddialog namens `q13_auktionsmeister` anlegen, Text übernehmen, einen Antwortknopf mit `Close Dialog` erstellen. EasyNPC-Dialog-ID `7edc2203-9e3c-3d8c-9011-055ccb05b05c` ist erfasst. Die FTB-Aufgabe wird erst später verbunden.
