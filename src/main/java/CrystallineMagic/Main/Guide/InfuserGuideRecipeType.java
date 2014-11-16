package CrystallineMagic.Main.Guide;

import CrystallineApi.Recipes.InfusionRecipe;
import CrystallineApi.Recipes.RecipeHandler;
import CrystallineMagic.Utils.Ref;
import MiscUtils.GuideBase.Gui.Utils.GuideItem;
import MiscUtils.GuideBase.Utils.GuideRecipeTypeRender;
import MiscUtils.Utils.StackUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class InfuserGuideRecipeType extends GuideRecipeTypeRender {
    @Override
    public String GetName() {
        return "tile.magical_infuser.name";
    }

    @Override
    public int GetRenderXSize() {
        return 129;
    }

    @Override
    public int GetRenderYSize() {
        return 65;
    }

    @Override
    public int GetRenderPositionX() {
        return 5;
    }

    @Override
    public int GetRenderPositionY() {
        return 11;
    }

    @Override
    public ResourceLocation GetRenderTexture() {
        return new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/NEI/MagicalInfuserGui.png");
    }

    @Override
    //Wont work for infusion recipes!
    public ItemStack[] GetRequiredItemsFor(ItemStack stack, int At) {
        return null;
    }

    @Override
    public boolean ContainsRecipeFor(ItemStack stack) {
        return GetRecipesAmountFor(stack) > 0;
    }

    @Override
    public int GetRecipesAmountFor(ItemStack stack) {
        int num = 0;

        for(Object r : RecipeHandler.getCraftingRecipes()) {
            if (r instanceof InfusionRecipe) {
                InfusionRecipe res = (InfusionRecipe) r;
                if (StackUtils.AreStacksEqualIgnoreData(stack, res.GetOutput())) {
                    num += 1;
                }
            }
        }

        return num;
    }

    @Override
    public ArrayList<GuideItem> AddItemsFor(int PosX, int PosY, ArrayList<GuideItem> ListToAddTo, ItemStack stack, int At) {
        ItemStack render = stack.copy();
        InfusionRecipe ress = null;

        int h = 0;
        for(Object r : RecipeHandler.getCraftingRecipes()) {
            if (r instanceof InfusionRecipe) {
                InfusionRecipe res = (InfusionRecipe)r;
                if (StackUtils.AreStacksEqualIgnoreData(render, res.GetOutput())) {

                    if (h == At) {
                        render.stackSize = res.GetOutput().stackSize;
                        render.setItemDamage(res.GetOutput().getItemDamage());

                        ress = res;
                    }
                    h += 1;
                }
            }
        }


        if(ress != null){
            ListToAddTo.add(new GuideItem(0, PosX + 106, PosY + 25, render));

            PosX += 5;

            for(int i = 0; i < ress.stacks.length+1; i++){
            if(i > ress.stacks.length)
                break;
            if(i == 0)
                 ListToAddTo.add(new GuideItem(0, 25 + PosX, 25 + PosY, ress.item));

            else if(i == 1)
                ListToAddTo.add(new GuideItem(0, 6 + PosX, 6 + PosY, ress.stacks[0]));

            else if(i == 2)
                ListToAddTo.add(new GuideItem(0, 25 + PosX, 4 + PosY, ress.stacks[1]));

            else if(i == 3)
                ListToAddTo.add(new GuideItem(0, 44 + PosX, 6 + PosY, ress.stacks[2]));

            else if(i == 4)
                ListToAddTo.add(new GuideItem(0, 4 + PosX, 25 + PosY, ress.stacks[3]));

            else if(i == 5)
                ListToAddTo.add(new GuideItem(0, 46 + PosX, 25 + PosY, ress.stacks[4]));

            else if(i == 6)
                ListToAddTo.add(new GuideItem(0, 6 + PosX, 44 + PosY, ress.stacks[5]));

            else if(i == 7)
                ListToAddTo.add(new GuideItem(0, 25 + PosX, 46 + PosY, ress.stacks[6]));

            else if(i == 8)
                ListToAddTo.add(new GuideItem(0, 44 + PosX, 44 + PosY, ress.stacks[7]));

        }

        }







        return ListToAddTo;
    }

    double burn = 0, BurnMax = 25;

    public void RenderExtras(GuiScreen gui, int posX, int posY, ItemStack stack, int at){

        if(burn >= BurnMax)
            burn = 0;

        else
            burn += 0.008;

        gui.drawTexturedModalRect(posX + 122, posY + 59, 102, 79, (int)burn, 6);

        int h = 0;
        for(Object r : RecipeHandler.getCraftingRecipes()) {
            if (r instanceof InfusionRecipe) {
                InfusionRecipe res = (InfusionRecipe)r;
                if (StackUtils.AreStacksEqualIgnoreData(stack, res.GetOutput())) {

                    if (h == at) {

                        GL11.glPushMatrix();
                        gui.mc.fontRenderer.drawString(EnumChatFormatting.WHITE + "Energy: " + EnumChatFormatting.AQUA + (int)res.EnergyAmount, posX + 120, posY + 85, 0x000000, false);

                        GL11.glColor4f(1F, 1F, 1F, 1F);
                        GL11.glPopMatrix();

                    }
                    h += 1;
                }
            }
        }

    }
}
