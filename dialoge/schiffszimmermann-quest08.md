# La Familia – Schiffszimmermann: Quest 08 „Holz für den Schiffsbau“

**NPC:** Schiffszimmermann (eigener Quest-NPC ohne Shopfunktion)  
**NPC-UUID:** `07f6f682-8be6-4a91-aa99-4c3ec711d25d`  
**Quest-ID:** `LF-L02-Q02` / FTB `FA10010300080000`  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“  
**Dialogname:** `q08_schiffszimmermann`  
**Dialog-ID:** `325abe87-2b95-35ab-8910-924352962719` (vom Nutzer angegeben)  
**Status:** Beide IDs dokumentiert; EasyNPC-FTB-Abschlusstask wird erst eingerichtet, wenn alle NPCs erfasst sind.

## Einziger Dialog – q08_schiffszimmermann

**Schiffszimmermann:**

Ahoy, Landratte! Du musst der Neue sein, den unser Quartiermeister geschickt hat!

Ich bin der Schiffszimmermann von La Familia!

Siehst du die mächtigen Schiffe in unserem Hafen? Jedes einzelne davon braucht gutes Holz!

Ob Planken, Masten oder Reparaturen nach einer stürmischen Fahrt – ohne ordentliches Bauholz läuft hier gar nichts!

Besonders Eichenholz ist bei uns gefragt. Ohne Nachschub bleibt selbst das stolzeste Piratenschiff irgendwann am Kai liegen!

Gleich hier findest du unseren **Holzfäller-Händler Jack**. Merke dir seinen Standort gut, denn dort kannst du **sämtliches Holz, das du findest, an uns verkaufen!**

Jetzt weißt du, wie wichtig unser Holzhandel für den Schiffsbau ist.

Gut gemacht, Landratte! Deine nächste Aufgabe wartet bereits auf dich!

**Spieler – Antwort:**

Aye, Schiffszimmermann! Ich werde mir merken, wo ich Bauholz bekomme!

**EasyNPC-Aktion:** `Close Dialog`

## In EasyNPC und FTB Quests

- Der einzige Standarddialog heißt `q08_schiffszimmermann` mit Dialog-ID `325abe87-2b95-35ab-8910-924352962719`.
- Die FTB-Quest soll **beim Öffnen dieses Dialogs** einmalig abgeschlossen werden – aber erst nach vollständiger NPC-Erfassung durch die spätere Aufgabenintegration.
- Der Holzfäller-Shop von **Jack** (registrierte Shop-NPC-UUID `b424fb91-290c-422e-9475-970a1fc4ec64`) bleibt unverändert. Sein Verkauf von Holz wird durch diesen Text nur erklärt; es ist keine Änderung am Shop.
- **Kein Scoreboard, keine tatsächliche Holzlieferung und keine Kontrolle von Shop-Interaktionen** für Quest 08.
- Der aktuelle Testbuild nutzt weiterhin den Checkmark-Task und führt keine automatischen Dialogabschlüsse aus.
