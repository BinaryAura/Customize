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

/**
 * Main mod file for Customize. This file directs FML how to register this mod.
 *  
 * @author	BinaryAura
 */
@Mod(modid = Customize.MODID, name = Customize.NAME, version = Customize.VERSION)
public class Customize {
	
	/**
	 * Internal string for mod reference.
	 */
    public static final String MODID = "customize";
    
    /**
     * Display string for this mod.
     */
	public static final String NAME = "Customize";
	
	/**
	 * Version string for the mod.
	 */
    public static final String VERSION = "1A1-8";
    
    /**
     * Client proxy class directory.
     */
    public static final String CLIENTPROXY = "net.binaryaura.customize.client.";
    
    /**
     * Common proxy class directory.
     */
    public static final String COMMONPROXY = "net.binaryaura.customize.common.";
    
    /**
     * Instance of Minecraft.
     */
    public static final Minecraft mc = Minecraft.getMinecraft();
    
    /**
     * Logger for the mod.
     */
    public static final Logger log = LogManager.getLogger("CUSTOMIZE");
    
    /**
     * Instance of the HudItemManager.
     */
    public static final HudItemManager hudManager = HudItemManager.getInstance();
    
    /**
     * A single global instance for this mod.
     */
    @Instance
    public static Customize instance;
    
    /**
     * Sets the Proxy for both the Client and the Common Proxy.
     */
    @SidedProxy(clientSide = CLIENTPROXY + "ClientProxy", serverSide = COMMONPROXY + "CommonProxy")
    public static CommonProxy proxy;
    
    /**
     * Called before FMLInitializationEvent during mod startup. This is the first
     * of three commonly called events during mod initialization. Recommended activities:
     * Setup your logging getModLog() Load any configuration data you might have
     * getSuggestedConfigurationFile() Search for a version.properties file and load it
     * getVersionProperties() Configure your ModMetadata programmatically getModMetadata()
     * Register your blocks and items with the net.minecraftforge.fml.common.registry.
     * GameRegistry Discover parts of your mod by using annotation search getAsmData().
     * 
     * @param event			PreInitialization Event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {    	
    	log.info("Loading " + NAME);
    }
    
    /**
     * Called after FMLPreInitializationEvent and before FMLPostInitializationEvent
     * during mod startup. This is the second of three commonly called events during
     * mod initialization. Recommended activities: Register your recipes and Ore
     * Dictionary entries in the net.minecraftforge.fml.common.registry.GameRegistry
     * and net.minecraftforge.oredict.OreDictionary Dispatch requests through FMLInterModComms
     * to other mods, to tell them what you wish them to do.
     * 
     * @param event			Initialization Event
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
    /**
     * Called after FMLInitializationEvent has been dispatched on every mod.
     * This is the third and last commonly called event during mod initialization.
     * Recommended activities: interact with other mods to establish cross-mod
     * behaviors.
     * 
     * @param event			PostInitialization Event
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    	log.info("Successfully loaded " + NAME);
    }
    
    /**
     * This event is define but is currently never called.
     * It is supposed to be called when a game is disabled in
     * game. Only client mods can use this. Server mods would
     * have to reload.
     * @param event
     */
    @EventHandler
    public void disable(FMLModDisabledEvent event) {
    	mc.ingameGUI = new GuiIngameForge(mc);
    	log.info("Successfully unloaded " + NAME);
    }
    
}
