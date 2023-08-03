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
public class XPToNextLVLProcedure {
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
		return "XP TO NEXT LVL: " + new java.text.DecimalFormat("###").format((world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_BASE))
				* Math.pow((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).LEVEL, (world.getLevelData().getGameRules().getInt(SoulRpgModGameRules.EXP_POWER)))
				- (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).EXP);
	}
}
