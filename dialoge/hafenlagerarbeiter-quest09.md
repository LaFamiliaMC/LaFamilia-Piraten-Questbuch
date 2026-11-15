# La Familia – Hafenlagerarbeiter: Quest 09 „Fässer und Kisten“

**NPC:** Hafenlagerarbeiter (eigenständiger EasyNPC ohne Shopfunktion)  
**NPC-UUID:** `da5f8bc7-e0d5-48ed-9a1a-91a582458327`  
**Plan-ID:** `LF-L02-Q03`  
**FTB-Quest-ID:** `FA10010300090000`  
**Questtyp:** Nebenquest, optional  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“ (`LF-L02-Q01`)  
**Dialogname:** `q09_hafenlagerarbeiter`  
**Dialog-ID:** `98258fb6-9e43-371a-a51c-433c0248c504` (vom Nutzer übermittelt)  
**FTB-Status:** Nur Dokumentation; bestehender Testbuild verwendet weiterhin einen Checkmark-Task.

## Ein einziger Erklärdialog

**Hafenlagerarbeiter:**

„Ahoy, Landratte! Pass auf, wo du hintrittst! Hier wird geschleppt, gestapelt und verladen!

Ich bin der Hafenlagerarbeiter von La Familia. Ohne unsere Fässer und Kisten wäre der Hafen ein einziges Chaos.

In den Fässern lagern wir Vorräte und allerlei Dinge für lange Seereisen. In unseren Kisten und Truhen verstauen wir Werkzeuge, Baumaterial und alles, was die Mannschaft an Bord benötigt.

Ob ein Schiff mit voller Ladung ausläuft oder nach einem Sturm wieder eintrifft – wir sorgen dafür, dass nichts verloren geht!

Wenn du selbst Fässer, Kisten oder Schiffsvorräte brauchst, schau bei unserem Händler direkt hier am Hafen vorbei. Er hält einiges für tüchtige Seefahrer bereit.

Nun weißt du, warum ein gut geführtes Lager so wichtig ist. Halte die Augen offen und deinen Laderaum in Ordnung, Landratte!

Deine Einweisung ist damit beendet. Aye, und nun an die Arbeit!“

**Spieler – einzige Antwort:**

„Aye! Ohne Fässer und Kisten kommt kein Schiff weit!“

**EasyNPC-Aktion:** `Close Dialog`

## Einrichtung

1. NPC mit der UUID `da5f8bc7-e0d5-48ed-9a1a-91a582458327` im EasyNPC-Editor öffnen.
2. Einen einzigen **Standarddialog** namens `q09_hafenlagerarbeiter` anlegen.
3. Den Text übernehmen, einen Antwortknopf mit `Close Dialog` setzen.
4. Die **Dialog-ID `98258fb6-9e43-371a-a51c-433c0248c504`** ist erfasst und wird erst bei der späteren FTB-Quests-Anbindung verwendet.
5. Später wird die FTB-EasyNPC-Task an die Öffnung genau dieses Dialogs gebunden. Die Questbelohnung erfolgt dann einmalig; die Voraussetzung Quest 07 bleibt erhalten.
6. Der vorhandene Händler **Fässer & Schiffsvorräte** mit Shop-UUID `a092c754-26c6-458e-8c42-de3ad3e26c1d` bleibt unverändert.

**Keine Scoreboards, keine Shopprüfung, kein Einkauf und keine Abgabe von Fässern oder Kisten.** Als optionale Nebenquest muss Quest 09 nicht zwingend die nächste Hauptquest freischalten.
