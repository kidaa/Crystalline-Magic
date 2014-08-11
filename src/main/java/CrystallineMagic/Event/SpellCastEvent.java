package CrystallineMagic.Event;

import CrystallineMagic.Event.Custom.EventSpellCast;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpellCastEvent {

    @SubscribeEvent
    public void Event(EventSpellCast event){
        if(!event.player.worldObj.isRemote)
        if(MagicInfoStorage.get(event.player) != null){
            if(MagicInfoStorage.get(event.player).HasMagic()){
                MagicInfoStorage data = MagicInfoStorage.get(event.player);

                int g = (int)MagicUtils.GetSpellCost(event.stack);
                int Xp = (g / 20) / (data.GetPlayerLevel() / 3);

                if(Xp <= 0)
                    Xp = 1;

                data.IncreasePlayerXp(Xp);

            }

        }

    }
}
