package CrystallineMagic.Items;

import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.SpellComponent;
import MiscUtils.Utils.Handlers.ChatMessageHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ModItemSpell extends Item {



    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 25;
    }


    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }


    public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer player)
    {

               if(stack.getTagCompound() != null && MagicUtils.GetSpellComponents(stack).length > 0)
                player.setItemInUse(stack, this.getMaxItemUseDuration(stack));



        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        EntitySpellProjectile EntSpell = new EntitySpellProjectile(world, player, 2.0F, MagicUtils.GetSpellComponents(stack));

        double Eng = MagicUtils.GetSpellCost(stack);

        if(MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
            if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng) {

                if (!world.isRemote) {
                    MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);
                    world.spawnEntityInWorld(EntSpell);
                }


            } else {

                if (player.worldObj.isRemote)
                    ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
            }

        }

        return stack;
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        SpellComponent[] Comps = MagicUtils.GetSpellComponents(stack);

        for(int i = 0; i < Comps.length; i++){
            if(Comps[i] != null){
                list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + Comps[i].GetName());
            }

        }

        if(Comps.length > 0){
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.cost").replace("$cost", MagicUtils.GetSpellCost(stack) + ""));
        }else{
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.empty"));
        }
    }
}
