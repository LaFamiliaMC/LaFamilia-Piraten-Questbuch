# Quartiermeister – Quest 07: Ein einziger EasyNPC-Dialog

**NPC-UUID:** `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**FTB-Quest-ID:** `FA10010300070000`  
**Dialogname:** `q07_quartiermeister`  
**Dialog-ID:** `ffab60c6-f23e-3594-bdef-e94c5c82ba42` (vom Nutzer übermittelt).  
**Status:** Dialog-ID gespeichert; noch kein aktivierter FTB-Abschlusstask.

## Text des einzigen Dialogs

**Quartiermeister:**

Ahoy, Landratte! Willkommen bei der Hafenversorgung von La Familia!

Ich bin der Quartiermeister und sorge dafür, dass unsere Mannschaft stets bestens versorgt ist.

Direkt hinter mir findest du drei wichtige Geschäfte unseres Hafens!

**Fässer und Schiffsvorräte:** Hier bekommst du alles, was ein ordentliches Piratenschiff an Vorräten und Ausrüstung benötigt.

**Geschirr und Küchenwaren:** Hier findest du die passende Ausstattung für unsere Küchen und die Versorgung der Mannschaft.

**Naturdeko:** Unser Hafen soll schließlich nicht wie eine verlassene Räuberhöhle aussehen! Hier findest du allerlei schöne Dinge, um dein Zuhause zu gestalten.

Merk dir diese drei Händler gut, Landratte! Du wirst ihre Dienste auf deinen kommenden Abenteuern sicher brauchen.

Nun bist du mit unserer Hafenversorgung vertraut!

Als Nächstes wartet der Schiffsbau auf dich. Dort wird dringend Holz benötigt!

**Antwort des Spielers:**

Aye, Quartiermeister! Vielen Dank für die Einweisung!

**Aktion:** `Close Dialog`

## In EasyNPC einrichten

1. Quartiermeister im Dialogeditor bearbeiten.
2. **Nur einen** Standarddialog erstellen, am besten mit Label `q07_quartiermeister`.
3. Text aus diesem Dokument einfügen.
4. **Einen** Antwortknopf mit „Aye, Quartiermeister! Vielen Dank für die Einweisung!“ hinzufügen; Aktion `Close Dialog`.
5. Im EasyNPC x FTB Quests Compat Menü **die gespeicherte Dialog-ID `ffab60c6-f23e-3594-bdef-e94c5c82ba42`** beim späteren FTB-Quests-EasyNPC-Task verwenden.

## Wann wird Quest 07 abgeschlossen?

Die genutzte EasyNPC-x-FTB-Quests-Kompatibilität erkennt nach bisherigem Integrationsplan das **Öffnen** des angegebenen Zieldialogs als Taskabschluss. Deshalb wäre Quest 07 künftig **bereits beim Öffnen** des Erklärdialogs erfüllt, auch wenn der Text noch nicht gelesen wurde. Der Knopf beendet nur das Gespräch.

Wir binden die FTB-Aufgabe, die Quest-02-Voraussetzung und die einmalige Belohnung **erst nach Erfassung aller NPCs** ein. Bis dahin ist das hier nur die vorbereitete Dialogvorlage.

**Es gibt keine Scoreboards, keine Shopbesuchprüfung, keinen Kauf, keine Rückkehr und keinen weiteren Dialog.**
