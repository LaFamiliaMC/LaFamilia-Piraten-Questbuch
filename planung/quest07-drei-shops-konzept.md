# Quest 07 – Die drei Geschäfte kennenlernen (ohne Scoreboard)

**Quest-Plan-ID:** `LF-L02-Q01`  
**FTB-Quest-ID:** `FA10010300070000`  
**Quartiermeister-NPC-UUID:** `10bbc089-6e58-4d92-b3c0-c1cf911f8d74`  
**Voraussetzung:** Quest 02 „Der Hafenmeister“; wird bei der späteren FTB-Integration umgesetzt.

Der Quartiermeister steht **draußen vor der Tür** des Handelshauses. Im Haus stehen drei separate Shops:
- **Fässer & Schiffsvorräte** – `a092c754-26c6-458e-8c42-de3ad3e26c1d`
- **Geschirr & Küchenwaren** – `b9150d76-1221-40eb-9736-867be277fdef`
- **Naturdeko** – vermutlich **Decocraft Nature**, `c9f6d74e-35ba-4083-8688-013d7ecbd161`. Die genaue Zuordnung bestätigt der Serverbetreiber noch.

## Ablauf

1. Quartiermeister erzählt von den drei Shops.
2. Spieler kann sie besuchen, **kein Kauf erforderlich** und **keine Kontrolle, ob er die Shops geöffnet hat**.
3. Bei einer Interaktion mit dem Quartiermeister kann der Spieler über **„Ich bin zurück“** zu einer Rückkehrfrage gelangen.
4. Er bestätigt **„Aye, ich habe mir die drei Geschäfte angesehen!“**.
5. Diese Antwort öffnet einen **separaten Abschlussdialog** `q07_abschluss`. **Nur dessen Dialog-ID** löst später die EasyNPC-FTB-Aufgabe aus, weil die Kompatibilitätsmod bereits beim Öffnen des Zieldialogs auslöst.
6. Nach Abschluss wird Quest 08 „Holz für den Schiffsbau“ freigeschaltet.

## Technisch bewusst einfach

- **Keine Scoreboards**, Befehle, Spieler-Tags oder individuelle Shopbesuchsflags.
- Drei EasyNPC-Dialoge: `q07_start` als Standard, `q07_rueckkehr` und `q07_abschluss` mit **Manual Only (-1)**.
- Button „Ich schaue mich um“ schließt `q07_start`, Button „Ich bin zurück“ öffnet `q07_rueckkehr`; Abschlussbutton öffnet `q07_abschluss`.
- **Bekannte Einschränkung:** Auch beim ersten Gespräch kann ein Spieler direkt „Ich bin zurück“ wählen. Eine echte Unterscheidung zwischen Erst- und Zweitbesuch erfordert einen gespeicherten Status, den wir ausdrücklich nicht nutzen.
- Sobald wir am Ende aller NPC-Erfassungen FTB Quests integrieren, kann ein Dialog nach Questabschluss abhängig vom FTB-Queststatus angezeigt werden. Der Questabschluss und die Belohnung werden pro Spieler über FTB Quests geregelt.
- Shop-NPCs bleiben reine Shops und unverändert.

**Detaillierte Texte und Einrichtung:** [Quartiermeister – Dialoge ohne Scoreboard](../dialoge/quartiermeister-quest07.md).
