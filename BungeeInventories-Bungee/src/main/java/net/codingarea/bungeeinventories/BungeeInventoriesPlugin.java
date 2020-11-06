package net.codingarea.bungeeinventories;

import net.codingarea.bungeeinventories.commands.TestCommand;
import net.codingarea.bungeeinventories.manager.BungeeInventoryManager;
import net.codingarea.bungeeinventories.utils.BungeeInventory;
import net.codingarea.bungeeinventories.utils.ItemStack;
import net.md_5.bungee.api.plugin.Plugin;

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
public class BungeeInventoriesPlugin extends Plugin {

	private static BungeeInventoriesPlugin instance;

	private final BungeeInventoryManager manager = new BungeeInventoryManager();;

	@Override
	public void onLoad() {
		instance = this;

		getProxy().registerChannel("codingarea:bungeeinventories");

		//getProxy().getPluginManager().registerCommand(this, new TestCommand());
	}

	@Nonnull
	@CheckReturnValue
	public BungeeInventoryManager getManager() {
		return manager;
	}

	@Nonnull
	@CheckReturnValue
	public static BungeeInventoriesPlugin getInstance() {
		return instance;
	}

}