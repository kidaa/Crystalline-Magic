package CrystallineMagic.Items;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.ISpellPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ModItemSpellType extends Item implements ISpellPart {

    public ModItemSpellType(){
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CrystMagic.SpellPart);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        if(stack.getTagCompound() != null){
            list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + stack.getTagCompound().getString("Type"));

        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

        for(int i = 0; i < MagicUtils.Types.size(); i++){
            ItemStack stack = new ItemStack(item);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Type", MagicUtils.Types.get(i).GetName());
            stack.getTagCompound().setString("TypeId", MagicUtils.Types.get(i).GetId());

            list.add(stack);


        }

    }
}