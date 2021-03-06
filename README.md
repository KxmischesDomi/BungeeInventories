# BungeeInventories [Archived]
 
## A simple API to send Inventories to Spigot Servers and let players open them. 

### Setup
[Download](https://github.com/KxmischesDomi/BungeeInventories/releases) the newest version of the API's and import them into you're plugins
You don't have to make a Spigot Plugin with the Library, only if you want to change something described in the following spigot documentation
Don't export the Libraries, just put them on your servers as plugins.

### Development
#### Bungeecord

##### Creating an Inventory:

```java
BungeeInventory chestInventory = new BungeeInventory("§7Server-GUI", 3*9);

BungeeInventory furnaceInventory = new BungeeInventory("§7Multi Server Furnace", InventoryType.FURNACE);
```

<br>

##### Filling an Inventory:

```java
ItemStack lobbyItem = new ItemStack("CLOCK", "§6Lobby", "§7Click to be send to the Lobby");
ItemStack pvpItem = new ItemStack("DIAMOND_SWORD", "§bPvP");

inventory.addItem(pvpItem);
inventory.setItem(15, lobbyItem);
```


<br>

##### Sending an Inventory:

You can choose an <code>OpenInventoryType</code> to choose when the inventory should open or not.
Default value is <code>OpenInventoryType.ALWAYS</code>

```java
BungeeInventoryManager.getInstance().sendInventory(player, inventory);

BungeeInventoryManager.getInstance().sendInventory(player, inventory, OpenInventoryType.ONLY_WHEN_INVENTORY_CLOSED);
```
<br>

#### Overwrite <code>maySendInventory</code>

With overwriting this <code>Factory</code> you can block sending specified inventories when a condition is true or lots of other Things.
This <code>Factory</code> returns a <code>boolean</code>.

```java
BungeeInventoryManager.getInstance().setMaySendInventory((player, inventory) -> {
	if (...) {
		// Inventory will be sent to Spigot Server
		return true;
	}
	// Inventory will not be sent to Spigot Server
	return false;
 });
```
<br>

#### Spigot

##### Overwrite <code>mayOpenInventory</code>
With overwriting this <code>Factory</code> you can block sending specified inventories when a condition is true or lots of other Things like play Sounds.
This <code>Factory</code> returns a <code>boolean</code>.

```java
BungeeInventoryManager.getInstance().setMayOpenInventory((player, inventory) -> {
	if (...) {
		// Inventory will be opened
		return true;
	}
	// Inventory will not be opened
	return false;
 });
```
