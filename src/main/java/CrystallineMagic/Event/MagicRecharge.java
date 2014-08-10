package CrystallineMagic.Event;

import CrystallineMagic.Utils.MagicInfoStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class MagicRecharge {



    @SubscribeEvent
    public void event(TickEvent.PlayerTickEvent event){
      if(MagicInfoStorage.get(event.player) != null){


          MagicInfoStorage data = MagicInfoStorage.get(event.player);

          if(data.HasMagic() && data.GetPlayerEnergy() < data.GetPlayerMaxEnergy()){

              if(data.RechargeTime >= data.Recharge){
                  data.RechargeTime = 0;

                 data.IncreasePlayerEnergy(1);


              }else{
                  data.RechargeTime += 1;
              }


          }


        }


    }
}
