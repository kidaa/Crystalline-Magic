package CrystallineMagic.Spells.SpellTypes;

import CrystallineApi.Spells.*;
import CrystallineMagic.Entity.EntitySpellProjectile;
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
        return SpellUseType.Self;
    }

    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide){
        world.spawnEntityInWorld(new EntitySpellProjectile(world, player, 2.0F, SpellUtils.GetSpellComponents(SpellStack), SpellStack));

        return true;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Both;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return null;
    }
}
