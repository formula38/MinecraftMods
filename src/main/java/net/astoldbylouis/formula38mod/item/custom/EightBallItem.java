package net.astoldbylouis.formula38mod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EightBallItem extends Item {

    public EightBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            // Output a random number
            outputRandomNumber(player);
            // Set a Cooldown
            // This will have a cooldown of 20 tick = 1 second
            player.getCooldowns().addCooldown(this, 20);
        }

        return super.use(level, player, hand);
    }

    // Helper Methods
    private void outputRandomNumber(Player player) {
        player.sendSystemMessage(Component.literal("Your number is " + getRandomNumber()));
    }

    private int getRandomNumber() {
        // Makes random number between 0 and 9
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}