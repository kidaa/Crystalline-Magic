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
        Utils.Register(InvisHelmet, "InvisHelmet");

        InvisChestPlate =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 1, 2)).setUnlocalizedName("InvisChestPlate").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisChestPlate");
        Utils.Register(InvisChestPlate, "InvisChestPlate");

        InvisLeggings =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 2, 3)).setUnlocalizedName("InvisLeggings").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisLeggings");
        Utils.Register(InvisLeggings, "InvisLeggings");

        InvisBoots =  (new ModItemMagicArmor(InvisArmor, CrystMagic.proxy.addArmor("Invis"), 3, 4)).setUnlocalizedName("InvisBoots").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:InvisBoots");
        Utils.Register(InvisBoots, "InvisBoots");

        BlueCrystal = new Item().setUnlocalizedName("BlueCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:BlueCrystal");
        Utils.Register(BlueCrystal, "BlueCrystal");

        GreenCrystal = new Item().setUnlocalizedName("GreenCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:GreenCrystal");
        Utils.Register(GreenCrystal, "GreenCrystal");

        RedCrystal = new Item().setUnlocalizedName("RedCrystal").setCreativeTab(CrystMagic.CreativeTab).setTextureName("crystmagic:RedCrystal");
        Utils.Register(RedCrystal, "RedCrystal");

        ChargedCrystal = new ModItemChargedCrystal().setUnlocalizedName("ChargedCrystal").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(ChargedCrystal, "ChargedCrystal");

        SoulOrb = new ModItemSoulOrb().setUnlocalizedName("SoulOrb").setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":SoulOrb");
        Utils.Register(SoulOrb, "SoulOrb");

        InvisibilityAmulet = new ModItemInvisibilityAmulet().setUnlocalizedName("InvisibilityCore").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(InvisibilityAmulet, "InvisibilityAmulet");

        CrystalSilk = new Item().setCreativeTab(CrystMagic.CreativeTab).setUnlocalizedName("CrystalSilk").setTextureName("crystmagic:CrystalSilk");
        Utils.Register(CrystalSilk, "CrystalSilk");

        CrystalBlade = new ModItemCrystalBlade().setCreativeTab(CrystMagic.CreativeTab).setUnlocalizedName("CrystalBlade").setTextureName("crystmagic:CrystalBlade");
        Utils.Register(CrystalBlade, "CrystalBlade");

        CrystalPickaxe = new ModItemCrystalPickaxe().setTextureName(Ref.ModId + ":CrystalPickaxe").setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(CrystalPickaxe, "CrystalPickaxe");

        CrystalInfusedMetal = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":CrystalInfusedMetal");
        Utils.Register(CrystalInfusedMetal, "CrystalInfusedMetal");

        CrystalInfusedGem = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":CrystalInfusedGem");
        Utils.Register(CrystalInfusedGem, "CrystalInfusedGem");

        Parchment = new Item().setCreativeTab(CrystMagic.CreativeTab).setTextureName(Ref.ModId + ":Parchment").setMaxStackSize(1);
        Utils.Register(Parchment, "Parchment");

        WritingRecipePage = new CrystallineMagic.Items.WritingRecipePage().setCreativeTab(CrystMagic.CreativeTab).setMaxStackSize(1).setTextureName(Ref.ModId + ":WritingRecipePage");
        Utils.Register(WritingRecipePage, "WritingRecipePage");

        Spell = new ModItemSpell().setTextureName(Ref.ModId + ":Spell").setMaxStackSize(1).setCreativeTab(CrystMagic.SpellPart);
        Utils.Register(Spell, "Spell");

        SpellType = new ModItemSpellType().setTextureName(Ref.ModId + ":SpellType");
        Utils.Register(SpellType, "SpellType");

        SpellComponent = new ModItemSpellComponent().setTextureName(Ref.ModId + ":SpellComponent");
        Utils.Register(SpellComponent, "SpellComponent");

        SpellModifier = new ModItemSpellModifier().setTextureName(Ref.ModId + ":SpellModifier");
        Utils.Register(SpellModifier, "SpellModifier");



        SpellIconItem = new Item().setTextureName(Ref.ModId + ":MagicEffect");
        Utils.SilentRegister(SpellIconItem);



    }
}
