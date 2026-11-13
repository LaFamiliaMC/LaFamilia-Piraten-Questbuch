from pathlib import Path
from PIL import Image
import json
P=Path(__file__).resolve().parents[1]
base=P/'src/main/resources/assets/lafamilia_questbook'
q=json.loads((base/'demo_quests.json').read_text('utf-8'))
assert len(q['categories'])==9, 'Alle neun Kategorien muessen erhalten bleiben'
assert len(q['quests'])==52
assert len(list((base/'textures/icons').rglob('*.png')))==162
assert len({c['id'] for c in q['categories']})==9
for item in q['quests']:
 assert (base/item['icon']).is_file(),item['icon']
 assert not item['icon'].startswith('textures/gui/masked_cards')
 assert item['tasks'] and item['description'] and item['rewards']
 assert item.get('render_reference_card') is False
p=base/'textures/gui/pirate_empty_background.png'
assert p.is_file(), p
with Image.open(p) as img: assert img.size==(1671,941)
assert not (base/'textures/gui/approved_reference_1671x941.png').exists()
assert not (base/'textures/gui/alternate_quest_board.png').exists()
assert not (base/'textures/gui/right_details_blank.png').exists()
client=(P/'src/main/java/de/lafamilia/questbook/client/LaFamiliaQuestbookClient.java').read_text('utf-8')
screen=(P/'src/main/java/de/lafamilia/questbook/client/PirateQuestScreen.java').read_text('utf-8')
assert 'GLFW_KEY_P' not in client and 'KeyBindingHelper' not in client
assert 'approved_reference' not in screen and 'OTHER_BOARD' not in screen and 'DETAIL_BLANK' not in screen
assert 'pirate_empty_background.png' in screen
assert (P/'src/main/resources/lafamilia_questbook.mixins.json').is_file()
assert (P/'src/main/java/de/lafamilia/questbook/mixin/ClientQuestFileMixin.java').is_file()
print('OK: echter leerer Hintergrund, neun Kategorien, 52 dynamische Questkarten, 162 transparente Icons, FTB-Mixin, kein P')
