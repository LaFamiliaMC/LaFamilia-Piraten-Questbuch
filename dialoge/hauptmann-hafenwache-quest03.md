# La Familia – Hauptmann der Hafenwache (Quest 03)

**Minecraft:** Fabric 1.21.1 · EasyNPC × FTB Quests  
**NPC-UUID:** `04df3e39-1e9e-468e-8d23-94454743bbce`  
**Quest:** `LF-L01-Q03` – „Die Hafenwache kennenlernen“  
**FTB-Quest-ID:** `FA10010300030000`  
**Questart:** Optionale Nebenquest in Kapitel 1, nach Quest 01 verfügbar  
**Geplante einmalige Belohnung:** 25 Erfahrungspunkte und 4 Fackeln. Kein neuer Rang.  
**Dialog-ID:** Noch nicht vorhanden; nach Erstellung von Dialog B aus EasyNPC kopieren.

## Dialog A – Spieler hat Quest 01 beim Kapitän noch nicht beendet

**Hauptmann:** „Halt! Wer da? Hier wacht die Hafenwache von La Familia. Fremde melden sich zuerst bei unserem Kapitän, bevor sie durch den Hafen ziehen.“

**Spieler:** „Ich wollte mich nur umsehen.“

**Hauptmann:** „Dann tu das mit offenen Augen. Der Kapitän nimmt neue Leute auf. Mit seiner Zustimmung bist du hier willkommen.“

**Ende:** Kein Questabschluss.

## Dialog B – Erstes Gespräch zur optionalen Quest 03

**Hauptmann:** „Ahoy, Landratte! Der Kapitän hat schon von dir erzählt. Ich bin der Hauptmann der Hafenwache. Meine Leute sorgen dafür, dass unser Hafen sicher bleibt.“

**Spieler (Auswahl):** „Wovor beschützt ihr den Hafen?“

**Hauptmann:** „Vor Plünderern, Schmugglern und allem, was unsere Mannschaft bedroht. Wir halten Wache an den Kais, kontrollieren die Zugänge und helfen Seefahrern, die in Not geraten.“

**Spieler (Auswahl):** „Welche Regeln gelten hier?“

**Hauptmann:** „Respektiere deine Kameraden, greife niemanden grundlos an und lass fremde Vorräte unangetastet. Wer Ärger sucht, bekommt es mit meiner Wache zu tun.“

**Spieler (abschließende Auswahl):** „Aye, Hauptmann! Ihr könnt auf mich zählen.“

**Hauptmann:** „Gut gesprochen! Nimm diese Fackeln für deinen Weg durch den Hafen. Eine wachsame Landratte ist mehr wert als zehn schlafende Piraten. Halte die Augen offen und lerne unsere Regeln kennen!“

**Questabschluss:** Nur die **abschließende Gesprächsoption** soll Quest 03 erfüllen; die anderen Fragen können davor ohne Abschluss angeklickt werden. Die FTB-EasyNPC-Task-Konfiguration wird erst am Ende aller NPC-Einrichtungen umgesetzt.

## Dialog C – Nach Abschluss von Quest 03

**Hauptmann:** „Ahoy, Landratte! Gut, dass du dich mit der Hafenwache vertraut gemacht hast. Du hast Fragen zu unserer Sicherheit?“

**Spieler (Auswahl):** „Was macht eure Wache heute?“

**Hauptmann:** „Wir prüfen die Zugänge und halten am Kai Ausschau. Wenn du verdächtige Gestalten entdeckst, gib uns Bescheid.“

**Spieler (Auswahl):** „Wo lerne ich die Hafenregeln?“

**Hauptmann:** „Erkunde die Lobby und sprich mit den Verantwortlichen. Unser Hafen funktioniert nur, wenn alle zusammenhalten.“

**Spieler:** „Aye, weiter auf Wache!“

**Hauptmann:** „Und du pass auf dich auf. Möge der Wind günstig stehen!“

**Ende:** Keine erneute Quest- oder Gegenstandsbelohnung.

## Umsetzung später (wie mit dem Serverbetreiber vereinbart)

1. EasyNPC-NPC-ID `04df3e39-1e9e-468e-8d23-94454743bbce` ist fest.
2. Dialoge A–C im EasyNPC-Editor anlegen; **nur die tatsächliche Dialog-ID von B** für Questabschluss kopieren (sofern die Erweiterung diesen Abschlussdialog registriert).
3. Die automatische Task-Konvertierung aller NPCs erfolgt gesammelt, sobald alle NPCs platziert sind. Danach **jeden NPC separat testen**.
4. Quest-ID `FA10010300030000` beibehalten. Weder bestehenden Händler-Shop noch andere Quest-Dateien überschreiben.
