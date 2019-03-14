package at.htl.mccoding.buildHouse;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class HouseManager {

    private Main plugin;
    private HouseBuilder houseBuilder;
    private HashMap<Integer, House> houses;

    public HouseManager(Main plugin) {
        this.plugin = plugin;
        this.houseBuilder = new HouseBuilder();
        this.houseBuilder.runTaskTimer(plugin, 0, 1);
        houses = new HashMap<Integer, House>();
    }

    public void addHouse(Location location, int width, int length, int height) {
        House house = new House(location, width, length, height);
        if (houseBuilder.setHouse(house)) {
            houses.put(house.getId(), house);
            plugin.getServer().broadcastMessage("House with " + ChatColor.RED + "ID " + house.getId() + ChatColor.WHITE + " was create at" + ChatColor.LIGHT_PURPLE + " x:" + house.getLocation().getBlockX() + " y:" + house.getLocation().getBlockY() + " z:" + house.getLocation().getBlockZ());
        }
    }

    public void destroyHouse(int id) {
        if (houses.containsKey(id)) {
            houses.get(id).destroy();
            houses.remove(id);
            plugin.getServer().broadcastMessage("House " + ChatColor.RED + id + ChatColor.WHITE + " was destroyed");
        } else {
            plugin.getServer().broadcastMessage("Could not find house with " + ChatColor.RED + "ID " + id);
        }

    }

    public void listHouses() {
        for (House house : houses.values()) {
            plugin.getServer().broadcastMessage("House " + ChatColor.RED + house.getId() + ChatColor.WHITE + ": " + ChatColor.YELLOW + house.getDimensions() + ChatColor.LIGHT_PURPLE + " x:" + house.getLocation().getBlockX() + " y:" + house.getLocation().getBlockY() + " z:" + house.getLocation().getBlockZ());
        }
    }
}
