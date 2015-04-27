package CrystallineMagic.Utils;

import CrystallineMagic.Main.CrystMagic;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class MagicEffects {

    public static void SpawnMagicEffect(World world, double x, double y, double z,int amount, int width, Color c){
        Random rand = new Random();
        float life = 1 + rand.nextFloat() * 0.5F;
        SpawnMagicEffect(world, x, y, z, 0.02F, life, amount, width, c);
    }

    public static void SpawnMagicEffect(World world, double x, double y, double z,int amount, int width, float life, Color c){
        SpawnMagicEffect(world, x, y, z, 0.02F, life, amount, width, c);
    }

    public static void SpawnMagicEffect(World world, double x, double y, double z, float gravity, int amount, int width, Color c){
        Random rand = new Random();
        float life = 1 + rand.nextFloat() * 0.5F;
        SpawnMagicEffect(world, x, y, z, gravity, life, amount, width, c);
    }


    public static void SpawnMagicEffect(World world, double x, double y, double z, float gravity, float life, int amount, int width, Color c){
        for (int i = 0; i < amount; ++i) {
            Random rand = new Random();

            double X = (x + 0.2) + (double) (rand.nextFloat() * width);
            double Y =  y +        (double) (rand.nextFloat() * width);
            double Z = (z + 0.2) + (double) (rand.nextFloat() * width);

            CrystMagic.proxy.MagicEffect(world,X,Y,Z, c, rand.nextFloat() - 0.5F, 0, gravity, 0, life);
        }

    }
}
