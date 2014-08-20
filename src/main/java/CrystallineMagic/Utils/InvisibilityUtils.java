package CrystallineMagic.Utils;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.ServerSyncInvisPlayers;
import MiscUtils.Network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

public class InvisibilityUtils {

    private static ArrayList<EntityPlayer> InvisPlayers = new ArrayList<EntityPlayer>();

    public static ArrayList<EntityPlayer> GetList(){
        return InvisPlayers;
    }

    public static void AddInvisiblePlayer(EntityPlayer player, boolean Sync){
        if(!InvisPlayers.contains(player))
        InvisPlayers.add(player);

        if(Sync)
        PacketHandler.sendToServer(new ServerSyncInvisPlayers(1, player), CrystMagic.Utils.channels);


    }

    public static void RemoveInvisiblePlayer(EntityPlayer player, boolean Sync){
        if(InvisPlayers.contains(player))
            InvisPlayers.remove(player);

        if(Sync)
        PacketHandler.sendToServer(new ServerSyncInvisPlayers(2,player), CrystMagic.Utils.channels);


    }
}
