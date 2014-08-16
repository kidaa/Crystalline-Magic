package CrystallineMagic.Utils.Effects;

import CrystallineMagic.Main.CrystMagic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EffectUpdate {


    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.entityLiving.isPotionActive(CrystMagic.GravityEffect)) {
            if(event.entityLiving.isAirBorne){
                event.entityLiving.motionY = -0.08;
            }

        }

        if (event.entityLiving.isPotionActive(CrystMagic.AntiGravityEffect)) {
            event.entityLiving.motionY = 0.02;

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
   }
}