# La Familia – Hafenlaternenwärter: Quest 12 „Licht für den Hafen“

**NPC:** Hafenlaternenwärter (eigener Quest-NPC ohne Shopfunktion)  
**NPC-UUID:** `cf2887c7-a332-4bf0-8ebb-03ef60bcc486`  
**Quest-Plan-ID:** `LF-L02-Q06`  
**FTB-Quest-ID:** `FA10010300120000`  
**Questtyp:** Hauptquest (verpflichtender Übergang zur Abschlussreihe)  
**Voraussetzung:** Quest 08 „Holz für den Schiffsbau“  
**Dialogname:** `q12_hafenlaternenwaerter`  
**Dialog-ID:** `c636022c-fb3e-3b71-b5c1-d4d0be8eda9a` (vom Nutzer übermittelt)  
**FTB-Status:** Nur NPC und Text vorbereitet. Der Testbuild verwendet noch `ftbquests:checkmark`.

## Einziger EasyNPC-Dialog

**Hafenlaternenwärter:**

Ahoy, Landratte! Bleib einen Augenblick stehen und schau dich um!

Ich bin der Hafenlaternenwärter von La Familia. Meine Aufgabe beginnt, wenn die Sonne hinter dem Meer versinkt und dunkle Schatten über unseren Hafen kriechen.

Ohne das Licht unserer Laternen würden die Seeleute nachts kaum den Weg über die Stege finden. Und wenn ein Schiff im Dunkeln anlegt, kann ein einziger falscher Schritt gefährlich werden!

Darum sorgen wir dafür, dass die Kais, die Wege und die Eingänge zu unseren Lagerhäusern gut beleuchtet bleiben.

Ob kleine Kerzen für eine gemütliche Kajüte oder große Laternen für den Hafen – Licht ist für einen Piraten weit mehr als bloße Dekoration!

Wenn du dein eigenes Haus, dein Schiff oder deine Wege ausstatten möchtest, findest du gleich hier unseren Händler für **Kerzen und Laternen**. Bei ihm kannst du passende Lichtquellen entdecken.

Merk dir das gut, Landratte: Ein Hafen, der selbst in der dunkelsten Nacht leuchtet, heißt seine Mannschaft sicher willkommen!

Nun weißt du, warum wir so auf unsere Lichter achten. Halte deine Augen offen und verliere niemals den sicheren Weg nach Hause!

**Spieler – einzige Antwort:**

Aye, Laternenwärter! Ich werde dafür sorgen, dass ich den Weg auch bei Nacht finde!

**EasyNPC-Aktion:** `Close Dialog`

**Quest 12:** Bei der späteren FTB-EasyNPC-Aufgabe soll die Quest bereits beim **Öffnen** dieses einen Dialogs einmalig abgeschlossen werden; der Antwortknopf schließt nur das Gespräch.

## Einrichtung

1. EasyNPC für den Hafenlaternenwärter mit NPC-UUID `cf2887c7-a332-4bf0-8ebb-03ef60bcc486` öffnen.
2. Genau **einen Standarddialog** mit dem Namen `q12_hafenlaternenwaerter` anlegen.
3. Obigen Text einfügen und eine Antwort mit der Aktion `Close Dialog` anlegen.
4. Dialog-ID `c636022c-fb3e-3b71-b5c1-d4d0be8eda9a` ist erfasst und wird bei der späteren EasyNPC-FTB-Aufgabenanbindung genutzt.
5. Den FTB-Aufgabentyp, die Abhängigkeit von Quest 08 und eine eventuelle einmalige Belohnung **erst später**, nach Erfassung aller NPCs, aktivieren und testen.

Der vorhandene **Kerzen-&-Laternen-Händler** mit Shop-UUID `492d0ebe-2b7c-4b18-b7e5-5276e8e5a21f` bleibt unverändert. Kein Scoreboard, keine Kaufpflicht, kein Platzieren von Lichtquellen.

## Aktualisierte Kapitel-Reihenfolge (15.11.2026)

Nach Kürzung der Einführungsreihe von 48 auf 16 Quests ist Quest 12 jetzt die **verpflichtende Übergangsquest** vor Quest 13 „Das Auktionshaus“. Der vorhandene Standarddialog bleibt unverändert; seine Dialog-ID `c636022c-fb3e-3b71-b5c1-d4d0be8eda9a` wurde erfasst. Die Änderung ist bislang nur geplant und nicht im laufenden FTB-Testbuild eingespielt.
