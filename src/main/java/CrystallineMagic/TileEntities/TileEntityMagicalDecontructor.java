package CrystallineMagic.TileEntities;


import CrystallineApi.Magic.IEnergyStorageItem;
import CrystallineApi.Magic.IMagicReceiver;
import CrystallineApi.Magic.IMagicSender;
import CrystallineApi.Magic.MagicEnergyUtils;
import CrystallineMagic.Items.ModItemSoulOrb;
import CrystallineMagic.Utils.MagicalMaterialUtils;
import CrystallineMagic.Utils.MaterialValue;
import MiscUtils.TileEntity.TileEntityInvBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagicalDecontructor extends TileEntityInvBase implements IMagicSender {

    public double Energy;

    public int GenTimer = 0;
    public static int GenTime = 25;

    int SenderTimer = 0;
    int SendTime = 20;

    public TileEntityMagicalDecontructor() {
        super(1, "MagicalDeconstructor", 64);
    }

    public void updateEntity(){

        if(this.getStackInSlot(0) != null){
            if(MagicalMaterialUtils.ContainesStack(this.getStackInSlot(0))){
                MaterialValue val = MagicalMaterialUtils.GetValue(this.getStackInSlot(0));
                ItemStack stack = this.getStackInSlot(0);

                if(Energy + 1 <= GetMaxEnergy())
                if(val != null && val.value > 0.0 || this.getStackInSlot(0).getItem() instanceof IEnergyStorageItem && !(stack.getItem() instanceof ModItemSoulOrb) || stack.getItem() instanceof ModItemSoulOrb && ((ModItemSoulOrb)stack.getItem()).GetOwner(stack) != null) {

                    if (GenTimer >= GenTime || stack.getItem() instanceof ModItemSoulOrb && ((ModItemSoulOrb)stack.getItem()).GetOwner(stack) != null) {
                        GenTimer = 0;

                    if (stack.getItem() instanceof IEnergyStorageItem) {
                        GenTimer = 0;

                        IEnergyStorageItem itm = (IEnergyStorageItem) this.getStackInSlot(0).getItem();



                        //Removing power from a players soul orb
                        if(itm instanceof ModItemSoulOrb){

                            ModItemSoulOrb orb = (ModItemSoulOrb)itm;

                            if(orb.GetOwner(stack) != null){

                                if(orb.GetEnergyStored(stack) > 0.0){
                                    orb.RemoveEnergy(stack, 1);
                                    Energy += 1;


                                }else{

                                    //Removing power from a players health
                                    if(orb.GetOwner(stack).getHealth() > 1){

                                        orb.RemoveEnergy(stack, 10);
                                        Energy += 0.5;

                                    }

                                }

                            }



                        }else {

                            //Removing power from a storage item
                            if (itm.GetEnergyStored(this.getStackInSlot(0)) > 0.0) {
                                itm.RemoveEnergy(this.getStackInSlot(0), 0.5);
                                Energy += 0.5;

                            }
                        }




                    }



                    //Deconstructing a normal item
                    else {


                        if (Energy + (val.value / 3) <= GetMaxEnergy()) {

                            this.decrStackSize(0, 1);
                            Energy += val.value / 3;

                        } else {

                            if (Energy < GetMaxEnergy()) {
                                this.decrStackSize(0, 1);
                                Energy = GetMaxEnergy();
                            }
                        }

                    }

                        } else {
                            if (Energy + (val.value / 3) <= GetMaxEnergy())
                                GenTimer += 1;
                            else
                                GenTimer = 0;
                        }



                }else{
                    if(GenTimer > 0)
                        GenTimer = 0;
                }



            }else
                GenTimer = 0;

        }else
            GenTimer = 0;

        if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && CanSendEnergyAmount(GetEnergyPacketSize())) {
            if (SenderTimer >= SendTime) {
                SenderTimer = 0;
                if (CanSendEnergyAmount(GetEnergyPacketSize()))
                    MagicEnergyUtils.SendPowerToNearbyReceiversWithRadius(this, 10);


            } else {
                SenderTimer += 1;
            }

        }else{
            SenderTimer = 0;
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Energy = nbtTagCompound.getDouble("En");

        GenTimer = nbtTagCompound.getInteger("Gen");

        SenderTimer = nbtTagCompound.getInteger("Send");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setDouble("En", Energy);

        nbtTagCompound.setInteger("Gen", GenTime);

        nbtTagCompound.setInteger("Send", SenderTimer);

    }



    @Override
    public double GetStoredEnergy() {
        return Energy;
    }

    @Override
    public double GetEnergyOffered() {
        return Energy;
    }

    @Override
    public double GetEnergyPacketSize() {
        return 1;
    }

    @Override
    public boolean CanSendEnergy() {
        return worldObj.isBlockIndirectlyGettingPowered(xCoord,yCoord,zCoord);
    }

    @Override
    public void DecreaseEnergy(double i) {
        if(Energy >= i)
            Energy -= i;
        else
            Energy = 0;
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

    public double GetMaxEnergy() {
        return 3000;
    }


    public boolean CanSendEnergyAmount(double i) {
        return Energy >= i && CanSendEnergy();
    }
}
