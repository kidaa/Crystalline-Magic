package CrystallineMagic.Gui.GuiObjects.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotOneStackSize extends Slot {
    public SlotOneStackSize(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public int getSlotStackLimit()
    {
        return 1;
    }
}
