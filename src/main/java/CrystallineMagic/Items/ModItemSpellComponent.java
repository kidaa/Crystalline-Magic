package CrystallineMagic.Items;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.ISpellPart;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ModItemSpellComponent extends Item implements ISpellPart{

    public ModItemSpellComponent(){
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CrystMagic.SpellPart);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        if(stack.getTagCompound() != null){
            list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + stack.getTagCompound().getString("Comp"));

            SpellComponent comp = MagicUtils.GetCompFromSpellComp(stack);
            if(comp.GetUsage() == SpellPartUsage.Block){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.block"));

            }else if(comp.GetUsage() == SpellPartUsage.Entity){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.entity"));

            }else if(comp.GetUsage() == SpellPartUsage.Both){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.both"));
            }



        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

        if(MagicUtils.Components.size() > 0)
      for(int i = 0; i < MagicUtils.Components.size(); i++){
          ItemStack stack = new ItemStack(item);

          stack.setTagCompound(new NBTTagCompound());
          stack.getTagCompound().setString("Comp", StatCollector.translateToLocal("spellpart.component." + MagicUtils.Components.get(i).GetName().toLowerCase().replace(" ", "_") + ".name"));
          stack.getTagCompound().setString("CompId", MagicUtils.Components.get(i).GetId());

          list.add(stack);


      }

    }
}
