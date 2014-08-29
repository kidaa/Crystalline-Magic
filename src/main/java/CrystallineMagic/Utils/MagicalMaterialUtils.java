package CrystallineMagic.Utils;

import MiscUtils.Utils.StackUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;

public class MagicalMaterialUtils {



    public static ArrayList list = new ArrayList();


    public static void RegisterManualValues(){

        //Vanilla values
        RegisterManualValue(Blocks.cobblestone, 2);
        RegisterManualValue(Blocks.mossy_cobblestone, 6);
        RegisterManualValue(Blocks.stone, 3);
        RegisterManualValue(Blocks.sand, 4);
        RegisterManualValue(Blocks.glass, 4);
        RegisterManualValue(Blocks.log, 5);
        RegisterManualValue(Blocks.melon_block, 72);
        RegisterManualValue(Blocks.pumpkin, 72);
        RegisterManualValue(Blocks.netherrack, 32);
        RegisterManualValue(Blocks.hardened_clay, 64);
        RegisterManualValue(Blocks.red_flower, 16);
        RegisterManualValue(Blocks.yellow_flower, 16);
        RegisterManualValue(Blocks.sapling, 27);
        RegisterManualValue(Blocks.leaves, 20);
        RegisterManualValue(Blocks.cactus, 17);
        RegisterManualValue(Blocks.soul_sand, 22);
        RegisterManualValue(Blocks.end_portal, 25);
        RegisterManualValue(Blocks.obsidian, 28);

        RegisterManualValue(Items.iron_ingot, 8);
        RegisterManualValue(Items.gold_ingot, 32);
        RegisterManualValue(Items.diamond, 128);
        RegisterManualValue(Items.emerald, 254);
        RegisterManualValue(Items.wheat, 25);
        RegisterManualValue(Items.wheat_seeds, 15);
        RegisterManualValue(Items.melon_seeds, 18);
        RegisterManualValue(Items.pumpkin_seeds, 18);
        RegisterManualValue(Items.melon, 8);
        RegisterManualValue(Items.coal, 14);
        RegisterManualValue(Items.redstone, 24);
        RegisterManualValue(Items.glowstone_dust, 38);
        RegisterManualValue(Items.gunpowder, 36);
        RegisterManualValue(Items.leather, 22);
        RegisterManualValue(Items.bone, 19);
        RegisterManualValue(Items.nether_star, 1028);
        RegisterManualValue(Items.netherbrick, 12);
        RegisterManualValue(Items.flint, 9);
        RegisterManualValue(Items.blaze_rod, 45);
        RegisterManualValue(Items.clay_ball, 16);
        RegisterManualValue(Items.string, 23);
        RegisterManualValue(Items.ender_pearl, 36);
        RegisterManualValue(Items.quartz, 48);
        RegisterManualValue(Items.nether_wart, 22);
        RegisterManualValue(Items.reeds, 14);
        RegisterManualValue(Items.nether_wart, 22);
        RegisterManualValue(Items.feather, 18);
        RegisterManualValue(Items.slime_ball, 28);
        RegisterManualValue(Items.porkchop, 27);
        RegisterManualValue(Items.beef, 28);
        RegisterManualValue(Items.fish, 19);
        RegisterManualValue(Items.chicken, 21);
        RegisterManualValue(Items.cooked_porkchop, 27);
        RegisterManualValue(Items.cooked_beef, 28);
        RegisterManualValue(Items.cooked_fished, 19);
        RegisterManualValue(Items.cooked_chicken, 21);
        RegisterManualValue(Items.potato, 16);
        RegisterManualValue(Items.baked_potato, 16);
        RegisterManualValue(Items.carrot, 19);
        RegisterManualValue(Items.apple, 24);
        RegisterManualValue(Items.snowball, 12);
        RegisterManualValue(Items.stick, 10);


    }


    public static void RegisterAutomaticValues(){

        for(Object r : Block.blockRegistry){
            if(r instanceof Block){
                Block bl = (Block)r;
                ItemBlock b = (ItemBlock)ItemBlock.getItemFromBlock(bl);

                if(b != null && b.getHasSubtypes())
                    for(int i = 0; i < 16; i++)
                        RegisterAutomaticValue(new ItemStack(bl, 1, i));

                else
                RegisterAutomaticValue(new ItemStack(bl));

            }

        }


        for(Object r : Item.itemRegistry){
            if(r instanceof Item){
                Item bl = (Item)r;

                if(bl.getHasSubtypes())
                    for(int i = 0; i < 16; i++)
                        RegisterAutomaticValue(new ItemStack(bl, 1, i));

                else
                    RegisterAutomaticValue(new ItemStack(bl));

            }

        }

    }


    public static boolean ContainesStack(ItemStack stack){
        return GetValue(stack) != null;
    }

    public static void RegisterManualValue(Object r, double value){
        ItemStack bl = StackUtils.GetObject(r);

        if(bl.getItem() != null) {
                Item b = bl.getItem();

                if (b != null && b.getHasSubtypes()) {
                    for (int i = 0; i < 16; i++)
                        RegisterValue(new ItemStack(bl.getItem(), 1, i), value);
                }else{

                    RegisterValue(bl, value);
                }


            }



    }

    public static void RegisterAutomaticValue(ItemStack stack){
        double val = 0;

        ArrayList<ItemStack> RecipeItems = GetResItems(stack);

        for(ItemStack sta : RecipeItems){
            if(ContainesStack(sta)){
                MaterialValue valu = GetValue(sta);
                if(valu != null)
                    val += valu.value;

            }
        }

        RegisterValue(stack, val / GetOutputNumber(stack));

    }

