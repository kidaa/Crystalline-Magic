package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.Items.ModItemSpellModifier;
import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import net.minecraft.item.ItemStack;

public class SlotSpellModifier extends SlotSpellCreation {
    public SlotSpellModifier(TileEntitySpellCreationTable p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ModItemSpellModifier && stack.getTagCompound() != null;
    }
}
