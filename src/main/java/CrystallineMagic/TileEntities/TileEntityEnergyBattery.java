package CrystallineMagic.TileEntities;


import CrystallineApi.Magic.IMagicReceiver;
import CrystallineApi.Magic.IMagicSender;
import CrystallineApi.Magic.MagicEnergyUtils;
import CrystallineMagic.Utils.MagicUtils;
import MiscUtils.TileEntity.ModTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyBattery extends ModTileEntity implements IMagicReceiver, IMagicSender {


    public double Power;

    public static double MaxPower = 400;

    int g = 0;


    public void updateEntity(){


        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            if (g >= 50) {
                g = 0;

                MagicEnergyUtils.SendPowerToNearbyReceivers(this);
            } else
                g += 1;

        }else
            g = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        Power = nbtTagCompound.getDouble("Power");

        g = nbtTagCompound.getInteger("G");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setDouble("Power", Power);

        nbtTagCompound.setInteger("G", g);
    }

    @Override
    public double GetStoredEnergy() {
        return Power;
    }

    @Override
    public double GetEnergyOffered() {
        return Power;
    }

    @Override
    public double GetEnergyPacketSize() {
        return Power >= 5 ? 5 : 1;
    }

    @Override
    public boolean CanSendEnergy() {
        return Power > 0 && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean CanSendEnergyAmount(double i) {
        return CanSendEnergy() && Power >= i;
    }

    @Override
    public void DecreaseEnergy(double i) {
        if(Power >= i)
            Power -= i;
        else
            Power = 0;
    }

    @Override
    public void SendEnergy(IMagicReceiver receiver, double i) {
        if(CanSendEnergyAmount(i)){
            if(receiver.CanReceiveEnergyAmount(i)){
                DecreaseEnergy(i);
                receiver.ReceiveEnergy(i);

            }
        }

    }

    @Override
    public void OnSendEnergyPacket(TileEntity tile, IMagicReceiver receiver) {

        if(receiver.CanReceiveEnergy() && CanSendEnergyAmount(GetEnergyPacketSize())){

            receiver.ReceiveEnergy(GetEnergyPacketSize());
            DecreaseEnergy(GetEnergyPacketSize());


 }
    }

    @Override
    public double GetMaxEnergy() {
        return MaxPower;
    }

    @Override
    public boolean CanReceiveEnergy() {
        return Power < MaxPower;
    }

    @Override
    public boolean CanReceiveEnergyAmount(double i) {
        return Power + i <= MaxPower;
    }

    @Override
    public void ReceiveEnergy(double i) {
        Power += i;

        MagicUtils.ReceiveEnergy(this);
    }

}
