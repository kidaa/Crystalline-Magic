package CrystallineMagic.Items;

import CrystallineApi.Magic.IEnergyStorageItem;
import CrystallineMagic.Main.CrystMagic;
import MiscUtils.Item.ModItemWithDamage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ModItemChargedCrystal extends ModItemWithDamage implements IEnergyStorageItem {


    public ModItemChargedCrystal() {
        super(50);
    }

    public boolean hasEffect(ItemStack stack, int pas)
    {
        return stack.getItemDamage() <= 25;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {

        this.itemIcon = par1IconRegister.registerIcon(CrystMagic.ModId.toLowerCase() + ":ChargedCrystal");
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
    {

        double DamageLeft = itemstack.getMaxDamage() - itemstack.getItemDamage();
        double Prosentage = (DamageLeft/ 50) * 100;

        if(DamageLeft == 1)
            Prosentage = 0;

        list.add(StatCollector.translateToLocal("items.desc.invisCharged")+ " " + (int)Prosentage + "%");
    }


    @Override
    public double GetEnergyStored(ItemStack stack) {
        return stack.getMaxDamage() - stack.getItemDamage();
    }

    @Override
    public double GetMaxEnergy(ItemStack stack) {
        return stack.getMaxDamage();
    }

    @Override
    public void SetEnergy(ItemStack stack, double i) {
        stack.setItemDamage((int)i);
    }

    @Override
    public void AddEnergy(ItemStack stack, double i) {
        stack.setItemDamage(stack.getItemDamage() - (int)i);
    }

    @Override
    public void RemoveEnergy(ItemStack stack, double i) {
        stack.setItemDamage(stack.getItemDamage() + (int)i);
    }
}
