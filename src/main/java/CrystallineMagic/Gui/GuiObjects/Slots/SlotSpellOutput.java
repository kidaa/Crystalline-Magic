package CrystallineMagic.Gui.GuiObjects.Slots;

import CrystallineMagic.Items.ModItemSoulOrb;
import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
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

    public boolean canTakeStack(EntityPlayer pl)
    {
        if(tile.getStackInSlot(2) == null || tile.getStackInSlot(21) == null)
            return false;

        ItemStack stack = tile.getStackInSlot(21);
        ModItemSoulOrb orb = (ModItemSoulOrb) tile.getStackInSlot(2).getItem();

        EntityPlayer player = null;

        if (orb.GetOwner(tile.getStackInSlot(2)) != null) {
            player = orb.GetOwner(tile.getStackInSlot(2));
        }





        return player.capabilities.isCreativeMode || MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic() && MagicInfoStorage.get(player).GetPlayerMaxEnergy() >= MagicUtils.GetSpellCost(stack);
    }
}
