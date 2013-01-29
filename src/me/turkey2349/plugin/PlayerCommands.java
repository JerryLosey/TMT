package me.turkey2349.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCommands extends JavaPlugin implements CommandExecutor {

	TMT Maps;
	TMT players;
	TMT JoinedPlayers;
	TMT MapsVeto;
	
	public int VetoCount;
	
	private TMT plugin;
	public PlayerCommands(TMT plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			String name = player.getName();
			
			if (cmd.getName().equalsIgnoreCase("TMTjoin")) 
			{
				if(getConfig().getBoolean("Force Join on Game Start:") == false)
				{
				    plugin.Jadd(name);
				    player.sendMessage(ChatColor.GREEN + "You Joined the Match!");
				}
				else if(getConfig().getBoolean("Force Join on Game Start:") == true)
				{
				    player.sendMessage(ChatColor.RED + "You cannot Join The Game!");
				}
				
				
			}
			if (cmd.getName().equalsIgnoreCase("TMTleave")) 
			{
				plugin.Jremove(name);
				player.sendMessage(ChatColor.BLUE +  "You Left the Match!");
			}
			if (cmd.getName().equalsIgnoreCase("TMTreset")) 
			{
				// Clear the players hashmap
				// Essentially allows admin to reset the player roles!
				player.sendMessage(ChatColor.BLUE + "You reset the players!");
				plugin.clearPlayers();
			}
			if (cmd.getName().equalsIgnoreCase("TMTfs")) 
			{
				// What to do when player forces start of match.
				// Call a method in TMT to handle matches
				player.sendMessage(ChatColor.BLUE + "You forced the match to start!");
				plugin.forceStart();
			}
			/*if (cmd.getName().equalsIgnoreCase("TMTcreate"))
			{
				if(args.length == 0)
		    	{
		    	    player.sendMessage(ChatColor.BLUE + "to create an arena you have to do /TMT create (arena name), Then you have to do /TMT ArenaSpawn (arena name)");
		    	}
		    	else if(args.length == 1)
		    	{
		    		if(plugin.getcustomConfig().contains("number of arenas"))
		    		{
		    		    int NA = (int) plugin.getcustomConfig().get("number of arenas");
		    		    NA ++;
		    		    plugin.getcustomConfig().set("number of arenas", NA);
		    		    plugin.getcustomConfig().addDefault("Arena" + NA ,args[0]);
		    		    plugin.getcustomConfig().addDefault(args[0], "");
		    		}
		    		else
		    		{
		    			plugin.getcustomConfig().set("number of arenas", "1");
		    			plugin.getcustomConfig().addDefault(args[0], "");
		    			int NA = (int) plugin.getcustomConfig().get("number of arenas");
			    		NA ++;
			    		plugin.getcustomConfig().set("number of arenas", NA);
			    		plugin.getcustomConfig().addDefault("Arena" + NA ,args[0]);
			    		plugin.getcustomConfig().addDefault(args[0], "");
		    		}
		    		
		    		player.sendMessage(ChatColor.GREEN + "Arena " + args[0] + " has been created!");
		    	}
		    	else if(args.length > 1)
		    	{
		    		player.sendMessage(ChatColor.RED + "Invalid arena name");
		    	}
			}
		    if (cmd.getName().equalsIgnoreCase("TMTArenaSpawn"))
			{
			    if(args.length == 0)
			    {
			    	player.sendMessage(ChatColor.RED + "please enter the name of the arena that you would like to set the spawnpoint of.");
			    }
			    if(args.length == 1)
			    {
			    	if((boolean) plugin.getcustomConfig().get(args[0]) == false)
			    	{
			    		plugin.getcustomConfig().set(args[0], (Location) player.getLocation());
			    	}
			    	if((boolean) plugin.getcustomConfig().get(args[0]) == true)
			    	{
			    		player.sendMessage(ChatColor.RED + "That Arena Already Exsists");
			    	}
			    }
			}
		    if (cmd.getName().equalsIgnoreCase("TMTDefultSpawn"))
		    {
		    	plugin.getcustomConfig().set("DefultSpawn", (Location) player.getLocation());
		    }
		    if (cmd.getName().equalsIgnoreCase("TMTVeto"))
		    {
		    	if(MapsVeto.MVcontains(name))
		    	{
		    		player.sendMessage(ChatColor.RED + "You have already Voted!!");
		    	}
		    	else
		    	{
		    		MapsVeto.MVadd(player.getPlayerListName());
		    	    VetoCount++;
		    	    player.sendMessage(ChatColor.GREEN + "You have voted to change the map!");
		    	}
		    	if (VetoCount == getConfig().getInt("Votes to Veto"))
		    	{
		    		plugin.ArenaSetup();
		    	}
		    }*/
		} 
		else 
		{
			// Denies access to console users
			sender.sendMessage(ChatColor.RED + "Only in-game players can use this command!");
			return false;
		}
		return false;
		
	}
}

