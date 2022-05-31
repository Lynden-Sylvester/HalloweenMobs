package me.lyndensylvester.halloween.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;

public class HalloweenCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players may execute this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("Halloween")) {
			p.sendMessage("Happy Halloween >:)");
			
			Location spawnLoc = p.getLocation().add(2,0,0);
			World world = p.getWorld();
			
			@SuppressWarnings("unused")
			Skeleton skeleton = (Skeleton) world.spawnEntity(spawnLoc, EntityType.SKELETON);
			
			return true;
		}
		return true;	
		/**	For multiple commands in one file
		 *	if (cmd.getName().equalsIgnoreCase("Hello"){
		 * 
		 *	}
		**/
		
	}
	
}