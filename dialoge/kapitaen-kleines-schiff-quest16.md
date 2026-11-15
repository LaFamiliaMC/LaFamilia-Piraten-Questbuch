# La Familia – Quest 16 „Leinen los – Aufbruch in die Spielwelt“

**Plan-ID:** `LF-L03-Q04`  
**Questtyp:** Verpflichtender Abschluss der 16-Quest-Lobby-Einführung  
**Voraussetzung:** Quest 15 „Das Tor zum End“  
**NPC:** Neuer Kapitän **auf dem kleinen Schiff**; getrennt vom Startkapitän aus Quest 01  
**NPC-UUID:** noch einzutragen  
**Dialogname:** `q16_kapitaen_kleines_schiff`  
**Dialog-ID:** noch einzutragen  
**FTB-Quest-ID:** noch nicht erzeugt  
**Einmalige spätere Questbelohnung:** Rang `Deckjunge` (Stage `lafamilia_rank_02_deckjunge`, nach serverseitiger Verifikation)  
**Teleport-Zielwelt:** noch unbekannt  
**Sichere Zielkoordinaten / Spawn:** noch unbekannt  
**Status:** Planung; weder Questabschluss noch Teleportfunktion sind aktiv

## Einziger Dialog

**Kapitän auf dem kleinen Schiff:**

Ahoy, Landratte! Da bist du ja endlich!

Ich habe gehört, du hast unseren Hafen erkundet, die Händler kennengelernt und sogar einen Blick auf die verbotenen Portale geworfen.

Dann weißt du nun, was es bedeutet, zur Mannschaft von La Familia zu gehören: Wir halten zusammen, handeln klug und lassen keinen Kameraden zurück!

Du hast deine ersten Schritte gemeistert. Ab heute darfst du dich **Deckjunge** nennen!

Doch eine Piratenlaufbahn beginnt nicht am Kai. Sie beginnt dort draußen, jenseits der sicheren Mauern dieses Hafens!

Mein kleines Schiff bringt dich zum Ausgangspunkt deiner großen Reise in die Spielwelt. Dort kannst du bauen, erkunden, Schätze suchen und deine eigene Geschichte schreiben.

Also, Matrose: Bist du bereit, die Segel zu setzen?

**Spieler – Antwort 1:**

Aye, Kapitän! Leinen los – bringt mich in die Spielwelt!

**Geplante Aktion auf Antwort 1:** `Server-side teleport to configured game-world spawn` – **noch kein ausführbarer Befehl**, da Weltname, Spawn und ausführende Mod/Command-Bridge erst bestätigt werden müssen. Nur nach bewusstem Klick teleportieren, nicht bereits beim Dialogöffnen. Dieselbe Reiseoption soll bei späteren Gesprächen erneut funktionieren.

**Spieler – Antwort 2:**

Noch nicht, Kapitän. Ich bleibe vorerst im Hafen!

**EasyNPC-Aktion auf Antwort 2:** `Close Dialog`

**FTB-Aufgabe später:** Quest 16 beim ersten **Öffnen** des Dialogs abschließen; einmalige Deckjunge-Belohnung in FTB Quests (unabhängig vom später beliebig wiederholbaren Teleport). Soll der Rang stattdessen erst **nach** der Reise verliehen werden, braucht es einen bestätigten Teleport-Erfolgs-Task – derzeit nicht konfiguriert.

## Technische Umsetzungsgrenzen

- Die Teleportation muss auf dem **Server** mit bestätigtem Multiworld-System und einem sicheren, geladenen Zielort umgesetzt werden.
- Weltname, Dimension, Koordinaten, Rotationsausrichtung und Rückreisemöglichkeit sind vor Aktivierung festzulegen.
- Prüfen, dass nur die vorgesehene NPC-Antwort reisen lässt und Spieler ohne Operator-Rechte den Transport verwenden können, ohne gefährlich umfassende Command-Rechte zu erhalten.
- Prüfen, ob die Reise nach der einmaligen Questbelohnung erneut ausführbar bleibt.
- Nicht mit dem bestehenden Startkapitän verwechseln. Kein Shop; nur ein Dialog mit zwei Antwortmöglichkeiten.
- Keine Änderung an Welt- oder Portalmod-Konfiguration in der reinen Planungsphase.
