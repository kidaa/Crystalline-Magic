package CrystallineMagic.Event;

import CrystallineMagic.Utils.MagicInfoStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class LevelUpHandeling {

    @SubscribeEvent
    public void event(TickEvent.PlayerTickEvent event) {
        if (MagicInfoStorage.get(event.player) != null) {
            MagicInfoStorage data = MagicInfoStorage.get(event.player);

            if (data.HasMagic() && !event.player.worldObj.isRemote) {

                if (data.GetPlayerXp() >= data.GetRequiredXp()) {

                    if (data.GetPlayerXp() > data.GetRequiredXp()) {
                        data.DecreasePlayerXp(data.GetRequiredXp());
                    } else {
                        data.SetPlayerXp(0);
                    }

                    data.IncreasePlayerLevelWithEngUpdate(1);

                }

            }

        }
    }
}

