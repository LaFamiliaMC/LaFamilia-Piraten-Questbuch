from pathlib import Path
from PIL import Image
import json,hashlib
P=Path(__file__).resolve().parents[1]
base=P/'src/main/resources/assets/lafamilia_questbook'
d=json.loads((base/'demo_quests.json').read_text('utf-8'))
assert len(d['categories'])==9, 'Need exactly nine approved categories'
assert len(d['quests'])==52, 'Unexpected demo quest count'
ids=set()
for q in d['quests']:
 assert q['id'] not in ids, q['id'];ids.add(q['id'])
 assert q['category'] in {c['id'] for c in d['categories']}
 assert (base/q['icon']).is_file(), q['icon']
 assert q['rect']['w']>0 and q['rect']['h']>0
 assert q['description'] and q['tasks'] and q['rewards']
assert len(list((base/'textures/icons').rglob('*.png')))==162
pic=base/'textures/gui/approved_reference_1671x941.png'
with Image.open(pic) as image:assert image.size==(1671,941)
for f in ['right_details_blank.png','alternate_quest_board.png','quest_frame_empty.png','portrait_tharion.png']:
 assert (base/'textures/gui'/f).is_file(), f
for f in ['VORSCHAU_Hauptgeschichte.png','VORSCHAU_Landwirtschaft.png']:
 assert (P/'preview'/f).is_file(), f
assert (P/'.github/workflows/build-questbook.yml').is_file()
print('OK: 9 categories, 52 sample quests, 162 icons, existing textures, resource paths, previews and CI workflow')
print('Approved background SHA256:',hashlib.sha256(pic.read_bytes()).hexdigest())
