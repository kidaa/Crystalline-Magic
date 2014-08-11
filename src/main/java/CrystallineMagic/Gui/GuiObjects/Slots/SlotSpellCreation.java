package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import net.minecraft.inventory.Slot;

public class SlotSpellCreation extends Slot {

    TileEntitySpellCreationTable tile;

    public SlotSpellCreation(TileEntitySpellCreationTable p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this. tile = p_i1824_1_;
    }

    public void onSlotChanged()
    {
        this.inventory.markDirty();
        tile.OnSlotChanged();

    }
}
