package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.Items.ModItemSpell;
import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import net.minecraft.item.ItemStack;

public class SlotSpellOnly extends SlotSpellCreation{
    public SlotSpellOnly(TileEntitySpellCreationTable p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ModItemSpell && stack.getTagCompound() == null;
    }
}
