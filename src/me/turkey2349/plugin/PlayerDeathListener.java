package me.turkey2349.plugin;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class PlayerDeathListener implements Listener {
	
	TMT plugin;
	TMT Joinedplayers;
	TMT Innocentplayers;
	TMT Traitorplayers;
	TMT Deadplayers;
	TMT Detectiveplayers;
	TMT players;
	
	public PlayerDeathListener(TMT plugin)
	{
		this.plugin = plugin;
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onplayerNameDeath(PlayerDeathEvent deathevent)
	{
		String playerName = deathevent.getEntity().getDisplayName();
		//String killer = deathevent.getEntity().getKiller().getDisplayName();
		
		deathevent.setDeathMessage("" + playerName + " Died");
		
		
		Location location = deathevent.getEntity().getLocation();
		location.getBlock().setType(Material.SIGN_POST);
		Block signBlock = location.getBlock();
		if(signBlock.getType() == Material.SIGN_POST){
			plugin.getServer().broadcastMessage(""+ signBlock.getState()+"");
			Sign sign = (Sign) signBlock.getState();
			sign.setLine(1, ChatColor.GREEN + playerName);
			// Detect if traitor or innocent
			String Assign = TMT.players.get(playerName);
			sign.setLine(2, ChatColor.UNDERLINE + Assign);
			sign.update(true);
		}
		if(Innocentplayers.Icontains(playerName))
		{
		    Innocentplayers.Iremove(playerName);
		    TMT.players.remove(playerName);
		    TMT.DeadPlayers.add(playerName);
		}
		if(TMT.TraitorPlayers.contains(playerName))
		{
		    TMT.TraitorPlayers.remove(playerName);
		    TMT.players.remove(playerName);
		    TMT.DeadPlayers.add(playerName);
		}
		if(TMT.InnocentPlayers.size() == 0)
		{
		    plugin.getServer().broadcastMessage("The Traitors have Won!");
		    TMT.JoinedPlayers.addAll((Collection<? extends String>) Deadplayers);
		    TMT.JoinedPlayers.addAll((Collection<? extends String>) Traitorplayers);
		    TMT.JoinedPlayers.addAll((Collection<? extends String>) Innocentplayers);
		    for(int c = 0; TMT.JoinedPlayers.size() >= c; c++)
		    {
		    	String TpPlayer = TMT.JoinedPlayers.get(c);
		    	Player player = Bukkit.getPlayer(TpPlayer);
		    	//player.teleport((Location) plugin.getcustomConfig().get("Spawn"));
		    }
		    TMT.players.clear();
		    
		}
		if(TMT.TraitorPlayers.size() == 0)
		{
			plugin.getServer().broadcastMessage("The Innocent have Won!");
			 TMT.JoinedPlayers.addAll((Collection<? extends String>) Deadplayers);
			    TMT.JoinedPlayers.addAll((Collection<? extends String>) Traitorplayers);
			    TMT.JoinedPlayers.addAll((Collection<? extends String>) Innocentplayers);
			    for(int c = 0; TMT.JoinedPlayers.size() >= c; c++)
			    {
			    	String TpPlayer = TMT.JoinedPlayers.get(c);
			    	Player player = Bukkit.getPlayer(TpPlayer);
			    //	player.teleport((Location) plugin.getcustomConfig().get("Spawn"));
			    }
			    TMT.players.clear();
		    
		}

		
		//if(InnocentplayerNames.Icontains(playerName))
		//{
			//TMT.setLine(1, "Innocent");
		//}
		//else if(TraitorplayerNames.Tcontains(playerName))
		//{
		//	TMT.setLine(1, "Traitor");
		//}
		
		//DeadplayerNames.Dadd(playerName);
		//TraitorplayerNames.Tremove(playerName);
		//InnocentplayerNames.Iremove(playerName);

		
	}
}
