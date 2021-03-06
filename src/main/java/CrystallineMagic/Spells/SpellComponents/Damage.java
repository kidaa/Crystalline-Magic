package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.awt.*;

public class Damage implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {

        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            float Damage = 2 + (SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()) * 2);

            ent.attackEntityFrom(DamageSource.generic, Damage);

            return true;
        }

        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {
        return false;
    }

    @Override
    public String GetName() {
        return "Damage";
    }

    @Override
    public double EnergyCost() {
        return 60;
    }

    @Override
    public String GetId() {
        return "DM";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Entity;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return new SpellModifier[]{new StrengthUpgrade()};
    }

    @Override
    public Color GetComponentColor() {
        return new Color(107,0, 7);
    }
}
