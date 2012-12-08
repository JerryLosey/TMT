package me.turkey2349.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class TMT extends JavaPlugin
{
	// Initialize arrays
	public final static ArrayList<String> JoinedPlayers = new ArrayList<String>();
	public final static ArrayList<String> InnocentPlayers = new ArrayList<String>();
	public final static ArrayList<String> TraitorPlayers = new ArrayList<String>();
	public final static ArrayList<String> DeadPlayers = new ArrayList<String>();
	public final static ArrayList<String> DetectivePlayers = new ArrayList<String>();
	public final static ArrayList<String> Maps = new ArrayList<String>();
	public final static ArrayList<Integer> MapsSpawn = new ArrayList<Integer>();
	public final Listeners Listeners = new Listeners(this);
	
	// Test
	public static Map<String,String> players = new HashMap<String,String>();
	public static Player[] onlinePlayers;
	
	// Initialize Listeners
	public final PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public final BlockBreakListener BlockBreakListener = new BlockBreakListener(this);
	
	
	public int A;
	public int B;
	
	static Random random = new Random();
	Logger log = Logger.getLogger("Minecraft");
	String LOG_PREFIX = "[TMT] ";
	//Listeners listeners = new Listeners();
	FileConfiguration config;
		
	public void onEnable()
	{
		//Register commands for use in PlayerCommands.class
		getCommand("TMTjoin").setExecutor(new PlayerCommands(this));
		getCommand("TMTleave").setExecutor(new PlayerCommands(this));
		getCommand("TMTfs").setExecutor(new PlayerCommands(this));
		
		String pluginFolder = getDataFolder().getAbsolutePath();
        new File(pluginFolder).mkdirs();
        
        if(!pluginFolder.contains("config.yml"))
        {
        getConfig().options().copyDefaults(true);
        getConfig();
        saveDefaultConfig();
        }
		getServer().getPluginManager().registerEvents(Listeners, this);
		getServer().getPluginManager().registerEvents(PlayerDeathListener, this);
		getServer().getPluginManager().registerEvents(BlockBreakListener, this);
	}
	
	public void onDisable()
	{
	}
	
	public static void setLine(int i, Player player) 
	{
	}
	public static void setLine(int i, String string) 
	{
	}
	
	public void forceStart(){
		A = getConfig().getInt("Wait Time(Sec)");
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			
			public void run()
			{
				if(A != -1)
				{
				if(A != 0)
				{
					getServer().broadcastMessage("" + A);
					A--;
				}
				else
				{
					//forceStart();
					A--;
				}
				}
			}
		},0L ,20L);
		B = getConfig().getInt("Minimum Players");
		setPlayers();
		if(players.size() >= B) {
			getServer().broadcastMessage("Starting!");
			for (int i = 0; i < JoinedPlayers.size(); i++) {
				String playerName = JoinedPlayers.get(i).toString();
				Player player = Bukkit.getPlayer(playerName);
				player.sendMessage("You are a: " + players.get(playerName));
			}
			
			
		} else {
			getServer().broadcastMessage("Not Enough Players!");
		}
	}
	
	public void clearPlayers() {
		players.clear();
	}
	
	public void setPlayers() {
		Random randomGenerator = new Random();
		storePlayers();
		
		// We need to determine how many Innocent will be in the game
		// Innocent = 75%
		// Traitor = 25%
		int numOfInnocent = (int) ( (players.size() + 1) * .75);
		// Lets loop through and use the random int generator to change player statuses
		int randomIndex = randomGenerator.nextInt(players.size());
		
		int x = 0;
		// Loop through the number of innocent players
		// choose a random player and set them as innocent
		// The remaining will be traitors!
		for (x = 1; x <= numOfInnocent; x++) {
			String playname = onlinePlayers[randomIndex].getName();
			players.put(playname, "Innocent");
		}
	}
	public void storePlayers(){
		onlinePlayers = this.getServer().getOnlinePlayers();
		for (Player player : onlinePlayers) {
			String playerName = player.getName();
			players.put("" + playerName + "", "Traitor");
		}
	}
	public boolean getPlayers(String playerName){
		for(Player player : onlinePlayers) {
			String name = player.getName();
			if(name == playerName) {
				return true;
			}
		}
		return false;
	}
	
	public void Jadd(String playerName)
	{
			JoinedPlayers.add(playerName);
	}
	public void Dadd(Player player)
	{
			DeadPlayers.add(player.getDisplayName());

	}
	public void Jremove(Player player)
	{
			JoinedPlayers.remove(player);

	}
	public void Iremove(Player player)
	{
			InnocentPlayers.remove(player);

	}
	public void Tremove(Player player)
	{
			TraitorPlayers.remove(player);

	}
	public boolean Tcontains(Player player) 
	{
		TMT.setLine(1, "Traitor");
		return false;
	}
	public boolean Icontains(Player player) 
	{
		TMT.setLine(1, "Innocent");
		return false;
	}
	public void Madd(String args)
	{
			Maps.add(args);
	}
	public boolean Mcontains(String args) 
	{
		return false;
	}

	 @Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 Player player = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("TMT"))
			{
				if(args.length == 0)
				{
					player.sendMessage(ChatColor.RED + "/TMT ForceStart: Starts the TMT game");
				}
			    if (args[0].equalsIgnoreCase("Join"))
			    {
			    	if(getConfig().getBoolean("Force Join on Game Start") == false)
			    	{
				    if (player == JoinedPlayers)
				    {
					    player.sendMessage(ChatColor.RED + "You Are Already Playing!");
				    }
				    else
				    {
				        JoinedPlayers.add(player.getDisplayName());
				        player.sendMessage(ChatColor.GREEN + "You Have Joined The Game!");
				    }
			    	}
			    	else if(getConfig().getBoolean("Force Join on Game Start") == true)
			    	{
			    		player.sendMessage(ChatColor.RED + "You cannot leave or join the game!");
			    	}
		        }
			    else if (args[0].equalsIgnoreCase("Leave"))
			    {
			    	if(getConfig().getBoolean("Force Join on Game Start") == false)
			    	{
				    if (player == JoinedPlayers)
				    {
				    	JoinedPlayers.remove(player);
				    	 player.sendMessage(ChatColor.GREEN + "You Have Left The Games!");
				    }
				    else
				    {
				        player.sendMessage(ChatColor.RED + "You Are Not already playing");
				    }
			    	}
			    	else if(getConfig().getBoolean("Force Join on Game Start") == true)
			    	{
			    		player.sendMessage(ChatColor.RED + "You cannot leave or join the game!");
			    	}
				
			    }
			    else if (args[0].equalsIgnoreCase("ForceStart"))
			    {
			    	getServer().broadcastMessage("TMT was force started by: " + player);
			    	forceStart();
			    }
		}
		return true;
	 }
}
