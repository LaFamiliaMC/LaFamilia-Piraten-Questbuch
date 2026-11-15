# La Familia – Proviantmeister: Quest 10 „Fehlende Vorräte“

**NPC:** Proviantmeister (eigener EasyNPC ohne Shopfunktion)  
**NPC-UUID:** `af4c5c5e-aa26-453b-8962-b740932a8143`  
**Quest-ID:** `LF-L02-Q04` / FTB `FA10010300100000`  
**Questtyp:** Optionale Nebenquest  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“  
**Dialogname:** `q10_proviantmeister`  
**Dialog-ID:** Noch aus EasyNPC zu übermitteln  
**Status:** NPC gespeichert und Dialogvorlage erstellt, kein aktivierter FTB-Questabschluss im Testbuild.

## Einziger EasyNPC-Dialog

**Proviantmeister:**

„Ahoy, Landratte! Gut, dass du hier bist! Ich bin der Proviantmeister von La Familia, und in unseren Vorratskammern herrscht gerade ein ordentliches Durcheinander!

Stell dir vor, ein Schiff sticht in See und nach wenigen Tagen sind Brot und Trinkwasser alle. Da hilft auch die größte Kanone nichts mehr!

Darum achten wir darauf, dass immer genug Lebensmittel, Wasser und Ersatzvorräte an Bord sind. Wir verstauen sie ordentlich in Fässern und Kisten, damit die Mannschaft auch auf langen Reisen versorgt bleibt.

Unser Händler für Fässer und Schiffsvorräte ist gleich hier in der Nähe. Bei ihm kannst du dich über wichtige Waren für die Versorgung informieren.

Merke dir eines, Landratte: Ein kluger Kapitän zählt nicht nur seine Kanonenkugeln, sondern auch seine Vorräte!

Damit kennst du die Aufgabe unserer Hafenversorgung. Für heute musst du nichts beschaffen – diese Einweisung reicht aus. Halte dein Questbuch im Auge, denn La Familia hat noch viel mit dir vor!“

**Spieler – einzige Antwort:**

„Aye, Proviantmeister! Ich werde unsere Vorräte nicht vergessen!“

**EasyNPC-Aktion:** `Close Dialog`

**Ziel:** Sobald die EasyNPC-FTB-Questaufgabe später verbunden ist, wird die optionale Quest 10 bereits beim **Öffnen** dieses Dialogs einmalig abgeschlossen, nicht erst beim Anklicken des Schließen-Knopfs.

## Einrichtung in EasyNPC

1. Den NPC `af4c5c5e-aa26-453b-8962-b740932a8143` mit dem Dialogeditor bearbeiten.
2. Genau **einen Standarddialog** namens `q10_proviantmeister` anlegen.
3. Obigen Text und den einzigen Antwortknopf mit der Aktion `Close Dialog` übernehmen.
4. Seine **Dialog-ID** aus EasyNPC kopieren und für Quest 10 weitergeben.
5. Die Zuordnung zu FTB Quests wird erst eingerichtet, wenn alle Quest-NPCs gesammelt wurden. Voraussetzung Quest 07 bleibt erhalten und die Questbelohnung soll einmalig sein.

**Wichtig:** Der vorhandene Shop-NPC **Fässer & Schiffsvorräte** (`a092c754-26c6-458e-8c42-de3ad3e26c1d`) bleibt unverändert. Trotz des Titels „Fehlende Vorräte“ verlangt diese vereinfachte Einführungsquest weder Einkauf noch reale Vorratsabgabe oder Scoreboard. Der aktuelle Testbuild behält bis zur gemeinsamen Integration die Checkmark-Aufgabe.
