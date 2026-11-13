#!/usr/bin/env python3
"""Guard rails for mapping GUI sample cards to real FTB Quests IDs."""
import json, re
from collections import Counter
from pathlib import Path
p=Path(__file__).resolve().parents[1]/'src/main/resources/assets/lafamilia_questbook/demo_quests.json'
d=json.loads(p.read_text(encoding='utf-8'))
ids=[]
for q in d['quests']:
    val=q.get('ftb_quest_id', '')
    if not isinstance(val,str) or (val and not re.fullmatch(r'[a-fA-F0-9]{1,16}',val)):
        raise SystemExit('Invalid FTB ID in GUI quest '+q['id'])
    if val:ids.append(val.lower())
repeats=[i for i,c in Counter(ids).items() if c>1]
if repeats:raise SystemExit('Same FTB ID mapped to multiple different cards: '+', '.join(repeats))
print(f'FTB mappings valid: {len(ids)} real IDs mapped, {len(d["quests"])-len(ids)} still unmapped (correct demo mode).')
