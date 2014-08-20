package CrystallineMagic.Event;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import MiscUtils.Network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class JoinWorld {


    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
            PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(((EntityPlayer) event.entity)), (EntityPlayer) event.entity, CrystMagic.Utils.channels);
    }
}
