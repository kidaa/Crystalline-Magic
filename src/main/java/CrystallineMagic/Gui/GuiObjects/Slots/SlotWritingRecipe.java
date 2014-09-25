package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.Items.WritingRecipePage;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWritingRecipe  extends Slot {

    public SlotWritingRecipe(IInventory inv,int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(inv, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public boolean isItemValid(ItemStack stack)
    {


        return stack != null && stack.getItem() instanceof WritingRecipePage;
    }

}
