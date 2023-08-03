
package net.mcreator.soulrpg.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.soulrpg.procedures.XPToNextLVLProcedure;
import net.mcreator.soulrpg.procedures.PlayerModelProcedure;
import net.mcreator.soulrpg.procedures.PlayerEXPReturnProcedure;
import net.mcreator.soulrpg.procedures.PlayerEXPCurveProcedure;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class EXPOverOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		int posX = w / 2;
		int posY = h / 2;
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level;
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (true) {
			Minecraft.getInstance().font.draw(event.getPoseStack(),

					PlayerEXPCurveProcedure.execute(world, entity), posX + -207, posY + -103, -1);
			Minecraft.getInstance().font.draw(event.getPoseStack(),

					PlayerEXPReturnProcedure.execute(entity), posX + -207, posY + -85, -1);
			Minecraft.getInstance().font.draw(event.getPoseStack(),

					XPToNextLVLProcedure.execute(world, entity), posX + -207, posY + -67, -1);
			if (PlayerModelProcedure.execute(entity) instanceof LivingEntity livingEntity) {
				InventoryScreen.renderEntityInInventoryRaw(posX + 173, posY + 106, 15, 0f, 0, livingEntity);
			}
		}
	}
}
