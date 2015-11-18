package net.binaryaura.customize.client;

import net.binaryaura.customize.client.gui.huditem.HudItemExperience;
import net.binaryaura.customize.client.gui.huditem.HudItemHealth;
import net.binaryaura.customize.client.gui.huditem.HudItemObjective;
import net.binaryaura.customize.client.gui.huditem.HudItemToolTip;
import net.binaryaura.customize.common.CommonProxy;
import net.binaryaura.customize.common.Customize;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerRenderers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	private void registerHudItems() {
		Customize.hudManager.registerHudItem(new HudItemHealth("health"));
		Customize.hudManager.registerHudItem(new HudItemExperience("experience"));
		Customize.hudManager.registerHudItem(new HudItemToolTip("tooltip"));
	}
	
	private void registerRenderers() {
		registerHudItems();
	}

}
