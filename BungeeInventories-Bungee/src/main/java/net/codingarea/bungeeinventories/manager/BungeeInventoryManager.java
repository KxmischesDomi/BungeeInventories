package net.codingarea.bungeeinventories.manager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.codingarea.bungeeinventories.utils.BungeeInventory;
import net.codingarea.bungeeinventories.utils.Factory;
import net.codingarea.bungeeinventories.utils.OpenInventoryType;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
public class BungeeInventoryManager {

	private static BungeeInventoryManager instance;

	private Factory<ProxiedPlayer, Boolean> maySendInventory;

	public BungeeInventoryManager() {
		instance = this;
		setMaySendInventory(player -> true);
	}

	public void setMaySendInventory(final @Nonnull Factory<ProxiedPlayer, Boolean> maySendInventory) {
		this.maySendInventory = maySendInventory;
	}

	public void sendInventory(final @Nonnull ProxiedPlayer player, final @Nonnull BungeeInventory inventory, final @Nonnull OpenInventoryType openInventoryType) {
		if (!maySendInventory.accept(player)) return;

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(player.getName());
		out.writeUTF(openInventoryType.name());
		out.writeUTF(inventory.toJSONObject().toString());

		player.getServer().getInfo().sendData("codingarea:bungeeinventories", out.toByteArray());
	}

	public void sendInventory(final @Nonnull ProxiedPlayer player, final @Nonnull BungeeInventory inventory) {
		this.sendInventory(player, inventory, OpenInventoryType.ALWAYS);
	}

	@Nonnull
	@CheckReturnValue
	protected Factory<ProxiedPlayer, Boolean> getMaySendInventory() {
		return maySendInventory;
	}

	@Nonnull
	@CheckReturnValue
	public static BungeeInventoryManager getInstance() {
		return instance;
	}

}