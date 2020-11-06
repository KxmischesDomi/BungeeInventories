package net.codingarea.bungeeinventories.utils;

import javax.annotation.CheckReturnValue;

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
public enum InventoryType {

	CHEST(27),
	DISPENSER(9),
	DROPPER(9),
	FURNACE(3),
	WORKBENCH(10),
	ENCHANTING(2),
	BREWING(5),
	ANVIL(3),
	BEACON(1),
	HOPPER(5);

	private final int defaultSize;

	InventoryType(int defaultSize) {
		this.defaultSize = defaultSize;
	}

	@CheckReturnValue
	public int getDefaultSize() {
		return defaultSize;
	}

}