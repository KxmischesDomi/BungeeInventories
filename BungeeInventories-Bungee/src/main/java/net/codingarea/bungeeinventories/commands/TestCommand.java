package net.codingarea.bungeeinventories.commands;

import net.codingarea.bungeeinventories.manager.BungeeInventoryManager;
import net.codingarea.bungeeinventories.utils.BungeeInventory;
import net.codingarea.bungeeinventories.utils.ItemStack;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

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
public class TestCommand extends Command {

	private static final BungeeInventoryManager manager = BungeeInventoryManager.getInstance();

	public TestCommand() {
		super("inventory");
	}

	@Override
	public void execute(CommandSender sender, String[] strings) {

		ProxiedPlayer player = ((ProxiedPlayer) sender);

		BungeeInventory inventory = new BungeeInventory("§aTest", 9);
		inventory.setItem(0, new ItemStack("PAPER", "§aPaper"));

		manager.sendInventory(player, inventory);
	}

}