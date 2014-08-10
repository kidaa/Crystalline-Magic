package CrystallineApi.Recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class InfusionRecipe {

    public ItemStack[] stacks;
    public ItemStack item;
    public Object output;
    public double EnergyAmount;

    public InfusionRecipe(Object output, ItemStack Item, ItemStack[] items, double Energy){
        this.stacks = items;
        this.output = output;
        this.EnergyAmount = Energy;
        this.item = Item;

    }


    public ItemStack GetOutput(){
        if(output instanceof Block)
            return new ItemStack((Block)output);

        if(output instanceof Item)
            return new ItemStack((Item)output);

            if(output instanceof  ItemStack)
                return (ItemStack)output;


        return null;
    }

    public ArrayList GetStacks(){
        ArrayList list = new ArrayList();

        for(int i = 0; i < stacks.length; i++)
            list.add(stacks[i]);

        return list;


    }
}
