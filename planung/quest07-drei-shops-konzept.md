# Kapitel 2 – Quest 07: Die drei Geschäfte des Hafens kennenlernen

**Quest-Plan-ID:** `LF-L02-Q01`  
**FTB-Quest-ID:** `FA10010300070000`  
**Questtitel:** „Beim Quartiermeister melden“  
**Voraussetzung:** Quest 02 „Der Hafenmeister“ (`LF-L01-Q02`).

## Aufbau

Ein **eigener Quartiermeister ohne Shopfunktion** steht draußen vor dem Eingang des Handelshauses. Seine NPC-UUID und die ID des Abschlussdialogs werden später eingetragen.

Im Gebäude befinden sich drei unveränderte, eigenständige Shop-NPCs:

| Geschäft | Aus Händlerregister bekannte NPC-UUID |
|---|---|
| Fässer & Schiffsvorräte | `a092c754-26c6-458e-8c42-de3ad3e26c1d` |
| Geschirr & Küchenwaren | `b9150d76-1221-40eb-9736-867be277fdef` |
| Naturdeko (vermutlich Decocraft Nature; Zuordnung noch bestätigen) | `c9f6d74e-35ba-4083-8688-013d7ecbd161` |

Die genannten Shop-UUIDs dienen nur der Dokumentation, **nicht** zur technischen Kontrolle des Questfortschritts. Der genaue Naturdeko-Shop kann später bestätigt werden.

## Questablauf – bewusst ohne Shop-Kontrolle

1. Nach Quest 02 spricht der Spieler vor dem Handelshaus mit dem Quartiermeister.
2. Der Quartiermeister erklärt die drei Geschäfte und bittet den Spieler, sich drinnen umzusehen.
3. Der Spieler kann die Shops besuchen und die Angebote anschauen. **Kein Einkauf erforderlich.**
4. Anschließend kommt er zum Quartiermeister zurück und wählt **„Aye, ich habe mir die drei Geschäfte angesehen!“**.
5. **Diese Dialogauswahl ist später der alleinige Auslöser zum Abschluss von Quest 07.** Ob der Spieler die drei Shopmenüs tatsächlich geöffnet hat, wird **nicht geprüft**.
6. Quest 07 wird pro Spieler nur einmal belohnt und schaltet danach Quest 08 „Holz für den Schiffsbau“ frei.
7. Nach Abschluss bietet der Quartiermeister einen einfachen Nachgespräch-Dialog an, aber keine weitere Belohnung.

**Wichtig:** Keine Prüfung von Shop-Öffnungen, keine drei Besuchsflags, keine zusätzliche Fabric-Tracking-Mod und keine Änderungen an den bestehenden Shops. Die reine Rückmeldung beim Quartiermeister reicht aus.

## Dialog A – Noch nicht bereit

**Quartiermeister:** „Halt, Fremder! Melde dich zuerst beim Kapitän und dann beim Hafenmeister. Danach zeige ich dir die Versorgung unseres Hafens.“

**Spieler:** „Aye, ich komme wieder.“

**Ende:** Keine Questbelohnung.

## Dialog B – Die drei Läden kennenlernen (Quest 07 wird begonnen)

**Quartiermeister:** „Ahoy, Landratte! Ich bin der Quartiermeister von La Familia. Ohne Vorräte, Geschirr und ein bisschen Ordnung hält kein Schiff lange durch! Gleich hinter dieser Tür liegen drei wichtige Läden. Dort gibt es Fässer und Schiffsvorräte, Geschirr und Küchenwaren sowie Naturdeko für unseren Hafen.“

**Spieler:** „Was soll ich dort tun?“

**Quartiermeister:** „Schau dich in allen drei Geschäften um und merke dir, was die Händler anbieten. Du musst nichts kaufen. Komm anschließend zu mir zurück und erzähl mir von deinem Rundgang!“

**Spieler (Startantwort):** „Aye! Ich sehe mich in den drei Läden um.“

**Ende:** Quest bleibt offen; **noch kein Abschluss**.

## Dialog C – Rückkehr beim Quartiermeister (Questabschluss)

**Quartiermeister:** „Na, Landratte? Hast du unsere drei Geschäfte kennengelernt?“

**Spieler (Abschlussantwort):** „Aye, ich habe mir die drei Geschäfte angesehen!“

**Quartiermeister:** „Sehr gut! Nun weißt du, wo unsere Mannschaft ihre Vorräte, Küchenwaren und Hafenverzierungen findet. Mit dem Handelshaus im Rücken kann die nächste Aufgabe beginnen. Melde dich zum Holztransport für den Schiffsbau!“

**Ende:** Nur diese **Abschlussantwort** soll später den FTB-Questabschluss auslösen. Der tatsächliche Shopbesuch ist keine Voraussetzung.

## Dialog D – Wenn Quest 07 bereits abgeschlossen ist

**Quartiermeister:** „Ahoy, Landratte! Die Läden kennst du nun. Unsere Vorräte warten auf tatkräftige Hände – schau dir deinen nächsten Auftrag im Questbuch an!“

**Spieler:** „Aye, Quartiermeister!“

**Ende:** Keine erneute Questbelohnung.

## Umsetzung später

- **Noch offen:** Eigener Quartiermeister-NPC mit UUID; Abschluss-Dialog-ID von Dialog C.
- Bis alle NPCs registriert sind, keine automatische EasyNPC-FTB-Aufgabe einbauen.
- Bei der späteren Integration wird **nur der Abschlussdialog** als Quest-Trigger genutzt. Keine Shop-Ereignisprüfung implementieren.
- Jeden NPC anschließend einzeln testen, insbesondere dass Quest 07 nicht schon beim ersten Dialog abschließt und nur einmal belohnt wird.
