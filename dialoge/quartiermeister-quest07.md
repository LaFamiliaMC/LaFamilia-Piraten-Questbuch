# La Familia – Quartiermeister Quest 07, einfache Einrichtung OHNE Scoreboard

**NPC:** Quartiermeister vor dem Handelshaus  
**NPC-UUID:** `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**FTB-Quest-ID:** `FA10010300070000`  
**Quest:** „Beim Quartiermeister melden“ / „Die drei Geschäfte im Hafen kennenlernen“  
**Abschluss-Dialog-ID:** noch ausstehend; **nur `q07_abschluss`** aus EasyNPC kopieren.

## So funktioniert die Variante ohne Scoreboard

Wir verwenden drei EasyNPC-Dialogeinträge und keine Scoreboards, Spieler-Tags, Befehle oder Shopbesuchskontrolle. Die Shop-NPCs bleiben unverändert.

### Dialog 1 – `q07_start` (Standard-/Fallback-Dialog)

Text: „Ahoy, Landratte! Ich bin der Quartiermeister von La Familia. Direkt hinter dieser Tür befinden sich drei Geschäfte: Fässer und Schiffsvorräte, Geschirr und Küchenwaren sowie Naturdeko. Schau dich dort in Ruhe um. Du musst nichts kaufen – komm danach wieder zu mir!“

**Antwortknopf A:** „Aye! Ich schaue mich in den drei Geschäften um!“  
**Aktion:** `Close Dialog`

**Antwortknopf B:** „Ich bin zurück und möchte Bericht erstatten!“  
**Aktion:** `Open Dialog` → `q07_rueckkehr`

`q07_start` ist als **Standarddialog** markiert. Bei jedem erneuten Anklicken kann er wieder erscheinen, solange die spätere FTB-Zustandsbedingung noch nicht eingerichtet ist.

### Dialog 2 – `q07_rueckkehr` (Manual Only, Priorität -1)

Text: „Na, Landratte? Hast du dir unsere drei Geschäfte angesehen?“

**Antwortknopf:** „Aye, ich habe mir die drei Geschäfte angesehen!“  
**Aktion:** `Open Dialog` → `q07_abschluss`

**Alternativer Antwortknopf:** „Noch nicht, ich sehe mich weiter um.“  
**Aktion:** `Close Dialog`

Wichtig: Die FTB-Quest-Aufgabe später **nicht an `q07_rueckkehr`** knüpfen. Ihre Öffnung bedeutet noch keine Bestätigung.

### Dialog 3 – `q07_abschluss` (Manual Only, Priorität -1)

Text: „Ausgezeichnet, Landratte! Jetzt kennst du unsere drei Händler und weißt, wo unsere Mannschaft ihre Vorräte herbekommt. Nun wartet der Schiffsbau: Dort wird dringend Holz benötigt!“

**Antwortknopf:** „Aye, Quartiermeister!“  
**Aktion:** `Close Dialog`

**Nur diese Dialog-ID** wird in der abschließenden gemeinsamen Integration in die FTB-Quests-EasyNPC-Aufgabe für Quest 07 eingetragen. Laut Kompatibilitätsmod löst die Quest-Aufgabe beim **Öffnen des Zieldialogs** aus.

## Wichtige Grenzen

- **Keine Überprüfung** der tatsächlichen Shopbesuche.
- **Keine Erinnerung an einen ersten Besuch:** Da kein Scoreboard oder anderer Speicher genutzt wird, kann ein Spieler bereits beim ersten Gespräch auf „Ich bin zurück“ klicken. Das ist laut Wunsch des Serverbetreibers für diese Einführungsquest in Ordnung.
- Erst später richten wir für abgeschlossene FTB-Quest 07 optional einen Nachgesprächs-Dialog ein, der sich über den FTB-Queststatus auswählt. Dadurch bleibt die einmalige Belohnung auf Quest-Ebene.
- **Bis alle NPCs stehen:** kein automatischer FTB-Aufgaben-Abschluss, keine echten Belohnungen über diese Dialoge.
- Vor der finalen Integration kann man alle Dialoge testen, aber Quest 07 bleibt im Testbuild eine Checkmark-Aufgabe.

## In EasyNPC eintragen

1. Quartiermeister im NPC-Editor öffnen, **Advanced Dialog / Erweiterter Dialog** wählen.
2. Eintrag `q07_start` als Standarddialog anlegen; zwei Antwortknöpfe wie oben.
3. Eintrag `q07_rueckkehr` mit Priorität **Manual Only (-1)** anlegen; sein Abschlussknopf öffnet `q07_abschluss`.
4. Eintrag `q07_abschluss` ebenfalls mit Priorität **Manual Only (-1)** anlegen.
5. Funktion **Open Dialog** in der Aktion des jeweiligen Antwortknopfs benutzen.
6. Am Ende über die **Q**-Schaltfläche der Kompatibilitätsmod **nur die Dialog-ID von `q07_abschluss`** kopieren und dem Projekt mitteilen.

**Fazit:** Drei Dialogeinträge ohne Scoreboard; Questabschluss später erst nach Bestätigung durch den Spieler. Kein Kauf und kein Tracking.
