package CrystallineMagic.Utils.RecipeUtils;

import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Spells.ISpellPart;
import CrystallineApi.Spells.SpellComponent;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpellPartCopy implements IRecipe
{
    ItemStack CopyItem;


    public SpellPartCopy(ItemStack sp)
    {
        CopyItem = sp.copy();
    }

    @Override
    public boolean matches (InventoryCrafting inventorycrafting, World world) {
        int invLength = inventorycrafting.getSizeInventory();
        boolean foundCopy = false;
        boolean foundComp = false;
        boolean foundParch = false;

        ItemStack tmpStack;
        for (int i = 0; i < invLength; ++i) {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack != null && tmpStack.getItem() != null) {


                if (tmpStack.getItem() instanceof ISpellPart) {

                    if (foundCopy)
                        return false;

                    CopyItem = tmpStack;

                    foundCopy = true;

                } else if (tmpStack.getItem() == ModItems.Parchment) {

                    if(foundParch)
                        return false;

                    foundParch = true;

                } else if (tmpStack.getItem() == Items.dye && tmpStack.getItemDamage() == 0) {

                    if(foundComp)
                        return false;

                    foundComp = true;

                } else {


                    if(tmpStack != null && tmpStack.getItem() != null)
                    return false;
                }
            }

            if (foundComp && foundCopy && foundParch) {
                return true;
            }

        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult (InventoryCrafting inventorycrafting)
    {

        int invLength = inventorycrafting.getSizeInventory();
        ItemStack newSpell = null;

        ItemStack tmpStack;
        ItemStack tg = null;

        ArrayList<SpellComponent> list = new ArrayList<SpellComponent>();

        for (int i = 0; i < invLength; ++i)
        {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack instanceof ItemStack)
            {
                if (tmpStack.getItem() instanceof ISpellPart)
                {
                    tg = tmpStack;
                }
            }
        }


        newSpell = tg.copy();

        if(newSpell != null) {

            CopyItem = newSpell.copy();

            newSpell.stackSize = 2;
            return newSpell;

        }




        return CopyItem;
    }

    @Override
    public int getRecipeSize ()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput ()
    {
        return CopyItem.copy();
    }

}