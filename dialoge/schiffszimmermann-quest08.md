# La Familia – Schiffszimmermann: Quest 08 „Holz für den Schiffsbau“

**NPC:** Schiffszimmermann (eigener Quest-NPC ohne Shopfunktion)  
**NPC-UUID:** `07f6f682-8be6-4a91-aa99-4c3ec711d25d`  
**Quest-ID:** `LF-L02-Q02` / FTB `FA10010300080000`  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“  
**Dialogname:** `q08_schiffszimmermann`  
**Dialog-ID:** Noch ausstehend; nach Erstellung aus EasyNPC kopieren  
**Aufgabe:** Ein einziger Erklärdialog; Quest soll bei der späteren EasyNPC-FTB-Task-Konfiguration durch Öffnen dieses Dialogs einmalig abgeschlossen werden.  
**Status:** Planung, noch keine aktive Verknüpfung oder Belohnung im Testbuild.

## Einziger Dialog – q08_schiffszimmermann

**Schiffszimmermann:**

„Ahoy, Landratte! Du musst der Neue sein, den unser Quartiermeister geschickt hat!

Ich bin der Schiffszimmermann von La Familia. Siehst du die mächtigen Schiffe in unserem Hafen? Jedes davon braucht gutes Holz – für die Planken, die Masten und all die Reparaturen nach einer stürmischen Fahrt.

Besonders Eichenholz ist bei uns gefragt. Ohne verlässlichen Nachschub bleibt selbst das stolzeste Piratenschiff irgendwann am Kai liegen!

Gleich hier findest du unseren Holzfäller-Händler. Merke dir seinen Standort gut: Wenn die Mannschaft Bauholz braucht, ist er deine erste Anlaufstelle.

Für heute reicht es, dass du weißt, warum Holz für unseren Hafen so wichtig ist. Jetzt kennst du auch die Arbeit unserer Schiffszimmerleute!

Deine nächste Reise durch die Versorgung des Hafens kann beginnen – behalte dein Questbuch im Auge, Landratte!“

**Spieler – einzige Antwort:**

„Aye, Schiffszimmermann! Ich werde mir merken, wo es Bauholz gibt!“

**EasyNPC-Aktion:** `Close Dialog`

## In EasyNPC einrichten

1. Mit dem Schiffszimmermann `07f6f682-8be6-4a91-aa99-4c3ec711d25d` den Dialogeditor öffnen.
2. **Nur einen Standarddialog** anlegen, Name `q08_schiffszimmermann`.
3. Obigen Text und den einzelnen Abschlussknopf mit `Close Dialog` einfügen.
4. **Nur die Dialog-ID dieses einen Dialogs** kopieren und weitergeben.
5. Die FTB-Questaufgabe wird erst in unserem abschließenden gemeinsamen Integrationsschritt auf das Öffnen dieses Dialogs gestellt – mit Quest 07 als vorheriger Voraussetzung.
6. Shop-NPC „Holzfäller“ (`b424fb91-290c-422e-9475-970a1fc4ec64`) bleibt unverändert und benötigt keinen Dialog.
7. Kein Scoreboard, keine Abgabe von Holz, keine Shopkontrolle, keine automatisch vergebene Belohnung vor finaler Integration.

**Wichtige Klarstellung:** Der Questtitel lautet „Holz für den Schiffsbau“, aber in dieser vereinfachten Einführungsvariante wird **kein Holz tatsächlich eingesammelt oder abgegeben**. FTB soll erst nach dem Einbau des EasyNPC-Tasks beim **Öffnen** dieses Gesprächs abschließen, nicht erst beim Klick auf den Antwortknopf.
