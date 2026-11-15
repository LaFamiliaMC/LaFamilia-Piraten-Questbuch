# La Familia – Hafenmeister (Quest 02)

**Minecraft:** Fabric 1.21.1 · EasyNPC × FTB Quests  
**NPC-UUID:** `aaa51318-0fd4-47af-baca-8f8c4d727499`  
**Quest:** `LF-L01-Q02` – „Der Hafenmeister“  
**FTB-Quest-ID:** `FA10010300020000`  
**Abhängigkeit:** Quest 01 „Ein neuer Seefahrer“, Kapitän-Dialog abgeschlossen  
**Dialog-IDs:** Noch nicht zugewiesen; nach Anlage der Dialoge aus EasyNPC kopieren und in NPC-Register eintragen.

## Dialog A – Vor der Kapitänsquest (Quest 01 nicht abgeschlossen)

**Hafenmeister:** „Halt, Fremder! Ohne Anmeldung beim Kapitän kommt hier niemand ins Hafenbuch. Geh zuerst zu ihm – er entscheidet, ob du zur Mannschaft von La Familia gehörst.“

**Spieler – Antwort:** „Aye, ich suche den Kapitän!“

**Hafenmeister:** „Dann halte die Augen offen. Ein echter Pirat findet seinen Weg durch jeden Hafen.“

**Ende:** Nur Hinweis, **kein Questabschluss**, keine Belohnung.

## Dialog B – Die Aufnahme ins Hafenbuch (Quest 01 abgeschlossen, Quest 02 offen)

**Hafenmeister:** „Ahoy! Du musst die neue Landratte sein, von der der Kapitän gesprochen hat. Willkommen am Hafen von La Familia! Ich bin der Hafenmeister und führe das Hafenbuch.“

**Spieler – Antwort 1:** „Der Kapitän schickt mich. Was muss ich tun?“

**Hafenmeister:** „Zuerst bekommst du einen ordentlichen Eintrag ins Hafenbuch. Danach lernst du die Leute kennen, die unseren Hafen am Laufen halten: Händler, Handwerker und die Mannschaft.“

**Spieler – Antwort 2 (optional):** „Was kann ich hier alles entdecken?“

**Hafenmeister:** „Am Kai findest du Schiffsvorräte und Fässer. Weiter drüben warten die Holzfäller, Handwerker und die Hafenküche. Schau dir die Hafenwache, unser großes Schiff und die Hafenglocke an. Wer sich hier auskennt, wird eines Tages auf große Fahrt gehen!“

**Spieler – Antwort 3:** „Trag mich ins Hafenbuch ein!“

**Hafenmeister:** „So sei es! Dein Name steht nun im Hafenbuch, Landratte. Such als Nächstes den Quartiermeister bei den Schiffsvorräten auf. Er hat den ersten Versorgungsauftrag für dich. Gute Fahrt – und immer eine Handbreit Wasser unter dem Kiel!“

**Ende/Questabschluss:** Quest 02 „Der Hafenmeister“ nach dem abschließenden Gespräch beenden. In der EasyNPC-FTB-Quests-Anbindung den tatsächlich aus EasyNPC kopierten Dialogbezeichner für diesen Abschluss verwenden. **Nicht** eine erfundene ID wie `dialog_02` eintragen. Die Aufgabenart im älteren Testbuild ist noch eine manuelle Checkmark-Aufgabe; automatische Auslösung erfordert die korrekte Task-Konfiguration.

**Nächste Hauptquest:** Kapitel 2, „Beim Quartiermeister melden“ (Shop-/Stationszuordnung „Fässer & Schiffsvorräte“). Noch keine automatische Itemübergabe behaupten, bis sie implementiert und getestet wurde.

## Dialog C – Nach Abschluss der Hafenmeisterquest

**Hafenmeister:** „Ahoy, Landratte! Dein Name steht bereits in meinem Hafenbuch. Der Quartiermeister wartet bei den Schiffsvorräten auf dich. Hast du dich schon am Kai umgesehen?“

**Spieler – Antwort 1:** „Wo finde ich den Quartiermeister?“

**Hafenmeister:** „Folge dem Kai bis zu den Fässern und Vorratskisten. Dort werden die Lieferungen für unsere Schiffe verwaltet.“

**Spieler – Antwort 2:** „Was sollte ich noch kennenlernen?“

**Hafenmeister:** „Sieh dir die Hafenwache, die Regeln von La Familia, unser Schiff und die Hafenglocke an. Die Nebenaufträge helfen dir dabei.“

**Spieler – Antwort 3:** „Aye, danke!“

**Hafenmeister:** „Möge dir der Wind stets günstig stehen!“

**Ende:** Kein erneuter Questabschluss und keine wiederholte Belohnung.

## Einstellungen / offen

- Dialog A anzeigen, solange Quest 01 **nicht** abgeschlossen ist.
- Dialog B anbieten, sobald Quest 01 abgeschlossen, Quest 02 aber **nicht** abgeschlossen ist.
- Dialog C anzeigen, sobald Quest 02 abgeschlossen ist.
- Zustandsabhängige Zuordnung erst in der tatsächlich installierten EasyNPC-Kompatibilitätsmod prüfen, sonst im Editor manuell verschalten.
- Dialog B darf die Quest nur **einmalig** abschließen.
- NPC **UUID**: `aaa51318-0fd4-47af-baca-8f8c4d727499`. FTB **Quest-ID**: `FA10010300020000`. **Echte EasyNPC Dialog-ID(s) fehlen noch.**
- Kein Shop überschreiben, keine globalen Questdaten zurücksetzen.
