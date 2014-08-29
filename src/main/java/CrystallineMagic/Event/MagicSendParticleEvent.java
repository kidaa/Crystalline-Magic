package CrystallineMagic.Event;

import CrystallineApi.Events.MagicSendEvent;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.MagicSendParticleSync;
import MiscUtils.Network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MagicSendParticleEvent {


    @SubscribeEvent
    public void Event(MagicSendEvent event){
        PacketHandler.sendToDimension(new MagicSendParticleSync(event.recTile.xCoord, event.recTile.yCoord, event.recTile.zCoord), event.recTile.getWorldObj().provider.dimensionId, CrystMagic.Utils.channels);

    }
}
