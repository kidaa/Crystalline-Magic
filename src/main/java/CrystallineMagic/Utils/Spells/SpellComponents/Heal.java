package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.world.World;

public class Heal implements SpellComponent {
    @Override
    public void OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            int t = 0 + (MagicUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()));

           ent.addPotionEffect(new PotionEffect(PotionHealth.heal.getId(), 1, t));

        }
    }

    @Override
    public void OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {

    }

    @Override
    public String GetName() {
        return "Heal";
    }

    @Override
    public double EnergyCost() {
        return 80;
    }

    @Override
    public String GetId() {
        return "HL";
    }
}
