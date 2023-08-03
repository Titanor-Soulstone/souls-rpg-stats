package net.mcreator.soulrpg.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import net.mcreator.soulrpg.world.inventory.PlayerStatsMenu;
import net.mcreator.soulrpg.procedures.XPToNextLVLProcedure;
import net.mcreator.soulrpg.procedures.StatPointsReturnProcedure;
import net.mcreator.soulrpg.procedures.ReturnWISProcedure;
import net.mcreator.soulrpg.procedures.ReturnSTRProcedure;
import net.mcreator.soulrpg.procedures.ReturnINTProcedure;
import net.mcreator.soulrpg.procedures.ReturnHPProcedure;
import net.mcreator.soulrpg.procedures.ReturnDEXProcedure;
import net.mcreator.soulrpg.procedures.ReturnCONProcedure;
import net.mcreator.soulrpg.procedures.ReturnCHAProcedure;
import net.mcreator.soulrpg.procedures.ReturnATKSpeedProcedure;
import net.mcreator.soulrpg.procedures.ReturnATKProcedure;
import net.mcreator.soulrpg.procedures.PlayerNameProcedure;
import net.mcreator.soulrpg.procedures.PlayerModelProcedure;
import net.mcreator.soulrpg.procedures.PlayerEXPReturnProcedure;
import net.mcreator.soulrpg.procedures.PlayerEXPCurveProcedure;
import net.mcreator.soulrpg.network.PlayerStatsButtonMessage;
import net.mcreator.soulrpg.SoulRpgMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class PlayerStatsScreen extends AbstractContainerScreen<PlayerStatsMenu> {
	private final static HashMap<String, Object> guistate = PlayerStatsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_plus_btn;
	ImageButton imagebutton_plus_btn1;
	ImageButton imagebutton_plus_btn2;
	ImageButton imagebutton_plus_btn3;
	ImageButton imagebutton_plus_btn4;
	ImageButton imagebutton_plus_btn5;
	ImageButton imagebutton_min_btn;
	ImageButton imagebutton_min_btn1;
	ImageButton imagebutton_min_btn2;
	ImageButton imagebutton_min_btn3;
	ImageButton imagebutton_min_btn4;
	ImageButton imagebutton_min_btn5;

	public PlayerStatsScreen(PlayerStatsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 270;
		this.imageHeight = 202;
	}

	private static final ResourceLocation texture = new ResourceLocation("soul_rpg:textures/screens/player_stats.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		if (PlayerModelProcedure.execute(entity) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventoryRaw(this.leftPos + 37, this.topPos + 109, 30, 0f, 0, livingEntity);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack,

				PlayerNameProcedure.execute(entity), 8, 7, -12829636);
		this.font.draw(poseStack,

				PlayerEXPReturnProcedure.execute(entity), 170, 7, -12829636);
		this.font.draw(poseStack,

				PlayerEXPCurveProcedure.execute(world, entity), 89, 7, -12829636);
		this.font.draw(poseStack,

				StatPointsReturnProcedure.execute(entity), 89, 34, -12829636);
		this.font.draw(poseStack,

				ReturnSTRProcedure.execute(entity), 89, 43, -12829636);
		this.font.draw(poseStack,

				ReturnDEXProcedure.execute(entity), 89, 52, -12829636);
		this.font.draw(poseStack,

				ReturnCONProcedure.execute(entity), 89, 61, -12829636);
		this.font.draw(poseStack,

				ReturnINTProcedure.execute(entity), 89, 70, -12829636);
		this.font.draw(poseStack,

				ReturnWISProcedure.execute(entity), 89, 79, -12829636);
		this.font.draw(poseStack,

				ReturnCHAProcedure.execute(entity), 89, 88, -12829636);
		this.font.draw(poseStack,

				ReturnHPProcedure.execute(entity), 179, 34, -12829636);
		this.font.draw(poseStack,

				ReturnATKProcedure.execute(entity), 179, 43, -12829636);
		this.font.draw(poseStack,

				ReturnATKSpeedProcedure.execute(entity), 179, 52, -12829636);
		this.font.draw(poseStack,

				XPToNextLVLProcedure.execute(world, entity), 170, 16, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		imagebutton_plus_btn = new ImageButton(this.leftPos + 170, this.topPos + 43, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(0, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn", imagebutton_plus_btn);
		this.addRenderableWidget(imagebutton_plus_btn);
		imagebutton_plus_btn1 = new ImageButton(this.leftPos + 170, this.topPos + 52, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn1.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(1, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn1", imagebutton_plus_btn1);
		this.addRenderableWidget(imagebutton_plus_btn1);
		imagebutton_plus_btn2 = new ImageButton(this.leftPos + 170, this.topPos + 61, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn2.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(2, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn2", imagebutton_plus_btn2);
		this.addRenderableWidget(imagebutton_plus_btn2);
		imagebutton_plus_btn3 = new ImageButton(this.leftPos + 170, this.topPos + 70, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn3.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(3, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn3", imagebutton_plus_btn3);
		this.addRenderableWidget(imagebutton_plus_btn3);
		imagebutton_plus_btn4 = new ImageButton(this.leftPos + 170, this.topPos + 79, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn4.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(4, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn4", imagebutton_plus_btn4);
		this.addRenderableWidget(imagebutton_plus_btn4);
		imagebutton_plus_btn5 = new ImageButton(this.leftPos + 170, this.topPos + 88, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_plus_btn5.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(5, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		guistate.put("button:imagebutton_plus_btn5", imagebutton_plus_btn5);
		this.addRenderableWidget(imagebutton_plus_btn5);
		imagebutton_min_btn = new ImageButton(this.leftPos + 152, this.topPos + 43, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(6, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn", imagebutton_min_btn);
		this.addRenderableWidget(imagebutton_min_btn);
		imagebutton_min_btn1 = new ImageButton(this.leftPos + 152, this.topPos + 52, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn1.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(7, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn1", imagebutton_min_btn1);
		this.addRenderableWidget(imagebutton_min_btn1);
		imagebutton_min_btn2 = new ImageButton(this.leftPos + 152, this.topPos + 61, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn2.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(8, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn2", imagebutton_min_btn2);
		this.addRenderableWidget(imagebutton_min_btn2);
		imagebutton_min_btn3 = new ImageButton(this.leftPos + 152, this.topPos + 70, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn3.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(9, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 9, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn3", imagebutton_min_btn3);
		this.addRenderableWidget(imagebutton_min_btn3);
		imagebutton_min_btn4 = new ImageButton(this.leftPos + 152, this.topPos + 79, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn4.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(10, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 10, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn4", imagebutton_min_btn4);
		this.addRenderableWidget(imagebutton_min_btn4);
		imagebutton_min_btn5 = new ImageButton(this.leftPos + 152, this.topPos + 88, 8, 8, 0, 0, 8, new ResourceLocation("soul_rpg:textures/screens/atlas/imagebutton_min_btn5.png"), 8, 16, e -> {
			if (true) {
				SoulRpgMod.PACKET_HANDLER.sendToServer(new PlayerStatsButtonMessage(11, x, y, z));
				PlayerStatsButtonMessage.handleButtonAction(entity, 11, x, y, z);
			}
		});
		guistate.put("button:imagebutton_min_btn5", imagebutton_min_btn5);
		this.addRenderableWidget(imagebutton_min_btn5);
	}
}
