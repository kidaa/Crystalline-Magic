package CrystallineMagic.Utils.RecipeUtils;

import CrystallineMagic.Items.ModItemSpell;
import CrystallineMagic.Items.ModItemSpellComponent;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellComponent;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpellCreationRecipe implements IRecipe
{
    ItemStack SpellItem;


    public SpellCreationRecipe(ItemStack sp)
    {
        SpellItem = sp.copy();
    }

    @Override
    public boolean matches (InventoryCrafting inventorycrafting, World world) {
        int invLength = inventorycrafting.getSizeInventory();
        boolean foundSpell = false;
        boolean foundComp = false;

        ItemStack tmpStack;
        for (int i = 0; i < invLength; ++i) {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack != null && tmpStack.getItem() != null) {


                if (tmpStack.getItem() instanceof ModItemSpell) {

                    if (foundSpell)
                        return false;

                    SpellItem = tmpStack;

                    foundSpell = true;

                } else if (tmpStack.getItem() instanceof ModItemSpellComponent && tmpStack.getTagCompound() != null) {
                    foundComp = true;


                } else {


                    if(tmpStack != null && tmpStack.getItem() != null)
                    return false;
                }
            }

            if (foundComp && foundSpell) {
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
                if (tmpStack.getItem() == SpellItem.getItem() && tmpStack.getTagCompound() == null)
                {
                    tg = tmpStack;
                }

                else if (tmpStack.getItem() == ModItems.SpellComponent && tmpStack.getTagCompound() != null)
                {
                    list.add(MagicUtils.GetCompFromSpellComp(tmpStack));

                }
            }
        }

        newSpell = tg.copy();

        SpellComponent[] Comps = new SpellComponent[list.size()];

        if(Comps != null && newSpell != null && newSpell.getItem() != null && Comps.length > 0) {
            for (int i = 0; i < list.size(); i++)
                Comps[i] = list.get(i);

            MagicUtils.SetSpellComponents(newSpell, Comps);
            SpellItem = newSpell.copy();
            return newSpell;


        }

        return SpellItem;
    }

    @Override
    public int getRecipeSize ()
    {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput ()
    {
        return SpellItem.copy();
    }

}