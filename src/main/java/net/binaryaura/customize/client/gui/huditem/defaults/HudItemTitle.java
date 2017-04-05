package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItemText;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

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
	 * Default size for title
	 */
	private static final float DFLT_SIZE = 4.0f;
	
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
	private static final int DFLT_Y = -28;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.CENTER;
	
	/**
	 * The text style font title
	 */
	private static final Style DFLT_STY = Style.SHADOWED;
	
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
		if (ticksRemaining > 0 && getAlpha() > 8 || isInPreview()) {
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			super.renderHUDItem(x, y);
			GlStateManager.disableBlend();
		}
	}
	
	@Override
	protected void init() {
		super.init();
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
		String title = HudItemManager.getTitleText();
		setString(title);
		ticksRemaining = HudItemManager.getTitleTicksRemaining();
		fadeInTime = HudItemManager.getTitleFadeInTime();
		displayTime = HudItemManager.getTitleDisplayTime();
		fadeOutTime = HudItemManager.getTitleFadeOutTime();
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
		return WHITE;
	}

	@Override
	protected int getBGAlpha() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		float age = (float)ticksRemaining - partialTicks;
		int opacity = 255;
		if(ticksRemaining > fadeOutTime + displayTime) {
			float f3  = (float)(fadeInTime + displayTime + fadeOutTime) - age;  // elapsed ticks
			opacity = (int)(f3 * 255.0F / (float)fadeInTime);
		} else if (ticksRemaining <= fadeOutTime)
			opacity = (int)(age * 255.0F / (float)fadeOutTime);
		opacity = MathHelper.clamp_int(opacity, 0, 255);
		return opacity;
	}
	
	@Override
	protected float getSize() {
		return DFLT_SIZE;
	}
	
	@Override
	protected String getString() {
		return HudItemManager.getTitleText();
	}
	
	protected int ticksRemaining;
	protected int fadeOutTime;
	protected int fadeInTime;
	protected int displayTime;

}
