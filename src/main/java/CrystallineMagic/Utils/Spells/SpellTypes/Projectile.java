package CrystallineMagic.Utils.Spells.SpellTypes;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import CrystallineMagic.Utils.Spells.Utils.SpellType;
import CrystallineMagic.Utils.Spells.Utils.SpellUseType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Projectile implements SpellType {
    @Override
    public String GetName() {
        return "Projectile";
    }

    @Override
    public String GetId() {
        return "Proj";
    }

    @Override
    public SpellUseType GetUseType() {
        return SpellUseType.Ranged;
    }

    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide){
        EntitySpellProjectile EntSpell = new EntitySpellProjectile(world, player, 2.0F, MagicUtils.GetSpellComponents(SpellStack), SpellStack);

        if (!world.isRemote) {
            world.spawnEntityInWorld(EntSpell);
        }


        return true;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Both;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1;
    }
}
