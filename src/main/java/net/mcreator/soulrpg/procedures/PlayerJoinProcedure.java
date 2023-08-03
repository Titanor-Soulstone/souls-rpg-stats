package net.mcreator.soulrpg.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.soulrpg.network.SoulRpgModVariables;
import net.mcreator.soulrpg.init.SoulRpgModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerJoinProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level, event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).PLAYER_FIRST_JOIN == false) {
			if ((world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.MAX_LVL)) >= 1 || (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.MAX_LVL)) <= -1) {
				{
					double _setval = 1;
					entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.LEVEL = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.SP_PER_LVL));
					entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.STAT_POINTS = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			{
				boolean _setval = false;
				entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PLAYER_FIRST_JOIN = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
