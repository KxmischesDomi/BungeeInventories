package net.codingarea.bungeeinventories.utils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

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
public class Enchantment {

	private final String enchantment;
	private int level = 1;

	public Enchantment(final @Nonnull String enchantment, final @Nonnull int level) {
		this.enchantment = enchantment;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	@Nonnull
	@CheckReturnValue
	public String getEnchantment() {
		return enchantment;
	}

	@Nonnull
	@CheckReturnValue
	public JSONObject toJSONObject() {
		final JSONObject object = new JSONObject();
		try {
			object.put("enchantment", enchantment);
			object.put("level", level);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}

}