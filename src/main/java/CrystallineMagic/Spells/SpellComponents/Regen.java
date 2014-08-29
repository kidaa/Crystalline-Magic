package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.world.World;

public class Regen implements SpellComponent{


    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            int t = 1 + (SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()));
            int g = t;

            if(t >= 3)
                g = 3;

            ent.addPotionEffect(new PotionEffect(PotionHealth.regeneration.getId(), t * 300, g));

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
        return "Regeneration";
    }

    @Override
    public double EnergyCost() {
        return 70;
    }

    @Override
    public String GetId() {
        return "RG";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Entity;
    }
}
