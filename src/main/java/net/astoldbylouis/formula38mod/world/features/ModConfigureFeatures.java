package net.astoldbylouis.formula38mod.world.features;

import com.google.common.base.Suppliers;
import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.module.Configuration;
import java.util.List;
import java.util.function.Supplier;

public class ModConfigureFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Formula38Mod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ZIRCON_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(
                                    OreFeature.STONE_ORE_REPLACEABLES,
                                    ModBlocks.ZIRCON_ORE.get().defaultBlockState()
                            ),
                            OreConfiguration.target(
                                    OreFeature.DEEPSLATE_ORE_REPLACEBLES,
                                    ModBlocks.DEEPSLATE_ZIRCON_ORE.get().defaultBlockState()
                            )
                    )
            );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_ZIRCON_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreConfiguration.target(
                                    new BlockMatchTest(Blocks.END_STONE),
                                    ModBlocks.ENDSTONE_ZIRCON_ORE.get().defaultBlockState()
                            )
                    )
            );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ZIRCON_ORES =
            Suppliers.memoize(
                    () -> List.of(
                            OreFeature.NETHER_ORE_REPLACEABLES,
                            ModBlocks.NETHERRACK_ZIRCON_ORE.get().defaultBlockState()
                    )
            );


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
