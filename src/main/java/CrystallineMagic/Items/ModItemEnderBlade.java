package CrystallineMagic.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ModItemEnderBlade extends ItemSword {
    public ModItemEnderBlade() {
        super(ToolMaterial.WOOD);
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {


        list.add(EnumChatFormatting.GRAY +""+ EnumChatFormatting.ITALIC + StatCollector.translateToLocal("items.desc.enderblade") + EnumChatFormatting.RESET);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase entHitter)
    {
        stack.damageItem(1, entHitter);

        World world = entityHit.worldObj;


        Random rand = new Random();

        double MaxDistance = 18;

        double d0 = entityHit.posX + (rand.nextDouble() - 0.5D) * MaxDistance;
        double d1 = entityHit.posY + (double)(rand.nextInt(32) - 16);
        double d2 = entityHit.posZ + (rand.nextDouble() - 0.5D) * MaxDistance;

        while(world.getBlock((int)d0,(int)d1,(int)d2).getMaterial().blocksMovement())
            d1 += 1;

        if(world.getBlock((int)d0, (int)d1 -1, (int)d2) == Blocks.air)
            while(world.getBlock((int)d0, (int)d1 -1, (int)d2) == Blocks.air)
                d1 -= 1;

        world.playSoundEffect(entityHit.posX, entityHit.posY, entityHit.posZ, "mob.endermen.portal", 1.0F, 1.0F);

        if(entityHit instanceof EntityPlayer) {
            EntityPlayer pl = (EntityPlayer)entityHit;
            pl.setPositionAndUpdate(d0,d1,d2);
        }else{
            entityHit.setPosition(d0, d1, d2);
        }



        return true;
    }
}
