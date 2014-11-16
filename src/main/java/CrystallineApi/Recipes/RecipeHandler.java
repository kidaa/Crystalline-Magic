package CrystallineApi.Recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeHandler {

    private static ArrayList craftingRecipes = new ArrayList();

    public static List getCraftingRecipes()
    {
        return craftingRecipes;
    }


    //From StackUtils class in MiscUtils
    public static ItemStack GetObject(Object ob){
        if(ob == null)
            return null;

        if(ob instanceof Block)
            return new ItemStack((Block)ob);

        if(ob instanceof Item)
            return new ItemStack((Item)ob);

        if(ob instanceof ItemStack)
            return (ItemStack)ob;

        if(ob instanceof List){
            if((List)ob != null && ((List)ob).size() > 0)
                if(((List)ob).get(0) instanceof ItemStack){
                    return (ItemStack)((List)ob).get(0);
                }
        }

        if(ob instanceof String){
            ArrayList<ItemStack> stacks = OreDictionary.getOres((String) ob);


            if(stacks.size() > 0)
                return OreDictionary.getOres((String)ob).get(new Random().nextInt(stacks.size()));
        }

        return null;
    }

    //From StackUtils class in MiscUtils
    public static ItemStack[] GetMultiObject(Object... ob){

        ItemStack[] stacks = new ItemStack[ob.length];

        for(int i = 0; i < stacks.length; i++){
            stacks[i] = GetObject(ob);

        }

        return stacks;
    }
    //From StackUtils class in MiscUtils
    public static boolean AreStacksEqual(ItemStack stack1, ItemStack stack2, boolean IgnoreDamageValue, boolean IgnoreNBT, boolean IgnoreStackSize){

        boolean flag1 = stack1 == null && stack2 == null || stack1 != null && stack2 != null;
        if(stack1 == null || stack2 == null)
            return flag1;


        boolean flag2 = stack1.getItem() == null && stack2.getItem() == null || stack1.getItem() == stack2.getItem();
        if(stack1.getItem() == null || stack2.getItem() == null)
            return flag1 && flag2;


        boolean flag3 = IgnoreDamageValue ? true : stack1.getItemDamage() == 0 && stack2.getItemDamage() == 0 || stack1.getItemDamage() == stack2.getItemDamage();
        boolean flag4 = IgnoreNBT ? true : stack1.getTagCompound() == null && stack2.getTagCompound() == null || stack1.getTagCompound() == stack2.getTagCompound();
        boolean flag5 = IgnoreStackSize ? true : stack1.stackSize == 0 && stack2.stackSize == 0 || stack1.stackSize == stack2.stackSize;

        return flag1 && flag2 && flag3 && flag4 && flag5;
    }
    //From StackUtils class in MiscUtils
    public static boolean AreStacksEqualIgnoreData(ItemStack stack1, ItemStack stack2){
        return AreStacksEqual(stack1, stack2, true, true, true);
    }
    //From StackUtils class in MiscUtils
    public static boolean AreStacksEqual(ItemStack stack1, ItemStack stack2){
        return AreStacksEqual(stack1, stack2, false, false, false);
    }







    public static InfusionRecipe RegisterInfusionRecipe(Object rg, ItemStack input, Object[] recipe, double Energy)
    {
        ItemStack result = GetObject(rg);

        ItemStack[] stacks = new ItemStack[recipe.length];

        for(int i = 0; i < recipe.length; i++){
            ItemStack stack = (ItemStack)GetObject(recipe[i]);

            stacks[i] = stack;
        }

        InfusionRecipe r = new InfusionRecipe(result, input, stacks, Energy);
        craftingRecipes.add(r);
        return r;
    }

    public static InfusionRecipe GetInfusionRecipeFromOutput(ItemStack Output){
        InfusionRecipe var13 = null;
        for (Object var11 : getCraftingRecipes()) {
            if (((var11 instanceof InfusionRecipe)))
            {
                if(AreStacksEqual(((InfusionRecipe) var11).GetOutput(), Output)){

                            var13 = (InfusionRecipe) var11;
                            return var13;



                }

            }
        }
        return var13;
    }

    public static InfusionRecipe GetInfusionRecipe(ArrayList<ItemStack> items, ItemStack input)
    {
        InfusionRecipe var13 = null;
        for (Object var11 : getCraftingRecipes()) {
            if (((var11 instanceof InfusionRecipe)))
            {
                if(AreStacksEqual(((InfusionRecipe) var11).item, input)){

                    if(items.size() == ((InfusionRecipe) var11).stacks.length){


                        boolean HasAllItems = false;
                        for(int i = 0; i < ((InfusionRecipe) var11).stacks.length; i++){
                            boolean t = false;

                            for(int g = 0; g < items.size(); g++){
                                if(AreStacksEqual(items.get(g), ((InfusionRecipe) var11).stacks[i])){
                                    t = true;
                                }
                            }
                            HasAllItems = t;

                            if(HasAllItems == false)
                                return null;

                        }

                        if(HasAllItems) {
                            var13 = (InfusionRecipe) var11;
                            return var13;
                        }




                    }

                }

            }
        }
        return var13;
    }




}
