package CrystallineMagic.Items;

import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Spells.ISpellPart;
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


            SpellType type = SpellUtils.GetTypeFromSpellType(stack);

            if(type.GetUsage() == SpellPartUsage.Block){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.block"));

            }else if(type.GetUsage() == SpellPartUsage.Entity){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.entity"));

            }else if(type.GetUsage() == SpellPartUsage.Both){
                list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.usage.both"));
            }


            SpellModifier[] CompMods = type.CompatibleModifiers();


            if(CompMods != null && CompMods.length > 0){

                    list.add(EnumChatFormatting.GOLD + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.compatibleMods"));

                    for (int i = 0; i < CompMods.length; i++) {

                        list.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + "- " + StatCollector.translateToLocal("spellpart.modifier." + CompMods[i].GetName().toLowerCase().replace(" ", "_") + ".name"));
                    }

            }
        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

        for(int i = 0; i < SpellUtils.Types.size(); i++){
            ItemStack stack = new ItemStack(item);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Type", StatCollector.translateToLocal("spellpart.type." + SpellUtils.Types.get(i).GetName().toLowerCase().replace(" ", "_") + ".name"));
            stack.getTagCompound().setString("TypeId", SpellUtils.Types.get(i).GetId());

            list.add(stack);


        }

    }
}