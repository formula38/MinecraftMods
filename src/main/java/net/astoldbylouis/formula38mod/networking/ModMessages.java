package net.astoldbylouis.formula38mod.networking;

import net.astoldbylouis.formula38mod.Formula38Mod;
import net.astoldbylouis.formula38mod.client.ClientThirstData;
import net.astoldbylouis.formula38mod.networking.packet.DrinkWaterC2FPacket;
import net.astoldbylouis.formula38mod.networking.packet.ExampleC2SPacket;
import net.astoldbylouis.formula38mod.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry
                .ChannelBuilder
                .named(
                        new ResourceLocation(
                                Formula38Mod.MOD_ID,
                                "messages"
                        )
                )
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(DrinkWaterC2FPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkWaterC2FPacket::new)
                .encoder(DrinkWaterC2FPacket::toBytes)
                .consumerMainThread(DrinkWaterC2FPacket::handle)
                .add();

//        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(ExampleC2SPacket::new)
//                .encoder(ExampleC2SPacket::toBytes)
//                .consumerMainThread(ExampleC2SPacket::handle)
//                .add();

        net.messageBuilder(ThirstDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSyncS2CPacket::new)
                .encoder(ThirstDataSyncS2CPacket::toBytes)
                .consumerMainThread(ThirstDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> player),
                message
        );
    }

}
