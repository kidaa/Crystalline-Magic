package CrystallineMagic.Packets;


import CrystallineApi.Magic.IMagicReceiver;
import CrystallineMagic.Main.CrystMagic;
import MiscUtils.Network.AbstractPacket;
import MiscUtils.Utils.Handlers.ParticleHelper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MagicReciveParticleEffects extends AbstractPacket {

    int x,y,z;

    public MagicReciveParticleEffects(){}
    public MagicReciveParticleEffects(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        x= buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {

        if(player.worldObj.getTileEntity(x,y,z) != null && player.worldObj.getTileEntity(x,y,z) instanceof IMagicReceiver){

            ParticleHelper helper = new ParticleHelper(player.worldObj, CrystMagic.config.CanSpawnParticles());

            helper.SpawnParticleRandomDr("happyVillager", x, y, z, 2, 2, 7);
        }

    }
}
