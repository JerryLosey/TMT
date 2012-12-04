package me.turkey2349.plugin;

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
	
	public PlayerDeathListener(TMT plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent deathevent)
	{
		Player player = deathevent.getEntity();
		
		Location location = deathevent.getEntity().getLocation();
		location.getBlock().setType(Material.SIGN_POST);
		Block signBlock = location.getBlock();
		if(signBlock.getType() == Material.SIGN_POST){
			plugin.getServer().broadcastMessage(""+ signBlock.getState()+"");
			Sign sign = (Sign) signBlock.getState();
			sign.setLine(1, player.getDisplayName());
			// Detect if traitor or innocent
			//sign.setLine(2, player.getDisplayName());
			
			sign.update(true);
		}
		
		//deathevent.setLine();
		
		//Sign sign = location.getBlockY();
		//if(InnocentPlayers.Icontains(player))
		//{
			TMT.setLine(1, "Innocent");
		//}
		//else if(TraitorPlayers.Tcontains(player))
		//{
		//	TMT.setLine(1, "Traitor");
		//}
		
		//DeadPlayers.Dadd(player);
		//TraitorPlayers.Tremove(player);
		//InnocentPlayers.Iremove(player);

		
	}
}
