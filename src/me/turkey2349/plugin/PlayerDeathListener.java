package me.turkey2349.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
	TMT plugin;
	TMT JoinedPlayers;
	TMT InnocentPlayers;
	TMT TraitorPlayers;
	TMT DeadPlayers;
	TMT DetectivePlayers;
	
	public PlayerDeathListener(TMT plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent deathevent)
	{
		Player player = deathevent.getEntity();
	//	String name = player.getDisplayName();
		
		Location location = deathevent.getEntity().getLocation();
		location.getBlock().setType(Material.SIGN_POST);
		Block signBlock = location.getBlock();
		if(signBlock.getType() == Material.SIGN_POST)
		{
			plugin.getServer().broadcastMessage(""+ signBlock.getState()+"");
			Sign sign = (Sign) signBlock.getState();
			sign.setLine(1, ChatColor.GREEN + player.getDisplayName());
			sign.setLine(2, ChatColor.UNDERLINE + player.getDisplayName());
			
			sign.update(true);
		}
		/* if(InnocentPlayers.contains(name)
		{
		    InnocentPlayers.Iremove(name);
		    Players.remove(name);
		    DeadPlayers.Dadd(name);
		}
		if(triatorPlayers.contians(name))
		{
		    TriatorPlayers.Tremove(name);
		    Players.remove(name);
		    DeadPlayers.Dadd(name);
		}
		if(InnocentPlayers.size == 0)
		{
		    getServer.BroadcastMessage("The Traitors have Won!");
		    TriatorPlayers.moveall(/*to Joined Players*//*);
		    InnoccentPlayers.moveall(/*to Joined Players*//*);
		    DeadPlayers.moveall(/*to Joined Players*//*);
		    Players.clear();
		}
		if(TraitorPlayers.size == 0)
		{
		    getServer.BroadcastMessage("The Innocent have Won!");
		    TriatorPlayers.moveall(/*to Joined Players*//*);
		    InnoccentPlayers.moveall(/*to Joined Players*//*);
		    DeadPlayers.moveall(/*to Joined Players*//*);
		    Players.clear();
		    
		}
	}
}
