package net.astoldbylouis.formula38mod.item;

import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.block.ModBlocks;
import net.astoldbylouis.formula38mod.fluid.ModFluids;
import net.astoldbylouis.formula38mod.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Formula38Mod.MOD_ID);

    public static final RegistryObject<Item> ZIRCON =
            ITEMS.register(
                    "zircon",
                    () -> new Item(
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                    )
            );

    public static final RegistryObject<Item> RAW_ZIRCON =
            ITEMS.register(
                    "raw_zircon",
                    () -> new Item(
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                    )
            );

    public static final RegistryObject<Item> EIGHT_BALL =
            ITEMS.register(
                    "eight_ball",
                    () -> new EightBallItem(
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                                    .stacksTo(1)
                    )
            );

    public static final RegistryObject<Item> BLUEBERRY_SEEDS =
            ITEMS.register(
                    "blueberry_seeds",
                    () -> new ItemNameBlockItem(
                            ModBlocks.BLUEBERRY_CROP.get(),
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                    )
            );

    public static final RegistryObject<Item> BLUEBERRY =
            ITEMS.register(
                    "blueberry",
                    () -> new Item(
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                                    .food(
                                            new FoodProperties.Builder()
                                                    .nutrition(2)
                                                    .saturationMod(2f)
                                                    .build()
                                    )
                    )
            );
    public static final RegistryObject<Item> SOAP_WATER_BUCKET =
            ITEMS.register(
                    "soap_water_bucket",
                    () -> new BucketItem(
                            ModFluids.SOURCE_SOAP_WATER,
                            new Item.Properties()
                                    .tab(ModCreativeModeTab.FORMULA38_TAB)
                                    .craftRemainder(Items.BUCKET)
                                    .stacksTo(1)
                    )
            );

    public static final RegistryObject<Item> KAUPENSWORD =
            ITEMS.register(
                    "kaupensword",
                    () -> new SwordItem(Tiers.DIAMOND,
                                    19,
                                    5f,
                                    new Item.Properties()
                                            .tab(ModCreativeModeTab.FORMULA38_TAB)
                                            .stacksTo(1)
                    )
            );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
