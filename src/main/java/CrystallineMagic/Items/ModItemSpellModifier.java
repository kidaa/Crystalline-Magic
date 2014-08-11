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

public class ModItemSpellModifier extends Item implements ISpellPart {

    public ModItemSpellModifier(){
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CrystMagic.SpellPart);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        if(stack.getTagCompound() != null){
            list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + stack.getTagCompound().getString("Mod"));

        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

        if(MagicUtils.Modifiers.size() > 0)
        for(int i = 0; i < MagicUtils.Modifiers.size(); i++){
            ItemStack stack = new ItemStack(item);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Mod", MagicUtils.Modifiers.get(i).GetName());
            stack.getTagCompound().setString("ModId", MagicUtils.Modifiers.get(i).GetId());

            list.add(stack);


        }

    }
}