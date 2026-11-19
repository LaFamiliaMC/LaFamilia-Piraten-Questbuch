# La Familia – Quest 14 „Das Tor zum Nether“

**Plan-ID:** `LF-L03-Q02`  
**Questtyp:** Hauptquest, Einführungsreihe  
**Voraussetzung:** Quest 13 „Das Auktionshaus“  
**NPC:** Separater Netherportal-Wächter nahe dem vorhandenen Netherportal (nicht den Spenden-NPC oder dessen Funktion ersetzen)  
**NPC-UUID:** `f13b074e-ef9b-4710-a1ed-51c1b1260330`  
**Dialogname:** `q14_netherportalwaechter`  
**Dialog-ID:** `55daa677-e99b-3859-932c-7b3ed2853f97` (vom Nutzer übermittelt)  
**FTB-Quest-ID:** noch nicht erzeugt  
**Status:** NPC-UUID und Dialog-ID dokumentiert; die FTB-Aufgabe ist noch nicht auf dem Server angebunden.

## Einziger Dialog

**Netherportal-Wächter:**

Halt ein, Landratte! Vor dir liegt eines der gefährlichsten Tore von La Familia: das Netherportal!

Hinter diesem Portal wartet eine Welt aus Feuer, Lava und Gefahren, die selbst erfahrene Piraten nicht unterschätzen sollten.

Ein solches Tor öffnen wir nicht leichtfertig. Die Mannschaft muss zuerst gemeinsam genügend Münzen für die Freischaltung spenden.

Beim Spendenwächter am Portal kannst du später erfahren, wie weit die Sammlung vorangeschritten ist. Erst wenn das Spendenziel erreicht ist und das Portal tatsächlich aktiviert wurde, ist die Reise möglich.

Für deine Einweisung musst du weder spenden noch durch das Portal gehen. Ich will nur, dass du weißt, wovor du stehst!

Und nun geh weiter, Landratte. Auch das Endportal hat seine eigenen Geheimnisse.

**Spieler – einzige Antwort:**

Aye! Ich werde das Netherportal und die gemeinsame Freischaltung nicht vergessen!

**EasyNPC-Aktion:** `Close Dialog`

**FTB-Aufgabe später:** Quest 14 einmalig bei **Öffnung** des Erklärdialogs abschließen, ohne Spenden-/Portalaktivierungsprüfung.

**Wichtig:** Der bestehende Netherportal-Spendenmechanismus bleibt unabhängig. Keine Änderung an Freischaltung, Zielbetrag, Portalbau oder vorhandenen Portal-NPCs. Funktionstests erfolgen getrennt.

**Nächste Quest:** 15 „Das Tor zum End“.

## Einrichtung in EasyNPC

1. Mit dem eigenständigen Netherportal-Wächter `f13b074e-ef9b-4710-a1ed-51c1b1260330` den Dialogeditor öffnen.
2. **Einen einzigen Standarddialog** `q14_netherportalwaechter` erstellen.
3. Den obigen Erklärungstext einfügen und eine Antwort mit `Close Dialog` anlegen.
4. Die EasyNPC-Dialog-ID `55daa677-e99b-3859-932c-7b3ed2853f97` ist erfasst und wird später für die FTB-Aufgabenverknüpfung genutzt.
5. Erst nach Erfassung aller NPCs diesen Dialog als FTB-Questaufgabe verknüpfen; Quest 13 ist Voraussetzung. Die Quest darf keine Spende und keine Portalaktivierung verlangen.
