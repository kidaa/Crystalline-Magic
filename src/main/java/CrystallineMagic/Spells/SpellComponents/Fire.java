package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Fire implements SpellComponent {


    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        entityHit.setFire(5 + (5 * SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade())));
        entityHit.attackEntityFrom(DamageSource.onFire, SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()));

        return true;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block,  EntityPlayer player, int Side) {

        if (Side == 0)
        {
            --y;
        }

        if (Side == 1)
        {
            ++y;
        }

        if (Side == 2)
        {
            --z;
        }

        if (Side == 3)
        {
            ++z;
        }

        if (Side == 4)
        {
            --x;
        }

        if (Side == 5)
        {
            ++x;
        }

        if(Side == -1)
            y += 1;

            if (world.isAirBlock(x, y, z))
            {
                world.setBlock(x, y, z, Blocks.fire);

                return true;
            }

        return false;

    }


    @Override
    public String GetName() {
        return "Flame";
    }

    @Override
    public double EnergyCost() {
        return 50;
    }

    @Override
    public String GetId() {
        return "FL";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Both;
    }
}
