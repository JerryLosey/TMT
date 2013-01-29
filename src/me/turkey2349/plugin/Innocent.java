package me.turkey2349.plugin;
//
//import org.bukkit.Bukkit;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//
//import net.citizensnpcs.api.trait.Trait;
//import net.citizensnpcs.api.util.DataKey;
//
//public class Innocent extends Trait {
//
//  public Innocent() {
//		super("Innocent");
//		plugin = (TMT) Bukkit.getServer().getPluginManager().getPlugin("TMT");
//	}
//	TMT plugin = null;
//
//	boolean SomeSetting = false;
//	
//	public void load(DataKey key) {
//		SomeSetting = key.getBoolean("SomeSetting", plugin.getConfig().getBoolean("Defaults.SomeSetting"));
//	}
//	
//	public void save(DataKey key) {
//		key.setBoolean("SomeSetting",SomeSetting);
//		npc.data().set(arg0, arg1);
//		Player player = 
//		npc.data().g
//	}
//	
//	@EventHandler
//	public void click(net.citizensnpcs.api.event.NPCClickEvent event){
//		//Handle a click on a NPC. The event has a getNPC() method. 
//		//Be sure to check event.getNPC() == this.getNPC() so you only handle clicks on this NPC!
//
//		if(event.getNPC() == this.getNPC()){
//			this.ge
//			Player player = event.getClicker();
//			(NPC) player;
//			player.sendMessage("");
//		}
//	}
//	
//	//Run code when your trait is attached to a NPC. 
//		//This is called BEFORE onSpawn so do not try to access npc.getBukkitEntity(). It will be null.
//		@Override
//		public void onAttach() {
//			plugin.getServer().getLogger().info(npc.getName() + "has been assigned MyTrait!");
//			
//			//This will send a empty key to the Load method, forcing it to load the config.yml defaults.
//			//Load will get called again with a real key if this NPC has previously been saved
//			load(new net.citizensnpcs.api.util.MemoryDataKey());
//		}
//
//		// Run code when the NPC is despawned. This is called before the entity actually despawns so npc.getBukkitEntity() is still valid.
//		@Override
//		public void onDespawn() {
//		}
//
//		//Run code when the NPC is spawned. Note that npc.getBukkitEntity() will be null until this method is called.
//		//This is called AFTER onAttach and AFTER Load when the server is started.
//		@Override
//		public void onSpawn() {
//
//		}
//
//		//run code when the NPC is removed. Use this to tear down any repeating tasks.
//		@Override
//		public void onRemove() {
//		}
//}
