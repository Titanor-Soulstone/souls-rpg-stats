package net.mcreator.soulrpg.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.soulrpg.network.SoulRpgModVariables;
import net.mcreator.soulrpg.init.SoulRpgModGameRules;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerEXPCurveProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player);
		}
	}

	public static String execute(LevelAccessor world, Entity entity) {
		return execute(null, world, entity);
	}

	private static String execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return "";
		if ((world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_BASE)) <= -1 || (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_BASE)) >= 2
				&& (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).LEVEL < (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.MAX_LVL))) {
			if ((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).EXP >= (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_BASE))
					* Math.pow((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).LEVEL, (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_POWER)))) {
				{
					double _setval = Math.round((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).LEVEL + 1);
					entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.LEVEL = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = Math.round(
							(entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).STAT_POINTS + (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.SP_PER_LVL)));
					entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.STAT_POINTS = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
		return "LEVEL: " + new java.text.DecimalFormat("###").format((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).LEVEL);
	}
}
