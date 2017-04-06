package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemHealth;
import net.minecraft.util.MathHelper;

/**
 * This HUDItem acts as a gauge. It is made up of several
 * icons each with one or more stages. When increasing, the
 * value the gauge goes through the the different stages
 * of each icon.
 * 
 *  0 0 0 0 0		
 *  1 0 0 0 0		<
 *  2 0 0 0 0		<3
 *  2 1 0 0 0		<3 <
 *  2 2 0 0 0		<3 <3
 *     etc
 * 
 * Each icon is rendered independently as layers. Rendering
 * background layers first then, successive layers on top.
 * Each icon is separated by <code>space</code>.
 * 
 * When the number of icons exceeds <code>maxPerRow</code>
 * Additional rows are rendered perpendicularly to the gauge
 * with <code>stackSpace</code> difference between each row.
 * <code>stackSpace</code> decreases as more and more stacks
 * are added.
 * 
 * Specific instances should extend this class. Default values
 * should be overridden in the extended class.
 * 
 * @author	BinaryAura
 * @see		HudItem
 */
public abstract class HudItemIconGauge extends HudItem {

	/**
	 * Default Maximum value to be used when displaying
	 * Icon Gauges in {@link GuiScreenAdjustHud}.
	 * 
	 * @see	GuiScreenAdjustHud
	 */
	protected static final int DFLT_DEMO_AMT = 20;
	
	/**
	 * Default Maximum amount of icons to be rendered
	 * in a row for all Icon Gauges.
	 */
	protected static final int DFLT_MAX_PER_ROW = 10;
	
	/**
	 * Default Maximum space between rows for all Icon
	 * Gauges.
	 */
	protected static final int DFLT_MAX_STK_SPC = 11;
	
	/**
	 * Default Minimum space between rows for all Icon
	 * Gauges.
	 */
	protected static final int DFLT_MIN_STK_SPC = 3;
	
	/**
	 * Default space between icons in the rows of all
	 * Icon Gauges.
	 */
	protected static final int DFLT_SPC = 8;
	
	//	TODO: Fix Button when Rotating with IconGauge Stacks
	
	/**
	 * Constructs an instance of an Icon Gauge with the
	 * specified <code>name</code>. IconGauge characteristics 
	 * are set here along with the default values. The default
	 * values can be overridden by the specific gauge's class.
	 * 
	 * @see HudItem
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemIconGauge(String name) {
		super(name);
		canFlip = true;
		canRotate = true;
		maxPerRow = DFLT_MAX_PER_ROW;
		maxStackSpace = DFLT_MAX_STK_SPC;
		minStackSpace = DFLT_MIN_STK_SPC;
		space = DFLT_SPC;
	}

	/**
	 * Amount is reset each time {@link #getAmount()} changes. If the game
	 * {@link #isInPreview()} then, <code>demoAmount</code> is used instead.
	 * Height and Width is reset as well.
	 * 
	 * How the gauge is rendered depends on the orientation of the gauge.
	 * The texture and specific attributes are obtained from the subclass.
	 * From the information retrieved the subclass is rendered icon by icon.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		SpriteSet iconLayers;
		bind(layers.getLocation());
		if(isInPreview() && amount != getDemoAmount())
			setAmount(getDemoAmount());
		else if(amount != getAmount())
			setAmount(getAmount());
			
		switch(orientation) {
			case RIGHT:
				for(int i = MathHelper.ceiling_float_int(amount / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i+1) / maxPerRow) - 1;
						int iconX = x + space*(i % maxPerRow) + getIconDeltaPara(i);
						int iconY = y - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case DOWN:
				space = layers.getHeight();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int iconY = y + space*(i % maxPerRow) + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case LEFT:
				x += width - layers.getWidth();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x - space*(i % maxPerRow) + getIconDeltaPara(i);
						int iconY = y + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case UP:
				y += height - layers.getHeight();
				space = layers.getHeight();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int iconY = y - space*(i % maxPerRow) + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
		}
		mc.mcProfiler.endSection();
	}
 
	/**
	 * Setter for {@link #maxPerRow}.
	 * 
	 * @param max		New maximum amount of icons for each row.
	 */
	public void setMaxPerRow(int max) {
		maxPerRow = max;
		setHeightAndWidth();
	}
	
	/**
	 * Calculates the x-value of this gauge's button counterpart.  
	 * 
	 * @return x-value of this gauge's button counterpart in {@link GuiScreenAdjustHud}.
	 */
	@Deprecated
	@Override
	public int getButtonX() {
		return super.getButtonX();
	}

	/**
	 * Calculates the y-value of this gauge's button counterpart.  
	 * 
	 * @return y-value of this gauge's button counterpart in {@link GuiScreenAdjustHud}.
	 */
	@Deprecated
	@Override
	public int getButtonY() {
		return super.getButtonY();
	}
	
	/**
	 * Sets Height and Width of the gauge based on the orientation.
	 * Also, sets Icon Gauge specific settings.
	 */
	@Override
	protected void setHeightAndWidth() {
		stacks = MathHelper.ceiling_float_int(amount / (layers.getAmount() - 1) / maxPerRow);
		stackSpace = Math.max(maxStackSpace - (stacks - 1), minStackSpace);
		switch(orientation) {
			case RIGHT:
			case LEFT:
				height = (stacks - 1)*stackSpace + layers.getHeight()*stacks;
				width = space*(maxPerRow - 1) + layers.getWidth();
				break;
			case DOWN:
			case UP:
				height = space*(maxPerRow - 1) + layers.getHeight();
				width = (stacks - 1)*stackSpace + layers.getWidth()*stacks;
				break;
		}
	}
	
