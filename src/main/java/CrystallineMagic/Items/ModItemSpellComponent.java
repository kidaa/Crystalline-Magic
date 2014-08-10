package CrystallineMagic.Items;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ModItemSpellComponent extends Item {

    public ModItemSpellComponent(){
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CrystMagic.CreativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        if(stack.getTagCompound() != null){
            list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + stack.getTagCompound().getString("Comp"));

        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

      for(int i = 0; i < MagicUtils.Components.size(); i++){
          ItemStack stack = new ItemStack(item);

          stack.setTagCompound(new NBTTagCompound());
          stack.getTagCompound().setString("Comp", MagicUtils.Components.get(i).GetName());

          list.add(stack);


      }

    }
}
