package CrystallineMagic.Items;

import CrystallineMagic.Utils.InvisibilityUtils;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.Ref;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ModItemInvisibilityAmulet extends ModItemBaublesItem {




    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {

        itemIcon = par1IconRegister.registerIcon(Ref.ModId.toLowerCase() + ":InvisibilityAmulet");
    }


    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase pl) {

        EntityPlayer player = (EntityPlayer)pl;
        Random rand = new Random();


        if(MagicInfoStorage.get(player) != null){
            if(MagicInfoStorage.get(player).HasMagic()){

                double def = 4;
                    double energy = def;

                    if(energy <= 0)
                        energy = def;


                    if (!InvisibilityUtils.GetList().contains((EntityPlayer) player))
                        InvisibilityUtils.AddInvisiblePlayer((EntityPlayer) player, true);

                    if (MagicInfoStorage.get(player).GetPlayerEnergy() >= energy) {
                        MagicInfoStorage.get(player).DecreasePlayerEnergy(energy / 10);


                    } else {
                        InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer) player, true);

                }

            }else{
                InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer)player, true);
            }

        }else{
            InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer)player, true);
        }


    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
             if (!InvisibilityUtils.GetList().contains((EntityPlayer)player))
                InvisibilityUtils.AddInvisiblePlayer((EntityPlayer)player, true);

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
       InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer)player, true);

    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return  MagicInfoStorage.get((EntityPlayer)player) != null && MagicInfoStorage.get((EntityPlayer)player).HasMagic();
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}
