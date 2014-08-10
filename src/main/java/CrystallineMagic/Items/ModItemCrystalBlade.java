package CrystallineMagic.Items;

import CrystallineMagic.Main.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;



public class ModItemCrystalBlade extends ItemSword {


    public ModItemCrystalBlade() {
        super(ModItems.CrystalMaterial);
        this.setMaxDamage(-1);

    }

    public boolean UseEnergy(EntityPlayer player, int Amount, ItemStack stack){

        for(int i = 0; i < player.inventory.getSizeInventory(); i++){
            if(player.inventory.getStackInSlot(i) != null){
                ItemStack tmp = player.inventory.getStackInSlot(i);
                if(tmp.getItem() == stack.getItem()){
                    int t = tmp.getMaxDamage() - tmp.getItemDamage();
                    if(t < Amount)  {
                        tmp.setItemDamage(tmp.getMaxDamage());
                        Amount -= t;
                        continue;

                    }else {
                        tmp.setItemDamage(tmp.getItemDamage() + Amount);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase EntityHit, EntityLivingBase EntityAttacker)
    {

        if(EntityAttacker instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)EntityAttacker;

            if(UseEnergy(player,10,new ItemStack(ModItems.ChargedCrystal))){
                EntityHit.attackEntityFrom(DamageSource.causeMobDamage(player), 18);
            }
        }


        return true;
    }


    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        return true;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }



//    public boolean hasEffect(ItemStack stack)
//    {
//        return stack.getTagCompound() != null;
//    }

    public int getItemEnchantability()
    {
        return 0;
    }



}

