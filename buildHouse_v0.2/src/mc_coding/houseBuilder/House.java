package mc_coding.houseBuilder;

import org.bukkit.Location;
import org.bukkit.Material;

public class House {
	
	private Location location;
	private String[] blocks;
	
	private int width;
	private int length;
	private int height;
	
	static int totalId = 0;
	private int id;
	
	public House(Location location, int width, int length, int height) {
		this.location = location;
		this.width = width;
		this.length = length;
		this.height = height;
		blocks = new String[width * length * (height + 1)];
		totalId ++;
		id = totalId;
		int counter = 0;
//		for(int x = 0; x < width; x++) {
//			for(int y = 0; y < length; y++) {
//				for(int z = 0; z < height; z++) {
//					blocks[counter] = x + "," + y + "," + z + "," + "ACACIA_PLANKS";
//					counter ++;
//				}
//			}
//		}
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < length; y++) {
				blocks[counter] =x + ",-1," + y + ",STONE_BRICKS";
				counter ++;
			}
		}
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < length; y++) {
				for(int z = 0; z < height - 1; z++) {
					if(x == Math.floor(width/2) && y == 0 && z == 0 || x == Math.floor(width/2) && y == 0 && z == 1) {
						blocks[counter] = x + "," + z + "," + y + "," + "AIR";
					}else if(x == 0 && y == 0 || x == width - 1 && y == 0 || x == 0 && y == length -1 || x == width - 1 && y == length -1) {
						blocks[counter] = x + "," + z + "," + y + "," + "SPRUCE_LOG";
					}else if(x == 0 || x == width - 1 || y == 0 || y == length - 1) {
						blocks[counter] = x + "," + z + "," + y + "," + "SPRUCE_PLANKS";
					}else {
						blocks[counter] = x + "," + z + "," + y + "," + "AIR";
					}	
					counter ++;
				}
			}
		}
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < length; y++) {
				for(int z = height - 1; z < height; z++) {
					if(x == 0 || x == width - 1 || y == 0 || y == length - 1) {
						blocks[counter] = x + "," + z + "," + y + "," + "SPRUCE_SLAB";
					}else {
						blocks[counter] = x + "," + z + "," + y + "," + "SPRUCE_PLANKS";
					}	
					counter ++;
				}
			}
		}
		
	}
	
	public void destroy() {
		for(int i = 0; i < blocks.length; i++) {
			String[] block = blocks[i].split(",");	
			location.add(Integer.parseInt(block[0]), Integer.parseInt(block[1]), Integer.parseInt(block[2])).getBlock().setType(Material.AIR);
			location.add(-Integer.parseInt(block[0]), -Integer.parseInt(block[1]), -Integer.parseInt(block[2]));
		}
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String[] getBlocks() {
		return blocks;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDimensions() {
		return width + "x" + length + "x" + height;
	}
}
