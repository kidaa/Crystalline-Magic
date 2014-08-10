package CrystallineMagic.Main;

import CrystallineMagic.Blocks.ModBlockMagicalEnergyRecharger;
import CrystallineMagic.Blocks.ModBlockBlueCrystalOre;
import CrystallineMagic.Blocks.ModBlockEnergyBattery;
import CrystallineMagic.Blocks.ModBlockGreenCrystalOre;
import CrystallineMagic.Blocks.ModBlockMagicalDeconstructor;
import CrystallineMagic.Blocks.ModBlockMagicalInfuser;
import CrystallineMagic.Blocks.ModBlockPowerCrystal;
import CrystallineMagic.Blocks.ModBlockRedCrystalOre;
import CrystallineMagic.ItemBlocks.ModItemBlockEnergyBattery;
import CrystallineMagic.ItemBlocks.ModItemBlockPowerCrystal;
import CrystallineMagic.TileEntities.TileEntityEnergyBattery;
import CrystallineMagic.TileEntities.TileEntityMagicalDecontructor;
import CrystallineMagic.TileEntities.TileEntityMagicalEnergyRecharger;
import CrystallineMagic.TileEntities.TileEntityMagicalInfuser;
import CrystallineMagic.TileEntities.TileEntityPowerCrystal;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Utils.Register.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;

public class ModBlocks {


    public static Block PowerCrystal;
    public static Block EnergyBattery;
    public static Block MagicalInfuser;
    public static Block InfusedGemBlock, InfusedMetalBlock;
    public static Block MagicalRecharger;
    public static Block MagicalDeconstructor;
    public static Block BlueCrystalOre, GreenCrystalOre, RedCrystalOre;
    
    
    public static void RegisterBlocks(){
        BlockRegister Utils = new BlockRegister(CrystMagic.config, Ref.ModId);


        BlueCrystalOre = new ModBlockBlueCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":BlueCrystalOre");
        Utils.Register(BlueCrystalOre, "BlueCrystalOre");

        GreenCrystalOre = new ModBlockGreenCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":GreenCrystalOre");
        Utils.Register(GreenCrystalOre, "GreenCrystalOre");

        RedCrystalOre = new ModBlockRedCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedCrystalOre");
        Utils.Register(RedCrystalOre, "RedCrystalOre");

        PowerCrystal = new ModBlockPowerCrystal().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId + ":PowerCrystal");
        Utils.Register(PowerCrystal, ModItemBlockPowerCrystal.class, "PowerCrystal", TileEntityPowerCrystal.class);

        MagicalDeconstructor = new ModBlockMagicalDeconstructor().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalDeconstructor, "MagicalDeconstructor", TileEntityMagicalDecontructor.class);

        MagicalInfuser = new ModBlockMagicalInfuser().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalInfuser, "MagicalInfuser", TileEntityMagicalInfuser.class);

        EnergyBattery = new ModBlockEnergyBattery().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId + ":EnergyBattery");
        Utils.Register(EnergyBattery, ModItemBlockEnergyBattery.class, "EnergyBattery", TileEntityEnergyBattery.class);

        MagicalRecharger = new ModBlockMagicalEnergyRecharger().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalRecharger, "MagicalEnergyRecharger", TileEntityMagicalEnergyRecharger.class);

        InfusedGemBlock = new BlockCompressed(MapColor.blueColor).setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId + ":InfusedGemBlock").setHardness(2F);
        Utils.Register(InfusedGemBlock, "InfusedGemBlock");

        InfusedMetalBlock = new BlockCompressed(MapColor.blueColor).setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(Ref.ModId + ":InfusedMetalBlock").setHardness(2F);
        Utils.Register(InfusedMetalBlock, "InfusedMetalBlock");
        
        
        
    }
}
