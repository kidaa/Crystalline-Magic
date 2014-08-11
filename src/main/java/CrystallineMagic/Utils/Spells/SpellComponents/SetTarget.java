package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SetTarget implements SpellComponent {


    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {

        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {
        MagicInfoStorage mag = MagicInfoStorage.get(player);
        mag.SetTarget(x,y,z);

        return true;
    }

    @Override
    public String GetName() {
        return "Place Target";
    }

    @Override
    public double EnergyCost() {
        return 12;
    }

    @Override
    public String GetId() {
        return "PT";
    }
}
