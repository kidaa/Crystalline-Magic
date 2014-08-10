package CrystallineApi.Recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeHandler {

    private static ArrayList craftingRecipes = new ArrayList();

    public static List getCraftingRecipes()
    {
        return craftingRecipes;
    }


    private static ItemStack GetObject(Object ob){

        if(ob instanceof Block)
            return new ItemStack((Block)ob);

        if(ob instanceof Item)
            return new ItemStack((Item)ob);

        if(ob instanceof ItemStack)
            return (ItemStack)ob;

        return null;
    }


    private static ItemStack[] GetMultiObject(Object... ob){
        if(ob instanceof Block[]) {
            Block[] bl = (Block[])ob;
           ItemStack[] stacks = new ItemStack[bl.length];

            for(int i  = 0; i < stacks.length; i++){
                stacks[i] = new ItemStack(bl[i]);
            }

            return stacks;
        }

        if(ob instanceof Item[]) {
            Item[] bl = (Item[])ob;
            ItemStack[] stacks = new ItemStack[bl.length];

            for(int i  = 0; i < stacks.length; i++){
                stacks[i] = new ItemStack(bl[i]);
            }

            return stacks;
        }



        if(ob instanceof ItemStack[])
            return (ItemStack[])ob;

        if(ob instanceof Object[]){
            Object[] obj = (Object[])ob;
            ItemStack[] stacks = new ItemStack[obj.length];

            for(int i = 0; i < stacks.length; i++){
                stacks[i] = (ItemStack)GetObject(obj[i]);
            }

            return stacks;

        }


        return null;
    }

    private static boolean AreStacksEqual(ItemStack stack1, ItemStack stack2){
        return stack1 == null && stack2 == null ||
                stack1 != null && stack2 == null ? false :
                stack1 == null && stack2 != null ? false :

                stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage()

                && stack1.getTagCompound() == null && stack2.getTagCompound() == null ||
                 stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage() &&
                stack1.getTagCompound() != null && stack2.getTagCompound() != null &&
                stack1.getTagCompound().equals(stack2.getTagCompound());
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