    public static boolean HasRecipe(ItemStack stack){
        ArrayList<ItemStack> rs = GetRecipeItems(stack);
        return rs != null && rs.size() > 0;
    }

    public static ArrayList<ItemStack> GetResItems(Object stack){
        return GetTotalRecipeItems(GetRecipeItems(stack));
    }

    public static ArrayList<ItemStack> GetTotalRecipeItems(ArrayList<ItemStack> stacks){
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

                ArrayList<ItemStack> Total = stacks;
                ArrayList<ItemStack> e = new ArrayList<ItemStack>();


        int x = 0;


            Start:
            for (ItemStack s : Total) {
                x += 1;


                if (s != null) {
                    ItemStack st = s.copy();

                    if (!st.getHasSubtypes())
                        st.setItemDamage(0);

                    if (!HasRecipe(st)) {
                        e.add(st);
                        continue;
                    }


                    if (!ContainesStack(st)) {
                        ArrayList<ItemStack> RecipeItems = GetRecipeItems(st);
                        for (ItemStack sta : RecipeItems) {
                            if (sta != null && !StackUtils.AreStacksEqual(sta, st)) {
                                if (sta.getItem() instanceof ItemBlock) {

                                    Block bl = Block.getBlockFromItem(sta.getItem());
                                    if(!(bl instanceof BlockCompressed)) {
                                        e.add(sta);
                                    }else{
                                        e.add(st);
                                    }

                                }else
                                e.add(sta);
                            }
                        }
                    } else {
                        e.add(st);
                    }


                    if (x >= Total.size()) {
                        Total = e;
                        break Start;
                    }


                }
            }



        for(ItemStack sta : e) {
            if(sta != null)
            list.add(sta);
        }

        return list;
    }

    public static void RegisterValue(ItemStack stack, double value) {
        if (!ContainesStack(stack)) {

            MaterialValue val = new MaterialValue(stack, value);

            list.add(val);

        }
    }

    public static MaterialValue GetValue(ItemStack stack){
        for(Object r : list){
            if(r instanceof MaterialValue){
                ItemStack sta = stack.copy();
                sta.stackSize = 1;

                MaterialValue mat = (MaterialValue)r;
                if(StackUtils.AreStacksEqual(mat.stack, sta)){
                    return mat;


                }

            }

        }

        return null;
    }

    public static double GetEnergyValue(ItemStack stack){
        double i = 0;

        if(GetValue(stack) != null){
            MaterialValue val = GetValue(stack);
            i = val.value;
        }

        return i;
    }

    public static int GetOutputNumber(ItemStack stack){
        for(Object r : CraftingManager.getInstance().getRecipeList()) {
            if (r instanceof IRecipe) {
                IRecipe rep = (IRecipe) r;

                if (StackUtils.AreStacksEqual(rep.getRecipeOutput(), stack)) {
                    int i = rep.getRecipeOutput().stackSize;

                    return i;
                }
            }

        }

        return 1;
    }

    public static ArrayList<ItemStack> GetRecipeItems(Object r)
    {

        return GetRecipeItems(StackUtils.GetObject(r));
    }

    public static ArrayList<ItemStack> GetRecipeItems(ItemStack st)
    {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        if(st != null){
        ItemStack stack = st.copy();
                if(stack != null && stack.getItem() != null)
                        if(!stack.getHasSubtypes())
                            stack.setItemDamage(0);

        for(Object r : CraftingManager.getInstance().getRecipeList()) {
            if (r instanceof IRecipe) {
                IRecipe rep = (IRecipe) r;

                if (StackUtils.AreStacksEqual(rep.getRecipeOutput(), st)) {

                    if (rep instanceof ShapedRecipes) {
                        ShapedRecipes res = (ShapedRecipes) rep;
                        if (StackUtils.AreStacksEqual(res.getRecipeOutput(), stack)) {
                            for (ItemStack sta : res.recipeItems)
                                list.add(sta);


                        }
                    } else if (rep instanceof ShapelessRecipes) {
                        ShapelessRecipes res = (ShapelessRecipes) rep;
                        if (StackUtils.AreStacksEqual(res.getRecipeOutput(), stack)) {
                            for (Object h : res.recipeItems) {
                                list.add(StackUtils.GetObject(h));
                            }
                        }

                    } else if (rep instanceof ShapedOreRecipe) {
                        ShapedOreRecipe res = (ShapedOreRecipe) rep;
                        if (StackUtils.AreStacksEqual(res.getRecipeOutput(), stack)) {
                            for (Object h : res.getInput()) {
                                list.add(StackUtils.GetObject(h));
                            }
                        }
                    } else if (rep instanceof ShapelessOreRecipe) {
                        ShapelessOreRecipe res = (ShapelessOreRecipe) rep;
                        if (StackUtils.AreStacksEqual(res.getRecipeOutput(), stack)) {
                            for (Object h : res.getInput()) {
                                list.add(StackUtils.GetObject(h));
                            }
                        }
                    }

                }
            }

        }


        }
        for(ItemStack stack : list){
            if(stack != null && stack.getItem() != null)
            if(stack.getItemDamage() > 0)
            if(!stack.getHasSubtypes())
                stack.setItemDamage(0);

        }


        return list;
    }



}
