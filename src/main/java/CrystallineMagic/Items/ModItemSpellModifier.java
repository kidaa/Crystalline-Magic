package CrystallineMagic.Items;

import CrystallineApi.Spells.SpellModifier;
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

            SpellModifier mod = SpellUtils.GetModifierFromSpellModifier(stack);

            if(mod.IgnoreCompatibility()){
                list.add(EnumChatFormatting.GOLD + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.modifer.compatibleWithAll"));
            }

        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {

        if(SpellUtils.Modifiers.size() > 0)
        for(int i = 0; i < SpellUtils.Modifiers.size(); i++){
            ItemStack stack = new ItemStack(item);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Mod", StatCollector.translateToLocal("spellpart.modifier." + SpellUtils.Modifiers.get(i).GetName().toLowerCase().replace(" ", "_") + ".name"));
            stack.getTagCompound().setString("ModId", SpellUtils.Modifiers.get(i).GetId());

            list.add(stack);


        }

    }
}