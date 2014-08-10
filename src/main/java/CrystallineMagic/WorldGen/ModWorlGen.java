package CrystallineMagic.WorldGen;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Main.ModBlocks;
import MiscUtils.Utils.WorldGen.WorldGenUtils;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class ModWorlGen extends WorldGenerator implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        return false;
    }

    private void generateSurface(World world, Random random, int ChunkX, int ChunkZ) {


        WorldGenUtils.RegisterWorldGenerator(new WorldGenMinable(ModBlocks.BlueCrystalOre, 8), "BlueCrystalOre", 23, 42, null, world, random, ChunkX, 0, ChunkZ, CrystMagic.config);
        WorldGenUtils.RegisterWorldGenerator(new WorldGenMinable(ModBlocks.GreenCrystalOre, 5), "GreenCrystalOre", 12, 30, null, world, random, ChunkX, 0, ChunkZ,  CrystMagic.config);
        WorldGenUtils.RegisterWorldGenerator(new WorldGenMinable(ModBlocks.RedCrystalOre, 4), "RedCrystalOre", 8, 28, null, world, random, ChunkX, 0, ChunkZ,  CrystMagic.config);

    }


    private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}
    private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}

}