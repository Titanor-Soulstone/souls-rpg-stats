
package net.mcreator.soulrpg.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.soulrpg.world.inventory.PlayerStatsMenu;
import net.mcreator.soulrpg.procedures.SPSpendWISProcedure;
import net.mcreator.soulrpg.procedures.SPSpendSTRProcedure;
import net.mcreator.soulrpg.procedures.SPSpendINTProcedure;
import net.mcreator.soulrpg.procedures.SPSpendDEXProcedure;
import net.mcreator.soulrpg.procedures.SPSpendCONProcedure;
import net.mcreator.soulrpg.procedures.SPSpendCHAProcedure;
import net.mcreator.soulrpg.procedures.SPRtrWISProcedure;
import net.mcreator.soulrpg.procedures.SPRtrSTRProcedure;
import net.mcreator.soulrpg.procedures.SPRtrINTProcedure;
import net.mcreator.soulrpg.procedures.SPRtrDEXProcedure;
import net.mcreator.soulrpg.procedures.SPRtrCONProcedure;
import net.mcreator.soulrpg.procedures.SPRtrCHAProcedure;
import net.mcreator.soulrpg.SoulRpgMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerStatsButtonMessage {
	private final int buttonID, x, y, z;

	public PlayerStatsButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public PlayerStatsButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(PlayerStatsButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(PlayerStatsButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = PlayerStatsMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			SPSpendSTRProcedure.execute(entity);
		}
		if (buttonID == 1) {

			SPSpendDEXProcedure.execute(entity);
		}
		if (buttonID == 2) {

			SPSpendCONProcedure.execute(entity);
		}
		if (buttonID == 3) {

			SPSpendINTProcedure.execute(entity);
		}
		if (buttonID == 4) {

			SPSpendWISProcedure.execute(entity);
		}
		if (buttonID == 5) {

			SPSpendCHAProcedure.execute(entity);
		}
		if (buttonID == 6) {

			SPRtrSTRProcedure.execute(entity);
		}
		if (buttonID == 7) {

			SPRtrDEXProcedure.execute(entity);
		}
		if (buttonID == 8) {

			SPRtrCONProcedure.execute(entity);
		}
		if (buttonID == 9) {

			SPRtrINTProcedure.execute(entity);
		}
		if (buttonID == 10) {

			SPRtrWISProcedure.execute(entity);
		}
		if (buttonID == 11) {

			SPRtrCHAProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		SoulRpgMod.addNetworkMessage(PlayerStatsButtonMessage.class, PlayerStatsButtonMessage::buffer, PlayerStatsButtonMessage::new, PlayerStatsButtonMessage::handler);
	}
}
