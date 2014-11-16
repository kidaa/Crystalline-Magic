package CrystallineMagic.Main;

import CrystallineMagic.Items.ModItemChargedCrystal;
import CrystallineMagic.Items.ModItemCrystalBlade;
import CrystallineMagic.Items.ModItemCrystalPickaxe;
import CrystallineMagic.Items.ModItemInvisibilityAmulet;
import CrystallineMagic.Items.ModItemMagicArmor;
import CrystallineMagic.Items.ModItemSoulOrb;
import CrystallineMagic.Items.ModItemSpell;
import CrystallineMagic.Items.ModItemSpellComponent;
import CrystallineMagic.Items.ModItemSpellModifier;
import CrystallineMagic.Items.ModItemSpellType;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Register.ItemRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {


    public static Item InvisHelmet, InvisChestPlate, InvisLeggings, InvisBoots;
    public static Item BlueCrystal, GreenCrystal, RedCrystal, ChargedCrystal, InvisibilityAmulet;
    public static Item CrystalSilk, CrystalBlade, CrystalPickaxe, CrystalInfusedMetal, CrystalInfusedGem;
    public static Item SoulOrb, Spell, Parchment;
    public static Item SpellComponent, SpellType, SpellModifier;
    public static Item WritingRecipePage;

    public static Item SpellIconItem;


    public static ItemArmor.ArmorMaterial InvisArmor = EnumHelper.addArmorMaterial("InvisArmor", 1, new int[]{0, 0, 0, 0}, 0);

    public static Item.ToolMaterial CrystalMaterial = EnumHelper.addToolMaterial("Crystal", Item.ToolMaterial.EMERALD.getHarvestLevel(), 0, Item.ToolMaterial.EMERALD.getEfficiencyOnProperMaterial(), Item.ToolMaterial.EMERALD.getDamageVsEntity(), 0);

    public static void RegisterItems(){
        ItemRegister Utils = new ItemRegister(CrystMagic.config, Ref.ModId);


        InvisHelmet =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 0, 1)).setUnlocalizedName("InvisHelmet").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisHelmet");
        Utils.Register(InvisHelmet, "Crystal Infused Hood");

        InvisChestPlate =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 1, 2)).setUnlocalizedName("InvisChestPlate").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisChestPlate");
        Utils.Register(InvisChestPlate, "Crystal Infused Robes");

        InvisLeggings =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 2, 3)).setUnlocalizedName("InvisLeggings").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisLeggings");
        Utils.Register(InvisLeggings, "Crystal Infused leggings");

        InvisBoots =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 3, 4)).setUnlocalizedName("InvisBoots").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisBoots");
        Utils.Register(InvisBoots, "Crystal Infused Boots");

        BlueCrystal = new Item().setUnlocalizedName("BlueCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:BlueCrystal");
        Utils.Register(BlueCrystal, "Blue Crystal");

        GreenCrystal = new Item().setUnlocalizedName("GreenCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:GreenCrystal");
        Utils.Register(GreenCrystal, "Green Crystal");

        RedCrystal = new Item().setUnlocalizedName("RedCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:RedCrystal");
        Utils.Register(RedCrystal, "Red Crystal");

        ChargedCrystal = new ModItemChargedCrystal().setUnlocalizedName("ChargedCrystal").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(ChargedCrystal, "Charged Crystal");

        SoulOrb = new ModItemSoulOrb().setUnlocalizedName("SoulOrb").setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":SoulOrb");
        Utils.Register(SoulOrb, "Soul Orb");

        InvisibilityAmulet = new ModItemInvisibilityAmulet().setUnlocalizedName("InvisibilityCore").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(InvisibilityAmulet, "Invisibility Amulet");

        CrystalSilk = new Item().setCreativeTab(CrystMagic.CreativeTab).setUnlocalizedName("CrystalSilk").setTextureName("crystmagic:CrystalSilk");
        Utils.Register(CrystalSilk, "Crystal Silk");

        CrystalBlade = new ModItemCrystalBlade().setCreativeTab(CrystMagic.CreativeTab).setUnlocalizedName("CrystalBlade").setTextureName("crystmagic:CrystalBlade");
        Utils.Register(CrystalBlade, "Crystal Blade");

        CrystalPickaxe = new ModItemCrystalPickaxe().setTextureName(Ref.ModId + ":CrystalPickaxe").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(CrystalPickaxe, "Crystal Pickaxe");

        CrystalInfusedMetal = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":CrystalInfusedMetal");
        Utils.Register(CrystalInfusedMetal, "Crystal Infused Metal");

        CrystalInfusedGem = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":CrystalInfusedGem");
        Utils.Register(CrystalInfusedGem, "Crystal Infused Gem");

        Parchment = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":Parchment").setMaxStackSize(1);
        Utils.Register(Parchment, "Parchment");

        WritingRecipePage = new CrystallineMagic.Items.WritingRecipePage().setCreativeTab(CrystMagic.CreativeTab).setMaxStackSize(1).setTextureName(Ref.ModId + ":WritingRecipePage");
        Utils.Register(WritingRecipePage, "Writing Recipe Page");

        Spell = new ModItemSpell().setTextureName(Ref.ModId + ":Spell").setMaxStackSize(1).setCreativeTab(CrystMagic.SpellPart);
        Utils.Register(Spell, "Spell");

        SpellType = new ModItemSpellType().setTextureName(Ref.ModId + ":SpellType");
        Utils.Register(SpellType, "Spell Type");

        SpellComponent = new ModItemSpellComponent().setTextureName(Ref.ModId + ":SpellComponent");
        Utils.Register(SpellComponent, "Spell Component");

        SpellModifier = new ModItemSpellModifier().setTextureName(Ref.ModId + ":SpellModifier");
        Utils.Register(SpellModifier, "Spell Modifier");



        SpellIconItem = new Item().setTextureName(Ref.ModId + ":MagicEffect");
        Utils.SilentRegister(SpellIconItem);



    }
}
