package net.codingarea.bungeeinventories.manager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.codingarea.bungeeinventories.BungeeInventoriesPlugin;
import net.codingarea.bungeeinventories.utils.BiFactory;
import net.codingarea.bungeeinventories.utils.BungeeInventory;
import net.codingarea.bungeeinventories.utils.Factory;
import net.codingarea.bungeeinventories.utils.OpenInventoryType;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.UUID;

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
public class BungeeInventoryManager {
	
	private static final BungeeInventoriesPlugin plugin = BungeeInventoriesPlugin.getInstance();
	private static BungeeInventoryManager instance;

	private BiFactory<ProxiedPlayer, BungeeInventory, Boolean> maySendInventory;

	public BungeeInventoryManager() {
		instance = this;
		setMaySendInventory((player, inventory) -> true);
	}

	public void setMaySendInventory(BiFactory<ProxiedPlayer, BungeeInventory, Boolean> maySendInventory) {
		this.maySendInventory = maySendInventory;
	}

	public void sendInventory(final @Nonnull ProxiedPlayer player, final @Nonnull BungeeInventory inventory, final @Nonnull OpenInventoryType openInventoryType) {
		if (!maySendInventory.accept(player, inventory)) return;

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(player.getName());
		out.writeUTF(openInventoryType.name());
		out.writeUTF(inventory.toJSONObject().toString());

		player.getServer().getInfo().sendData("bungee:invs", out.toByteArray());
	}

	/**
	 * @throws NullPointerException when player is not online
	 */
	public void sendInventory(final @Nonnull String name, final @Nonnull BungeeInventory inventory) {
		this.sendInventory(plugin.getProxy().getPlayer(name), inventory);
	}

	/**
	 * @throws NullPointerException when player is not online
	 */
	public void sendInventory(final @Nonnull UUID uuid, final @Nonnull BungeeInventory inventory) {
		this.sendInventory(plugin.getProxy().getPlayer(uuid), inventory);
	}

	/**
	 * @throws NullPointerException when player is not online
	 */
	public void sendInventory(final @Nonnull String name, final @Nonnull BungeeInventory inventory, OpenInventoryType type) {
		this.sendInventory(plugin.getProxy().getPlayer(name), inventory, type);
	}

	/**
	 * @throws NullPointerException when player is not online
	 */
	public void sendInventory(final @Nonnull UUID uuid, final @Nonnull BungeeInventory inventory, OpenInventoryType type) {
		this.sendInventory(plugin.getProxy().getPlayer(uuid), inventory, type);
	}

	public void sendInventory(final @Nonnull ProxiedPlayer player, final @Nonnull BungeeInventory inventory) {
		this.sendInventory(player, inventory, OpenInventoryType.ALWAYS);
	}

	@Nonnull
	@CheckReturnValue
	public BiFactory<ProxiedPlayer, BungeeInventory, Boolean> getMaySendInventory() {
		return maySendInventory;
	}

	@Nonnull
	@CheckReturnValue
	public static BungeeInventoryManager getInstance() {
		return instance;
	}

}