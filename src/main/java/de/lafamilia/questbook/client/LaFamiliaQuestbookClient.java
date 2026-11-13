package de.lafamilia.questbook.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/** Step 2: local GUI prototype. No server actions and no FTB progress writes. */
public final class LaFamiliaQuestbookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding openQuestbook = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.lafamilia_questbook.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P,
            "category.lafamilia_questbook"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openQuestbook.wasPressed()) {
                if (client.currentScreen instanceof PirateQuestScreen) {
                    client.setScreen(null);
                } else if (client.player != null) {
                    client.setScreen(new PirateQuestScreen());
                }
            }
        });
    }
}
