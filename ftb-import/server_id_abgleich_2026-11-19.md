# La Familia – Abgleich mit dem tatsächlich laufenden Questbuch (19.11.2026)

**Quelle:** Die vom Nutzer hochgeladene `quests(1).zip`, nicht eine ältere GitHub-Testvorlage. Prüfstatus: **SNBT offline geprüft, noch kein Testserverstart**.

**Aktuelle Datei:** `quests/chapters/lafamilia_willkommen_16.snbt` • **Kapitel-ID:** `62DAD70B515151F8` • **16 Quest-IDs beibehalten** • **15 direkte Abhängigkeiten ergänzt** • **keine neuen FTB-IDs generiert**.

| Nr. | Quest | Reale FTB-Quest-ID | Reale FTB-Task-ID | NPC-UUID | EasyNPC-Dialog-ID | Voraussetzung |
|---:|---|---|---|---|---|---|
| 01 | Ein neuer Seefahrer | `42DA15436861A1E5` | `2BF4A6C03655EFED` | `8c658830-313e-4f63-8dc2-f7ff4a51b669` | `addaae3c-bd31-37a4-8e70-188d7680f404` | – |
| 02 | Der Hafenmeister | `14264C52C89FAC7F` | `6077A26056AA27BD` | `aaa51318-0fd4-47af-baca-8f8c4d727499` | `09301480-fd54-3179-bb8b-c2603fe58c6e` | Quest 1 |
| 03 | Die Hafenwache kennenlernen | `4D0955976DE3274F` | `6F5A09B0D6825705` | `04df3e39-1e9e-468e-8d23-94454743bbce` | `666df7d2-8638-37b7-a4d9-efd806c8eceb` | Quest 2 |
| 04 | Die Regeln von La Familia | `59F6F019A4F3B5AD` | `783FFD11429F1CFD` | `0ddeb1e3-8414-4a10-ac46-22263e28bc26` | `23b56d3e-5da3-338a-b351-07b485ef88fe` | Quest 3 |
| 05 | Ein Blick auf das große Schiff | `22B67C91451AACDE` | `7B1D56BA697EE38A` | `f5e8ef08-d548-47c2-88c0-d473843b9c65` | `fc757df3-d218-3b94-892e-29f55513e646` | Quest 4 |
| 06 | Die Glocke des Hafens | `05D52C297CEFA5EE` | `67688A4092D408BB` | `f410950f-02e6-4731-a239-3dc06c486e61` | `5087c3db-a43a-35b5-b26e-0108686bbfb4` | Quest 5 |
| 07 | Beim Quartiermeister melden | `5B1FD863DBED2183` | `77F9F4188906B84E` | `10bbc089-6e58-4d92-b3c0-c1cf911f8d74` | `ffab60c6-f23e-3594-bdef-e94c5c82ba42` | Quest 6 |
| 08 | Holz für den Schiffsbau | `772F8BC48D5406F8` | `454A66EF97778AD7` | `07f6f682-8be6-4a91-aa99-4c3ec711d25d` | `325abe87-2b95-35ab-8910-924352962719` | Quest 7 |
| 09 | Fässer und Kisten | `6CD8D9B156851E29` | `28DD55CE9B7F52E3` | `da5f8bc7-e0d5-48ed-9a1a-91a582458327` | `98258fb6-9e43-371a-a51c-433c0248c504` | Quest 8 |
| 10 | Fehlende Vorräte | `796F63B8AB645A01` | `32A97E5D730EDF3B` | `af4c5c5e-aa26-453b-8962-b740932a8143` | `28fe37f8-7908-328a-811c-16d363f92f03` | Quest 9 |
| 11 | Ein Bett für die Nacht | `24A66749F68586C2` | `1685ED9A7244BB9E` | `a20fafc8-1146-4855-a55d-be29bad42ef1` | `f7ad98f2-9ca4-379f-aa04-94854ef04bc2` | Quest 10 |
| 12 | Licht für den Hafen | `492E688642216F18` | `35EE7B3A6691195D` | `cf2887c7-a332-4bf0-8ebb-03ef60bcc486` | `c636022c-fb3e-3b71-b5c1-d4d0be8eda9a` | Quest 11 |
| 13 | Das Auktionshaus | `3E14B252EB9609D5` | `5F41E5072C9E4A70` | `b0269d82-742e-422b-b730-dbffa19e423c` | `7edc2203-9e3c-3d8c-9011-055ccb05b05c` | Quest 12 |
| 14 | Das Tor zum Nether | `1C501D72E01931D9` | `39AEDCAB10F37DD4` | `f13b074e-ef9b-4710-a1ed-51c1b1260330` | `55daa677-e99b-3859-932c-7b3ed2853f97` | Quest 13 |
| 15 | Das Tor zum End | `7C68D411714E5935` | `6BCCE4D45E4C1173` | `c23edf20-1e79-4a98-8680-b0417f8e4f26` | `1ecb05cb-dbcd-340d-a4ae-18e662d7b12d` | Quest 14 |
| 16 | Leinen los – Aufbruch in die Spielwelt | `1775F03AFDAAC8F9` | `77FB8C29F3A51A02` | `b1e62449-ff6f-40ad-8aca-eeeb3a2d1346` | `2a381170-c4b7-3a0f-be8a-875991921e9a` | Quest 15 |

## Prüf- und Reparaturergebnis

- Die Aufgaben in der tatsächlich hochgeladenen Questdatei haben alle bereits den Typ `easynpc_ftbq_compat:easy_npc_task` und die korrekten `dialogId`/`npcUuid`-Werte aus diesem Repository.
- Die unter `quests/lang/de_de.snbt` und `en_us.snbt` vorhandenen alten `FA100103...`-Schlüssel gehörten zu *anderen* Quest-IDs. Sie wurden in der Chat-Reparatur auf die obigen realen IDs übertragen, inklusive Titel, Untertitel, Questbeschreibung und Aufgabentitel.
- Zusätzlich 988 gültige Spracheinträge für andere derzeit aktive Quests aus der vorhandenen `en_us.snbt` in `de_de.snbt` ergänzt, weil die deutsche Datei keine passenden aktiven Quest-IDs enthielt. Keine bestehenden Translationen anderer IDs überschrieben.
- Die zwei *vollständig leeren* Dateien `quests/chapters/3dac008a3c00af21.snbt` und `4fd0e4a4f8f25316.snbt` sowie die im Kapitelordner falsch platzierte `chapter_groups.snbt` wurden aus der neuen aktiven ZIP entfernt und separat gesichert.
- Die tatsächlich gültige `quests/chapter_groups.snbt` und sämtliche übrigen 23 spielbaren Kapitel, das Questbuch-`data.snbt`, alle sonstigen Quests, NPC-Zuordnungen und Belohnungen blieben unverändert.
- **Nicht enthalten:** Ein funktionierender Welt-Teleport zu einer bislang unbestätigten Welt und die nicht implementierten Rang-Stage-Belohnungen `Landratte`/`Deckjunge`. Diese Angaben stehen nur als geplante Spielmechanik im Text.

**Nicht das alte GitHub-Importkapitel anstelle des reparierten ZIPs verwenden.** Das ZIP ist die einzige vollständig in die aktuelle Originalstruktur eingepasste Fassung. Weitere Reload-Probleme erfordern den tatsächlichen Serverlog.
