package me.turkey2349.plugin;
//Test Comment

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TMT extends JavaPlugin
{
	public final static ArrayList<String> JoinedPlayers = new ArrayList<String>();
	public final static ArrayList<String> InnocentPlayers = new ArrayList<String>();
	public final static ArrayList<String> TraitorPlayers = new ArrayList<String>();
	public final static ArrayList<String> DeadPlayers = new ArrayList<String>();
	public final static ArrayList<String> DetectivePlayers = new ArrayList<String>();
	public final static ArrayList<String> Maps = new ArrayList<String>();
	public final static ArrayList<Integer> MapsSpawn = new ArrayList<Integer>();
	public final Listeners Listeners = new Listeners(this);
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
					ForceStart();
					A--;
				}
				}
			}
		},0L ,20L);
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
	public void ForceStart()
	{
    	Collections.shuffle(JoinedPlayers, random);
    	B = getConfig().getInt("Minimum Players");
    	
    	if(JoinedPlayers.size() >= B)
    	{
    	    for (int i = 0; i < JoinedPlayers.size(); i++) 
    	    {
    	    	String playerName = JoinedPlayers.get(i).toString();
    	    	Player player = Bukkit.getPlayer(playerName);
    	        //Player playerName = JoinedPlayers.get(i);
    	    
    	        int a = i % 6;
    	        if(a == 0)
    	        {
    	    	    TraitorPlayers.add(playerName);
    	    	    player.sendMessage("You are a Tritor!!");
    	    	    player.sendMessage("Kill all the Inocent");
    	    	    player.sendMessage("The Traitors are:");
    	 
        	    	getServer().broadcastMessage("~Traitor Message~");
    	    	    int b = 0;
    	    	    while(b < JoinedPlayers.size() )
    	    	    {
    	    	    	player.sendMessage("" + JoinedPlayers.get(b));
    	    		    b+=6;
    	    	    }
    	        }
    	        else if (a == 1)
    	        {
    	        	DetectivePlayers.add(playerName);
    	        	player.sendMessage("You are a Detective!!");
    	        	player.sendMessage("Kill all the Traitors");
        	    	getServer().broadcastMessage("Detective Message");
    	        }
    	        else if(a != 0 && a != 1)
    	        {
    	        	InnocentPlayers.add(playerName);
    	        	player.sendMessage("You are Innocent!!");
    	        	player.sendMessage("Kill all the Traitors");
        	    	getServer().broadcastMessage("~Innocent Message~");
    	        } 
    	    }
    	    if(TraitorPlayers.size() == 0)
    	    {
    		    getServer().broadcastMessage("All of the Traitors have been KILLED!!!");
    		    getServer().broadcastMessage("The innocent WIN!!!!!");
    		    if(getConfig().getBoolean("Server Restart") == true)
    		    {
    		        getServer().shutdown();
    		    }
    		    else if(getConfig().getBoolean("Server Restart") == false)
    		    {
    		        ForceStart();
    		    }
    	    }
    	    if(InnocentPlayers.size() == 0)
    	    {
    	    	getServer().broadcastMessage("All of the Innocent have been KILLED!!!");
    	    	getServer().broadcastMessage("The Traitors WIN!!!!!");
    	    	if(getConfig().getBoolean("Server Restart") == true)
    		    {
    		        getServer().shutdown();
    		    }
    	    	else if(getConfig().getBoolean("Server Restart") == false)
    		    {
    	    		
    		        ForceStart();
    		    }
    	    }
        }
    	else if(JoinedPlayers.size() < B)
    	{
    		getServer().broadcastMessage("There are not enough players online!");
    		getServer().broadcastMessage("TMT is restarting Countdown!");
    		
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
    					ForceStart();
    					A--;
    				}
    				}
    			}
    		},0L ,20L);
    	}
	}
	public void Jadd(String name)
	{
			JoinedPlayers.add(name);
	}
	public void Dadd(String name)
	{
			DeadPlayers.add(name);

	}
	public void Madd(String args)
	{
			Maps.add(args);
	}
	public void MSadd(String MSname) 
	{
		MapsSpawn.add(MSname);
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
	public boolean Tcontains(String name) 
	{
		return true;
	}
	public boolean Icontains(String name) 
	{
		return true;
	}
	public boolean Dcontains(String name) 
	{
		return true;
	}
	public boolean Mcontains(String args) 
	{
		return true;
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
			    	ForceStart();
			    }
		}
		return true;
	 }
}
