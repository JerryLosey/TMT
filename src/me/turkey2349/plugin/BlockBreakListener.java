package me.turkey2349.plugin;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;

public class BlockBreakListener implements Listener 
{
	
	private TMT plugin;

	public BlockBreakListener(TMT plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent e)
	{
		e.setCancelled(true);
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		e.setCancelled(true);
	}
}
