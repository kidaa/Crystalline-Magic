package CrystallineMagic.Items;

import baubles.api.IBauble;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

@Optional.Interface(iface = "baubles.api.IBauble",    modid = "Baubles")
public abstract class ModItemBaublesItem extends Item implements IBauble {

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {


        list.add(EnumChatFormatting.GRAY +""+ EnumChatFormatting.ITALIC + StatCollector.translateToLocal("items.desc.baublesRequired") + EnumChatFormatting.RESET);
    }
}
