package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Fire implements SpellComponent {


    @Override
    public void OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        entityHit.setFire(5 + (5 * MagicUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade())));
        entityHit.attackEntityFrom(DamageSource.onFire, MagicUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()));


    }

    @Override
    public void OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block,  EntityPlayer player, int Side) {
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

            if (world.isAirBlock(x, y, z))
            {
                world.setBlock(x, y, z, Blocks.fire);
            }else if (world.isAirBlock(x, y+1, z)){
                world.setBlock(x, y+1, z, Blocks.fire);
            }

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
}
