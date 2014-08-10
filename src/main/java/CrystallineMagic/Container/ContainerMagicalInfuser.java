package CrystallineMagic.Container;

import CrystallineMagic.TileEntities.TileEntityMagicalInfuser;
import MiscUtils.GuiObjects.Slots.SlotOutput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMagicalInfuser extends Container {

    private TileEntityMagicalInfuser tile;

    int LastProg, LastEnergy, LastFin;

    public ContainerMagicalInfuser(InventoryPlayer InvPlayer, TileEntityMagicalInfuser tile)
    {
        this.tile = tile;


        for(int x = 0; x < 9; x++){

            addSlotToContainer(new Slot(InvPlayer, x, 8 + 18 * x, 202));
        }

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){

                addSlotToContainer(new Slot(InvPlayer, x + y * 9 + 9, 8 + 18 * x, 144 + y * 18));
            }
        }

        addSlotToContainer(new Slot(tile, 0, 69, 55));
        addSlotToContainer(new SlotOutput(tile, 9, 167, 56));

        addSlotToContainer(new Slot(tile, 1, 36, 21));
        addSlotToContainer(new Slot(tile, 2, 69, 18));
        addSlotToContainer(new Slot(tile, 3, 102, 21));

        addSlotToContainer(new Slot(tile, 4, 32, 55));
        addSlotToContainer(new Slot(tile, 5, 106, 55));

        addSlotToContainer(new Slot(tile, 6, 36, 89));
        addSlotToContainer(new Slot(tile, 7, 69, 92));
        addSlotToContainer(new Slot(tile, 8, 102, 89));




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

            if (par2 < 10)
            {
                if (!this.mergeItemStack(itemstack1, 10, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 10, false))
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
        par1ICrafting.sendProgressBarUpdate(this, 1, (int)this.tile.GetProgress());
        par1ICrafting.sendProgressBarUpdate(this, 3, (int)this.tile.GetMaxProg());
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

            if (this.LastProg != this.tile.GetProgress())
            {
                icrafting.sendProgressBarUpdate(this, 1, (int)this.tile.GetProgress());
            }

            if (this.LastFin != this.tile.GetMaxProg())
            {
                icrafting.sendProgressBarUpdate(this, 2, (int)this.tile.GetMaxProg());
            }

        }

        this.LastEnergy = (int)this.tile.GetStoredEnergy();
        this.LastProg = (int)this.tile.GetProgress();
        this.LastFin = this.tile.GetMaxProg();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.SetPower(par2);
        }

        if (par1 == 1)
        {
            this.tile.SetProgress(par2);
        }

        if (par1 == 2)
        {
            this.tile.SetMaxProg(par2);
        }


    }

}
