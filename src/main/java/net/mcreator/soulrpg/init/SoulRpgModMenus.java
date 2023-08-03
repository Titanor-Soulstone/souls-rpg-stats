
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulrpg.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.soulrpg.world.inventory.PlayerStatsMenu;
import net.mcreator.soulrpg.SoulRpgMod;

public class SoulRpgModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SoulRpgMod.MODID);
	public static final RegistryObject<MenuType<PlayerStatsMenu>> PLAYER_STATS = REGISTRY.register("player_stats", () -> IForgeMenuType.create(PlayerStatsMenu::new));
}
