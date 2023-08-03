package net.mcreator.soulrpg.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.Entity;

import net.mcreator.soulrpg.network.SoulRpgModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerNameProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static String execute(Entity entity) {
		return execute(null, entity);
	}

	private static String execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return "";
		{
			String _setval = entity.getDisplayName().getString();
			entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.NAME = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		return (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).NAME;
	}
}
