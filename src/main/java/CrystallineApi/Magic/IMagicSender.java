package CrystallineApi.Magic;

import net.minecraft.tileentity.TileEntity;

public interface IMagicSender {

    public double GetStoredEnergy();
    public double GetEnergyOffered();
    public double GetEnergyPacketSize();

    public boolean CanSendEnergy();
    public boolean CanSendEnergyAmount(double i);

    public void DecreaseEnergy(double i);

    public void SendEnergy(IMagicReceiver receiver, double i);

    public void OnSendEnergyPacket(TileEntity tile, IMagicReceiver receiver);
}
