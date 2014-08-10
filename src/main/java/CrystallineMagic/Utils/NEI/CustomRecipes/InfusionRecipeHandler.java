package CrystallineMagic.Utils.NEI.CustomRecipes;

import CrystallineApi.Recipes.InfusionRecipe;
import CrystallineApi.Recipes.RecipeHandler;
import CrystallineMagic.Gui.GuiMagicalInfuser;
import CrystallineMagic.Utils.Ref;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class InfusionRecipeHandler extends TemplateRecipeHandler {

int OffsetX = 5;
int OffsetY = 0;

    public class InfusionRes extends CachedRecipe
    {

        public InfusionRes(InfusionRecipe res) {
            this(res.item, res.stacks, res.GetOutput());

        }

        PositionedStack result;
        ArrayList<PositionedStack>Stacks;

        public InfusionRes(ItemStack MainItem, ItemStack[] stacks, ItemStack Result) {
            MainItem.stackSize = 1;
            this.result = new PositionedStack(Result, 101 + OffsetX, 25 + OffsetY);

            Stacks = new ArrayList<PositionedStack>();

            for(int i = 0; i < stacks.length+1; i++){

                if(i > stacks.length)
                    break;

                if(i == 0)
                    Stacks.add(new PositionedStack(MainItem, 25 + OffsetX, 25 + OffsetY));

                else if(i == 1)
                        Stacks.add(new PositionedStack(stacks[0], 6 + OffsetX, 6 + OffsetY));

                else if(i == 2)
                        Stacks.add(new PositionedStack(stacks[1], 25 + OffsetX, 4 + OffsetY));

                else if(i == 3)
                        Stacks.add(new PositionedStack(stacks[2], 44 + OffsetX, 6 + OffsetY));

                else if(i == 4)
                        Stacks.add(new PositionedStack(stacks[3], 4 + OffsetX, 25 + OffsetY));

                else if(i == 5)
                        Stacks.add(new PositionedStack(stacks[4], 46 + OffsetX, 25 + OffsetY));

                else if(i == 6)
                        Stacks.add(new PositionedStack(stacks[5], 6 + OffsetX, 44 + OffsetY));

                else if(i == 7)
                        Stacks.add(new PositionedStack(stacks[6], 25 + OffsetX, 46 + OffsetY));

                else if(i == 8)
                        Stacks.add(new PositionedStack(stacks[7], 44 + OffsetX, 44 + OffsetY));



            }
        }

        public List<PositionedStack> getIngredients() {

            return getCycledIngredients(cycleticks / 20, Stacks);
        }

        public PositionedStack getResult() {
            return result;
        }

        public void computeVisuals() {
            for (PositionedStack p : Stacks)
                p.generatePermutations();

        }

    }



    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(67 + OffsetX, 29 + OffsetY, 26, 8), "MagicalInfuser"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiMagicalInfuser.class;
    }


    @Override
    public TemplateRecipeHandler newInstance() {
        return super.newInstance();
    }


    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(67 + OffsetX, 29 + OffsetY, 102, 79, 24, 6, 100, 0);

        if(GetRecipeFromInt(recipe) != null){
            InfusionRecipe res = GetRecipeFromInt(recipe);

            Minecraft.getMinecraft().fontRenderer.drawString("Energy: " + EnumChatFormatting.AQUA + (int)res.EnergyAmount, 65 + OffsetX, 53 + OffsetY, 0xFFFFFF);

        }

    }


    public InfusionRecipe GetRecipeFromInt(int i){
        return RecipeHandler.GetInfusionRecipeFromOutput(getResultStack(i).item);
    }

    @Override
    public String getOverlayIdentifier() {
        return "MagicalInfuser";
    }

    @Override
    public String getGuiTexture() {
        return new ResourceLocation(Ref.ModId, "textures/gui/NEI/MagicalInfuserGui.png").toString();
    }

    @Override
    public String getRecipeName() {
        return StatCollector.translateToLocal("tile.magicalinfuser.name");
    }


    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("MagicalInfuser") && getClass() == InfusionRecipeHandler.class) {
            for (Object r : RecipeHandler.getCraftingRecipes()) {
                InfusionRes recipe = null;
                if (r instanceof InfusionRecipe)
                    recipe = new InfusionRes((InfusionRecipe) r);


                if (recipe == null)
                    continue;

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (Object r : RecipeHandler.getCraftingRecipes()) {
            if(r instanceof InfusionRecipe)
            if (NEIServerUtils.areStacksSameTypeCrafting(((InfusionRecipe) r).GetOutput(), result)) {
                InfusionRes recipe = null;

                if (r instanceof InfusionRecipe)
                    recipe = new InfusionRes((InfusionRecipe) r);


                if (recipe == null)
                    continue;

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (Object r : RecipeHandler.getCraftingRecipes()) {
            InfusionRes recipe = null;

            if (r instanceof InfusionRecipe)
                recipe = new InfusionRes((InfusionRecipe) r);


            if (recipe == null || !recipe.contains(recipe.getIngredients(), ingredient.getItem()))
                continue;

            recipe.computeVisuals();
            if (recipe.contains(recipe.getIngredients(), ingredient)) {
                recipe.setIngredientPermutation(recipe.getIngredients(), ingredient);
                arecipes.add(recipe);
            }
        }
    }


}
