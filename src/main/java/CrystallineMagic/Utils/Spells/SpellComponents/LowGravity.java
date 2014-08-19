package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LowGravity implements SpellComponent {

    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            ent.addPotionEffect(new PotionEffect(CrystMagic.LowGravityEffect.getId(), 200 + (100 * MagicUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade())), 0));

            if(ent.isPotionActive(CrystMagic.GravityEffect))
                ent.removePotionEffect(CrystMagic.GravityEffect.getId());

            if(ent.isPotionActive(CrystMagic.AntiGravityEffect))
                ent.removePotionEffect(CrystMagic.AntiGravityEffect.getId());

        }

        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {
        return false;
    }

    @Override
    public String GetName() {
        return "Low Gravity";
    }

    @Override
    public double EnergyCost() {
        return 240;
    }

    @Override
    public String GetId() {
        return "LWG";
    }

    @Override
    public SpellPartUsage GetUsage() {
        return SpellPartUsage.Entity;
    }
}
