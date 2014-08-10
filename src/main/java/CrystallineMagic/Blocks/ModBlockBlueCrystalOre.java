package CrystallineMagic.Blocks;

import CrystallineMagic.Main.ModItems;
import MiscUtils.Block.ModBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class ModBlockBlueCrystalOre extends ModBlock {


    public ModBlockBlueCrystalOre() {
        super(Material.rock);
        this.setHardness(6);
    }



    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {

        return ModItems.BlueCrystal;
    }


    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {

        return this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1);
    }

    public int quantityDropped(Random p_149745_1_)
    {
        return 1 + p_149745_1_.nextInt(3);
    }


}