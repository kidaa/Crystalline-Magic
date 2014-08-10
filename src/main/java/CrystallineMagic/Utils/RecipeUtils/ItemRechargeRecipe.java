package CrystallineMagic.Utils.RecipeUtils;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class ItemRechargeRecipe implements IRecipe
{
    ItemStack repairingItem;
    ItemStack useItem;

    int Amount, Max;

    public ItemRechargeRecipe(int RepairAmount, int MinDamage, ItemStack repairingItem, ItemStack useItem)
    {
        this.repairingItem = repairingItem.copy();
        this.repairingItem.setItemDamage(this.repairingItem.getMaxDamage());
        this.useItem = useItem.copy();

        Amount=RepairAmount;
        Max=MinDamage;
    }

    @Override
    public boolean matches (InventoryCrafting inventorycrafting, World world)
    {
        int invLength = inventorycrafting.getSizeInventory();
        boolean foundrepairingItem = false;
        int countuseItem = 0;
        ItemStack tmpStack;
        for (int i = 0; i < invLength; ++i)
        {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack instanceof ItemStack)
            {
                if (tmpStack.getItem() == repairingItem.getItem())
                {
                    if (foundrepairingItem)
                    {
                        return false;
                    }
                    foundrepairingItem = true;
                }
                else if (tmpStack.getItem() == useItem.getItem() && tmpStack.getItemDamage() == useItem.getItemDamage())
                {
                    countuseItem += Amount;
                }
                else
                {
                    return false;
                }
            }
            if (countuseItem > 0 && foundrepairingItem)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStack getCraftingResult (InventoryCrafting inventorycrafting)
    {
        int invLength = inventorycrafting.getSizeInventory();
        ItemStack newrepairingItem = null;
        int countuseItem = 0;
        ItemStack tmpStack;
        for (int i = 0; i < invLength; ++i)
        {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack instanceof ItemStack)
            {
                if (tmpStack.getItem() == repairingItem.getItem())
                {
                    newrepairingItem = tmpStack.copy();
                }
                else if (tmpStack.getItem() == useItem.getItem() && tmpStack.getItemDamage() == useItem.getItemDamage())
                {
                    countuseItem += Amount;
                }
            }
        }
        if (countuseItem > 0 && newrepairingItem != null)
        {
            if(newrepairingItem.getItemDamage() - countuseItem < Max)
                newrepairingItem.setItemDamage(Max);
            else

            newrepairingItem.setItemDamage(newrepairingItem.getItemDamage() - countuseItem);
        }

        return newrepairingItem;
    }

    @Override
    public int getRecipeSize ()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput ()
    {
        return repairingItem.copy();
    }

}