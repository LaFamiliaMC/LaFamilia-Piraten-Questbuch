# La Familia – Proviantmeister: Quest 10 „Fehlende Vorräte“

**NPC:** Proviantmeister (eigener EasyNPC ohne Shopfunktion)  
**NPC-UUID:** `af4c5c5e-aa26-453b-8962-b740932a8143`  
**Quest-ID:** `LF-L02-Q04` / FTB `FA10010300100000`  
**Questtyp:** Optionale Nebenquest  
**Voraussetzung:** Quest 07 „Beim Quartiermeister melden“  
**Dialogname:** `q10_proviantmeister`  
**Dialog-ID:** `28fe37f8-7908-328a-811c-16d363f92f03` (vom Nutzer mitgeteilt)  
**Status:** NPC und Dialogvorlage gespeichert; keine aktivierte FTB-Questverknüpfung.

## Einziger EasyNPC-Dialog

**Proviantmeister:**

Ahoy, Landratte! Komm näher, ich habe etwas Wichtiges zu erzählen!

Ich bin der Proviantmeister von La Familia und sorge dafür, dass unsere Seeleute niemals mit leerem Magen in See stechen müssen!

Eine hungrige Mannschaft ist eine schlechte Mannschaft, und ein Schiff ohne Vorräte kommt nicht weit!

Deshalb kaufen wir hier am Hafen Lebensmittel und Proviant von fleißigen Abenteurern wie dir an.

Ob Fisch, Fleisch, Brot, Gemüse oder andere Lebensmittel – unsere Seeleute brauchen ständig Nachschub!

Hast du auf deinen Reisen überschüssige Lebensmittel gesammelt, kannst du sie bei unserem Proviantankäufer gegen Münzen verkaufen.

Aber merk dir eines, Landratte: **Hier wird nur angekauft! Unser Händler verkauft keine Waren.**

Alles, was wir ankaufen, landet in den Vorratskammern und versorgt unsere Mannschaft auf ihren langen Reisen über die sieben Meere.

Nun weißt du, wo du deine überschüssigen Lebensmittel zu Münzen machen und gleichzeitig unsere Seeleute unterstützen kannst!

Gut gemacht, Landratte! La Familia dankt dir für deine Unterstützung!

**Spieler – einzige Antwort:**

Aye, Proviantmeister! Ich werde meine überschüssigen Lebensmittel bei euch verkaufen!

**EasyNPC-Aktion:** `Close Dialog`

**Quest 10:** Wird erst nach der späteren EasyNPC-FTB-Verknüpfung beim Öffnen dieses Dialogs einmalig abgeschlossen. Für die Quest muss nichts verkauft werden.

## EasyNPC-Einrichtung

1. Den Proviantmeister-NPC `af4c5c5e-aa26-453b-8962-b740932a8143` im Dialogeditor öffnen.
2. Einen Standarddialog `q10_proviantmeister` mit dem obigen Text und genau einem Antwortknopf anlegen.
3. Als Antwortaktion `Close Dialog` wählen.
4. Die Dialog-ID `28fe37f8-7908-328a-811c-16d363f92f03` ist gespeichert und wird später bei der FTB-EasyNPC-Anbindung genutzt.
5. Die automatische FTB-Quest-Verknüpfung erst nach vollständiger Erfassung aller Quest-NPCs einrichten.

## Abgrenzung Händler / NPC

Der Proviantmeister ist der **separate Quest-Dialog-NPC**. Die Aussage „Hier wird nur angekauft“ beschreibt den vom Nutzer gewünschten **Proviantankäufer**. Sie richtet dessen tatsächliche Handelsangebote nicht automatisch ein und ändert keine bestehenden Shop-NPCs. Ob der bislang referenzierte Shop „Fässer & Schiffsvorräte“ (`a092c754-26c6-458e-8c42-de3ad3e26c1d`) oder ein anderer Händler für den Proviantankauf zuständig ist, wird hier nicht stillschweigend festgelegt.

**Keine Scoreboards, keine Shopprüfung, kein erzwungener Verkauf und keine Gegenstandsabgabe für die Quest.** Der aktuelle FTB-Testbuild nutzt noch die Checkmark-Aufgabe.
