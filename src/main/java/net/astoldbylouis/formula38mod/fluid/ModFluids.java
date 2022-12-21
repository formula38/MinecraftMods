package net.astoldbylouis.formula38mod.fluid;

import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.block.ModBlocks;
import net.astoldbylouis.formula38mod.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Formula38Mod.MOD_ID);


    public static final RegistryObject<FlowingFluid> SOURCE_SOAP_WATER =
            FLUIDS.register(
                    "soap_water_fluid",
                    () -> new ForgeFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES)
            );

    public static final RegistryObject<FlowingFluid> FLOWING_SOAP_WATER =
            FLUIDS.register(
                    "flowing_soap_water",
                    () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES)
            );

    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES =
            new ForgeFlowingFluid.Properties(ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
                    .slopeFindDistance(2)
                    .levelDecreasePerBlock(2)
                    .block(ModBlocks.SOAP_WATER_BLOCK)
                    .bucket(ModItems.SOAP_WATER_BUCKET);



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
