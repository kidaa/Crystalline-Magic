package CrystallineMagic.Main;

import CrystallineMagic.Blocks.ModBLockMagicalEnergyRecharger;
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
import MiscUtils.Utils.BlockRegister;
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
        BlockRegister Utils = new BlockRegister(CrystMagic.config, CrystMagic.ModId);


        BlueCrystalOre = new ModBlockBlueCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId.toLowerCase() + ":BlueCrystalOre");
        Utils.Register(BlueCrystalOre, "BlueCrystalOre");

        GreenCrystalOre = new ModBlockGreenCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId.toLowerCase() + ":GreenCrystalOre");
        Utils.Register(GreenCrystalOre, "GreenCrystalOre");

        RedCrystalOre = new ModBlockRedCrystalOre().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId.toLowerCase() + ":RedCrystalOre");
        Utils.Register(RedCrystalOre, "RedCrystalOre");


        PowerCrystal = new ModBlockPowerCrystal().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId + ":PowerCrystal");
        Utils.Register(PowerCrystal, ModItemBlockPowerCrystal.class, "PowerCrystal", TileEntityPowerCrystal.class);

        MagicalDeconstructor = new ModBlockMagicalDeconstructor().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalDeconstructor, "MagicalDeconstructor", TileEntityMagicalDecontructor.class);

        MagicalInfuser = new ModBlockMagicalInfuser().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalInfuser, "MagicalInfuser", TileEntityMagicalInfuser.class);

        EnergyBattery = new ModBlockEnergyBattery().setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId + ":EnergyBattery");
        Utils.Register(EnergyBattery, ModItemBlockEnergyBattery.class, "EnergyBattery", TileEntityEnergyBattery.class);

        MagicalRecharger = new ModBLockMagicalEnergyRecharger().setCreativeTab(CrystMagic.CreativeTab);
        Utils.Register(MagicalRecharger, "MagicalEnergyRecharger", TileEntityMagicalEnergyRecharger.class);

        InfusedGemBlock = new BlockCompressed(MapColor.blueColor).setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId + ":InfusedGemBlock").setHardness(2F);
        Utils.Register(InfusedGemBlock, "InfusedGemBlock");

        InfusedMetalBlock = new BlockCompressed(MapColor.blueColor).setCreativeTab(CrystMagic.CreativeTab).setBlockTextureName(CrystMagic.ModId + ":InfusedMetalBlock").setHardness(2F);
        Utils.Register(InfusedMetalBlock, "InfusedMetalBlock");
        
        
        
    }
}
