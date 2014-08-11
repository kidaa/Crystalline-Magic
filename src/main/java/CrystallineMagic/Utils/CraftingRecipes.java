package CrystallineMagic.Utils;

import CrystallineApi.Recipes.RecipeHandler;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Utils.RecipeUtils.CrystalToolUpgradeRecipe;
import CrystallineMagic.Utils.RecipeUtils.ItemRechargeRecipe;
import CrystallineMagic.Utils.RecipeUtils.SpellPartCopy;
import MiscUtils.Utils.CraftingUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingRecipes {

    public static void RegisterRecipes(){
        CraftingUtils Utils = new CraftingUtils(CrystMagic.config);


        //Infusion Recipes
        //MagicUtils.RegisterInfusionRecipe(new ItemStack(), new ItemStack(), new Object[]{}, 0);
        RecipeHandler.RegisterInfusionRecipe(ModItems.CrystalInfusedMetal, new ItemStack(Items.iron_ingot), new Object[]{ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal}, 10);
        RecipeHandler.RegisterInfusionRecipe(ModItems.CrystalInfusedGem, new ItemStack(Items.diamond), new Object[]{ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal}, 30);
        RecipeHandler.RegisterInfusionRecipe(new ItemStack(ModItems.CrystalSilk, 3), new ItemStack(Items.string), new Object[]{Items.leather, Items.leather, ModItems.BlueCrystal}, 5);
        RecipeHandler.RegisterInfusionRecipe(ModBlocks.InfusedMetalBlock, new ItemStack(Blocks.iron_block), new Object[]{ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal}, 90);
        RecipeHandler.RegisterInfusionRecipe(ModBlocks.InfusedGemBlock, new ItemStack(Blocks.diamond_block), new Object[]{ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal}, 90);
        RecipeHandler.RegisterInfusionRecipe(new ItemStack(ModItems.ChargedCrystal, 1, ModItems.ChargedCrystal.getMaxDamage()), new ItemStack(ModItems.RedCrystal), new Object[]{ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal, ModItems.BlueCrystal}, 150);

        RecipeHandler.RegisterInfusionRecipe(ModItems.CrystalPickaxe, new ItemStack(Items.diamond_pickaxe), new Object[]{ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedGem, ModItems.GreenCrystal, ModItems.GreenCrystal, ModItems.RedCrystal, ModItems.RedCrystal, ModItems.ChargedCrystal}, 1000);
        RecipeHandler.RegisterInfusionRecipe(ModItems.CrystalBlade, new ItemStack(Items.diamond_sword), new Object[]{ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedGem, ModItems.GreenCrystal, ModItems.GreenCrystal, ModItems.RedCrystal, ModItems.RedCrystal, ModItems.ChargedCrystal}, 1000);

        RecipeHandler.RegisterInfusionRecipe(ModItems.SoulOrb, new ItemStack(ModItems.CrystalInfusedGem), new Object[]{ModItems.ChargedCrystal, Items.ender_pearl, Items.glowstone_dust, Items.glowstone_dust, Items.glowstone_dust}, 380);

        RecipeHandler.RegisterInfusionRecipe(ModItems.InvisHelmet, new ItemStack(Items.leather_helmet), new Object[]{ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk}, 80);
        RecipeHandler.RegisterInfusionRecipe(ModItems.InvisChestPlate, new ItemStack(Items.leather_chestplate), new Object[]{ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk}, 80);
        RecipeHandler.RegisterInfusionRecipe(ModItems.InvisLeggings, new ItemStack(Items.leather_leggings), new Object[]{ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk}, 80);
        RecipeHandler.RegisterInfusionRecipe(ModItems.InvisBoots, new ItemStack(Items.leather_boots), new Object[]{ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk}, 80);
        RecipeHandler.RegisterInfusionRecipe(ModItems.InvisibilityCore, new ItemStack(ModItems.CrystalInfusedGem), new Object[]{ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.CrystalSilk, ModItems.RedCrystal, ModItems.RedCrystal, ModItems.ChargedCrystal, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal}, 500);

        RecipeHandler.RegisterInfusionRecipe(ModBlocks.MagicalRecharger, new ItemStack(ModBlocks.EnergyBattery), new Object[]{ModItems.CrystalInfusedGem, ModItems.CrystalInfusedGem, ModItems.CrystalInfusedGem, ModItems.CrystalInfusedGem, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal, ModItems.CrystalInfusedMetal}, 300);



        //Magic Recipes
        Utils.AddRecipe(new ItemStack(ModBlocks.EnergyBattery), new Object[]{"GMG", "MCM", "GMG", 'G', Items.gold_ingot, 'M', ModItems.CrystalInfusedMetal, 'C', ModItems.CrystalInfusedGem});
        Utils.AddRecipe(new ItemStack(ModBlocks.MagicalInfuser), new Object[]{"OCO", "GRG", "OBO", 'O', Blocks.obsidian, 'C', ModItems.ChargedCrystal, 'G', ModItems.GreenCrystal, 'B', ModItems.BlueCrystal, 'R', ModItems.RedCrystal});
        Utils.AddRecipe(new ItemStack(ModBlocks.MagicalDeconstructor), new Object[]{"IPI", "GBG", "IRI", 'I', ModItems.CrystalInfusedMetal, 'P', ModBlocks.PowerCrystal, 'G', ModItems.GreenCrystal, 'R', ModItems.RedCrystal, 'B', ModBlocks.EnergyBattery});
        Utils.AddRecipe(new ItemStack(ModBlocks.InfusedGemBlock), new Object[]{"XXX", "XXX", "XXX", 'X', ModItems.CrystalInfusedGem });
        Utils.AddRecipe(new ItemStack(ModBlocks.InfusedMetalBlock), new Object[]{"XXX", "XXX", "XXX", 'X', ModItems.CrystalInfusedMetal });

        Utils.AddShapelessRecipe(new ItemStack(ModItems.CrystalInfusedGem, 9), new Object[]{ModBlocks.InfusedGemBlock});
        Utils.AddShapelessRecipe(new ItemStack(ModItems.CrystalInfusedMetal, 9), new Object[]{ModBlocks.InfusedMetalBlock});

        Utils.AddRecipe(new ItemStack(ModItems.ChargedCrystal, 1, 50), new Object[]{"RBG", "BCB", "GBR", 'R', Items.redstone, 'G', Items.glowstone_dust, 'B', ModItems.BlueCrystal, 'C', ModItems.GreenCrystal});
        Utils.AddRecipe(new ItemStack(ModItems.ChargedCrystal, 1, 50), new Object[]{"GBR", "BCB", "RBG", 'R', Items.redstone, 'G', Items.glowstone_dust, 'B', ModItems.BlueCrystal, 'C', ModItems.GreenCrystal});

        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalBlade), new ItemStack(Items.diamond), Enchantment.sharpness, 3));
        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalBlade), new ItemStack(Items.blaze_rod), Enchantment.fireAspect, 1));
        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalBlade), new ItemStack(Items.ender_pearl), Enchantment.looting, 2));

        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalPickaxe), new ItemStack(Items.diamond), Enchantment.efficiency, 3));
        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalPickaxe), new ItemStack(Items.blaze_rod), Enchantment.silkTouch, 1));
        Utils.AddRecipe(new CrystalToolUpgradeRecipe(new ItemStack(ModItems.CrystalPickaxe), new ItemStack(Items.ender_pearl), Enchantment.fortune, 2));

        Utils.AddRecipe(new SpellPartCopy(new ItemStack(ModItems.SpellComponent)));

        Utils.AddRecipe(new ItemRechargeRecipe(50, 0, new ItemStack(ModItems.InvisibilityCore), new ItemStack(ModItems.ChargedCrystal, 1, 0)));
        Utils.AddRecipe(new ItemRechargeRecipe(2, 0, new ItemStack(ModItems.ChargedCrystal), new ItemStack(Items.redstone, 1)));
        Utils.AddRecipe(new ItemRechargeRecipe(5, 0, new ItemStack(ModItems.ChargedCrystal), new ItemStack(Items.glowstone_dust, 1)));
        Utils.AddRecipe(new ItemStack(ModItems.Parchment), new Object[]{"S  ", " P ", "  S", 'S', Items.stick, 'P', Items.paper});
        Utils.AddRecipe(new ItemStack(ModItems.Spell), new Object[]{"PPP", "PBP", "PPP", 'P', Items.paper, 'B', ModItems.Parchment});

        Utils.AddRecipe(new ItemStack(ModBlocks.PowerCrystal), new Object[]{"BPB", "GRG", "BPB", 'B', ModItems.BlueCrystal, 'P', ModItems.ChargedCrystal, 'G', ModItems.GreenCrystal, 'R', ModItems.RedCrystal});





    }
}
