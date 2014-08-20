package CrystallineMagic.Packets;


import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.InvisibilityUtils;
import MiscUtils.Network.AbstractPacket;
import MiscUtils.Network.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class ServerSyncInvisPlayers extends AbstractPacket {

    int Mode;
    String player;


    public ServerSyncInvisPlayers(){}
    public ServerSyncInvisPlayers(int Mode, EntityPlayer player){
        this.Mode = Mode;
        this.player = player.getCommandSenderName();
    }


    @Override
    public void fromBytes(ByteBuf buf, Side side) {
        Mode = buf.readInt();
        player = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf, Side side) {

        buf.writeInt(Mode);
        ByteBufUtils.writeUTF8String(buf,player);
    }

    @Override
    public void onMessage(Side side, EntityPlayer pl) {
        EntityPlayer player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(this.player);


        if (player != null) {
            if (Mode == 1) {
                InvisibilityUtils.AddInvisiblePlayer(player, false);
            } else if (Mode == 2) {
                InvisibilityUtils.RemoveInvisiblePlayer(player, false);
            }

        }

        PacketHandler.sendToAll(new ClientSyncInvisPlayers(Mode, player), CrystMagic.Utils.channels);

    }
}
