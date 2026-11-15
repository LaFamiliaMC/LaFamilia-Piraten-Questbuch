# La Familia – Quartiermeister (Quest 07)

**NPC:** Quartiermeister draußen vor dem Handelshaus  
**NPC-UUID:** `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**Quest-Plan-ID:** `LF-L02-Q01`  
**FTB-Quest-ID:** `FA10010300070000`  
**Questtitel:** „Beim Quartiermeister melden“ / „Die drei Geschäfte im Hafen kennenlernen“  
**Voraussetzung:** Quest 02 „Der Hafenmeister“  
**Abschluss-Dialog-ID:** Noch ausstehend – **Dialog C** (Rückkehr beim Quartiermeister)  
**Technik:** Keine Shopbesuchsprüfung. Keine Änderung an bestehenden Shop-NPCs. FTB-Quests-Anbindung erst nach Erfassen aller NPCs.

## Dialog A – Vor Quest 02

**Quartiermeister:** „Halt, Fremder! Erst wenn dich unser Kapitän willkommen geheißen hat und der Hafenmeister dich kennt, darfst du deine Aufgaben im Hafen antreten. Kehre danach zu mir zurück.“

**Spieler:** „Aye! Ich melde mich zuerst bei ihnen.“

**Ergebnis:** Keine Questbelohnung, kein Abschluss.

## Dialog B – Quest 07 beginnt: Die drei Läden kennenlernen

**Quartiermeister:** „Ahoy, Landratte! Ich bin der Quartiermeister von La Familia. Ohne gute Versorgung läuft selbst unser stolzestes Schiff keinen Meter weit!“

**Spieler:** „Wie kann ich helfen?“

**Quartiermeister:** „Direkt hinter dieser Tür liegen drei wichtige Geschäfte. Schau dir zuerst die Fässer und Schiffsvorräte an, dann Geschirr und Küchenwaren. Zuletzt besuche unseren Naturdeko-Laden. Dort siehst du, wie wir unseren Hafen ausstatten und in Schuss halten.“

**Spieler:** „Muss ich etwas kaufen?“

**Quartiermeister:** „Nein! Es geht darum, dass du unsere Händler kennenlernst. Sieh dich in Ruhe um und komm danach zu mir zurück.“

**Spieler – Startantwort:** „Aye! Ich schaue mich in den drei Geschäften um!“

**Quartiermeister:** „Gute Fahrt – und vergiss nicht, mir nachher Bescheid zu geben!“

**Ergebnis:** Kein Questabschluss. Das ist der Startdialog, *nicht* der EasyNPC-Questabschluss-Dialog.

## Dialog C – Rückkehr zum Quartiermeister (Quest 07 abschließen)

**Quartiermeister:** „Na, Landratte? Konntest du einen Blick in unsere drei Geschäfte werfen?“

**Spieler – Abschlussantwort:** „Aye, ich habe mir die drei Geschäfte angesehen!“

**Quartiermeister:** „Ausgezeichnet! Jetzt weißt du, wo unsere Mannschaft Schiffsvorräte, Küchenwaren und Ausrüstung für einen schönen Hafen bekommt. Ein guter Pirat kennt nicht nur sein Schiff, sondern auch die Leute, die es versorgen!“

**Quartiermeister:** „Deine nächste Aufgabe führt dich zum Schiffsbau. Dort wird dringend Holz benötigt. Schau in dein Questbuch und melde dich zum nächsten Auftrag!“

**Ergebnis:** *Nur* die Rückkehr-Bestätigung in **Dialog C** soll bei der späteren Anbindung Quest 07 einmalig abschließen und die abhängige Quest 08 freigeben. Es wird **nicht** geprüft, ob die drei Shops tatsächlich geöffnet wurden.

## Dialog D – Nach Questabschluss

**Quartiermeister:** „Ahoy, Landratte! Du kennst unser Handelshaus bereits. Vergiss nicht: Ohne geschickte Händler ist selbst der reichste Piratenhafen verloren.“

**Spieler:** „Wohin geht es als Nächstes?“

**Quartiermeister:** „Beim Schiffsbau wird Holz gebraucht! Geh deiner nächsten Aufgabe nach und mach La Familia stolz!“

**Ergebnis:** Keine erneute Questbelohnung.

## Spätere technische Umsetzung

1. NPC `10bbc089-6e58-4d92-b3c0-c1cf911f8d74` und Rollen/Dialoge A–D dokumentiert.
2. Nutzer übermittelt als Nächstes die EasyNPC **Dialog-ID von Dialog C**.
3. Alle EasyNPC-FTB-Tasks erst nach vollständiger NPC-Erfassung verbinden. Shop-NPCs bleiben reine Händler.
4. Quest 07 muss erst nach dem Rückkehrgespräch abschließen, ohne Shop-GUI-Checks und ohne Kaufzwang.
5. Pro Spieler nur einmal Belohnung; Quest 08 nach Questabschluss freischalten und jeden NPC einzeln testen.
