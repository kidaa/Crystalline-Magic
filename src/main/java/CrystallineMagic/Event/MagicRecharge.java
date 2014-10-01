package CrystallineMagic.Event;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicRef;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class MagicRecharge {



    @SubscribeEvent
    public void event(TickEvent.PlayerTickEvent event){
      if(MagicInfoStorage.get(event.player) != null){

          MagicInfoStorage data = MagicInfoStorage.get(event.player);


          if(data.GetPlayerLevel() <= 0)
              data.SetPlayerLevel(1);


          int g = (data.GetPlayerLevel() / 3);



          if(g <= 0)
              g = 1;



          data.Recharge = MagicRef.BaseRechargeTime / g;

          if(data.Recharge <= 0)
              data.Recharge = 1;

          if(data.HasMagic() && data.GetPlayerEnergy() < data.GetPlayerMaxEnergy()){

              if(data.RechargeTime >= data.Recharge){
                  data.RechargeTime = 0;

                  double x = 1 + (data.GetPlayerMaxEnergy() / 500);

                  if(CrystMagic.HasMagicRoobes(event.player))
                  {

                      x *= 3;
                  }

                 data.IncreasePlayerEnergy(x);


              }else{
                  data.RechargeTime += 1;
              }


          }

        }


    }
}
