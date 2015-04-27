package CrystallineMagic.Effects;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Spells.SpellComponents.Gravity;
import CrystallineMagic.Spells.SpellComponents.Levitation;
import CrystallineMagic.Spells.SpellComponents.LowGravity;
import CrystallineMagic.Utils.MagicEffects;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EffectUpdate {


    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if(event.entityLiving instanceof EntityPlayer){
            EntityPlayer pl = (EntityPlayer)event.entityLiving;

            if(pl.capabilities.isCreativeMode)
                return;
        }


        if (event.entityLiving.isPotionActive(CrystMagic.GravityEffect)) {
            MagicEffects.SpawnMagicEffect(event.entityLiving.worldObj, event.entityLiving.posX - 0.8, event.entityLiving.posY + event.entityLiving.getEyeHeight(), event.entityLiving.posZ - 0.8, 2, 1, new Gravity().GetComponentColor());
            event.entityLiving.setVelocity(0, -1, 0);
        }


        if (event.entityLiving.isPotionActive(CrystMagic.levitation)) {
            double m = 0.083;
            event.entityLiving.setVelocity(event.entityLiving.motionX, m, event.entityLiving.motionZ);
            MagicEffects.SpawnMagicEffect(event.entityLiving.worldObj, event.entityLiving.posX - 1.2, event.entityLiving.posY - 1.2, event.entityLiving.posZ - 1.2, 3, 2, new Levitation().GetComponentColor());
        }


        if (event.entityLiving.isPotionActive(CrystMagic.LowGravityEffect)) {
            event.entityLiving.addVelocity(0, 0.06, 0);
            event.entityLiving.fallDistance = 0;

            MagicEffects.SpawnMagicEffect(event.entityLiving.worldObj, event.entityLiving.posX - 1.2, event.entityLiving.posY, event.entityLiving.posZ - 1.2, 2, 2, new LowGravity().GetComponentColor());
        }




        if (event.entityLiving.isPotionActive(CrystMagic.levitation))
        if (event.entityLiving.getActivePotionEffect(CrystMagic.levitation).getDuration()==0) {
            event.entityLiving.removePotionEffect(CrystMagic.levitation.getId());
            return;
        }


        if (event.entityLiving.isPotionActive(CrystMagic.GravityEffect))
            if (event.entityLiving.getActivePotionEffect(CrystMagic.GravityEffect).getDuration()==0) {
                event.entityLiving.removePotionEffect(CrystMagic.GravityEffect.getId());
                return;
            }

        if (event.entityLiving.isPotionActive(CrystMagic.LowGravityEffect))
            if (event.entityLiving.getActivePotionEffect(CrystMagic.LowGravityEffect).getDuration()==0) {
                event.entityLiving.removePotionEffect(CrystMagic.LowGravityEffect.getId());
                return;
            }
   }
}