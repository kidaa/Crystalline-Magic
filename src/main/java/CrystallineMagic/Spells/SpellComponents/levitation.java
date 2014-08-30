package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.awt.*;

public class levitation implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {


        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            ent.addPotionEffect(new PotionEffect(CrystMagic.levitation.getId(), 200 + (100 * SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade())), 0));

            if(ent.isPotionActive(CrystMagic.GravityEffect))
                ent.removePotionEffect(CrystMagic.GravityEffect.getId());


            if(ent.isPotionActive(CrystMagic.LowGravityEffect))
                ent.removePotionEffect(CrystMagic.LowGravityEffect.getId());


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
        return "levitation";
    }

    @Override
    public double EnergyCost() {
        return 358;
    }

    @Override
    public String GetId() {
        return "AG";
    }

    @Override
    public SpellPartUsage GetUsage() {
        return SpellPartUsage.Entity;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return new SpellModifier[]{new StrengthUpgrade()};
    }

    @Override
    public Color GetComponentColor() {
        return new Color(201, 234, 255);
    }
}
