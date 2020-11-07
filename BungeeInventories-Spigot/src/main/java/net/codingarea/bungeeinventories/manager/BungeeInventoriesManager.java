package net.codingarea.bungeeinventories.manager;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.codingarea.bungeeinventories.utils.BiFactory;
import net.codingarea.bungeeinventories.utils.Factory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.List;

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
public class BungeeInventoriesManager {

	private static BungeeInventoriesManager instance;

	private BiFactory<Player, Inventory, Boolean> mayOpenInventory;

	public BungeeInventoriesManager() {
		instance = this;
		setMayOpenInventory((player, inventory) -> true);
	}

	@Nullable
	@CheckReturnValue
	public Inventory generateInventory(final @Nonnull JSONObject object) {
		try {

			InventoryType type = InventoryType.valueOf((String) object.get("type"));
			int size = ((Long) object.get("size")).intValue();
			String title = (String) object.get("title");

			Inventory inventory;

			if (type == InventoryType.CHEST) {
				inventory = Bukkit.createInventory(null, size, title);
			} else {
				inventory = Bukkit.createInventory(null, type, title);
			}

			try {

				JSONArray array = (JSONArray) object.get("contents");

				for (int i = 0; i < inventory.getSize(); i++) {
					if (i >= array.size()) break;
					if (array.get(i) == null) continue;
					ItemStack itemStack = generateItemStack((JSONObject) array.get(i));
					if (itemStack == null) continue;
					inventory.addItem(itemStack);
				}

			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}

			return inventory;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return null;
	}

	@Nullable
	@CheckReturnValue
	public ItemStack generateItemStack(final @Nonnull JSONObject object) {
		try {
			ItemStack itemStack = new ItemStack(Material.valueOf((String) object.get("material")));
			itemStack.setAmount(((Long) object.get("amount")).intValue());
			itemStack.setDurability(((Long) object.get("damage")).shortValue());

			ItemMeta itemMeta = itemStack.getItemMeta();

			if ((boolean) object.get("unbreakable")) {
				itemStack = NBTEditor.set(itemStack, (byte) 1, "Unbreakable");
			}
			itemMeta.setDisplayName((String) object.get("displayName"));
			itemMeta.setLore((List<String>) object.get("lore"));

			JSONArray jsonArray = (JSONArray) object.get("enchantments");
			for (Object o : jsonArray) {
				JSONObject jsonObject = ((JSONObject) o);
				itemStack.addUnsafeEnchantment(Enchantment.getByName((String) jsonObject.get("enchantment")), ((Long) jsonObject.get("level")).intValue());
			}

			return itemStack;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		return null;
	}

	@Nonnull
	@CheckReturnValue
	public void setMayOpenInventory(BiFactory<Player, Inventory, Boolean> mayOpenInventory) {
		this.mayOpenInventory = mayOpenInventory;
	}

	@Nonnull
	@CheckReturnValue
	public BiFactory<Player, Inventory, Boolean> getMayOpenInventory() {
		return mayOpenInventory;
	}

	@Nonnull
	@CheckReturnValue
	public static BungeeInventoriesManager getInstance() {
		return instance;
	}

}