package net.astoldbylouis.formula38mod.event;

import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.client.ThirstHudOverlay;
import net.astoldbylouis.formula38mod.networking.ModMessages;
import net.astoldbylouis.formula38mod.networking.packet.DrinkWaterC2FPacket;
import net.astoldbylouis.formula38mod.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(
            modid = Formula38Mod.MOD_ID,
            value = Dist.CLIENT)
    public static class ClientForgeEvents {



        @SubscribeEvent
        public static void OnKeyInput(InputEvent.Key event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkWaterC2FPacket());
//                ModMessages.sendToServer(new ExampleC2SPacket());

//                Minecraft.getInstance().player
//                        .sendSystemMessage(Component.literal(
//                                "Pressed a key!"
//                                )
//                        );
            }
        }
    }

    @Mod.EventBusSubscriber(
            modid = Formula38Mod.MOD_ID,
            value = Dist.CLIENT,
            bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }
    }


}
