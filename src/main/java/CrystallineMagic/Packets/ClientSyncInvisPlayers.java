package CrystallineMagic.Packets;


import CrystallineMagic.Utils.InvisibilityUtils;
import MiscUtils.Network.AbstractPacket;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientSyncInvisPlayers extends AbstractPacket {

    int Mode;
    String player;


    public ClientSyncInvisPlayers(){}
    public ClientSyncInvisPlayers(int Mode, EntityPlayer player){
        this.Mode = Mode;
        if(player != null)
        this.player = player.getDisplayName();
    }


    @Override
    public void fromBytes(ByteBuf buf, Side side) {
        Mode = buf.readInt();
        player = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf, Side side) {

        buf.writeInt(Mode);
        if(player != null)
        ByteBufUtils.writeUTF8String(buf,player);
    }

    @Override
    public void onMessage(Side side, EntityPlayer pl) {
        if(player != null) {
            EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(this.player);

            if (player != null) {
                if (Mode == 1) {
                    InvisibilityUtils.AddInvisiblePlayer(player, false);
                } else if (Mode == 2) {
                    InvisibilityUtils.RemoveInvisiblePlayer(player, false);
                }

            }

        }
    }
}
