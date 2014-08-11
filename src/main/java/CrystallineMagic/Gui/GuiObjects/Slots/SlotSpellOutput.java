package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SlotSpellOutput extends SlotSpellCreation {
    public SlotSpellOutput(TileEntitySpellCreationTable p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public void onSlotChanged()
    {
        this.inventory.markDirty();
    }

    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        tile.OnTakeOutput();
    }
}
