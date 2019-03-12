package buildHouse;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	 static Logger logger = Bukkit.getLogger();
	 HouseManager houseManager;

	    @Override
	    public void onEnable() {
	        logger.info("starting...");
	        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin loading...");
	        
	        houseManager = new HouseManager(this);
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
	            int width = 0;
	            int length = 0;
	            int height = 0;
	            if(args.length == 1) {
	            	width = Integer.parseInt(args[0]);
	            	length = width;
	            	height = width;	            
	            }else if(args.length == 3) {
	            	width = Integer.parseInt(args[0]);
	            	length = Integer.parseInt(args[1]);
	            	height = Integer.parseInt(args[2]);
	            }
	            if (width > 0 & length > 0 && height > 0) {
	            	houseManager.addHouse(block.getLocation(), width, length, height);
	            }

	        }
	        
	        if(commandLabel.equalsIgnoreCase("destroy")) {
	        	int id;
	        	try {
	                id = Integer.parseInt(args[0]);
	            } catch (Exception e) {
	               id = -1;
	            }
	        	if(id > -1) {
	        		houseManager.destroyHouse(id);
	        	}
	        }
	        if(commandLabel.equalsIgnoreCase("listHouses")) {
	        	houseManager.listHouses();
	        }
	        return true;
	    }
	    
	    public static void log(Object o) {
	    	logger.info((String) o);
	    }   
}
