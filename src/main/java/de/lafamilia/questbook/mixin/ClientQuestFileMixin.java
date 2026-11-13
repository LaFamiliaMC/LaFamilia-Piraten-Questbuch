package de.lafamilia.questbook.mixin;

import de.lafamilia.questbook.client.FtbNativeOpen;
import de.lafamilia.questbook.client.PirateQuestScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * FTB Quests 2101.x / Minecraft 1.21.1: intercept the existing (private) openQuestGui()
 * call. This covers the ordinary FTB Quests quest-book opening route; no P binding.
 * The selected UI never modifies FTB server data or reward claim state.
 * SHIFT while opening the FTB book bypasses our GUI for editors and debugging.
 */
@Mixin(targets = "dev.ftb.mods.ftbquests.client.ClientQuestFile", remap = false)
public abstract class ClientQuestFileMixin {
    @Inject(method = "openQuestGui", at = @At("HEAD"), cancellable = true, remap = false)
    private void laFamilia$openViaFtb(CallbackInfoReturnable<Object> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || Screen.hasShiftDown()) return;
        if (FtbNativeOpen.consumeNativeRequest()) return;
        if (!(client.currentScreen instanceof PirateQuestScreen)) {
            client.setScreen(new PirateQuestScreen());
        }
        // The original FTB screen is not opened; a null return is handled by FTB openGui().
        cir.setReturnValue(null);
    }
}
