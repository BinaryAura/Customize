package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItemText;
import net.binaryaura.customize.client.gui.huditem.HudItem.RenderPriority;

/**
 * Primary Title Text. TITLE fades in then, fades out
 * after a brief period. TITLE is a {@link HudItemText}
 * and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see 	HudItemText
 */
public class HudItemTitle extends HudItemText {
	
	/**
	 * Relative x-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = 0;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	
	/**
	 * The priority of the HUDItem during the rendering process.
	 * 
	 * @see HudItem.RenderPriority
	 */
	private static final RenderPriority DFLT_PRIO = RenderPriority.POST;

	//	TODO: Make Title HudItem
	
	/**
	 * Constructs an instance of the TITLE object with the specified
	 * <code>name</code>. This includes setting initial location values,
	 * orientation, and fetching the textures for the text.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemTitle(String name) {
		super(name);
	}
	
	
	@Override
	public void renderHUDItem(int x, int y) {
	}
	
	@Override
	protected void init() {
		x = DFLT_X;
		y = DFLT_Y;
		anchor = DFLT_ANCH;
		style = DFLT_STY;
		priority = DFLT_PRIO;		
	}
	
	@Override
	public void updateTick() {		
		super.updateTick();
		// Typical Update for Title is done through Minecraft.inGameGui
		
		// Update Title vars
		title = HudItemManager.getTitleText();
		subtitle = HudItemManager.getSubtitleText();
		fadeInTime = HudItemManager.getTitleFadeInTime();
		displayTime = HudItemManager.getTitleDisplayTime();
		fadeOutTime = HudItemManager.getTitleFadeOutTime();
		
		if(ticksRemaining > 0) {
			--ticksRemaining;
			if(ticksRemaining <= 0) {
				title = "";
				subtitle = "";
			}
		}
	}

	@Override
	protected int getDeltaX() {
		return 0;
	}

	@Override
	protected int getDeltaY() {
		return 0;
	}

	@Override
	protected int getBGColor() {
		return 0;
	}

	@Override
	protected int getColor() {
		return 0;
	}

	@Override
	protected int getBGAlpha() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		return 0;
	}

	protected void setHeightAndWidth() {
		
	}
	
	protected int ticksRemaining;
	protected String title;
	protected String subtitle;
	protected int fadeOutTime;
	protected int fadeInTime;
	protected int displayTime;

}
