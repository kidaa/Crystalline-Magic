package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Damage implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {

        if(entityHit instanceof EntityLivingBase){
            EntityLivingBase ent = (EntityLivingBase)entityHit;

            float Damage = 2 + (MagicUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()) * 2);

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
}
