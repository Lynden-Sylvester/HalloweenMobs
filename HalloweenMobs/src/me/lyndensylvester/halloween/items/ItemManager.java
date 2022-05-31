package me.lyndensylvester.halloween.items;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import me.lyndensylvester.halloween.Main;

public class ItemManager {
	
	@SuppressWarnings("unused")
	private final Main plugin;
	private final ItemStack swordItem;
	
	public ItemManager(Main plugin) {
		this.plugin = plugin;
		
		ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Super Sword");
		List<String> lore = new ArrayList<>();
		lore.add("This is the most powerful sword");
		lore.add("in all of Minecraft's history");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		
		this.swordItem = item;
		
		//Shaped recipe
		ShapedRecipe sr = new ShapedRecipe(new NamespacedKey(plugin, "superSword"), item);
		sr.shape("I  ",
				 " I ",
				 "  S");
		sr.setIngredient('I', Material.IRON_BLOCK);
		sr.setIngredient('S', Material.SLIME_BLOCK);
		Bukkit.getServer().addRecipe(sr);
		
		
		//Shapeless Recipe
		ShapelessRecipe sir = new ShapelessRecipe(new NamespacedKey(plugin, "shapeless_superSword"), item);
		sir.addIngredient(2, Material.IRON_BLOCK);
		sir.addIngredient(Material.SLIME_BLOCK);
		Bukkit.getServer().addRecipe(sir);
				
		//Furnace Recipe
		FurnaceRecipe smelt = new FurnaceRecipe(new NamespacedKey(plugin, "smelt_superSword"), item, Material.EMERALD, 5.0f, 10 * 20);
		Bukkit.getServer().addRecipe(smelt);

	}
	
	public ItemStack getSwordItem() {
		return this.swordItem;
	}
	
}
