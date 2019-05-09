package at.htl.mccoding.buildHouse;

import java.io.*;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Logger logger = Bukkit.getLogger();
    HouseManager houseManager;

    @Override
    public void onEnable() {
        logger.info("starting...");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin loading...");

        houseManager = new HouseManager(this);

        File jarPath=new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();
        File file = new File(propertiesPath + "\\buildHouse_data.txt");

        if(file.exists() && file.length() > 0){
            String fileContent = "";
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                fileContent = bufferedReader.readLine();
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String [] houseStrings = fileContent.split(";");
            for (int i = 0; i < houseStrings.length; i++){
                String [] houseValues = houseStrings[i].split("/");
                Location location = new Location(Bukkit.getWorlds().get(0), Double.parseDouble(houseValues[0]), Double.parseDouble(houseValues[1]), Double.parseDouble(houseValues[2]));
                houseManager.addHouse(new House(location, Integer.parseInt(houseValues[3]), Integer.parseInt(houseValues[4]), Integer.parseInt(houseValues[5])));
            }
        }
    }

    @Override
    public void onDisable() {
        logger.info("shutting down...");

        File jarPath=new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();

        BufferedWriter writer = null;

        String fileContent = "";
        for (House house : houseManager.getHouses().values()) {
            fileContent += house + ";";
        }

        try {
            writer = new BufferedWriter(new FileWriter(propertiesPath+"\\buildHouse_data.txt"));
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("build")) {
            Player player = (Player) sender;
            Block block = player.getLocation().add(1, 0, 0).getBlock();
            int width = 0;
            int length = 0;
            int height = 0;

            if (args.length == 1) {
                width = Integer.parseInt(args[0]);
                length = width;
                height = width;
            } else if (args.length == 3) {
                width = Integer.parseInt(args[0]);
                length = Integer.parseInt(args[1]);
                height = Integer.parseInt(args[2]);
            }
            if (width > 0 & length > 0 && height > 0) {
                houseManager.addHouse(block.getLocation(), width, length, height);
            }

        }

        if (commandLabel.equalsIgnoreCase("destroy")) {
            int id;
            try {
                id = Integer.parseInt(args[0]);
            } catch (Exception e) {
                id = -1;
            }
            if (id > -1) {
                houseManager.destroyHouse(id);
            }
        }
        if (commandLabel.equalsIgnoreCase("listHouses")) {
            houseManager.listHouses();
        }
        if (commandLabel.equalsIgnoreCase("fillHouse")){
            try{
                houseManager.getHouse(Integer.parseInt(args[0])).fill();
            } catch(Exception e){

            }
        }
        return true;
    }

    public static void log(Object o) {
        logger.info((String) o);
    }
}
