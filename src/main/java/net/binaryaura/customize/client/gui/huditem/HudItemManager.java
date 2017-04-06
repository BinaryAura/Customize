package net.binaryaura.customize.client.gui.huditem;

import java.lang.reflect.Field;

import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.collection.generic.BitOperations.Int;

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
	 * Render Engine for the HUD.
	 */
	public static GuiInGameCustomize ingameGui;  // NO static
	
	private static final HudItemManager instance = new HudItemManager();
	
	/**
	 * The collection of hudItems.
	 */
	public static final HudItemRegistry REGISTRY = new HudItemRegistry();

	private static final String TITLE_TEXT = "field_175201_x",
	                            SUBTITLE_TEXT = "field_175200_y",
	                            TITLE_TICKS_REMAINING = "field_175195_w",
	                            TITLE_FADE_IN_TIME = "field_175199_z",
							    TITLE_DISPLAY_TIME = "field_175192_A",
	                            TITLE_FADE_OUT_TIME = "field_175193_B",
	                            RECORD_PLAYING = "recordPlaying",
	                            RECORD_PLAYING_UP_FOR = "recordPlayingUpFor",
	                            RECORD_IS_PLAYING = "recordIsPlaying";
	
	/**
	 * Minecraft gameController
	 */
	protected Minecraft mc;;
	
	@Deprecated
	public static int updateCounter = 0;
	
	public static HudItemManager getInstance() {
		return instance;
	}
	
	public static boolean getRecordIsPlaying() {
		return HudItemManager.<Boolean>getGUIField(RECORD_IS_PLAYING);		
	}
	
	public static String getRecordPlaying() {
		return HudItemManager.<String>getGUIField(RECORD_PLAYING);
	}
	
	public static int getRecordRemainingPlayTime() {
		return HudItemManager.<Integer>getGUIField(RECORD_PLAYING_UP_FOR);
	}
	
	public static String getSubtitleText() {
		return HudItemManager.<String>getGUIField(SUBTITLE_TEXT);
	}
	
	public static int getTitleDisplayTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_DISPLAY_TIME);
	}
	
	public static int getTitleFadeInTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_FADE_IN_TIME);
	}
	
	public static int getTitleFadeOutTime() {
		return HudItemManager.<Integer>getGUIField(TITLE_FADE_OUT_TIME);
	}
	
	public static String getTitleText() {
		return HudItemManager.<String>getGUIField(TITLE_TEXT);
	}
	
	public static int getTitleTicksRemaining() {
		return HudItemManager.<Integer>getGUIField(TITLE_TICKS_REMAINING);
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
	
	@SuppressWarnings("unchecked")
	private static <T> T getGUIField(String fieldName) {
		GuiIngame instance = Minecraft.getMinecraft().ingameGUI;
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
	
	private HudItemManager() {
		mc = Minecraft.getMinecraft();
		ingameGui = new GuiInGameCustomize(mc);
	}
	
	/**
	 * Get the resolution of Minecraft Window.
	 * 
	 * @return		Resolution of Minecraft Window.
	 */
	public ScaledResolution getRes() {
		return eventParent.resolution;
	}
	
	@SubscribeEvent
	public void onTick(TickEvent event)
	{
		ingameGui.updateTick();
	}
	
	@SubscribeEvent
	public void renderGameOverlay(RenderGameOverlayEvent.Pre event)
	{
		if(event.type == ElementType.ALL) {
			event.setCanceled(true);
			eventParent = event;
			ingameGui.renderGameOverlay(event);
		}
	}
	
	/**
	 * Event that governs the HudItems for this renderTick
	 */
	private RenderGameOverlayEvent eventParent;
}
