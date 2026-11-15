# La Familia – Hüter des Hafenkodex (Quest 04)

**NPC:** Hüter des Hafenkodex  
**NPC-UUID:** `0ddeb1e3-8414-4a10-ac46-22263e28bc26`  
**FTB-Quest:** `FA10010300040000` / `LF-L01-Q04` – Die Regeln von La Familia  
**Questart:** optionale Lobby-Nebenquest, nach Quest 01 verfügbar  
**Abschluss-Dialog-ID:** Noch nicht bekannt. Nach Anlage von Dialog B aus EasyNPC kopieren.  
**Geplante einmalige Belohnung:** 25 XP und 1 Buch (ohne neue Rangstufe).  
**Status:** Dialogvorlage; kein aktiver EasyNPC-FTB-Task.

> Die unten erwähnten Grundsätze sind **Regelvorschläge für das Rollenspiel**, keine verifizierte offizielle Serverordnung. Vor Veröffentlichung mit den tatsächlichen La Familia Serverregeln abgleichen. Die vollständigen echten Regeln sollten zusätzlich im Server-Regelbuch oder einer verlinkten Quelle stehen.

## Dialog A – Vor der Kapitänsquest

**Hüter:** „Halt, Reisender! Vor dir liegt der Hafenkodex von La Familia. Doch erst wenn der Kapitän dich in unsere Mannschaft aufgenommen hat, kannst du hier deinen Eid ablegen.“

**Spieler:** „Dann suche ich zuerst den Kapitän.“

**Hüter:** „So sei es. Merke dir: Eine Mannschaft braucht nicht nur Mut, sondern auch Regeln, auf die sich alle verlassen können.“

**Ergebnis:** Kein Questabschluss, keine Belohnung.

## Dialog B – Die Regeln von La Familia (Quest 04 offen)

**Hüter:** „Ahoy, Landratte! Willkommen bei den Hütern des Hafenkodex. Wer unter der Flagge von La Familia segelt, trägt Verantwortung – für sich und für die gesamte Mannschaft.“

**Spieler (Frage 1):** „Was steht im Hafenkodex?“

**Hüter:** „Unsere wichtigsten Grundsätze sind Respekt vor den Mitspielern, Fairness und der Schutz fremder Bauten und Vorräte. Niemand soll seine Kameraden absichtlich bestehlen, belästigen oder deren Arbeit zerstören. Die verbindlichen Einzelheiten stehen in der offiziellen Serverordnung.“

**Spieler (Frage 2):** „Was ist mit Tricks und unfairen Vorteilen?“

**Hüter:** „Spiele fair und melde Fehler, statt sie zum Schaden anderer auszunutzen. Wenn du unsicher bist, frage die Mannschaft oder das Serverteam – so vermeiden wir Streit am Kai.“

**Spieler (Frage 3):** „Und wenn ich etwas beobachte, das gegen die Regeln verstößt?“

**Hüter:** „Bleib ruhig, löse keinen neuen Streit aus und wende dich mit den nötigen Informationen an das Serverteam. Ein guter Pirat beweist Stärke durch Verantwortung.“

**Spieler (Abschlussoption):** „Ich habe den Hafenkodex verstanden und werde die geltenden Serverregeln beachten!“

**Hüter:** „Gut gesprochen, Landratte! Ich vermerke dein Versprechen. Hier ist ein Buch für deine Reisen. Mögen deine Taten deinem Wort Ehre machen!“

**Ergebnis:** Nur **diese Abschlussantwort** als EasyNPC-Dialogauslöser für Quest 04 vorsehen, wenn wir später die automatische Verknüpfung einrichten. Ein bloßes Öffnen des NPC-Menüs darf die Quest nicht abschließen.

## Dialog C – Nach Abschluss von Quest 04

**Hüter:** „Ahoy, Landratte! Dein Versprechen ist bereits im Hafenkodex vermerkt. Hast du Fragen zu unseren Regeln?“

**Spieler (Option 1):** „Wo kann ich die vollständigen Regeln nachlesen?“

**Hüter:** „Im offiziellen Regelbuch oder dort, wo unser Serverteam die aktuellen Regeln veröffentlicht. Halte dich immer an diese Fassung.“

**Spieler (Option 2):** „Was ist das Wichtigste?“

**Hüter:** „Respektiere deine Mitspieler, handle fair und bewahre das Vertrauen deiner Mannschaft.“

**Spieler:** „Aye, ich werde daran denken!“

**Hüter:** „Guter Wind und sichere Fahrt!“

**Ergebnis:** Kein erneuter Questabschluss und keine Wiederholung der Belohnung.

## Integration (bewusst für später zurückgestellt)

1. Beim Hüter-NPC mit UUID `0ddeb1e3-8414-4a10-ac46-22263e28bc26` die Dialoge A–C im EasyNPC-Editor anlegen.
2. Die **wirkliche Dialog-ID des Abschlussdialogs B** nachreichen; sie ist nicht identisch mit der NPC-UUID.
3. Erst nachdem **alle** Lobby-NPCs angelegt wurden, Quest `FA10010300040000` im FTB-Quests-Editor von Checkmark auf „EasyNPC Task“ umstellen, NPC-UUID und reale Abschluss-Dialog-ID eintragen.
4. Anschließend jeden NPC einzeln testen; Originalshops und andere Quests nicht überschreiben.
