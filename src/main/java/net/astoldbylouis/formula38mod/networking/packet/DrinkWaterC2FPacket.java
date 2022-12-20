package net.astoldbylouis.formula38mod.networking.packet;

import net.astoldbylouis.formula38mod.networking.ModMessages;
import net.astoldbylouis.formula38mod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrinkWaterC2FPacket {
    private static final String MESSAGE_DRINK_WATER = "message.formula38mod.drink_water";
    private static final String MESSAGE_NO_WATER = "message.formula38mod.no_water";

    public DrinkWaterC2FPacket() {
    }

    public DrinkWaterC2FPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER SIDE
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if (hasWaterAroundThem(player, level, 2)) {

                // Notify the player that water has been drunk
                player.sendSystemMessage(
                        Component
                                .translatable(MESSAGE_DRINK_WATER)
                                .withStyle(ChatFormatting.DARK_AQUA)
                );

                // play drinking sound
                level.playSound(
                        null,
                        player.getOnPos(),
                        SoundEvents.GENERIC_DRINK,
                        SoundSource.PLAYERS,
                        0.5F,
                        level.random.nextFloat() * 0.1F * 0.9F
                );

                // Increase the water level / thirst level of player
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST)
                        .ifPresent(
                                thirst -> thirst.addThirst(1)
                        );

                // Output current thirst level
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST)
                        .ifPresent(
                                thirst -> {
                                    player.sendSystemMessage(
                                            Component
                                                    .literal("Current thirst " + thirst.getThirst())
                                                    .withStyle(ChatFormatting.DARK_AQUA)
                                    );
                                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                                }
                        );



            } else {
                // Notify the player that there is no water around
                player.sendSystemMessage(
                        Component
                                .translatable(MESSAGE_NO_WATER)
                                .withStyle(ChatFormatting.GRAY)
                );

            }
            // Check if player is near water

            // Output the current thrust level
            player.getCapability(PlayerThirstProvider.PLAYER_THIRST)
                    .ifPresent(
                            thirst -> {
                                player.sendSystemMessage(
                                        Component
                                                .literal("Current thirst " + thirst.getThirst())
                                                .withStyle(ChatFormatting.DARK_AQUA)
                                );
                            }
                    );
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {

        return level.getBlockStates(
                        player.getBoundingBox()
                                .inflate(size)
                )
                .filter(state -> state.is(Blocks.WATER))
                .toArray().length > 0;
    }
}
