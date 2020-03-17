package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.managers.SchematicsManager;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.io.InputStream;
import java.util.Random;

public class NetherListener extends BlockPopulator {

    public String schemName = "Nether.schematic";
    public FrozedUHCRun plugin;

    public NetherListener (FrozedUHCRun instance) {
        this.plugin = instance;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (chunk.getX() % 20 == 0 && chunk.getZ() % 20 == 0) {
            try {
                InputStream is = plugin.getClass().getClassLoader().getResourceAsStream(schemName);
                SchematicsManager manager = new SchematicsManager();
                manager.loadGzipedSchematic(is);

                int width = manager.getWidth();
                int height = manager.getHeight();
                int lenght = manager.getLength();
                int startY = 10;
                int endY = startY + height;

                for (int x = 0; x < width; x++) {
                    for (int z = 0; z < lenght; z++) {
                        int realX = x + chunk.getX() * 16;
                        int realZ = z + chunk.getZ() * 16;

                        for (int y = startY; y <= endY && y < 255; y++) {
                            int realY = y - startY;
                            int id = manager.getBlockIdAt(x, realY, z);
                            byte data = manager.getBlockIdAt(x, realY, z);

                            if (id == -103) {
                                world.getBlockAt(realX, y, realZ).setTypeIdAndData(153, data, true);
                            }

                            if (id > -1 && world.getBlockAt(realX, y, realZ) != null) {
                                world.getBlockAt(realX, y, realZ).setTypeIdAndData(id, data, true);
                            }
                        }
                    }
                }

            } catch(Exception e) {
                System.out.println("La schematic no es valida!");
                e.printStackTrace();
            }
        }
    }
}
