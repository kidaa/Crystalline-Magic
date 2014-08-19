package CrystallineMagic.Utils.Effects;

import CrystallineMagic.Main.CrystMagic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EffectUpdate {


    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving.isPotionActive(CrystMagic.GravityEffect)) {
            if(event.entityLiving.isAirBorne){
                event.entityLiving.addVelocity(0, -0.3, 0);
            }

        }



        if (event.entityLiving.isPotionActive(CrystMagic.AntiGravityEffect)) {
            double m = 0.083;
            event.entityLiving.setVelocity(event.entityLiving.motionX, m, event.entityLiving.motionZ);
        }



        if (event.entityLiving.isPotionActive(CrystMagic.LowGravityEffect)) {
            event.entityLiving.addVelocity(0, 0.05, 0);
            event.entityLiving.fallDistance = 0;
        }



        if (event.entityLiving.isPotionActive(CrystMagic.AntiGravityEffect))
        if (event.entityLiving.getActivePotionEffect(CrystMagic.AntiGravityEffect).getDuration()==0) {
            event.entityLiving.removePotionEffect(CrystMagic.AntiGravityEffect.getId());
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