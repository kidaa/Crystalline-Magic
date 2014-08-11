package CrystallineMagic.Items;

import CrystallineMagic.Event.Custom.EventSpellCast;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellModifier;
import CrystallineMagic.Utils.Spells.Utils.SpellType;
import CrystallineMagic.Utils.Spells.Utils.SpellUseType;
import MiscUtils.Utils.Handlers.ChatMessageHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

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

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {

     World world = target.worldObj;


        if(MagicUtils.GetSpellType(stack) != null && MagicUtils.GetSpellType(stack).GetUseType() == SpellUseType.Touch) {
            if (stack.getTagCompound() != null && MagicUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode) {


                double Eng = MagicUtils.GetSpellCost(stack);
                if (MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
                    if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {


                        if (MagicUtils.GetSpellType(stack) != null) {

                            SpellType type = MagicUtils.GetSpellType(stack);

                            if (!world.isRemote) {

                                EventSpellCast event = new EventSpellCast(player, stack);
                                MinecraftForge.EVENT_BUS.post(event);

                                if(event.isCanceled())
                                    return false;

                                if (type.OnUse(stack, player, target, world, (int) target.posX, (int) target.posY, (int) target.posZ, 0)) {
                                    if (!player.capabilities.isCreativeMode)
                                        MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);
                                }


                                return true;
                            }
                        }

                    } else {

                        if (player.worldObj.isRemote)
                            ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
                    }
                }

            }

        }

        return false;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sd, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {


        if(MagicUtils.GetSpellType(stack) != null && MagicUtils.GetSpellType(stack).GetUseType() == SpellUseType.Touch){
            if(stack.getTagCompound() != null && MagicUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode){

                double Eng = MagicUtils.GetSpellCost(stack);
                if(MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
                    if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {


                        if(MagicUtils.GetSpellType(stack) != null) {

                            SpellType type = MagicUtils.GetSpellType(stack);

                            if (!world.isRemote) {

                                EventSpellCast event = new EventSpellCast(player, stack);
                                MinecraftForge.EVENT_BUS.post(event);

                                if(event.isCanceled())
                                    return false;

                                if(type.OnUse(stack, player, null, world, x, y, z, sd))
                                if (!player.capabilities.isCreativeMode)
                                    MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);



                                return true;
                            }
                        }

                    }else {

                        if (player.worldObj.isRemote)
                            ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
                    }
                    }

            }

        }


        return false;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
         if(stack.getTagCompound() != null)
        if(MagicUtils.GetSpellType(stack) != null && MagicUtils.GetSpellType(stack).GetUseType() == SpellUseType.Ranged || MagicUtils.GetSpellType(stack) != null && MagicUtils.GetSpellType(stack).GetUseType() == SpellUseType.Self)
               if(stack.getTagCompound() != null && MagicUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode)
                player.setItemInUse(stack, this.getMaxItemUseDuration(stack));



        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        double Eng = MagicUtils.GetSpellCost(stack);
        if(MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
            if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {

                  if(MagicUtils.GetSpellType(stack) != null) {

                      SpellType type = MagicUtils.GetSpellType(stack);

                      if (!world.isRemote) {

                          EventSpellCast event = new EventSpellCast(player, stack);
                          MinecraftForge.EVENT_BUS.post(event);

                          if(event.isCanceled())
                              return stack;

                          if(type.OnUse(stack, player, null, world, (int)player.posX, (int)player.posY, (int)player.posZ, 0))
                          if (!player.capabilities.isCreativeMode)
                              MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);

                      }
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



        if(MagicUtils.GetSpellType(stack) != null) {
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC  + "* " + StatCollector.translateToLocal("items.desc.spell.type").replace("$type", MagicUtils.GetSpellType(stack).GetName()));
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "- " + StatCollector.translateToLocal("gui.spellcreation.components"));
        }


        SpellComponent[] Comps = MagicUtils.GetSpellComponents(stack);
        for(int i = 0; i < Comps.length; i++){
            if(Comps[i] != null){
                list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + Comps[i].GetName());
            }

        }

        SpellModifier[] Mods = MagicUtils.GetSpellModifiers(stack);

        if(Mods.length > 0 && Comps.length > 0)
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "- " + StatCollector.translateToLocal("gui.spellcreation.modifiers"));


        for(int i = 0; i < Mods.length; i++){
            if(Mods[i] != null){
                list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + Mods[i].GetName() + " x" + MagicUtils.GetAmountOfAModifer(stack, Mods[i]));
            }

        }


        if(Comps.length > 0){
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.cost").replace("$cost", (int)MagicUtils.GetSpellCost(stack) + ""));
        }else{
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.empty"));
        }
    }
}
