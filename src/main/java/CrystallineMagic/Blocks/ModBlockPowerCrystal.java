package CrystallineMagic.Blocks;

import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.TileEntities.TileEntityPowerCrystal;
import MiscUtils.Block.ModBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class ModBlockPowerCrystal extends ModBlockContainer {

    public ModBlockPowerCrystal(){
        super(Material.iron);
        this.setHardness(2F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {

        return new TileEntityPowerCrystal();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l){
        return false;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {

        TileEntity tileBase = world.getTileEntity(x,y,z);
        int Meta = world.getBlockMetadata(x,y,z);

        if(tileBase instanceof TileEntityPowerCrystal && Meta == 0){
            ((TileEntityPowerCrystal)tileBase).Bottom = true;

            world.setBlock(x,y+1,z, ModBlocks.PowerCrystal, 1, 2);
            world.setBlock(x,y+2,z, ModBlocks.PowerCrystal, 1, 2);
            world.setBlock(x,y+3,z, ModBlocks.PowerCrystal, 1, 2);

            TileEntityPowerCrystal tileMiddle = (TileEntityPowerCrystal)world.getTileEntity(x,y+1,z);
            tileMiddle.Middle = true;

            TileEntityPowerCrystal tileTop = (TileEntityPowerCrystal)world.getTileEntity(x,y+2,z);
            tileMiddle.Top = true;

            world.setTileEntity(x,y+1,z, tileMiddle);
            world.setTileEntity(x,y+2,z, tileMiddle);
        }

    }


    @Override
    public void breakBlock(World World, int x, int y, int z, Block id, int meta) {

        TileEntity tilee = World.getTileEntity(x, y, z);

        if(tilee != null && tilee instanceof TileEntityPowerCrystal) {

            TileEntityPowerCrystal tile = (TileEntityPowerCrystal)tilee;

            if (tile.Bottom) {
                SetEmptyBlock(World, x, y + 1, z);
                SetEmptyBlock(World, x, y + 2, z);
                SetEmptyBlock(World, x, y + 3, z);


            } else if (tile.Middle) {
                SetEmptyBlock(World, x, y + 1, z);
                SetEmptyBlock(World, x, y + 2, z);
                SetEmptyBlock(World, x, y - 1, z);


            } else if (tile.Top) {
                SetEmptyBlock(World, x, y - 1, z);
                SetEmptyBlock(World, x, y - 2, z);
                SetEmptyBlock(World, x, y + 1, z);


            } else if (!tile.Bottom && !tile.Middle) {
                SetEmptyBlock(World, x, y - 1, z);
                SetEmptyBlock(World, x, y - 2, z);
                SetEmptyBlock(World, x, y - 3, z);


            }

        }


        super.breakBlock(World, x, y, z, id, meta);

    }



    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.isAirBlock(x,y+1,z) && world.isAirBlock(x,y+2,z) && world.isAirBlock(x,y+3,z);
    }

    public static void SetEmptyBlock(World world, int x, int y, int z){
        if(!world.isAirBlock(x,y,z) && world.getBlock(x,y,z) != null) {
            if(world.getBlock(x,y,z) instanceof ModBlockPowerCrystal)
            world.setBlockToAir(x,y,z);
        }
    }

    }
