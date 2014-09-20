package CrystallineMagic.Spells.SpellTypes;

import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUseType;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Entity.EntitySpellProjectile;
import CrystallineMagic.Utils.MagicEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;

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
        EntitySpellProjectile EntSpell = new EntitySpellProjectile(world, player, 2.0F, SpellUtils.GetSpellComponents(SpellStack), SpellStack);


        if(SpellUtils.GetSpellComponents(SpellStack) != null && SpellUtils.GetSpellComponents(SpellStack).length > 0){
            Color c = new Color(255,255,255);

            if(SpellUtils.GetSpellComponents(SpellStack)[0].GetComponentColor() != null)
                c = SpellUtils.GetSpellComponents(SpellStack)[0].GetComponentColor();

            MagicEffects.SpawnMagicEffect(world, (x - 0.8) + EntSpell.motionX, y + EntSpell.motionY, (z - 0.8) + EntSpell.motionZ, 2, 1, c);
        }


        world.spawnEntityInWorld(EntSpell);


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
