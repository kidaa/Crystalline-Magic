package CrystallineMagic.Utils;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.MagicReciveParticleEffects;
import MiscUtils.Network.PacketHandler;
import net.minecraft.tileentity.TileEntity;

public class MagicUtils {

    public static void ReceiveEnergy(TileEntity tile){
        PacketHandler.sendToDimension(new MagicReciveParticleEffects(tile.xCoord, tile.yCoord, tile.zCoord), tile.getWorldObj().provider.dimensionId, CrystMagic.Utils.channels);
    }

}
