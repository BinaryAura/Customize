package net.binaryaura.customize;

import net.binaryaura.customize.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Customize.MODID, name = Customize.NAME, version = Customize.VERSION)
public class Customize {
	public static final String NAME = "Customize";
    public static final String MODID = "customize";
    public static final String VERSION = "1G0-2";
    public static final String CLIENTPROXY = "net.binaryaura.customize.";
    public static final String COMMONPROXY = "net.binaryaura.common.";
    
    @Instance
    public static Customize instance;
    
    @SidedProxy(clientSide = CLIENTPROXY + "ClientProxy", serverSide = COMMONPROXY + "CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
