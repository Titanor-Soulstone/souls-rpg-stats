package net.mcreator.soulrpg.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.soulrpg.network.SoulRpgModVariables;

public class CrystalisedSPRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = Math.round((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).STAT_POINTS + Mth.nextDouble(RandomSource.create(), 1, 3));
			entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.STAT_POINTS = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
