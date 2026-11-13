package de.lafamilia.questbook.client;

import net.fabricmc.api.ClientModInitializer;

/** Alpha 0.4: no extra P key. FTB Quests' normal open action opens our pirate UI via a client mixin. */
public final class LaFamiliaQuestbookClient implements ClientModInitializer {
    @Override public void onInitializeClient() {
        // Keeping the FTB Quests key binding, quest book item, and normal client UI trigger.
        // Do not register a second shortcut or duplicate quest progression.
    }
}
