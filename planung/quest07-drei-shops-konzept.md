# Kapitel 2 – Quest 07: Drei Läden im Hafen entdecken

**Quest-ID:** `LF-L02-Q01` / FTB `FA10010300070000`  
**Offizieller Questtitel:** „Beim Quartiermeister melden“  
**Ziel:** Im Ladenhaus des Hafens drei bestehende Shops öffnen, damit sich neue Spieler mit deren Sortiment vertraut machen.  
**Voraussetzung:** Quest 02 „Der Hafenmeister“ (`LF-L01-Q02`).

## Aufbau der Lobby

- **Neuer Quest-NPC „Quartiermeister“**: außerhalb des Gebäudes unmittelbar neben dem Eingang. Seine NPC-UUID ist noch offen.
- **Innerhalb des Gebäudes**: drei *vorhandene* reine Shop-NPCs, die beim Anklicken sofort ihr Verkaufsmenü öffnen. Keine Questdialoge an diesen Shop-NPCs und keine Shopänderungen.
- Vorhandene Shop-UUIDs aus dem Händlerverzeichnis (bis zur späteren Zuordnungsprüfung):
  1. **Fässer & Schiffsvorräte** – `a092c754-26c6-458e-8c42-de3ad3e26c1d`
  2. **Geschirr & Küchenwaren** – `b9150d76-1221-40eb-9736-867be277fdef`
  3. **Naturdeko** – vermutlich „Decocraft Nature“, `c9f6d74e-35ba-4083-8688-013d7ecbd161`. **Diese Zuordnung ist noch durch den Nutzer zu bestätigen.** Keinen anderen Natur- oder Pflanzenhändler stillschweigend einsetzen.

## Gewünschter Questablauf

1. Spieler erledigt Quest 02 und kann Quest 07 beginnen.
2. Spieler spricht draußen den Quartiermeister an.
3. Quartiermeister weist ihn an, im Haus alle drei Shops **je einmal zu öffnen**; **kein Kauf erforderlich**.
4. Spieler öffnet die Shop-GUIs von Fässern, Geschirr und Naturdeko in beliebiger Reihenfolge.
5. **Zielverhalten**: Nach dem dritten *unterschiedlichen* geöffneten Shop wird Quest 07 automatisch einmalig abgeschlossen, unabhängig von der Reihenfolge. Das Freischalten der nächsten Hauptquest „Holz für den Schiffsbau“ (Quest 08) folgt aus der bestehenden Quest-Abhängigkeit.
6. Wiederholtes Öffnen desselben Shops zählt nicht dreifach. Es darf keine Endlosschleife oder mehrfachen Belohnungen geben.
7. Rückkehr zum Quartiermeister ist im Idealablauf nicht erforderlich; nach Abschluss bietet er nur Nachgespräche an.

## Textentwurf für den Quartiermeister

**Noch nicht freigeschaltet:** „Halt, Fremder! Sprich zuerst mit dem Kapitän und unserem Hafenmeister. Danach zeige ich dir, was eine Mannschaft vor jeder Seereise benötigt.“

**Queststart:** „Ahoy, Landratte! Ich bin der Quartiermeister von La Familia. Ohne Vorräte bleibt selbst das stolzeste Schiff am Kai liegen. Hinter dieser Tür findest du drei Händler. Schau dich beim Fass- und Schiffsvorratshändler, beim Geschirr- und Küchenwarenhändler und bei unserem Naturdeko-Händler um. Öffne jedes Handelsmenü einmal und merk dir, was die Läden anbieten. Kaufen musst du nichts!“

**Spieler:** „Aye, ich sehe mir alle drei Shops an!“

**Während Quest offen:** „Na, Landratte? Schau dir drinnen wirklich alle drei Sortimente an. Ein guter Pirat kennt seine Versorgungswege.“

**Nach Questabschluss:** „Sehr gut! Jetzt weißt du, wo du Schiffsvorräte, Küchenwaren und passende Hafendekoration findest. Melde dich als Nächstes zum Holztransport für den Schiffsbau!“

## Technische Prüfung offen

- **Wichtig:** Das bloße Öffnen eines Shop-Menüs wird durch einen normalen FTB-Quests-Dialogtask nicht nachweislich als eigener Besuch erfasst.
- Die konkrete installierte Shop-Mod muss geprüft werden: Gibt es ein verlässliches serverseitiges Interaktions-/Menü-Event, anhand dessen **die jeweilige NPC-UUID** zugeordnet werden kann?
- Falls ja, im *späteren gemeinsamen Integrationsschritt* eine serverseitige Fabric-Brücke oder unterstützte Quest-Task-Anbindung bauen, die pro Spieler drei Flags speichert und danach den Questabschluss auslöst.
- Falls das Event ohne invasiven Mod-Eingriff nicht zuverlässig erfassbar ist, Alternativlösung mit ehrlichem Rückkehr-Dialog beim Quartiermeister anbieten – **nicht** fälschlich behaupten, Shopbesuche seien automatisch kontrolliert.
- Bis alle NPCs samt Dialog-IDs erfasst sind, **keine** automatische Verbindung, kein bestehendes Shop-GUI ändern, keine Quest-Test-ZIP überschreiben.
