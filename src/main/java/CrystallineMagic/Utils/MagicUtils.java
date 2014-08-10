package CrystallineMagic.Utils;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.MagicReciveParticleEffects;
import CrystallineMagic.Utils.Spells.SpellComponent;
import MiscUtils.Network.PacketHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MagicUtils {

    public static void ReceiveEnergy(TileEntity tile){
        PacketHandler.sendToDimension(new MagicReciveParticleEffects(tile.xCoord, tile.yCoord, tile.zCoord), tile.getWorldObj().provider.dimensionId, CrystMagic.channels);

    }


    public static SpellComponent[] GetSpellComponents(ItemStack stack){

        return null;
    }

}
