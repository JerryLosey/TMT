package me.turkey2349.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCommands extends JavaPlugin implements CommandExecutor {

	private TMT plugin;
	public PlayerCommands(TMT plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Need to make sure CommandSender is of type Player (don't want console doing this yet)
		if (sender instanceof Player){
			Player player = (Player) sender;
			//commands
			if (cmd.getName().equalsIgnoreCase("tjoin")) {
				// What to do when player joins match.
				// Call a method in TMT to add them to the ArrayList
				player.sendMessage("You Joined the Match!");
				
			}
			if (cmd.getName().equalsIgnoreCase("tLeave")) {
				// What to do when player leaves match.
				// Call a method in TMT to remove them from the ArrayList
				player.sendMessage("You Left the Match!");
			}
			if (cmd.getName().equalsIgnoreCase("tfs")) {
				// What to do when player leaves match.
				// Call a method in TMT to remove them from the ArrayList
				player.sendMessage("You forced the match to start!");
			}
		} else {
			// Denies access to console users
			sender.sendMessage("Only in-game players can use this command!");
			return false;
		}
		return false;
		
	}
}
