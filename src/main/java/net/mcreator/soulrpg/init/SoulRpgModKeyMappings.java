
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulrpg.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.soulrpg.network.StatsHotkeyMessage;
import net.mcreator.soulrpg.SoulRpgMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class SoulRpgModKeyMappings {
	public static final KeyMapping STATS_HOTKEY = new KeyMapping("key.soul_rpg.stats_hotkey", GLFW.GLFW_KEY_P, "key.categories.ui") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new StatsHotkeyMessage(0, 0));
				StatsHotkeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(STATS_HOTKEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				STATS_HOTKEY.consumeClick();
			}
		}
	}
}
