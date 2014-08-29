package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class TeleportRandom implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        Random rand = new Random();

        double MaxDistance = 5 + (SpellUtils.GetAmountOfAModifer(Spell, new StrengthUpgrade()) * 12);

            double d0 = entityHit.posX + (rand.nextDouble() - 0.5D) * MaxDistance;
            double d1 = entityHit.posY + (double)(rand.nextInt(32) - 16);
            double d2 = entityHit.posZ + (rand.nextDouble() - 0.5D) * MaxDistance;

        while(world.getBlock((int)d0,(int)d1,(int)d2).getMaterial().blocksMovement())
            d1 += 1;

        if(world.getBlock((int)d0, (int)d1 -1, (int)d2) == Blocks.air)
        while(world.getBlock((int)d0, (int)d1 -1, (int)d2) == Blocks.air)
            d1 -= 1;

        if(entityHit instanceof EntityPlayer) {
            EntityPlayer pl = (EntityPlayer)entityHit;
            pl.setPositionAndUpdate(d0,d1,d2);
        }else{
            entityHit.setPosition(d0, d1, d2);
        }

        world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);

        return true;
    }


    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {

        return false;
    }

    @Override
    public String GetName() {
        return "Teleport Random";
    }

    @Override
    public double EnergyCost() {
        return 80;
    }

    @Override
    public String GetId() {
        return "TR";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Entity;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return new SpellModifier[]{new StrengthUpgrade()};
    }
}
