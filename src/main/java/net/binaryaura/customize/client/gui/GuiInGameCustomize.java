package net.binaryaura.customize.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;

public class GuiInGameCustomize extends GuiIngameForge {

	public GuiInGameCustomize(Minecraft mc) {
		super(mc);
	}

	@Override
	public void renderGameOverlay(float partialTicks) {
		// TODO Auto-generated method stub
		super.renderGameOverlay(partialTicks);
	}

	@Override
	public ScaledResolution getResolution() {
		// TODO Auto-generated method stub
		return super.getResolution();
	}

	@Override
	protected void renderCrosshairs(int width, int height) {
		// TODO Auto-generated method stub
		super.renderCrosshairs(width, height);
	}

	@Override
	protected void renderBossHealth() {
		// TODO Auto-generated method stub
		super.renderBossHealth();
	}

	@Override
	protected void renderArmor(int width, int height) {
		// TODO Auto-generated method stub
		super.renderArmor(width, height);
	}

	@Override
	protected void renderPortal(ScaledResolution res, float partialTicks) {
		// TODO Auto-generated method stub
		super.renderPortal(res, partialTicks);
	}

	@Override
	protected void renderTooltip(ScaledResolution res, float partialTicks) {
		// TODO Auto-generated method stub
		super.renderTooltip(res, partialTicks);
	}

	@Override
	protected void renderAir(int width, int height) {
		// TODO Auto-generated method stub
		super.renderAir(width, height);
	}

	@Override
	public void renderHealth(int width, int height) {
		// TODO Auto-generated method stub
		super.renderHealth(width, height);
	}

	@Override
	public void renderFood(int width, int height) {
		// TODO Auto-generated method stub
		super.renderFood(width, height);
	}

	@Override
	protected void renderSleepFade(int width, int height) {
		// TODO Auto-generated method stub
		super.renderSleepFade(width, height);
	}

	@Override
	protected void renderExperience(int width, int height) {
		// TODO Auto-generated method stub
		super.renderExperience(width, height);
	}

	@Override
	protected void renderJumpBar(int width, int height) {
		// TODO Auto-generated method stub
		super.renderJumpBar(width, height);
	}

	@Override
	protected void renderToolHightlight(ScaledResolution res) {
		// TODO Auto-generated method stub
		super.renderToolHightlight(res);
	}

	@Override
	protected void renderHUDText(int width, int height) {
		// TODO Auto-generated method stub
		super.renderHUDText(width, height);
	}

	@Override
	protected void renderRecordOverlay(int width, int height, float partialTicks) {
		// TODO Auto-generated method stub
		super.renderRecordOverlay(width, height, partialTicks);
	}

	@Override
	protected void renderTitle(int width, int height, float partialTicks) {
		// TODO Auto-generated method stub
		super.renderTitle(width, height, partialTicks);
	}

	@Override
	protected void renderChat(int width, int height) {
		// TODO Auto-generated method stub
		super.renderChat(width, height);
	}

	@Override
	protected void renderPlayerList(int width, int height) {
		// TODO Auto-generated method stub
		super.renderPlayerList(width, height);
	}

	@Override
	protected void renderHealthMount(int width, int height) {
		// TODO Auto-generated method stub
		super.renderHealthMount(width, height);
	}
	
}
