package CrystallineApi.Recipes;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellType;
import CrystallineMagic.Main.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;

public class WritingRecipeHandler {

    public static ArrayList recipes = new ArrayList();



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




    public static void RegisterWriting(ItemStack InputItem, ElementBase[] Elements, SpellComponent Output){

        ItemStack stack = new ItemStack(ModItems.SpellComponent);
        stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setString("Comp", StatCollector.translateToLocal("spellpart.component." + Output.GetName().toLowerCase().replace(" ", "_") + ".name"));
        stack.getTagCompound().setString("CompId", Output.GetId());

        RegisterWriting(InputItem, Elements, stack);

    }

    public static void RegisterWriting(ItemStack InputItem, ElementBase[] Elements, SpellModifier Output){

        ItemStack stack = new ItemStack(ModItems.SpellModifier);
        stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setString("Mod", StatCollector.translateToLocal("spellpart.modifier." + Output.GetName().toLowerCase().replace(" ", "_") + ".name"));
        stack.getTagCompound().setString("ModId", Output.GetId());

        RegisterWriting(InputItem, Elements, stack);

    }

    public static void RegisterWriting(ItemStack input, ElementBase[] Elements, SpellType output){

        ItemStack stack = new ItemStack(ModItems.SpellType);
        stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setString("Type", StatCollector.translateToLocal("spellpart.type." + output.GetName().toLowerCase().replace(" ", "_") + ".name"));
        stack.getTagCompound().setString("TypeId", output.GetId());

        RegisterWriting(input, Elements, stack);

    }






    public static void RegisterWriting(ItemStack InputItem, ElementBase[] Elements, ItemStack Output){

        if(Elements.length > 8){
            System.err.println("ERROR: Registering writing recipe for: " + InputItem + " Too many elements!! Max 8!");
            return;

        }


            WritingRecipe res = new WritingRecipe(Elements, InputItem, Output);
            recipes.add(res);


    }

    public static WritingRecipe GetRecipe(ItemStack InputItem, ElementBase[] Elements){


        for(Object r : recipes){
            if(r instanceof WritingRecipe){
                WritingRecipe res = (WritingRecipe)r;

                boolean All = true;
                if(AreStacksEqual(res.InputItem, InputItem) && Elements != null && res.Elements != null && Elements.length == res.Elements.length){



                    for(int g = 0; g < res.Elements.length; g++){
                        ElementBase b = res.Elements[g];



                        boolean t = false;

                        for(int i = 0; i < Elements.length; i++){
                            ElementBase bb = Elements[i];

                            if(b != null && bb != null && b.Id.equals(bb.Id) || b == null && bb == null){
                                t = true;
                            }


                        }

                        if(!t)
                            All = false;


                    }



                    if(All)
                        return res;


                }


            }


        }

        return null;
    }



}
