
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulrpg.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.soulrpg.item.StatsOrbItem;
import net.mcreator.soulrpg.item.CrystalisedSPItem;
import net.mcreator.soulrpg.SoulRpgMod;

public class SoulRpgModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SoulRpgMod.MODID);
	public static final RegistryObject<Item> CRYSTALISED_SP = REGISTRY.register("crystalised_sp", () -> new CrystalisedSPItem());
	public static final RegistryObject<Item> STATS_ORB = REGISTRY.register("stats_orb", () -> new StatsOrbItem());
}
