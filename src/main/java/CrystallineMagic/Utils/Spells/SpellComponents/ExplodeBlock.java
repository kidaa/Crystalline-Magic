package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import MiscUtils.Block.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplodeBlock implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {


        if(world.getBlock(x,y,z) != null && world.getBlock(x,y,z) != Blocks.air){
            BlockUtil.explodeBlock(world, x, y, z);

        }

        return false;
    }

    @Override
    public String GetName() {
        return "Explode Block";
    }

    @Override
    public double EnergyCost() {
        return 125;
    }

    @Override
    public String GetId() {
        return "EB";
    }

    @Override
    public SpellPartUsage GetUsage() {
        return SpellPartUsage.Block;
    }
}
