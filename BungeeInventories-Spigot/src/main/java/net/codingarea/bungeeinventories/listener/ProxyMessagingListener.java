package net.codingarea.bungeeinventories.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.codingarea.bungeeinventories.manager.BungeeInventoriesManager;
import net.codingarea.bungeeinventories.utils.OpenInventoryType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * ⠀
 * .d88b          8 w                  db
 * 8P    .d8b. .d88 w 8d8b. .d88      dPYb   8d8b .d88b .d88
 * 8b    8' .8 8  8 8 8P Y8 8  8     dPwwYb  8P   8.dP' 8  8
 * `Y88P `Y8P' `Y88 8 8   8 `Y88    dP    Yb 8    `Y88P `Y88
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀wwdP
 *
 * @author Dominik https://github.com/kxmischesdomi
 * @author anweisen https://github.com/anweisen
 * @since 1.0
 */
public class ProxyMessagingListener implements PluginMessageListener {

	private final BungeeInventoriesManager manager = BungeeInventoriesManager.getInstance();

	@Override
	public void onPluginMessageReceived(String channel, Player unused, byte[] data) {
		if (!channel.equals("bungee:invs")) return;

		try {
			ByteArrayDataInput in = ByteStreams.newDataInput(data);

			Player player = Bukkit.getPlayer(in.readUTF());
			if (player == null) return;

			OpenInventoryType type = OpenInventoryType.valueOf(in.readUTF());
			if (type == OpenInventoryType.ONLY_WHEN_INVENTORY_CLOSED
					&& (player.getOpenInventory() != null || player.getOpenInventory().getTopInventory() != null)) return;

			JSONParser parser = new JSONParser();
			String jsonString = in.readUTF();
			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
			Inventory inventory = manager.generateInventory(jsonObject);

			if (!manager.getMayOpenInventory().accept(player, inventory)) return;

			player.openInventory(inventory);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}