package net.astoldbylouis.formula38mod.villager;

import com.google.common.collect.ImmutableSet;
import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POIT_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, Formula38Mod.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Formula38Mod.MOD_ID);

    public static final RegistryObject<PoiType> JUMPY_BLOCK_POI =
            POIT_TYPES.register(
                    "jumpy_block_poi",
                    () -> new PoiType(
                            ImmutableSet
                                    .copyOf(ModBlocks
                                            .JUMPY_BLOCK.get()
                                            .getStateDefinition()
                                            .getPossibleStates()),
                            1,
                            1
                    )
            );

    public static final RegistryObject<VillagerProfession> JUMPY_MASTER =
            VILLAGER_PROFESSIONS.register(
                    "jumpy_master",
                    () -> new VillagerProfession(
                            "jumpy_master",
                            x -> x.get() == JUMPY_BLOCK_POI.get(),
                            x -> x.get() == JUMPY_BLOCK_POI.get(),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_ARMORER
                    )
            );

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper
                    .findMethod(
                            PoiType.class,
                            "registersBlockStates",
                            PoiType.class)
                    .invoke(null, JUMPY_BLOCK_POI);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POIT_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
