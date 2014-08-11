package CrystallineMagic.Container;

import CrystallineMagic.Gui.GuiObjects.Slots.SlotSoulOrbOnly;
import CrystallineMagic.Gui.GuiObjects.Slots.SlotSpellComponent;
import CrystallineMagic.Gui.GuiObjects.Slots.SlotSpellModifier;
import CrystallineMagic.Gui.GuiObjects.Slots.SlotSpellOnly;
import CrystallineMagic.Gui.GuiObjects.Slots.SlotSpellOutput;
import CrystallineMagic.Gui.GuiObjects.Slots.SlotSpellType;
import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpellCreation extends Container {

    private TileEntitySpellCreationTable tile;

    public ContainerSpellCreation(InventoryPlayer InvPlayer, TileEntitySpellCreationTable tile)
    {
        this.tile = tile;


        for(int x = 0; x < 9; x++){

            addSlotToContainer(new Slot(InvPlayer, x, 8 + 18 * x, 166));
        }

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){

                addSlotToContainer(new Slot(InvPlayer, x + y * 9 + 9, 8 + 18 * x, 108 + y * 18));
            }
        }


        addSlotToContainer(new SlotSpellType(tile, 0, 15, 45));

        addSlotToContainer(new SlotSpellOnly(tile, 1, 15, 73));
        addSlotToContainer(new SlotSoulOrbOnly(tile, 2, 15, 17));


        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                addSlotToContainer(new SlotSpellComponent(tile, x + y * 3 + 3, 49 + 18 * x, 27 + 18 * y));

            }

        }

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                addSlotToContainer(new SlotSpellModifier(tile, x + y * 3 + 12, 122 + 18 * x, 27 + 18 * y));

            }

        }

        addSlotToContainer(new SlotSpellOutput(tile, 21, 207, 45));


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

            if (par2 < 22)
            {
                if (!this.mergeItemStack(itemstack1, 22, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 22, false))
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
}
