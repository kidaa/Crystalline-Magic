package CrystallineMagic.TileEntities;

import CrystallineApi.Magic.IMagicReceiver;
import CrystallineApi.Recipes.InfusionRecipe;
import CrystallineApi.Recipes.RecipeHandler;
import CrystallineMagic.Utils.MagicUtils;
import MiscUtils.TileEntity.TileEntityInvBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

public class TileEntityMagicalInfuser extends TileEntityInvBase implements IMagicReceiver {

    public static int MainSlot = 0, OutputSlot = 9;

    double Power = 0;
    public static double MaxPower = 1000;
    boolean HasWork = false;
    InfusionRecipe CurrentRes = null;

    int Progress = 0;
    int FinishProg = 50;

    public TileEntityMagicalInfuser() {
        super(10, "MagicalInfuser", 64);
    }


    public void updateEntity(){

        if(this.getStackInSlot(MainSlot) != null){
            if(GetStoredEnergy() > 0.0){
                ItemStack MainItem = this.getStackInSlot(MainSlot).copy();
                MainItem.stackSize = 1;

                ArrayList<ItemStack> ls = new ArrayList<ItemStack>();

                for(int i = 1; i < this.getSizeInventory() - 1; i++){
                    if(this.getStackInSlot(i) != null){
                        ls.add(this.getStackInSlot(i));
                    }
                }

                if(RecipeHandler.GetInfusionRecipe(ls, MainItem) != null){
                    InfusionRecipe infu = RecipeHandler.GetInfusionRecipe(ls, MainItem);
                    CurrentRes = infu;

                }else{
                    HasWork = false;
                    CurrentRes = null;
                    Progress = 0;
                }

            }
        }else{
            HasWork = false;
            CurrentRes = null;
            Progress = 0;
        }


        if(CurrentRes != null){
            FinishProg = 50 + ((int)CurrentRes.EnergyAmount * 2);
        }

        if(CurrentRes != null){
            if(!HasWork) {
                    if (this.getStackInSlot(OutputSlot) == null) {
                        HasWork = true;

                    }else if(this.getStackInSlot(OutputSlot) != null
                            && this.getStackInSlot(OutputSlot).getItem() == CurrentRes.GetOutput().getItem()
                            && this.getStackInSlot(OutputSlot).getItemDamage() == CurrentRes.GetOutput().getItemDamage()
                            && this.getStackInSlot(OutputSlot).stackSize + CurrentRes.GetOutput().stackSize <= this.getInventoryStackLimit()
                            && this.getStackInSlot(OutputSlot).isStackable()){
                        HasWork = true;

                    }



            }else{

                if(Progress >= FinishProg){
                    HasWork = false;
                    Progress = 0;

                    for(int i = 0; i < this.getSizeInventory()-1; i++){
                        if(this.getStackInSlot(i) != null){
                            this.decrStackSize(i, 1);
                        }
                    }

                    if(this.getStackInSlot(OutputSlot) == null){
                        this.setInventorySlotContents(OutputSlot, CurrentRes.GetOutput());

                    }else if(this.getStackInSlot(OutputSlot) != null
                            && this.getStackInSlot(OutputSlot).getItem() == CurrentRes.GetOutput().getItem()
                            && this.getStackInSlot(OutputSlot).getItemDamage() == CurrentRes.GetOutput().getItemDamage()
                            && this.getStackInSlot(OutputSlot).stackSize + CurrentRes.GetOutput().stackSize <= this.getInventoryStackLimit()
                            && this.getStackInSlot(OutputSlot).isStackable()){

                        ItemStack stack = this.getStackInSlot(OutputSlot).copy();
                        stack.stackSize += CurrentRes.GetOutput().stackSize;
                        this.setInventorySlotContents(OutputSlot, stack);
                    }



                    CurrentRes = null;


                }else{
                    if(GetStoredEnergy() >= (CurrentRes.EnergyAmount / FinishProg)) {
                        Progress += 1;
                        SetPower(GetStoredEnergy() - (CurrentRes.EnergyAmount / FinishProg));
                    }

                }
            }
        }else if(CurrentRes == null && Progress > 0)
            Progress = 0;


    }

    public void SetPower(double i){
         Power = i;
    }

    public int GetProgress(){
        return Progress;
    }

    public void SetProgress(int i){
        Progress = i;
    }


    public int GetMaxProg(){
        return FinishProg;
    }

    public void SetMaxProg(int i){
        FinishProg = i;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Power = nbtTagCompound.getDouble("Power");

        Progress = nbtTagCompound.getInteger("Prog");

        HasWork = nbtTagCompound.getBoolean("Work");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setDouble("Power", Power);

        nbtTagCompound.setInteger("Prog", Progress);

        nbtTagCompound.setBoolean("Work", HasWork);

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
        return Power + i <= MaxPower+1;
    }

    @Override
    public void ReceiveEnergy(double i) {
        Power += i;
        MagicUtils.ReceiveEnergy(this);
    }

}
