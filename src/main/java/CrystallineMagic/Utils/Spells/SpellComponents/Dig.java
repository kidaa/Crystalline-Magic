package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import MiscUtils.Block.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Dig implements SpellComponent{
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {

        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {
        if(block != Blocks.bedrock && block.getBlockHardness(world, x, y, z) > -1) {
            float h = block.getBlockHardness(world, x, y, z);
            if (h <= 2 + (SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()) * 6)){

                if(!world.isRemote)
                world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(world.getBlock(x, y, z)) + (world.getBlockMetadata(x, y, z) << 12));

                BlockUtil.breakBlockToPlayer(world, x, y, z, player);
                return true;
        }

        }

        return false;

    }

    @Override
    public String GetName() {
        return "Dig";
    }

    @Override
    public double EnergyCost() {
        return 48;
    }

    @Override
    public String GetId() {
        return "DG";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Block;
    }
}
