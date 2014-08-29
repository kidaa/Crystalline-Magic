package CrystallineMagic.Packets;

import CrystallineMagic.Utils.MagicEffects;
import MiscUtils.Network.AbstractPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.awt.*;

public class MagicSendParticleSync extends AbstractPacket
{

    int x, y, z;

    public MagicSendParticleSync(int x, int y, int z){

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MagicSendParticleSync(){}

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {
        if(side == Side.CLIENT){

            World world = player.worldObj;

            MagicEffects.SpawnMagicEffect(world, x-1, y, z-1, 8, 2, new Color(216, 213, 0));



        }

    }
}
