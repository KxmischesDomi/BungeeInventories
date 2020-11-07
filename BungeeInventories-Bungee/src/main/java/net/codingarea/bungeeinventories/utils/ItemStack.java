package net.codingarea.bungeeinventories.utils;

import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.*;

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
public class ItemStack {

	/**
	 * TODO: ADD: ADD METHODS
	 */

	private String material = "PAPER",
			       displayName = "PAPER";
	private int amount = 1;
	private List<String> lore = new ArrayList<>();
	private String[] itemFlags = {};
	private Enchantment[] enchantments = {};
	private final Map<String, Object> attributes = new HashMap<>();
	private short damage = 0;
	private boolean unbreakable = false;

	public ItemStack(final @Nonnull String material) {
		this.material = material.toUpperCase();
		this.displayName = Utils.getEnumName(material);
	}

	public ItemStack(final @Nonnull String material, final @Nonnull String displayName) {
		this.material = material.toUpperCase();
		this.displayName = displayName;
	}

	public ItemStack(final @Nonnull String material, final @Nonnull String displayName, final @Nonnull String... lore) {
		this.material = material.toUpperCase();
		this.displayName = displayName;
		this.lore = new ArrayList<>(Arrays.asList(lore));
	}

	public ItemStack setDisplayName(final @Nonnull String displayName) {
		this.displayName = displayName;
		return this;
	}

	public ItemStack setLore(final @Nonnull List<String> lore) {
		this.lore = lore;
		return this;
	}

	public ItemStack setMaterial(final @Nonnull String material) {
		this.material = material.toUpperCase();
		return this;
	}

	public ItemStack setEnchantments(final @Nonnull Enchantment[] enchantments) {
		this.enchantments = enchantments;
		return this;
	}

	public ItemStack setUnbreakable(final boolean unbreakable) {
		this.unbreakable = unbreakable;
		return this;
	}

	public ItemStack setItemFlags(final @Nonnull String[] itemFlags) {
		this.itemFlags = itemFlags;
		return this;
	}

	public ItemStack setAttribute(final @Nonnull String attribute, final Object value) {
		attributes.put(attribute, value);
		return this;
	}

	public void setDamage(final short damage) {
		this.damage = damage;
	}

	public ItemStack addItemFlag(final @Nonnull String itemFlag) {
		itemFlags = Utils.addElememtToArray(itemFlags, itemFlag);
		return this;
	}

	public ItemStack addEnchantment(final @Nonnull Enchantment enchantment) {
		Iterator<Enchantment> e = Arrays.stream(enchantments).iterator();

		e.forEachRemaining(ench -> {
			if (ench.getEnchantment().equals(enchantment.getEnchantment())) {
				e.remove();
			}
		});

		List<Enchantment> enchantments = Lists.newArrayList(e);
		enchantments.add(enchantment);
		this.enchantments = Utils.toArray(enchantments);
		return this;
	}

	public ItemStack addEnchantment(final @Nonnull String enchantment, final int level) {
		addEnchantment(new Enchantment(enchantment, level));
		return this;
	}

	public ItemStack removeEnchantment(final @Nonnull String enchantment) {
		Iterator<Enchantment> e = Arrays.stream(enchantments).iterator();

		e.forEachRemaining(ench -> {
			if (ench.getEnchantment().equals(enchantment)) {
				e.remove();
			}
		});

		enchantments = Utils.toArray(Lists.newArrayList(e));
		return this;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}

	@CheckReturnValue
	public int getAmount() {
		return amount;
	}

	@Nonnull
	@CheckReturnValue
	public Enchantment[] getEnchantments() {
		return enchantments;
	}

	@CheckReturnValue
	public boolean isUnbreakable() {
		return unbreakable;
	}

	@Nonnull
	@CheckReturnValue
	public String getDisplayName() {
		return displayName;
	}

	@Nonnull
	@CheckReturnValue
	public String getMaterial() {
		return material;
	}

	@Nonnull
	@CheckReturnValue
	public String[] getItemFlags() {
		return itemFlags;
	}

	@Nonnull
	@CheckReturnValue
	public List<String> getLore() {
		return lore;
	}

	@Nonnull
	@CheckReturnValue
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@CheckReturnValue
	public short getDamage() {
		return damage;
	}

	@Nonnull
	@CheckReturnValue
	public JSONObject toJSONObject() {
		final JSONObject object = new JSONObject();
		try {
			object.put("material", material);
			object.put("displayName", displayName);
			object.put("amount", amount);
			object.put("lore", Utils.toArray(lore));
			object.put("damage", damage);
			object.put("itemFlags", itemFlags);
			object.put("attributes", new JSONObject(attributes));
			object.put("unbreakable", unbreakable);

			final JSONArray enchantments = new JSONArray();
			if (this.enchantments != null) {
				Arrays.stream(this.enchantments).forEach(enchantment -> enchantments.put(enchantment.toJSONObject()));
			}
			object.put("enchantments", enchantments);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}

}