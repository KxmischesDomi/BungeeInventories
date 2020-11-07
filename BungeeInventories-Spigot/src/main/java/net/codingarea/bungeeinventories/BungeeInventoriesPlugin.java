package net.codingarea.bungeeinventories;

import net.codingarea.bungeeinventories.listener.ProxyMessagingListener;
import net.codingarea.bungeeinventories.manager.BungeeInventoriesManager;
import org.bukkit.plugin.java.JavaPlugin;

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
public class BungeeInventoriesPlugin extends JavaPlugin {

	private static BungeeInventoriesPlugin instance;

	private final BungeeInventoriesManager manager = new BungeeInventoriesManager();

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		registerListener();
	}

	private void registerListener() {
		getServer().getMessenger().registerIncomingPluginChannel(this, "bungee:invs", new ProxyMessagingListener());
	}

	@Nonnull
	public BungeeInventoriesManager getManager() {
		return manager;
	}

	@Nonnull
	public static BungeeInventoriesPlugin getInstance() {
		return instance;
	}

}