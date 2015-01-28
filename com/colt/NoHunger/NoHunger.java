package com.colt.NoHunger;
 
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class NoHunger extends JavaPlugin implements Listener {
       
        public void onEnable() {
	  saveDefaultConfig();
          Bukkit.getPluginManager().registerEvents(this, this);
        }
       
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.hasPermission("nohunger.use")) {
                List<String> worlds = getConfig().getStringList("worlds");
                for (String w : worlds) {
                    World world = Bukkit.getWorld(w);
                    if (player.getWorld().equals(world)) {
                        if (player.getHealth() != 20.0) {
                            player.setHealth(20.0D);
                        }
                        event.setCancelled( true );
                        break;
                    }
                }
            }
        }
    }
}
