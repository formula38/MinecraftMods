package net.astoldbylouis.formula38mod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.item.ModItems;
import net.astoldbylouis.formula38mod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Formula38Mod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if (event.getType() == VillagerProfession.TOOLSMITH) {

            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades =
                    event.getTrades();
            ItemStack stack = new ItemStack(
                    ModItems.EIGHT_BALL.get(),
                    1
            );
            int villagerLevel = 1;

            trades.get(villagerLevel).add(
                    (trader, rand) -> new MerchantOffer(
                            new ItemStack(
                                    Items.EMERALD,
                                    2
                            ),
                            stack,
                            10,
                            8,
                            0.02F
                    )
            );
        }

        if (event.getType() == ModVillagers.JUMPY_MASTER.get()) {

            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades =
                    event.getTrades();
            ItemStack stack = new ItemStack(
                    ModItems.BLUEBERRY.get(),
                    15
            );
            int villagerLevel = 1;

            trades.get(villagerLevel).add(
                    (trader, rand) -> new MerchantOffer(
                            new ItemStack(
                                    Items.EMERALD,
                                    5
                            ),
                            stack,
                            10,
                            8,
                            0.02F
                    )
            );
        }

    }

}
