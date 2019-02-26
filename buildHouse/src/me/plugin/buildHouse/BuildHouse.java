package me.plugin.buildHouse;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildHouse extends JavaPlugin {

    Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        logger.info("starting...");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin loading...");
    }

    @Override
    public void onDisable() {
        logger.info("shutting down...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("build")) {
            Player player = (Player) sender;
            Block block = player.getLocation().add(1, 0, 0).getBlock();
            int width;
            try {
                width = Integer.parseInt(args[0]);
            } catch (Exception e) {
                if(args.length == 0) {
                    width = 4;
                }else {
                    width = 0;
                    player.sendMessage(ChatColor.GOLD + "Argument has to be an integer");
                }
            }
            if (width > 0) {
                summonTNT(player, block, width);
            }

        }
        return true;
    }

    public void summonTNT(Player player, Block block, int width) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                for (int z = 0; z < width; z++) {
                    block.getLocation().add(x, y, z).getBlock().setType(Material.ACACIA_PLANKS);
                }
            }
        }
        // block.getLocation().add(1.0, width,
        // 0.0).getBlock().setType(Material.STONE_BUTTON);
        player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));

        player.sendMessage(
                (int) Math.pow(width, 3) + " Blocks " + ChatColor.RED + "TNT" + ChatColor.WHITE + " have been placed.");
        player.sendMessage(
                "1 " + ChatColor.GRAY + "Flint and Steel" + ChatColor.WHITE + " was given to " + player.getName());
    }
}
