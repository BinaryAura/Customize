/**
 * 
 */
package net.binaryaura.customize.client.gui.huditem;

import java.util.ArrayList;

import net.binaryaura.customize.common.Customize;

/**
 * This a collection of HudItems. It has three registers
 * to separate the different types of HudItems for ease
 * of rendering.
 * 
 * @author BinaryAura
 */
public class HudItemRegistry {

	/**
	 * Constructs a HudItemRegistry which consists of three registers:
	 * <code>pre</code>, <code>normal</code>, and
	 * <code>post</code>. 
	 */
	public HudItemRegistry() {
		pre = new ArrayList<HudItem>();
		normal = new ArrayList<HudItem>();
		post = new ArrayList<HudItem>();
		all = new ArrayList<HudItem>();
	}
	
	/**
	 * Get pre HudItems
	 * 
	 * @return		Collection of pre the assigned HUDItems.
	 */
	public Iterable<HudItem> getPre() {
		return pre;
	}
	
	/**
	 * Get normal HudItems.
	 * 
	 * @return		Collection of normal the assigned HUDItems.
	 */
	public Iterable<HudItem> getNormal() {
		return normal;
	}
	
	/**
	 * Get post HudItems.
	 * 
	 * @return		Collection of post the assigned HUDItems.
	 */
	public Iterable<HudItem> getPost() {
		return post;
	}
	
	/**
	 * Get all HudItems.
	 * 
	 * @return		Collection of all assigned HUDItems.
	 */
	public Iterable<HudItem> getAll() {
		return all;
	}
	
	/**
	 * Get the HudItem with the assigned <code>name</code>.
	 * 
	 * @param name		The name of the HUDItem to be retrieved.
	 * @return			HudItem with the given name.
	 */
	public HudItem get(String name) {
		for(HudItem hudItem : all.toArray(new HudItem[all.size()])) {
			if(name.equals(hudItem.getName()))
				return hudItem;
		}
		Customize.log.warn("HUDItem "+ name + " isn't in the registry. Skipping");
		return null;
	}
	
