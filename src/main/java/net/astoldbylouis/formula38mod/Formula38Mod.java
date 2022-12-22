package net.astoldbylouis.formula38mod;

import net.astoldbylouis.formula38mod.block.ModBlocks;
import net.astoldbylouis.formula38mod.block.entity.ModBlockEntities;
import net.astoldbylouis.formula38mod.fluid.ModFluidTypes;
import net.astoldbylouis.formula38mod.fluid.ModFluids;
import net.astoldbylouis.formula38mod.item.ModItems;
import net.astoldbylouis.formula38mod.networking.ModMessages;
import net.astoldbylouis.formula38mod.painting.ModPaintings;
import net.astoldbylouis.formula38mod.villager.ModVillagers;
import net.astoldbylouis.formula38mod.world.features.ModConfigureFeatures;
import net.astoldbylouis.formula38mod.world.features.ModPlacedFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Formula38Mod.MOD_ID)
public class Formula38Mod {
    public static final String MOD_ID = "formula38mod";

    public Formula38Mod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);

        ModConfigureFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
                    ModMessages.register();
                    ModVillagers.registerPOIs();
                }
        );

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes
                    .setRenderLayer(
                            ModBlocks.BLUEBERRY_CROP.get(),
                            RenderType.cutout()
                    );

            ItemBlockRenderTypes
                    .setRenderLayer(
                            ModFluids.SOURCE_SOAP_WATER.get(),
                            RenderType.translucent()
                    );

            ItemBlockRenderTypes
                    .setRenderLayer(
                            ModFluids.FLOWING_SOAP_WATER.get(),
                            RenderType.translucent()
                    );
        }
    }
}
