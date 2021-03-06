package CrystallineMagic.Blocks;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.TileEntities.TileEntityMagicalEnergyRecharger;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Block.ModBlockContainer;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ModBlockMagicalEnergyRecharger extends ModBlockContainer{
    public ModBlockMagicalEnergyRecharger() {
        super(Material.iron);
        this.setHardness(4);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMagicalEnergyRecharger();
    }

    IIcon Top;
    IIcon Side;
    IIcon Bottom;

    public void registerBlockIcons(IIconRegister icon)
    {
        Top = icon.registerIcon(Ref.ModId + ":MagicalChargerTop");
        Side = icon.registerIcon(Ref.ModId + ":MagicalChargerSide");
        Bottom = icon.registerIcon(Ref.ModId + ":MagicalChargerBottom");

    }

    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? Top : (par1 == 0 ? Bottom : Side);
    }


    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {

            return true;
        }
        else
        {


            FMLNetworkHandler.openGui(par5EntityPlayer, CrystMagic.instance, 0, par1World, par2, par3, par4);
            return true;
        }
    }

}
