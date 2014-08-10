package CrystallineApi.Magic;

import net.minecraft.item.ItemStack;

public interface IEnergyStorageItem {

    public double GetEnergyStored(ItemStack stack);
    public double GetMaxEnergy(ItemStack stack);

    public void SetEnergy(ItemStack stack, double i);
    public void AddEnergy(ItemStack stack, double i);
    public void RemoveEnergy(ItemStack stack, double i);
}
