package CrystallineMagic.Items;

import CrystallineApi.Recipes.WritingRecipe;
import CrystallineApi.Recipes.WritingRecipeHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class WritingRecipePage extends Item {



    public WritingRecipe GetStoredRecipe(ItemStack stack){


        if(stack.getTagCompound() != null){

            int h = stack.getTagCompound().getInteger("ResNum");

            if(WritingRecipeHandler.recipes.get(h) != null){
                WritingRecipe res = (WritingRecipe)WritingRecipeHandler.recipes.get(h);

               return res;

            }


        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

        if(stack.getTagCompound() != null){


            int h = stack.getTagCompound().getInteger("ResNum");

            if(WritingRecipeHandler.recipes.get(h) != null){
                WritingRecipe res = (WritingRecipe)WritingRecipeHandler.recipes.get(h);

                list.add(StatCollector.translateToLocal("items.desc.resPage.recipe").replace("$res", EnumChatFormatting.WHITE + res.Output.getDisplayName() + EnumChatFormatting.RESET));

            }

        }

    }
}
