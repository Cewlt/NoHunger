package com.colt.nohunger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoHunger extends JavaPlugin implements Listener {

	List<String> worlds = new ArrayList<String>();

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();

		for (String world : getConfig().getStringList("worlds")) {
			if (Bukkit.getWorld(world) == null) {
				getLogger().info("Invalid world in config: " + world);
				continue;
			} else {
				worlds.add(world);
				continue;
			}
		}
	}

	@EventHandler
	public void foodChangeEvent(FoodLevelChangeEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if (player.hasPermission("no.hunger")) {
				if (worlds.isEmpty())
					return;
				if (worlds.contains(player.getWorld().getName())) {
					event.setCancelled(true);
					if (player.getFoodLevel() < 19.0)
						player.setFoodLevel(20);
				}
			}
		}
	}
}
