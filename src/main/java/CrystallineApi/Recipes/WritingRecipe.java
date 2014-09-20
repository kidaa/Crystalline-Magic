package CrystallineApi.Recipes;

import CrystallineApi.Elements.ElementBase;
import net.minecraft.item.ItemStack;

public class WritingRecipe {

    public ElementBase[] Elements;
    public ItemStack InputItem, Output;

    public WritingRecipe(ElementBase[] Input, ItemStack InputItem, ItemStack Output){
        this.Elements = Input;
        this.Output = Output;
        this.InputItem = InputItem;
    }

}
