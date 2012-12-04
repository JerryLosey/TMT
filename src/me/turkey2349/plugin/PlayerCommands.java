package me.turkey2349.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCommands extends JavaPlugin implements CommandExecutor {

	TMT Maps;
	TMT MapsSpawn;
	
	private TMT plugin;
	public PlayerCommands(TMT plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("TMTjoin")) {
				// What to do when player joins match.
				// Call a method in TMT to add them to the ArrayList
				player.sendMessage("You Joined the Match!");
				
			}
			if (cmd.getName().equalsIgnoreCase("TMTleave")) {
				// What to do when player leaves match.
				// Call a method in TMT to remove them from the ArrayList
				player.sendMessage("You Left the Match!");
			}
			if (cmd.getName().equalsIgnoreCase("TMTfs")) {
				// What to do when player forces start of match.
				// Call a method in TMT to handle matches
				player.sendMessage("You forced the match to start!");
			}
			if (cmd.getName().equalsIgnoreCase("TMTcreate"))
			{
				if(args.length == 0)
		    	{
		    	    player.sendMessage("to create an arena you have to do /TMT create (arena name), Then you have to do /TMT ArenaSpawn (arena name)");
		    	}
		    	else if(args.length == 1)
		    	{
		    		Maps.Madd(args[1]);
		    		player.sendMessage("Arena " + args[1] + " has been created!");
		    	}
		    	else if(args.length > 1)
		    	{
		    		player.sendMessage("Invalid arena name");
		    	}
		    else if (args[0].equalsIgnoreCase("TMTas"))
			{
			    if(args.length == 1)
			    {
			    	player.sendMessage("please enter the name of the arena that you would like to set the spawnpoint of.");
			    }
			    if(args.length == 2)
			    {
			    	if(Maps.Mcontains(args[1]))
			        {
			    		String MSname = args[1] + ("'s Spawn");
			    		int MSnameCords = ;
			    	    MapsSpawn.MSadd(MSname);
			    	    
			        }
			    }
			 }

			}
		} else {
			// Denies access to console users
			sender.sendMessage("Only in-game players can use this command!");
			return false;
		}
		return false;
		
	}
}
