package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class OresListener extends BlockPopulator {

    private static final int[] iterations = new int []{5, 10, 8, 4, 4, 2};

    private static final Material[] type = new Material[] {
            Material.REDSTONE_ORE,
            Material.DIAMOND_ORE,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.COAL_ORE,
            Material.OBSIDIAN
    };

    private static final int[] maxHeight = new int[]{64, 64, 64, 64, 64, 64};
    private static int[] amounts = new int[] {10, 20, 30, 5, 10, 4};

    public FrozedUHCRun plugin;

    public OresListener(FrozedUHCRun instance) {
        this.plugin = instance;
    }

    @Override
    public void populate(World world, Random random, Chunk source) {
        for(int i = 0; i < type.length; i++){
            for(int j = 0; j < iterations[i]; j++){
                makeOres(source, random, random.nextInt(16), random.nextInt(maxHeight[i]), random.nextInt(16), amounts[i], type[i]);
            }
        }
    }

    private void makeOres(Chunk source, Random random, int originX, int originY, int originZ, int amount, Material type) {
        for (int i = 0; i < amount; i++) {
            int x = originX + random.nextInt(amount / 2) - amount / 4;
            int y = originY + random.nextInt(amount / 4) - amount / 8;
            int z = originZ + random.nextInt(amount / 2) - amount / 4;
            x &= 0xf;
            z &= 0xf;
            if (y > 127 || y < 0) {
                continue;
            }
            Block block = source.getBlock(x, y, z);
            if (block.getType() == Material.SAND) {
                block.setType(type, false);
            }
        }
    }

}
