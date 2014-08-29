package CrystallineMagic.Event;


import CrystallineMagic.Items.ModItemInvisArmor;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Utils.InvisibilityUtils;
import CrystallineMagic.Proxies.ClientProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

import java.util.Random;

public class InvisibilityEvents {



    @SideOnly(Side.CLIENT)
    @SubscribeEvent
         public void CancelRenderEvent(RenderPlayerEvent.Pre event){
        if(InvisibilityUtils.GetList().contains(event.entityPlayer)){
            event.setCanceled(true);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void CancelRenderEvent(RenderPlayerEvent.Post event){
        if(InvisibilityUtils.GetList().contains(event.entityPlayer)){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void CancelMobAttack(LivingSetAttackTargetEvent event){
        if(event.target != null && event.target instanceof EntityPlayer && event.entity instanceof EntityMob){
            EntityPlayer player = (EntityPlayer)event.target;
            if(InvisibilityUtils.GetList().contains(player)){
                ((EntityMob) event.entity).setAttackTarget(null);
            }
        }
    }

    @SubscribeEvent
    public void CorrectTargetIfInvis(LivingEvent.LivingUpdateEvent event){
        if(event.entity instanceof EntityMob){
            EntityMob mob = (EntityMob)event.entity;
            if(mob.getAttackTarget() != null && mob.getAttackTarget() instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer)mob.getAttackTarget();
                if(InvisibilityUtils.GetList().contains(player))
                    mob.setAttackTarget(null);
            }
        }
        }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void CheckArmorEvent(TickEvent.ClientTickEvent event){
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory != null) {

            if (Minecraft.getMinecraft().thePlayer.inventory.armorInventory[0] != null
                    && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[1] != null
                    && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2] != null
                    && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3] != null) {
                if (

                        Minecraft.getMinecraft().thePlayer.inventory.armorInventory[0].getItem() instanceof ModItemInvisArmor
                                && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[1].getItem() instanceof ModItemInvisArmor
                                && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2].getItem() instanceof ModItemInvisArmor
                                && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3].getItem() instanceof ModItemInvisArmor) {
                    ClientProxy.HasValidInvisibilityArmor = true;
                } else {
                    ClientProxy.HasValidInvisibilityArmor = false;

                    if (InvisibilityUtils.GetList().contains((EntityPlayer) Minecraft.getMinecraft().thePlayer))
                        InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer) Minecraft.getMinecraft().thePlayer, true);
                }

            }else {
                ClientProxy.HasValidInvisibilityArmor = false;

                if (InvisibilityUtils.GetList().contains((EntityPlayer) Minecraft.getMinecraft().thePlayer))
                    InvisibilityUtils.RemoveInvisiblePlayer((EntityPlayer) Minecraft.getMinecraft().thePlayer, true);
            }
        }

    }

    @SubscribeEvent
    public void UsePower(TickEvent.PlayerTickEvent event){
        if(InvisibilityUtils.GetList().contains(event.player)){

            for(int i = 0; i < event.player.inventory.getSizeInventory(); i++){
                if(event.player.inventory.getStackInSlot(i) != null && event.player.inventory.getStackInSlot(i).getItem() == ModItems.InvisibilityCore && event.player.inventory.getStackInSlot(i).getItemDamage() < event.player.inventory.getStackInSlot(i).getMaxDamage()){
                    ItemStack stack = event.player.inventory.getStackInSlot(i);

                    Random rand = new Random();
                    if(rand.nextInt(50) == 36){
                        stack.setItemDamage(stack.getItemDamage() + 1);
                    }
                    return;

                }else{
                    continue;
                }
            }

            InvisibilityUtils.RemoveInvisiblePlayer(event.player, true);

        }
    }



}
