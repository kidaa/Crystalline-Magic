package CrystallineMagic.Utils.RecipeUtils;

import CrystallineApi.Recipes.WritingRecipeHandler;
import CrystallineMagic.Items.WritingRecipePage;
import CrystallineMagic.Main.ModItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WritingRecipePageCreation implements IRecipe
{
    ItemStack CopyItem;


    public WritingRecipePageCreation(ItemStack sp)
    {
        CopyItem = sp.copy();
    }

    @Override
    public boolean matches (InventoryCrafting inventorycrafting, World world) {
        int invLength = inventorycrafting.getSizeInventory();
        boolean foundPage = false;
        boolean foundPart = false;


        ItemStack tmpStack;
        for (int i = 0; i < invLength; ++i) {
            tmpStack = inventorycrafting.getStackInSlot(i);
            if (tmpStack != null && tmpStack.getItem() != null) {


                if (tmpStack.getItem() instanceof WritingRecipePage) {

                    if (foundPage)
                        return false;

                    CopyItem = tmpStack;

                    foundPage = true;

                } else if (WritingRecipeHandler.Items().contains(tmpStack.getItem())) {

                    if(foundPart)
                        return false;

                    foundPart = true;

                }  else {


                    if(tmpStack != null && tmpStack.getItem() != null)
                    return false;
                }
            }

            if (foundPage && foundPart) {
                return true;
            }

        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult (InventoryCrafting inventorycrafting)
    {

        int invLength = inventorycrafting.getSizeInventory();
        ItemStack out = new ItemStack(ModItems.WritingRecipePage);

        ItemStack tmpStack;
        ItemStack tg = null;


        for (int i = 0; i < invLength; ++i)
        {
            tmpStack = inventorycrafting.getStackInSlot(i);

            if (tmpStack instanceof ItemStack)
            {
                if (WritingRecipeHandler.Items().contains(tmpStack.getItem()))
                {
                    tg = tmpStack;
                }
            }
        }

        if(tg != null) {
            for(int i = 0; i < WritingRecipeHandler.recipes.size(); i++){
                ItemStack stack = WritingRecipeHandler.recipes.get(i).Output;

                if(WritingRecipeHandler.AreStacksEqual(tg, stack)){
                    out.setTagCompound(new NBTTagCompound());
                    out.getTagCompound().setInteger("ResNum", i);

                    CopyItem = out.copy();

                    return out;
                }

            }

        }




        return null;
    }

    @Override
    public int getRecipeSize ()
    {
        return 4;
    }

    @Override
    public ItemStack getRecipeOutput ()
    {
        return CopyItem.copy();
    }

}