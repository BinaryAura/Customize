package net.binaryaura.customize.client.gui.huditem;

import java.lang.reflect.Field;

import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * <p>Manager for all HUDItems. HudItemManager keeps track of all
 * HudItems with a <code>HudItemRegistry</code> and has methods
 * to add and remove HudItems. It also holds values that
 * all HudItems may use.
 * 
 * <p>There is strictly one instance of the HudItemManager which can be
 * obtained by <code>HudItemManager.getInstance()</code>.
 * 
 * @see HudItemRegistry
 * 
 * @author	BinaryAura
 */
public class HudItemManager {
	
	/**
	 * Partial Ticks for this RenderTick
	 */
	public static float partialTicks;  // NO static
	
	/**
	 * Render Engine for the HUD.
	 */
	public static GuiInGameCustomize ingameGui = new GuiInGameCustomize(Customize.mc);  // NO static

	/**
	 * The collection of hudItems.
	 */
	public static final HudItemRegistry REGISTRY = new HudItemRegistry();
	
	@SubscribeEvent
	public void renderGameOverlay(RenderGameOverlayEvent.Pre event)
	{
		if(event.type == ElementType.ALL) {
			event.setCanceled(true);
			partialTicks = event.partialTicks;
			res = new ScaledResolution(mc);
			ingameGui.renderGameOverlay(event, partialTicks, res);
		}
	}
	
	@SubscribeEvent
	public void onTick(TickEvent event)
	{
		ingameGui.updateTick();
		Customize.log.info(getTitleText());
	}
	
	/**
	 * Get the resolution of Minecraft Window.
	 * 
	 * @return		Resolution of Minecraft Window.
	 */
	public static ScaledResolution getRes() {  // NO static
		return res;
	}
	
	public static String getTitleText() {
		return HudItemManager.<String>getGUIField(TITLE_TEXT);
	}
	
	public static String getSubtitleText() {
		return HudItemManager.<String>getGUIField(SUBTITLE_TEXT);
	}
	
	public static int getTitleFadeInTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_FADE_IN_TIME);
	}
	
	public static int getTitleDisplayTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_DISPLAY_TIME);
	}
	
	public static int getTitleFadeOutTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_FADE_OUT_TIME);
	}
	
	public static String getRecordPlaying() {
		return HudItemManager.<String>getGUIField(RECORD_PLAYING);
	}
	
	public static int getRecordRemainingPlayTime() {
		return HudItemManager.<Integer>getGUIField(RECORD_PLAYING_UP_FOR);
	}
	
	public static boolean getRecordIsPlaying() {
		return HudItemManager.<Boolean>getGUIField(RECORD_IS_PLAYING);		
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getGUIField(String fieldName) {
		GuiIngame instance = mc.ingameGUI;
		Class<?> ingameGui = instance.getClass();
		try {
			Field field = getField(ingameGui, fieldName);
			field.setAccessible(true);
			return (T) field.get(instance);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass == null) {
				throw e;
			} else {
				return getField(superClass, fieldName);
			}
		}
	}
	
	private static final String TITLE_TEXT = "field_175201_x",
	                            SUBTITLE_TEXT = "field_175200_y",
	                            TITLE_FADE_IN_TIME = "field_175199_z",
							    TITLE_DISPLAY_TIME = "field_175192_A",
	                            TITLE_FADE_OUT_TIME = "field_175193_B",
	                            RECORD_PLAYING = "recordPlaying",
	                            RECORD_PLAYING_UP_FOR = "recordPlayingUpFor",
	                            RECORD_IS_PLAYING = "recordIsPlaying";
		
	/**
	 * The resolution for Minecraft Window.
	 */
	private static ScaledResolution res;		// Passed     // NO static
	
	/**
	 * Minecraft gameController
	 */
	private static Minecraft mc = Minecraft.getMinecraft();		// Passed   // NO static
	
	@Deprecated
	public static int updateCounter = 0;
}
