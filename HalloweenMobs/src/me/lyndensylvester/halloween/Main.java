package me.lyndensylvester.halloween;


import org.bukkit.plugin.java.JavaPlugin;

import me.lyndensylvester.halloween.commands.HalloweenCommand;
import me.lyndensylvester.halloween.items.CustomBow;
import me.lyndensylvester.halloween.items.ItemManager;
import me.lyndensylvester.halloween.listener.EventListener;


public class Main extends JavaPlugin {

	private ItemManager itemManager;
	private CustomBow customBow;
	
	@Override
	public void onEnable() {
		this.itemManager = new ItemManager(this);
		this.customBow = new CustomBow(this);
		getServer().getPluginManager().registerEvents(new EventListener(this, itemManager, customBow), this);
		getCommand("Halloween").setExecutor(new HalloweenCommand());
	}	
}
