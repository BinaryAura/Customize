package net.binaryaura.customize.common.util;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.registry.LanguageRegistry;

/**
 * Retrieves text for the game. All languages should in
 * "net.binaryaura.customize.src.main.resources.assets.customize.lang".
 * @author	BinaryAura
 *
 */
public class Localization {
		
	/**
	 * Fetches the Lang string for the given string.
	 * 
	 * @param key		Key for the string to be retrieved.
	 * @return			String for the given key.
	 */
	public static String get(String key) {
		String text = LanguageRegistry.instance().getStringLocalization(key);
	     
	    if ((text == null) || (text.equals(""))) {
	      text = LanguageRegistry.instance().getStringLocalization(key, "en_US");
	    }
	    return text;
	}
	
	/**
	 * Fetches the Minecraft Lang string for the given string.
	 * 
	 * @param key		Key for the string to be retrieved.
	 * @return			String for the given key.
	 */
	public static String getMinecraft(String key) {
		return I18n.format(key, new Object[0]);
	}
	
}
