package CrystallineMagic.Container;

import CrystallineMagic.TileEntities.TileEntityMagicalEnergyRecharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMagicalCharger extends Container {

    private TileEntityMagicalEnergyRecharger tile;

    int LastPower, LastMaxPower, LastEnergy;

    public ContainerMagicalCharger(InventoryPlayer InvPlayer, TileEntityMagicalEnergyRecharger tile)
    {
        this.tile = tile;


        for(int x = 0; x < 9; x++){

            addSlotToContainer(new Slot(InvPlayer, x, 8 + 18 * x, 142));
        }

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){

                addSlotToContainer(new Slot(InvPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
            }
        }

        addSlotToContainer(new Slot(tile, 0, 80, 30));





    }


    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 1)
            {
                if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 1, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.tile.closeInventory();
    }

    public IInventory getLowerChestInventory()
    {
        return this.tile;
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, (int)this.tile.GetStoredEnergy());
        par1ICrafting.sendProgressBarUpdate(this, 1, (int)this.tile.CurrentItemPower);
        par1ICrafting.sendProgressBarUpdate(this, 2, (int)this.tile.CurrentItemMaxPower);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.LastEnergy != this.tile.GetStoredEnergy())
            {
                icrafting.sendProgressBarUpdate(this, 0, (int)this.tile.GetStoredEnergy());
            }

            if (this.LastPower != this.tile.CurrentItemPower)
            {
                icrafting.sendProgressBarUpdate(this, 1, (int)this.tile.CurrentItemPower);
            }

            if (this.LastMaxPower != this.tile.CurrentItemMaxPower)
            {
                icrafting.sendProgressBarUpdate(this, 2, (int)this.tile.CurrentItemMaxPower);
            }


        }

        this.LastEnergy = (int)this.tile.GetStoredEnergy();
        this.LastPower = (int)this.tile.CurrentItemPower;
        this.LastMaxPower = (int)this.tile.CurrentItemMaxPower;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            tile.Power = par2;
        }

        if (par1 == 1)
        {
            this.tile.CurrentItemPower = par2;
        }


        if (par1 == 2)
        {
            this.tile.CurrentItemMaxPower = par2;
        }


    }

}
