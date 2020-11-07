package net.codingarea.bungeeinventories.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * ⠀
 * .d88b          8 w                  db
 * 8P    .d8b. .d88 w 8d8b. .d88      dPYb   8d8b .d88b .d88
 * 8b    8' .8 8  8 8 8P Y8 8  8     dPwwYb  8P   8.dP' 8  8
 * `Y88P `Y8P' `Y88 8 8   8 `Y88    dP    Yb 8    `Y88P `Y88
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀wwdP
 *
 * @author Dominik https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeInventory {

	private String title;
	private int size;
	private String type;
	private final Map<Integer, ItemStack> contents = new HashMap<>();

	public BungeeInventory(final @Nonnull String title, final int size) {
		this.title = title;
		this.size = size;
		this.type = InventoryType.CHEST.name();
	}

	public BungeeInventory(final @Nonnull String title, final @Nonnull InventoryType type) {
		this.title = title;
		this.size = type.getDefaultSize();
		this.type = type.name();
	}

	public BungeeInventory setTitle(final @Nonnull String title) {
		this.title = title;
		return this;
	}

	public BungeeInventory setSize(final int size) {
		this.size = size;
		return this;
	}

	public BungeeInventory addItem(final @Nonnull ItemStack itemStack) {
		Integer slot = getFirstEmptySlot();
		if (slot == null) throw new IllegalArgumentException("Inventory is full");
		contents.put(slot, itemStack);
		return this;
	}

	public BungeeInventory setItem(final int slot, final @Nullable ItemStack itemStack) {
		if (itemStack == null) {
			contents.remove(slot);
			return this;
		}
		contents.put(slot, itemStack);
		return this;
	}

	public void setType(final @Nonnull InventoryType type) {
		this.type = type.name();
	}

	public void setType(final @Nonnull String type) {
		this.type = type;
	}

	@Nullable
	@CheckReturnValue
	private Integer getFirstEmptySlot() {
		for (int i = 0; i < size; i++) {
			if (getItem(i) == null) return i;
		}
		return null;
	}

	@Nullable
	@CheckReturnValue
	public ItemStack getItem(final int slot) {
		return contents.get(slot);
	}

	@CheckReturnValue
	public int getSize() {
		return size;
	}

	@Nonnull
	@CheckReturnValue
	public String getType() {
		return type;
	}

	@CheckReturnValue
	public Map<Integer, ItemStack> getContents() {
		return contents;
	}

	@CheckReturnValue
	public String getTitle() {
		return title;
	}

	@Nonnull
	@CheckReturnValue
	public JSONObject toJSONObject() {
		final JSONObject object = new JSONObject();

		try {
			object.put("title", title);
			object.put("size", size);
			object.put("type", type);

			JSONArray itemStacks = new JSONArray();
			for (int i = 0; i < size; i++) {
				if (contents.isEmpty()) break;
				ItemStack itemStack = contents.remove(i);
				if (itemStack == null) {
					itemStacks.put(JSONObject.NULL);
					continue;
				}
				itemStacks.put(itemStack.toJSONObject());
			}
			object.put("contents", itemStacks);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return object;
	}

}