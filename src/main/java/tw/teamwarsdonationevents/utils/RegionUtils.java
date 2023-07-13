package tw.teamwarsdonationevents.utils;

import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RegionUtils {
    public static List<Location> getRandomAirLocations(ProtectedCuboidRegion region,World world, int amount) {
        List<Location> airLocations = new ArrayList<>();
        Random random = ThreadLocalRandom.current();

        int minX = region.getMinimumPoint().getBlockX();
        int minY = 100;
        int minZ = region.getMinimumPoint().getBlockZ();

        int maxX = region.getMaximumPoint().getBlockX();
        int maxY = 200;
        int maxZ = region.getMaximumPoint().getBlockZ();

        int count = 0;
        while (count < amount) {
            int x = minX + random.nextInt(maxX - minX + 1);
            int z = minZ + random.nextInt(maxZ - minZ + 1);

            for (int y = maxY; y >= minY; y--) {
                Block block = world.getBlockAt(x, y, z);
                Block aboveBlock = world.getBlockAt(x, y + 1, z);

                if (block.getType().isAir() && aboveBlock.getType().isAir()) {
                    Location location = new Location(world, x + 0.5, y + 1, z + 0.5);
                    airLocations.add(location);
                    count++;
                    break;
                }
            }
        }

        return airLocations;
    }
}
