package CrystallineMagic.Utils.Proxies;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Utils.MagicInfoStorage;
import MiscUtils.Network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class ServerProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public void registerRenderThings(){

    }


    public int addArmor(String armor){
        return 0;
    }


    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }



    private static final String getSaveKey(EntityPlayer player) {
        return player.getCommandSenderName() + ":" + MagicInfoStorage.EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player) {
        MagicInfoStorage playerData = MagicInfoStorage.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        ServerProxy.storeEntityData(getSaveKey(player), savedData);
    }


    public static final void loadProxyData(EntityPlayer player) {
        MagicInfoStorage playerData = MagicInfoStorage.get(player);
        NBTTagCompound savedData = ServerProxy.getEntityData(getSaveKey(player));
        if (savedData != null) { playerData.loadNBTData(savedData); }

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player, CrystMagic.channels);
    }
}
