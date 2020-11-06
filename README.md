# BungeeInventories
 
## A simple API to send Inventories to Spigot Servers and let players open them. 

### Setup
Download the newest version of the API's and import the Bungee api into a bungeecord plugin and the Spigot api into a spigot plugin

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

inventory.setItem(13, pvpItem);
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
BungeeInventoryManager.getInstance().setMaySendInventory(player -> {
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
BungeeInventoryManager.getInstance().setMayOpenInventory(player -> {
	if (...) {
		// Inventory will be opened
		return true;
	}
	// Inventory will not be opened
	return false;
 });
```
