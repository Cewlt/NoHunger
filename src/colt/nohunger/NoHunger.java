package colt.nohunger;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class NoHunger extends JavaPlugin implements Listener {
    private List<String> worlds;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        loadWorlds();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void hungerChangeEvent(FoodLevelChangeEvent event) {
        if(worlds.isEmpty()) return;
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player)event.getEntity();
        if(worlds.contains(player.getWorld().getName())) {
            player.setFoodLevel(20);
            event.setCancelled(true);
        }
    }

    public void loadWorlds() {
        worlds = new ArrayList<>();
        for(String world : getConfig().getStringList("worlds")) {
            if(Bukkit.getWorld(world) == null) {
                getLogger().info("Invalid world in config: " + world);
            } else {
                worlds.add(world);
            }
        }
    }
}
