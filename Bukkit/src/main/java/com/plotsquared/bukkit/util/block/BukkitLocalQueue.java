package com.plotsquared.bukkit.util.block;

import com.intellectualcrafters.plot.object.PlotBlock;
import com.intellectualcrafters.plot.util.MainUtil;
import com.intellectualcrafters.plot.util.StringMan;
import com.intellectualcrafters.plot.util.block.BasicLocalBlockQueue;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class BukkitLocalQueue<T> extends BasicLocalBlockQueue<T> {

    public BukkitLocalQueue(String world) {
        super(world);
    }

    @Override
    public LocalChunk<T> getLocalChunk(int x, int z) {
        return (LocalChunk<T>) new BasicLocalChunk(this, x, z) {
            // Custom stuff?
        };
    }

    @Override
    public void optimize() {

    }

    @Override
    public PlotBlock getBlock(int x, int y, int z) {
        World worldObj = Bukkit.getWorld(getWorld());
        Block block = worldObj.getBlockAt(x, y, z);
        if (block == null) {
            return PlotBlock.get(0, 0);
        }
        int id = block.getTypeId();
        if (id == 0) {
            return PlotBlock.get(0, 0);
        }
        return PlotBlock.get(id, block.getData());
    }

    @Override
    public void refreshChunk(int x, int z) {
        World worldObj = Bukkit.getWorld(getWorld());
        worldObj.refreshChunk(x, z);
    }

    @Override
    public void fixChunkLighting(int x, int z) {
        // Do nothing
    }

    @Override
    public final void regenChunk(int x, int z) {
        World worldObj = Bukkit.getWorld(getWorld());
        worldObj.regenerateChunk(x, z);
    }

    @Override
    public final void setComponents(LocalChunk<T> lc) {
        setBlocks(lc);
        setBiomes(lc);
    }

    public World getBukkitWorld() {
        return Bukkit.getWorld(getWorld());
    }

    public Chunk getChunk(int x, int z) {
        return getBukkitWorld().getChunkAt(x, z);
    }

    public void setBlocks(LocalChunk<T> lc) {
        World worldObj = Bukkit.getWorld(getWorld());
        Chunk chunk = worldObj.getChunkAt(lc.getX(), lc.getZ());
        chunk.load(true);
        for (int layer = 0; layer < lc.blocks.length; layer++) {
            PlotBlock[] blocksLayer = (PlotBlock[]) lc.blocks[layer];
            if (blocksLayer != null) {
                for (int j = 0; j < blocksLayer.length; j++) {
                    PlotBlock block = blocksLayer[j];
                    if (block != null) {
                        int x = MainUtil.x_loc[layer][j];
                        int y = MainUtil.y_loc[layer][j];
                        int z = MainUtil.z_loc[layer][j];
                        Block existing = chunk.getBlock(x, y, z);
                        int existingId = existing.getTypeId();
                        if (existingId == block.id) {
                            if (existingId == 0) {
                                continue;
                            }
                            if (existing.getData() == block.data) {
                                continue;
                            }
                        }
                        existing.setTypeIdAndData(block.id, block.data, false);
                    }
                }
            }
        }
    }

    public void setBiomes(LocalChunk<T> lc) {
        if (lc.biomes != null) {
            World worldObj = Bukkit.getWorld(getWorld());
            int bx = lc.getX() << 4;
            int bz = lc.getX() << 4;
            String last = null;
            Biome biome = null;
            for (int x = 0; x < lc.biomes.length; x++) {
                String[] biomes2 = lc.biomes[x];
                if (biomes2 != null) {
                    for (int y = 0; y < biomes2.length; y++) {
                        String biomeStr = biomes2[y];
                        if (biomeStr != null) {
                            if (last == null || !StringMan.isEqual(last, biomeStr)) {
                                biome = Biome.valueOf(biomeStr.toUpperCase());
                            }
                            worldObj.setBiome(bx, bz, biome);
                        }
                    }
                }
            }
        }
    }
}