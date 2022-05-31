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

public class CustomBow {
	
	@SuppressWarnings("unused")
	private final Main plugin;
	private final ItemStack bowItem;
	
	public CustomBow(Main plugin) {
		this.plugin = plugin;
		
		ItemStack item = new ItemStack(Material.BOW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Super Bow");
		List<String> lore = new ArrayList<>();
		lore.add("This is the most powerful Bow");
		lore.add("in all of Minecraft's history");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		
		this.bowItem = item;
		
		//Shaped recipe
		ShapedRecipe sr = new ShapedRecipe(new NamespacedKey(plugin, "superBow"), item);
		sr.shape("CG ",
				 "C G",
				 "CG ");
		sr.setIngredient('C', Material.COAL_BLOCK);
		sr.setIngredient('G', Material.GOLD_BLOCK);
		Bukkit.getServer().addRecipe(sr);
		
		
		//Shapeless Recipe
		ShapelessRecipe sir = new ShapelessRecipe(new NamespacedKey(plugin, "shapeless_superBow"), item);
		sir.addIngredient(2, Material.COAL_BLOCK);
		sir.addIngredient(Material.GOLD_BLOCK);
		Bukkit.getServer().addRecipe(sir);
				
		//Furnace Recipe
		FurnaceRecipe smelt = new FurnaceRecipe(new NamespacedKey(plugin, "smelt_superBow"), item, Material.LAPIS_LAZULI, 5.0f, 10 * 20);
		Bukkit.getServer().addRecipe(smelt);

	}
	
	public ItemStack getBowItem() {
		return this.bowItem;
	}
	
}
