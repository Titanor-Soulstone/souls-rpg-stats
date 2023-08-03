package net.mcreator.soulrpg.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.soulrpg.network.SoulRpgModVariables;

public class SPRtrWISProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).WIS > 1) {
			{
				double _setval = (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).STAT_POINTS + 1;
				entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.STAT_POINTS = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).WIS - 0.25;
				entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.WIS = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