	/**
	 * Sets the amount and recalculates height and width
	 * 
	 * @param amount		Maximum value for the gauge
	 */
	protected void setAmount(float amount) {
		this.amount = amount;
		setHeightAndWidth();
	}

	/**
	 * Creates a bidirectional shake among the icons when used in
	 * {@link #getIconDeltaPara(int)} or {@link #getIconDeltaPerp(int)}.
	 * 
	 * @return delta movement per tick to produce a bidirectional shake.
	 */
	protected int bidirectionalShake() {
		return rand.nextInt(3) - 1;
	}
	
	/**
	 * Creates an animation of a moving wave through the bar. It uses a
	 * single sine wave with only the positive section when used in
	 * {{@link #getIconDeltaPara(int)} or {@link #getIconDeltaPerp(int)}.
	 * 
	 * @param icon		Index of the <code>icon</code>
	 * 
	 * @return delta movement per tick to produce a single moving wave.
	 */
	protected int movingHalfSinWave(int icon) {
		int leadIcon = updateCounter % MathHelper.ceiling_float_int(getAmount() + 5);
		if(leadIcon < icon && icon < leadIcon + 5) {
			return MathHelper.ceiling_double_int(5*Math.cos((icon - leadIcon)*5 / Math.PI));
		}
		return 0;
	}
	
	/**
	 * Creates a shaking animation among the icons when used in
	 * {@link #getIconDeltaPara(int)} or {@link #getIconDeltaPerp(int)}.
	 * 
	 * @return delta movement per tick to produce a shaking animation.
	 */
	protected int shake() {
		return 2*rand.nextInt(2);
	}
	
	/**
	 * Creates a continuous Sine wave among the icons when used in
	 * {@link #getIconDeltaPara(int)} or {@link #getIconDeltaPerp(int)}.
	 * 
	 * @param icon		Index of the <code>icon</code>
	 * 
	 * @return delta movement per tick to produce a continuous sine wave.
	 */
	protected int sinWave(int icon) {
		int leadIcon = updateCounter % MathHelper.ceiling_float_int(getAmount());
		return MathHelper.ceiling_double_int(5*Math.sin(leadIcon*5 / Math.PI));
	}
	
	// TODO: Fix IconGauge Animation Methods
	
	/**
	 * Creates a wave through the icons when used in {@link #getIconDeltaPara(int)}
	 * or {@link #getIconDeltaPerp(int)}.
	 * 
	 * @param icon		Index of the <code>icon</code>
	 * 
	 * @return delta movement per tick to produce a wave.
	 */
	protected int wave(int icon) {
		if(updateCounter % MathHelper.ceiling_float_int(getAmount() + 5) == icon) {
			animationFinished = false;
			if(MathHelper.ceiling_float_int(getAmount()) - 1 == icon)
				animationFinished = true;
			return 2;
		}
		return 0;
	}
	
	/**
	 * Calculates parallel movement of each icon of the gauge.							 <3 <- <3 -> <3
	 * 
	 * @param icon		The index of the icon.  <3 0 <3 1 <3 2 <3 3 <3 4 <3 5 etc
	 * 
	 * @return parallel movement direction and distance. 
	 */
	protected int getIconDeltaPara(int icon) {
		return 0;
	}
	
	/**																						   /\
	 * Calculates perpendicular movement of each icon of the gauge.		 				 <3    <3    <3
	 * 																						   \/
	 * @param icon		The index of the icon.
	 * 
	 * @return perpendicular movement direction and distance.
	 */
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}
	
	/**
	 * Gets the maximum value for during game-play.
	 * 
	 * @return the maximum value for the gauge (20 = 2 states per icon x 10 icons).
	 */
	protected float getAmount() {
		return 20.0f;
	}
	
	/**
	 * Gets the maximum value to be used when displaying the gauge
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the maximum value of this Icon Gauge as a preview.
	 */
	protected float getDemoAmount() {
		return 10.0f;
	}
	
	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge in {@link GuiScreenAdjustHud}.
	 * 
	 * @see SpriteSet
	 * 
	 * @return the textures to be used for preview.
	 */
	protected abstract SpriteSet getDemoSpriteSet();
	
	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge during game-play.
	 * 
	 * @param icon		The index of the icon.		
	 * 
	 * @return the textures to be used in <code>icon</code>.
	 */
	protected abstract SpriteSet getIconSpriteSet(int icon);
	
	/**
	 * Flag for when an animation finishes.
	 */
	@Deprecated
	protected boolean animationFinished = true;
		
	/**
	 * Maximum amount of icons to be rendered
	 * in a row.
	 */
	protected int maxPerRow;
	
	/**
	 * Maximum amount of icons to be rendered
	 * in a row.
	 */
	protected int maxStackSpace;
	
	/**
	 * Minimum space between rows.
	 */
	protected int minStackSpace;
	
	/**
	 * Space between icons in the rows
	 */
	protected int space;
	
	/**
	 * Textures for the icon gauge. Multiple layers could be made
	 * to represent different situations for textures. For example,
	 * for {@link HudItemHealth}:
	 * 
	 * background
	 * default
	 * defaultHL
	 * absorb
	 * poison
	 * poisonHL
	 * wither
	 * witherHL
	 * defaultHC
	 * defaultHCHL
	 * absorbHC
	 * poisonHC
	 * poisonHCHL
	 * witherHC
	 * witherHCHL
	 */
	protected LayeredSprite layers;

	/**
	 * Amount of rows required to render the gauge.
	 */
	private int stacks = 0;
	
	/**
	 * Space in between the rows.
	 */
	private int stackSpace = 0;
	
	/**
	 * Maximum value for the rendering. This value is used to determine
	 * the amount of icons to be rendered.
	 */
	private float amount = 0;
}
