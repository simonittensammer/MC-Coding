package buildHouse;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class HouseBuilder extends BukkitRunnable {

    private House house;
    private boolean building;

    private int buildingProgress;

    private ItemStack itemData;

    public HouseBuilder() {
        building = false;
        buildingProgress = 0;
    }

    @Override
    public void run() {
        if (building) {
            int idk = 0;
            String[] block;
            do {
                idk++;
                block = house.getBlocks()[buildingProgress].split(",");
                itemData = new ItemStack(Material.getMaterial(block[3]));
                house.getLocation().add(Integer.parseInt(block[0]), Integer.parseInt(block[1]), Integer.parseInt(block[2])).getBlock().setType(Material.getMaterial(block[3]));

                if (Material.getMaterial(block[3]) != Material.AIR) {
                    house.getLocation().getWorld().spawnParticle(Particle.ITEM_CRACK, house.getLocation(), 10, 0.4, 0.5, 0.4, 0, itemData);
                }

                house.getLocation().add(-Integer.parseInt(block[0]), -Integer.parseInt(block[1]), -Integer.parseInt(block[2]));

                buildingProgress++;
                if (buildingProgress == house.getBlocks().length) {
                    buildingProgress = 0;
                    building = false;
                }
            } while (block[3].equalsIgnoreCase("AIR"));
            idk = 0;
        }
    }

    public boolean setHouse(House house) {
        if (!building) {
            this.house = house;
            building = true;
            return true;
        } else {
            return false;
        }

    }

}
