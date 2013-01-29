package me.turkey2349.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

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
	public final static ArrayList<String> MapsVeto = new ArrayList<String>();
	
	
	// Test
	public static Map<String,String> players = new HashMap<String,String>();
	
	// Initialize Listeners
	public final PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public final BlockBreakListener BlockBreakListener = new BlockBreakListener(this);
	public final PlayerListener PlayerListener = new PlayerListener(this);
	
	
	public int A;
	public int B;
	public String Arena;
	public Location tpCords;
	
	
	static Random random = new Random();
	Logger log = Logger.getLogger("Minecraft");
	String LOG_PREFIX = "[TMT] ";
	//Listeners listeners = new Listeners();
		
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
        
		getServer().getPluginManager().registerEvents(PlayerListener, this);
		getServer().getPluginManager().registerEvents(PlayerDeathListener, this);
		getServer().getPluginManager().registerEvents(BlockBreakListener, this);
	}
	
	public void onDisable()
	{
	}
	
	public void forceStart()
	{
		//ArenaSetup();
		A = getConfig().getInt("Wait Time(Sec)");
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run()
			{
				if(A != -1)
				{
				if(A != 0)
				{
					
					if (A == 45)
					{
					getServer().broadcastMessage("Next Map: " + Arena);
					getServer().broadcastMessage("" + A);
					}
					if (A == 30)
					{
					getServer().broadcastMessage("Next Map: " + Arena);
					getServer().broadcastMessage("" + A);
					}
					if (A == 15)
					{
					getServer().broadcastMessage("Next Map: " + Arena);
					getServer().broadcastMessage("" + A);
					}
					if (A <= 10)
					{
					getServer().broadcastMessage("Next Map: " + Arena);
					getServer().broadcastMessage("" + A);
					}
					A--;
				}
				else
				{
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
				player.teleport(tpCords);
				player.sendMessage("You are a: " + players.get(playerName));
				
				
			}
			
			
		} else {
			getServer().broadcastMessage("Not Enough Players!");
		}
	}
	
	public void clearPlayers() {
		players.clear();
	}
	public void setPlayers() 
	{
		
		// Innocent = 75%
		// Traitor = 25%
		int numOfInnocent = (int) (Math.round( (JoinedPlayers.size() + 1) * .75));
		int numOfTriators = (int) (Math.round( (JoinedPlayers.size() + 1) * .25));
		
		int x = 0;

		for (x = 1; x <= numOfInnocent; x++) 
		{
			if(x % 6 == 1)
			{
			    String innocent = JoinedPlayers.get(x);
			    players.put(innocent, "Detective");
			}
			else if(x % 6 != 1)
			{
				String innocent = JoinedPlayers.get(x);
			    players.put(innocent, "Innocent");
			}
		}
		for (x = 1; x <= numOfTriators; x++)
		{
			String innocent = JoinedPlayers.get(x);
		    players.put(innocent, "Traitor");
		}
	}
	/*public void ArenaSetup()
	{
	MapsVeto.clear();
	int NA = getcustomConfig().getInt("number of arenas");
	int RA = (int) (2 + Math.random() * (NA - 2));
    Arena = (String) getcustomConfig().get("arena" + RA);
    tpCords = (Location) getcustomConfig().get(Arena);
    getServer().broadcastMessage("Map Change!! New Map: " + Arena);
	}
	*/
	
	public void Jadd(String playerName)
	{
			JoinedPlayers.add(playerName);
	}
	public void Dadd(Player player)
	{
			DeadPlayers.add(player.getDisplayName());

	}
	public void Jremove(String name)
	{
			JoinedPlayers.remove(name);

	}
	public void Iremove(String name)
	{
			InnocentPlayers.remove(name);

	}
	public void Tremove(String name)
	{
			TraitorPlayers.remove(name);
	}
	public void MVadd(String playerListName) {
		MapsVeto.add(playerListName);
		
	}
	public boolean Jcontains(String name)
	{
		if(JoinedPlayers.contains(name))
		{
		return true;
		}
		else if(JoinedPlayers.contains(name) == false)
		{
		return false;
		}
		return true;
	}
	public boolean Tcontains(String name) 
	{
		return true;
	}
	public boolean Icontains(String name) 
	{
		return true;
	}
	public boolean MVcontains(String name) 
	{
		return true;
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
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	 {
		return true;
	 }
}