	/**
	 * Get the HudItem with the assigned <code>id</code>.
	 * 
	 * @param id		The ID of the HUDItem to be retrieved.
	 * @return			HudItem with the given ID.
	 */
	public HudItem get(int id) {
		for(HudItem hudItem : all.toArray(new HudItem[all.size()])) {
			if(hudItem.getId() == id)
				return hudItem;
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem. Skipping");
		return null;
	}
	
	/**
	 * Move the HudItem with the assigned <code>id</code> down one. This means that the
	 * hudItem will be printed sooner, and thus under subsequent hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */	
	public void moveDown(int id) {
		for(HudItem hudItem : all) {
			if(id == hudItem.getId());
		}
		Customize.log.warn("ID " + id + " doesn't relate to a hudItem. Skipping");
	}
	
	/**
	 * Move the HudItem with the assigned <code>name</code> down one. This means that the
	 * hudItem will be printed sooner, and thus under subsequent hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */
	public void moveDown(String name) {
		for(HudItem hudItem : all) {
			if(name.equals(hudItem.getName())) {
				moveDown(hudItem);
			}
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registry. Skipping");
	}
	
	/**
	 * Move <code>hudItem</code> down one. This means that the hudItem will be printed
	 * sooner, and thus under subsequent hudItems.
	 * 
	 * @param hudItem	The HUDItem to be moved.
	 */
	public void moveDown(HudItem hudItem) {
		if(hudItem != null) {
			if(all.contains(hudItem)) {
				ArrayList<HudItem> reg = getRegistry(hudItem);
				int idx = reg.indexOf(hudItem);
				if(idx-- > 0) {
					reg.remove(hudItem);
					reg.add(idx, hudItem);
					Customize.log.info("Moved " + hudItem + " up");
				} else {
					Customize.log.warn(hudItem + " is already on the bottom for it's priority. Skipping");
				}
			} else {
				Customize.log.warn(hudItem + " isn't in the registry. Skipping");  // if locally called this doesn't happen
			}
		} else {
			Customize.log.warn("Null HudItem. Skipping");  // if locally called this doesn't happen
		}
	}
	
	/**
	 * Move the HudItem with the assigned <code>id</code> up one. This means that the
	 * hudItem will be printed later, and thus on top of preceding hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */	
	public void moveUp(int id) {
		for(HudItem hudItem : all) {
			if(id == hudItem.getId()) {
				moveUp(hudItem);
			}
		}
		Customize.log.warn("ID " + id + " doesn't relate to a hudItem. Skipping");
	}
	
	/**
	 * Move the HudItem with the assigned <code>name</code> up one. This means that the
	 * hudItem will be printed later, and thus on top of preceding hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */
	public void moveUp(String name) {
		for(HudItem hudItem : all) {
			if(name.equals(hudItem.getName())) {
				moveUp(hudItem);
			}
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registry. Skipping");
	}
	
	/**
	 * Move <code>hudItem</code> up one. This means that the hudItem will be printed
	 * later, and thus on top of preceding hudItems.
	 * 
	 * @param hudItem	The HUDItem to be moved.
	 */
	public void moveUp(HudItem hudItem) {
		if(hudItem != null) {
			if(all.contains(hudItem)) {
				ArrayList<HudItem> reg = getRegistry(hudItem);
				int idx = reg.indexOf(hudItem);
				if(++idx < reg.size()) {
					reg.remove(hudItem);
					reg.add(idx, hudItem);
					Customize.log.info("Moved " + hudItem + " up");
				} else {
					Customize.log.warn(hudItem + " is already on top for it's priority. Skipping");
				}
			} else {
				Customize.log.warn(hudItem + " isn't in the registry. Skipping");  // if locally called this doesn't happen
			}
		} else {
			Customize.log.warn("Null HudItem. Skipping");  // if locally called this doesn't happen
		}
	}
	
	/**
	 * Registers the <code>hudItem</code> to the Manager.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 */
	public void register(HudItem hudItem) {
		if(hudItem != null) {
			add(hudItem);
			hudItem.setId(nextId++);
			Customize.log.info("Registered " + hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param above			The name of the HudItem to be written beneath.
	 */
	public void register(HudItem hudItem, int ref) {
		if(hudItem != null) {
			for(HudItem item : all)
			{
				if(ref == item.getId())
				{
					add(hudItem, item);
					hudItem.setId(nextId++);
					Customize.log.info("Registered " + hudItem + " above " + item);
					return;
				}
			}
			Customize.log.warn("Reference ID " + ref + " doesn't relate to a hudItem. Adding HudItem " + hudItem + " to top");
			register(hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager above the hudItem with the assigned name
	 * <code>ref</code>.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param ref			The name of the HudItem to be written beneath.
	 */
	public void register(HudItem hudItem, String ref) {
		if(hudItem != null) {
			for(HudItem item : all)
			{
				if(ref.equals(item.getName()))
				{
					all.add(all.indexOf(item), hudItem);
					hudItem.setId(nextId++);
					Customize.log.info("Registered " + hudItem + " above " + item);
					return;
				}
			}
			Customize.log.warn("HudItem reference " + ref + " isn't in the registry. Adding " + hudItem + " to top");
			register(hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager beneath the hudItem <code>ref</code>.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param ref			The HudItem to be written beneath.
	 */
	public void register(HudItem hudItem, HudItem ref) {
		if(hudItem != null) {
			if(ref != null && all.contains(ref)) {
				add(ref, hudItem);
				hudItem.setId(nextId++);
				Customize.log.info("Registered " + hudItem + " above " + hudItem);
			} else {
				if(ref == null) {
					Customize.log.warn("Null HudItem Reference. Adding " + hudItem + " to top");
					register(hudItem);
				} else {
					Customize.log.warn("Reference " + ref + " isn't in the registry. Adding " + hudItem + " to top");
					register(hudItem);
				}
			}
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Unregisters the HudItem with the assigned <code>id</code> from the Manager.
	 * 
	 * @param id		The id of the HUDItem to be stricken from the registry.
	 */
	public void unregister(int id) {
		for(HudItem hudItem : new HudItem[all.size()]) {
			if(id == hudItem.getId()) {
				remove(hudItem);
				Customize.log.info("Unregistered HudItem " + hudItem);
				return;
			}
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem. Skipping");
	}
	
	/**
	 * Unregisters a HudItem with the assigned <code>name</code> from the Manager.
	 * 
	 * @param name		The name of the HUDItem to be stricken from the registry.
	 */
	public void unregister(String name) {
		for(HudItem hudItem : new HudItem[all.size()]) {
			if(name.equals(hudItem.getName())) {
				remove(hudItem);
				Customize.log.info("Unregisterd HudItem " + hudItem);
				return;
			}
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registery. Skipping");
	}
	
	/**
	 * Unregisters the <code>hudItem</code> from the Manager.
	 * 
	 * @param hudItem	The HUDItem to be stricken from the registry.
	 */
	public void unregister(HudItem hudItem) {
		if(hudItem != null) {
			if(all.contains(hudItem)) {
				remove(hudItem);
				Customize.log.info("Unregistered HudItem " + hudItem);
			} else {
				Customize.log.warn(hudItem + " isn't in the registry. Skipping");
			}
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Adds the <code>hudItem</code> to the appropriate manager ArrayList.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 */
	private void add(HudItem hudItem) {
		getRegistry(hudItem).add(hudItem);
		all.add(hudItem);
	}
	
	/**
	 * Adds the <code>hudItem</code> to the appropriate manager ArrayList. So that
	 * the HudItem is rendered beneath <code>ref</code>.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param ref			The HudItem to be written above.
	 */
	private void add(HudItem hudItem, HudItem ref) {
		ArrayList<HudItem> reg = getRegistry(hudItem);
		if(hudItem.getPriority() == ref.getPriority())
			reg.add(reg.indexOf(ref), hudItem);
		else if(hudItem.getPriority().ordinal() > ref.getPriority().ordinal())
			reg.add(0, hudItem);
		else {
			Customize.log.warn("Cannot register " + hudItem + " above " + ref + ", their priorities conflict. Skipping");
			return;
		}
		all.add(hudItem);
	}
	
	/**
	 * Gets the manager ArrayList that should contain <code>hudItem</code>.
	 * 
	 * @see pre
	 * @see normal
	 * @see post
	 * 
	 * @param hudItem	The HUDItem to test
	 * 
	 * @return 			ArrayList<HudItem> which contains <code>hudItem</code>
	 */
	private ArrayList<HudItem> getRegistry(HudItem hudItem) {
		switch(hudItem.getPriority()) {
			case PRE:
				return pre;
			case NORMAL:
				return normal;
			case POST:
				return post;
			default:
				Customize.log.error("getRegistry error-Unexpected priority");
				return null;
		}
	}
	
	/**
	 * Removes the <code>hudItem</code> from the appropriate manager ArrayList.
	 * 
	 * @param hudItem		The HUDItem to be stricken from the registry.
	 */
	private void remove(HudItem hudItem) {
		getRegistry(hudItem).remove(hudItem);
		all.remove(hudItem);
	}
	
	/**
	 * The ID for the next HUDItem assigned.
	 */
	private int nextId = 0;
	
	/**
	 * Registry of all the assigned pre HUDItems.
	 * 
	 * @see HudItem.RenderPriority
	 */
	private ArrayList<HudItem> pre;
	
	/**
	 * Registry of all the assigned normal HUDItems.
	 * 
	 * @see HudItem.RenderPriority
	 */
	private ArrayList<HudItem> normal;
	
	/**
	 * Registry of all the assigned post HUDItems.
	 * 
	 * @see HudItem.RenderPriority
	 */
	private ArrayList<HudItem> post;
	
	/**
	 * Utility List of ALL registered HUDItems
	*/
	private ArrayList<HudItem> all;
}
