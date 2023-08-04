package net.mcreator.soulrpg.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.soulrpg.network.SoulRpgModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class StatEffectsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
				.setBaseValue((20 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).CON));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
				.setBaseValue((2 + 0.2 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).STR));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK)
				.setBaseValue((0.1 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).STR));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED)
				.setBaseValue((4 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).DEX));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.LUCK)
				.setBaseValue((0.1 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).CHA));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR)
				.setBaseValue((0.5 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).CON));
		((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
				.setBaseValue((0.1 + 0.01 * (entity.getCapability(SoulRpgModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SoulRpgModVariables.PlayerVariables())).DEX));
	}
}
