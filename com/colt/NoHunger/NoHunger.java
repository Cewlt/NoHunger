package com.colt.NoHunger;
 
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class NoHunger extends JavaPlugin implements Listener{
       
        public void onEnable(){
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        }
       
        @EventHandler
        public void hunger(FoodLevelChangeEvent e) {
                if(e.getEntity() instanceof Player) {
                	Player p = (Player)e.getEntity();
                	if(p.hasPermission("nohunger.use")) {
                	List<String> worlds = getConfig().getStringList("worlds");
                	for(String w : worlds) {
                		if(p.getWorld().equals(w)) {
                			e.setCancelled(true);
                			p.setFoodLevel(20);
                		}
                	}
                	}
                }
        }
}
