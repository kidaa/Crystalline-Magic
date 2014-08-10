package CrystallineApi.Magic;

public interface IMagicReceiver {


    public double GetStoredEnergy();
    public double GetMaxEnergy();

    public boolean CanReceiveEnergy();
    public boolean CanReceiveEnergyAmount(double i);

    public void ReceiveEnergy(double i);





}
