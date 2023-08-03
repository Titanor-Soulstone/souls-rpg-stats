package net.mcreator.soulrpg.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.soulrpg.SoulRpgMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulRpgModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		SoulRpgMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.EXP = original.EXP;
			clone.LEVEL = original.LEVEL;
			clone.STAT_POINTS = original.STAT_POINTS;
			clone.TITLE = original.TITLE;
			clone.NAME = original.NAME;
			clone.STR = original.STR;
			clone.DEX = original.DEX;
			clone.CON = original.CON;
			clone.INTEL = original.INTEL;
			clone.WIS = original.WIS;
			clone.CHA = original.CHA;
			clone.PLAYER_FIRST_JOIN = original.PLAYER_FIRST_JOIN;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("soul_rpg", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public double EXP = 0.0;
		public double LEVEL = 0.0;
		public double STAT_POINTS = 0.0;
		public String TITLE = "\"ROOKIE\"";
		public String NAME = "\"\"";
		public double STR = 1.0;
		public double DEX = 1.0;
		public double CON = 1.0;
		public double INTEL = 1.0;
		public double WIS = 1.0;
		public double CHA = 1.0;
		public boolean PLAYER_FIRST_JOIN = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				SoulRpgMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("EXP", EXP);
			nbt.putDouble("LEVEL", LEVEL);
			nbt.putDouble("STAT_POINTS", STAT_POINTS);
			nbt.putString("TITLE", TITLE);
			nbt.putString("NAME", NAME);
			nbt.putDouble("STR", STR);
			nbt.putDouble("DEX", DEX);
			nbt.putDouble("CON", CON);
			nbt.putDouble("INTEL", INTEL);
			nbt.putDouble("WIS", WIS);
			nbt.putDouble("CHA", CHA);
			nbt.putBoolean("PLAYER_FIRST_JOIN", PLAYER_FIRST_JOIN);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			EXP = nbt.getDouble("EXP");
			LEVEL = nbt.getDouble("LEVEL");
			STAT_POINTS = nbt.getDouble("STAT_POINTS");
			TITLE = nbt.getString("TITLE");
			NAME = nbt.getString("NAME");
			STR = nbt.getDouble("STR");
			DEX = nbt.getDouble("DEX");
			CON = nbt.getDouble("CON");
			INTEL = nbt.getDouble("INTEL");
			WIS = nbt.getDouble("WIS");
			CHA = nbt.getDouble("CHA");
			PLAYER_FIRST_JOIN = nbt.getBoolean("PLAYER_FIRST_JOIN");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.EXP = message.data.EXP;
					variables.LEVEL = message.data.LEVEL;
					variables.STAT_POINTS = message.data.STAT_POINTS;
					variables.TITLE = message.data.TITLE;
					variables.NAME = message.data.NAME;
					variables.STR = message.data.STR;
					variables.DEX = message.data.DEX;
					variables.CON = message.data.CON;
					variables.INTEL = message.data.INTEL;
					variables.WIS = message.data.WIS;
					variables.CHA = message.data.CHA;
					variables.PLAYER_FIRST_JOIN = message.data.PLAYER_FIRST_JOIN;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
