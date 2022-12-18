package net.astoldbylouis.formula38mod.world.features;

import net.astoldbylouis.formula38mod.Formula38Mod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Formula38Mod.MOD_ID);


    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED =
            PLACED_FEATURE.register(
                    "zircon_ore_placed",
                    () -> new PlacedFeature(
                            ModConfigureFeatures.ZIRCON_ORE.getHolder().get(),
                            commonOrePlacement(
                                    7, // viens per chunck
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(80))
                            )
                    )
            );
    public static final RegistryObject<PlacedFeature> END_ZIRCON_ORE_PLACED =
            PLACED_FEATURE.register(
                    "end_zircon_ore_placed",
                    () -> new PlacedFeature(
                            ModConfigureFeatures.END_ZIRCON_ORE.getHolder().get(),
                            commonOrePlacement(
                                    7, // viens per chunck
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(80))
                            )
                    )
            );
    public static final RegistryObject<PlacedFeature> NETHER_ZIRCON_ORE_PLACED =
            PLACED_FEATURE.register(
                    "nether_zircon_ore_placed",
                    () -> new PlacedFeature(
                            ModConfigureFeatures.NETHER_ZIRCON_ORE.getHolder().get(),
                            commonOrePlacement(
                                    7, // viens per chunck
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(80))
                            )
                    )
            );














    // Helper methods

    public static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
        return List.of(
                modifier1,
                InSquarePlacement.spread(),
                modifier2,
                BiomeFilter.biome()
        );
    }
    public static List<PlacementModifier> commonOrePlacement(int num, PlacementModifier modifier) {
        return orePlacement(
                CountPlacement.of(num),
                modifier
        );
    }
    public static List<PlacementModifier> rareOrePlacement(int num, PlacementModifier modifier) {
        return orePlacement(
                RarityFilter.onAverageOnceEvery(num),
                modifier
        );
    }



    public static void register(IEventBus eventBus) {
        PLACED_FEATURE.register(eventBus);
    }
}
