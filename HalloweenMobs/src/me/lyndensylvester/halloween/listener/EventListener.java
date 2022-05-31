package me.lyndensylvester.halloween.listener;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import me.lyndensylvester.halloween.utils.Utils;
import me.lyndensylvester.halloween.Main;
import me.lyndensylvester.halloween.items.CustomBow;
import me.lyndensylvester.halloween.items.ItemManager;

public class EventListener implements Listener {

	
	private final Main plugin;
	private final ItemManager itemManager;
	private CustomBow customBow;
	
	public EventListener(Main plugin, ItemManager itemManager, CustomBow customBow) {
		this.plugin = plugin;
		this.itemManager = itemManager;
		this.customBow = customBow;
	}
	
	Player p;
	Block b;
	EntityType t;
	
	
	/**
	 * BlockBreak event
	 * 
	 * Detect if the player broke a melon block
	 * and send a success or failure spawn message
	 * 
	 * @param event
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		p = (Player) event.getPlayer();
		b = (Block) event.getBlock();

		if (b.getType() == Material.MELON) {
			Random rand = new Random();
			var max = 100;
			var min = 0;
			int chance = rand.nextInt(max - min + 1)  + min;
			
			if (chance > plugin.getConfig().getInt("Summon_Messages.Melon_Minion.Spawn_Chance")) {
				Location spawnLoc = p.getLocation().add(2,0,0);
				World world = p.getWorld();
				@SuppressWarnings("unused")
				Skeleton skeleton = (Skeleton) world.spawnEntity(spawnLoc, EntityType.SKELETON);
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Summon_Messages.Melon_Minion.Melon_Success").replace("<player>", p.getName())));
			}
			else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Summon_Messages.Melon_Minion.Melon_Fail").replace("<player>", p.getName())));
			}
		}
		if (b.getType() == Material.PUMPKIN) {
			Random rand = new Random();
			var max = 100;
			var min = 0;
			int chance = rand.nextInt(max - min + 1) + min;
			if (chance > plugin.getConfig().getInt("Summon_Messages.Pumpkin_Minion.Spawn_Chance")) {
				Location spawnLoc = p.getLocation().add(2,0,0);
				World world = p.getWorld();
				@SuppressWarnings("unused")
				Zombie zombie = (Zombie) world.spawnEntity(spawnLoc, EntityType.ZOMBIE);
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Summon_Messages.Pumpkin_Minion.Pumpkin_Success").replace("<player>", p.getName())));

			}
			else {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Summon_Messages.Pumpkin_Minion.Pumpkin_Fail").replace("<player>", p.getName())));
			}
		}
		if (plugin.getConfig().getBoolean("Spawn_Custom_Mobs.Spawn") == false) {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("Spawn_Custom_Mobs.Mobs")));
		}
	}

	/**
	 * EntitySpawn event
	 * 
	 * Spawn a skeleton 2 blocks to the right of the player
	 * 
	 * @param event
	 */
	@EventHandler
	public void spawn(EntitySpawnEvent event) {
			Entity entity = event.getEntity();
			
		if ((entity instanceof Skeleton) && ((plugin.getConfig().getBoolean("Spawn_Custom_Mobs.Spawn") == true) || (plugin.getConfig().getBoolean("Summon_Messages.Melon_Minion.Spawn") == true))) {

			EntityEquipment  ee = ((Skeleton)entity).getEquipment();
			ee.setHelmet(new ItemStack(Material.MELON));
			ee.setHelmetDropChance(100);
			
			ee.setItemInMainHand(customBow.getBowItem());
			ee.setItemInMainHandDropChance(50);

		}

		if ((entity instanceof Zombie) && ((plugin.getConfig().getBoolean("Spawn_Custom_Mobs.Spawn") == true) || (plugin.getConfig().getBoolean("Summon_Messages.Pumpkin_Minion.Spawn") == true))) {

			EntityEquipment  ee = ((Zombie)entity).getEquipment();
			ee.setHelmet(new ItemStack(Material.PUMPKIN));
			ee.setHelmetDropChance(100);
			
			ee.setItemInMainHand(itemManager.getSwordItem());
			ee.setItemInMainHandDropChance(50);
		}			
	}
	
	/**
	 * EntityDeath event
	 * 
	 * Add custom Drops to the mob
	 * 
	 * @param event
	 */
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		LivingEntity e = event.getEntity();
		
		Random rand = new Random();
		var max = 100;
		var min = 0;
		int chance = rand.nextInt(max - min + 1)  + min;
		if ((e instanceof Skeleton) || (e instanceof Zombie)) {
			Bukkit.broadcastMessage("Drop chance: " + chance);
			// 50%
			if (chance < 50) {
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.COAL));
			}
			// 25%
			else if ((49 < chance) && (chance < 75)) {
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.IRON_INGOT));
			}
			// 13%
			else if ((74 < chance) && (chance < 88)) {
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.GOLD_INGOT));
			}
			// 7%
			else if ((87 < chance) && (chance < 95))
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.REDSTONE));
			}
			// 4%
			else if ((94 < chance) && (chance < 99)) {
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.DIAMOND));
			}
			// 2%
			else if (chance == 100){
				e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.EMERALD));
			}
	}
}
