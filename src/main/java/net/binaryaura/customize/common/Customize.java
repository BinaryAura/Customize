package net.binaryaura.customize.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Customize.MODID, name = Customize.NAME, version = Customize.VERSION)
public class Customize {
    public static final String MODID = "customize";
	public static final String NAME = "Customize";
    public static final String VERSION = "1G0-4";
    public static final String CLIENTPROXY = "net.binaryaura.customize.client.";
    public static final String COMMONPROXY = "net.binaryaura.customize.common.";
    
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Logger log = LogManager.getLogger("CUSTOMIZE");
    public static final HudItemManager hudManager = new HudItemManager();
    
    @Instance
    public static Customize instance;
    
    @SidedProxy(clientSide = CLIENTPROXY + "ClientProxy", serverSide = COMMONPROXY + "CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {    	
    	log.info("Loading " + NAME);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    	log.info("Successfully loaded " + NAME);
    }
    
    @EventHandler
    public void disable(FMLModDisabledEvent event) {
    	mc.ingameGUI = new GuiIngameForge(mc);
    }
    
}
