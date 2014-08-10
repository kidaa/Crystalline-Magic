package CrystallineMagic.TileEntities;


import CrystallineApi.Magic.IEnergyStorageItem;
import CrystallineApi.Magic.IMagicReceiver;
import CrystallineMagic.Utils.MagicUtils;
import MiscUtils.TileEntity.TileEntityInvBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMagicalEnergyRecharger extends TileEntityInvBase implements IMagicReceiver {

    public double Power = 0;
    public static double MaxPower = 500;

    public double CurrentItemPower = 0;
    public double CurrentItemMaxPower = 0;

    int Tick = 0;
    static int ReachTick = 5;

    public void updateEntity(){
        if(this.getStackInSlot(0) != null){
            if(this.getStackInSlot(0).getItem() instanceof IEnergyStorageItem){
                ItemStack stack = this.getStackInSlot(0);
                IEnergyStorageItem stor = (IEnergyStorageItem)stack.getItem();

                CurrentItemMaxPower = stor.GetMaxEnergy(stack);
                CurrentItemPower = stor.GetEnergyStored(stack);


                if(CurrentItemPower < CurrentItemMaxPower){
                    if(GetStoredEnergy() > 0){
                        if(Tick >= ReachTick) {

                            Tick = 0;
                            Power -= 1;
                            stor.AddEnergy(stack, 1);

                        }else{
                           Tick += 1;
                        }


                    }
                }

            }

    }else{
           CurrentItemMaxPower = 0;
           CurrentItemPower = 0;
      }

    }

    public TileEntityMagicalEnergyRecharger() {
        super(1, "MagicalRecharger", 1);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Power = nbtTagCompound.getDouble("Power");

        Tick = nbtTagCompound.getInteger("Tick");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setDouble("Power", Power);

        nbtTagCompound.setInteger("Tick", Tick);

    }


    @Override
    public double GetStoredEnergy() {
        return Power;
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
