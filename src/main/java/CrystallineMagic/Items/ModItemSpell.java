package CrystallineMagic.Items;

import CrystallineApi.Spells.SpellUtils;
import CrystallineApi.Events.EventSpellCast;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUseType;
import MiscUtils.Handlers.ChatMessageHandler;
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


    //TODO Make spell projectile have custom color given by the first component in the list (All components should have a color which can then be used for spell effects)

    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 25;
    }


    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        if(SpellUtils.GetSpellType(stack) != null){
            if(SpellUtils.GetSpellComponents(stack).length > 1){
                return StatCollector.translateToLocal("item.spell.compound.name");
            }
        }

        return StatCollector.translateToLocal("item.spell.name");
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {

        if(stack.getTagCompound() != null) {
            World world = target.worldObj;
            SpellUseType useType = SpellUtils.GetSpellType(stack).GetUseType();
            SpellPartUsage use = SpellUtils.GetSpellType(stack).GetUsage();

            if (useType != null && use != null)
                if (useType == SpellUseType.Touch && use == SpellPartUsage.Both || useType == SpellUseType.Touch && use == SpellPartUsage.Entity) {
                    if (stack.getTagCompound() != null && SpellUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode) {


                        double Eng = SpellUtils.GetSpellCost(stack);
                        if (MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
                            if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {


                                if (SpellUtils.GetSpellType(stack) != null) {

                                    SpellType type = SpellUtils.GetSpellType(stack);


                                    EventSpellCast event = new EventSpellCast(player, stack);

                                    boolean t = type.OnUse(stack, player, target, world, (int) target.posX, (int) target.posY, (int) target.posZ, -1);

                                    if (t) {
                                        MinecraftForge.EVENT_BUS.post(event);

                                        if (!player.capabilities.isCreativeMode)
                                            MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);

                                    }else{
                                        return false;
                                    }


                                    return true;
                                }
                            } else {

                                if (player.worldObj.isRemote)
                                    ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
                            }

                        }
                    }

                }


        }

        return false;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sd, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if(stack.getTagCompound() != null) {
            SpellUseType useType = SpellUtils.GetSpellType(stack).GetUseType();
            SpellPartUsage use = SpellUtils.GetSpellType(stack).GetUsage();
            if (useType != null && use != null)
                if (useType == SpellUseType.Touch && use == SpellPartUsage.Both || useType == SpellUseType.Touch && use == SpellPartUsage.Block) {
                    if (stack.getTagCompound() != null && SpellUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode) {

                        double Eng = SpellUtils.GetSpellCost(stack);
                        if (MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
                            if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {


                                if (SpellUtils.GetSpellType(stack) != null) {

                                    SpellType type = SpellUtils.GetSpellType(stack);

                                    EventSpellCast event = new EventSpellCast(player, stack);

                                    boolean t = type.OnUse(stack, player, null, world, x, y, z, sd);

                                    if (t) {
                                        MinecraftForge.EVENT_BUS.post(event);

                                        if (!player.capabilities.isCreativeMode)
                                            MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);

                                    }else{
                                        return false;
                                    }



                                    return true;
                                }
                            } else {

                                if (player.worldObj.isRemote)
                                    ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
                            }

                        }
                    }

                }


        }
        return false;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getTagCompound() != null && SpellUtils.GetSpellType(stack) != null) {
            SpellUseType type = SpellUtils.GetSpellType(stack).GetUseType();
            SpellPartUsage use = SpellUtils.GetSpellType(stack).GetUsage();


            if(type != null && use != null)
            if (type == SpellUseType.Self && use == SpellPartUsage.Both || type == SpellUseType.Self && use == SpellPartUsage.Entity)
                if (stack.getTagCompound() != null && SpellUtils.GetSpellComponents(stack).length > 0 || stack.getTagCompound() != null && player.capabilities.isCreativeMode)
                    player.setItemInUse(stack, this.getMaxItemUseDuration(stack));


        }
        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        double Eng = SpellUtils.GetSpellCost(stack);
        if(MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic()) {
            if (MagicInfoStorage.get(player).GetPlayerEnergy() >= Eng || player.capabilities.isCreativeMode) {

                  if(SpellUtils.GetSpellType(stack) != null) {

                      SpellType type = SpellUtils.GetSpellType(stack);


                          EventSpellCast event = new EventSpellCast(player, stack);

                          if(type.OnUse(stack, player, null, world, (int)player.posX, (int)player.posY, (int)player.posZ, -1)) {
                              MinecraftForge.EVENT_BUS.post(event);


                              if (!player.capabilities.isCreativeMode)
                                  MagicInfoStorage.get(player).DecreasePlayerEnergy(Eng);

                          }
                      }
                  }else {

                if (player.worldObj.isRemote)
                    ChatMessageHandler.sendChatToPlayer(player, EnumChatFormatting.ITALIC + "" + EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("chat.message.spell.noEng"));
            }

            }



        return stack;
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {



        if(SpellUtils.GetSpellType(stack) != null) {
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC  + "* " + StatCollector.translateToLocal("items.desc.spell.type").replace("$type", SpellUtils.GetSpellType(stack).GetName()));
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "- " + StatCollector.translateToLocal("gui.spellcreation.components"));
        }


        SpellComponent[] Comps = SpellUtils.GetSpellComponents(stack);
        for(int i = 0; i < Comps.length; i++){
            if(Comps[i] != null){
                list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("spellpart.component." + Comps[i].GetName().toLowerCase().replace(" ", "_") + ".name"));
            }

        }

        SpellModifier[] Mods = SpellUtils.GetSpellModifiers(stack);

        if(Mods.length > 0 && Comps.length > 0)
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "- " + StatCollector.translateToLocal("gui.spellcreation.modifiers"));


        for(int i = 0; i < Mods.length; i++){
            if(Mods[i] != null){
                list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("spellpart.modifier." + Mods[i].GetName().toLowerCase().replace(" ", "_") + ".name") + " x" + SpellUtils.GetAmountOfAModifer(stack, Mods[i]));
            }

        }


        if(Comps.length > 0){
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.cost").replace("$cost", (int)SpellUtils.GetSpellCost(stack) + ""));
        }else{
            list.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "* " + StatCollector.translateToLocal("items.desc.spell.empty"));
        }
    }
}
