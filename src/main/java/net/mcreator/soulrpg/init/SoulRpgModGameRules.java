
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulrpg.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulRpgModGameRules {
	public static final GameRules.Key<GameRules.IntegerValue> EXP_POWER = GameRules.register("expPower", GameRules.Category.PLAYER, GameRules.IntegerValue.create(2));
	public static final GameRules.Key<GameRules.IntegerValue> EXP_BASE = GameRules.register("expBase", GameRules.Category.PLAYER, GameRules.IntegerValue.create(50));
	public static final GameRules.Key<GameRules.IntegerValue> MAX_LVL = GameRules.register("maxLVL", GameRules.Category.PLAYER, GameRules.IntegerValue.create(200));
	public static final GameRules.Key<GameRules.IntegerValue> SP_PER_LVL = GameRules.register("sPPerLVL", GameRules.Category.PLAYER, GameRules.IntegerValue.create(10));
}
