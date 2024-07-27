package fr.flastar.timberplugin.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;

public class LogBrokenListener implements Listener {

    private final Material[] logsTypes = new Material[] {
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.OAK_LOG,
            Material.CHERRY_LOG,
            Material.SPRUCE_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG,
            Material.MANGROVE_LOG,
            Material.CHERRY_LOG,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
    };

    private final int[][] offsets = {
            {1, 0, 0},
            {-1, 0, 0},
            {0, 1, 0},
            {0, -1, 0},
            {0, 0, 1},
            {0, 0, -1}
        };

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // start recursive method if the block is a log / stem type
        if (Arrays.asList(logsTypes).contains(block.getType())) {
            doRecursive(block);
        }
    }

    public void doRecursive(Block block) {
        // base case if the block is not a log
        if (!Arrays.asList(logsTypes).contains(block.getType())) {
            return;
        }

        // destroy wanted block
        block.breakNaturally();

        // iterate throught all adjacents blocks with the recursive method
        for (int[] offset : offsets) {
            int x = block.getX() + offset[0];
            int y = block.getY() + offset[1];
            int z = block.getZ() + offset[2];
            Block adjacentBlock = block.getWorld().getBlockAt(x, y, z);

            doRecursive(adjacentBlock);
        }
    }
}
