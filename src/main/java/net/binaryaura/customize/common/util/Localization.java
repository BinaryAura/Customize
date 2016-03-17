package net.binaryaura.customize.common.util;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.registry.LanguageRegistry;

public class Localization {
		
	public static String get(String key) {
		
	     String text = LanguageRegistry.instance().getStringLocalization(key);
	     
	     if ((text == null) || (text.equals(""))) {
	       text = LanguageRegistry.instance().getStringLocalization(key, "en_US");
	     }
	     return text;
	}

	public static String getMinecraft(String key) {
		return I18n.format(key, new Object[0]);
	}
	
}
