# La Familia – Hafenglockenwächter (Quest 06)

**NPC:** Hafenglockenwächter  
**NPC-UUID:** `f410950f-02e6-4731-a239-3dc06c486e61`  
**Quest:** `LF-L01-Q06` / `FA10010300060000` – Die Glocke des Hafens  
**Questart:** optionale Nebenquest in Kapitel 1, freigeschaltet nach Quest 01.  
**Abschluss-Dialog-ID (Dialog B):** `5087c3db-a43a-35b5-b26e-0108686bbfb4` (vom Nutzer geliefert).  
**Geplante einmalige Belohnung:** 25 Erfahrungspunkte und 1 Uhr (`minecraft:clock`).  
**Status:** NPC-UUID und Abschluss-Dialog-ID registriert; die automatische FTB-Quests-Aufgabe wird erst nach Erfassung aller NPCs eingerichtet.

## Dialog A – Vor Quest 01 beim Kapitän

**Glockenwächter:** „Halt, Fremder! Hörst du das Echo der Hafenglocke? Sie ruft unsere Mannschaft zusammen, wenn es Zeit zum Aufbruch ist – oder wenn Gefahr droht. Aber bevor ich dir unser Zeichen erkläre, lass dich vom Kapitän in La Familia aufnehmen.“

**Spieler:** „Aye! Ich melde mich zuerst beim Kapitän.“

**Glockenwächter:** „Gut so! Wer zu unserer Mannschaft gehören will, muss zuerst ihren Kapitän kennen.“

**Ende:** Kein Questabschluss, keine Belohnung.

## Dialog B – Die Glocke des Hafens (Quest 06)

**Glockenwächter:** „Ahoy, Landratte! Du hast dir schon deinen ersten Rang verdient. Ich bin der Wächter der Hafenglocke und hüte eines der wichtigsten Zeichen unserer Mannschaft.“

**Spieler (Antwort 1):** „Wofür läutet ihr diese Glocke?“

**Glockenwächter:** „Ein Ruf bedeutet: Augen auf und aufmerksam sein. Mehrere schnelle Schläge warnen vor Gefahr. Und wenn der Hafen zum Aufbruch gerufen wird, versammelt sich die Mannschaft am Kai. Das sind unsere Seefahrergeschichten – die Glocke selbst ist hier vor allem ein Zeichen des Zusammenhalts.“

**Spieler (Antwort 2):** „Darf ich sie auch läuten?“

**Glockenwächter:** „Wenn der Hafen dir das erlaubt, dann mit Bedacht! Ein falscher Alarm lässt die ganze Mannschaft aufhorchen. Ein Pirat soll Verantwortung zeigen und keine Panik stiften.“

**Spieler (Antwort 3):** „Was muss ich mir merken?“

**Glockenwächter:** „Achte auf die Zeichen unserer Mannschaft. Hilf anderen, wenn sie dich brauchen, und sei rechtzeitig zur Stelle, wenn der Ruf zum Aufbruch kommt.“

**Spieler (Abschlussantwort):** „Aye! Ich werde auf den Ruf der Hafenglocke achten!“

**Glockenwächter:** „Gut gesprochen! Nimm diese Uhr – damit du keinen wichtigen Aufbruch verpasst. Die Glocke und die Zeit erinnern uns beide daran: Kein Schiff fährt ohne seine Mannschaft. Willkommen im Hafen, Landratte!“

**Ende:** Nur die Abschlussantwort in Dialog B soll später Quest 06 auslösen. **Keine automatische Glockenmechanik, kein serverseitiges Läuten oder Alarm wird hier technisch behauptet oder eingerichtet.**

## Dialog C – Nach Questabschluss

**Glockenwächter:** „Ahoy, Landratte! Weißt du noch, warum die Hafenglocke für uns wichtig ist?“

**Spieler (Antwort 1):** „Sie ruft die Mannschaft zusammen.“

**Glockenwächter:** „Genau! Eine Mannschaft, die aufeinander hört, kommt auch durch schwere Stürme.“

**Spieler (Antwort 2):** „Wie erkenne ich Gefahr?“

**Glockenwächter:** „Bleib wachsam, achte auf Warnungen und melde Probleme der Hafenwache. Bei echter Gefahr hilft man seinen Kameraden.“

**Spieler:** „Aye, die Wache geht weiter!“

**Glockenwächter:** „Gute Winde und pünktliche Rückkehr!“

**Ende:** Keine wiederholte Questbelohnung.

## Technische Nacharbeit (gesammelt am Schluss)

1. NPC `f410950f-02e6-4731-a239-3dc06c486e61` ist fest mit Quest `FA10010300060000` registriert.
2. Abschluss-Dialog-ID von Dialog B ist bereits `5087c3db-a43a-35b5-b26e-0108686bbfb4`; Dialog A und C dürfen keinen Questabschluss auslösen.
3. Kein Umstellen der aktuellen Checkmark-Aufgabe, bevor **alle NPCs** eingerichtet sind.
4. Am Schluss NPC-UUID und Dialog-ID in die EasyNPC-FTB-Aufgabe setzen, Quest-ID erhalten, Voraussetzungen sowie einmalige Belohnung pro Spieler einzeln testen.
