package CrystallineMagic.Packets;


import CrystallineMagic.Utils.MagicInfoStorage;
import MiscUtils.Network.AbstractPacket;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsPacket extends AbstractPacket
{


    private NBTTagCompound data;

    public SyncPlayerPropsPacket() {}

    public SyncPlayerPropsPacket(EntityPlayer player) {
        data = new NBTTagCompound();
        MagicInfoStorage.get(player).saveNBTData(data);
    }



    @Override
    public void toBytes(ByteBuf data, Side side) {
        ByteBufUtils.writeTag(data, this.data);
    }

    @Override
    public void fromBytes(ByteBuf data, Side side) {
        this.data = ByteBufUtils.readTag(data);
    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {
            MagicInfoStorage.get(player).loadNBTData(data);

    }
}