package com.colt.NoHunger;
 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class NoHunger extends JavaPlugin implements Listener{
       
        public void onEnable(){
            Bukkit.getPluginManager().registerEvents(this, this);
        }
       
        @EventHandler
        public void hunger(FoodLevelChangeEvent e) {
                if(e.getEntity() instanceof Player) {
                        Player p = (Player)e.getEntity();
                        e.setCancelled(true);
                        p.setFoodLevel(20);
                }
        }
}
