package CrystallineMagic.Blocks;

import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.TileEntities.TileEntityEnergyBattery;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Block.ModBlockContainer;
import MiscUtils.Utils.Handlers.ChatMessageHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockEnergyBattery extends ModBlockContainer {


    IIcon off, on;

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        if(world.getTileEntity(x,y,z) != null)
            return world.getTileEntity(x,y,z).getWorldObj().isBlockIndirectlyGettingPowered(x,y,z) ? on : off;

        return off;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {

        return off;
    }

    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        off = par1IconRegister.registerIcon(Ref.ModId + ":" + "EnergyBatteryOff");
        on = par1IconRegister.registerIcon(Ref.ModId + ":" + "EnergyBatteryOn");

    }

    public ModBlockEnergyBattery() {
        super(Material.iron);
        this.setHardness(2f);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityEnergyBattery();
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer pl, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {

        if(!world.isRemote)
        if(world.getTileEntity(x,y,z) != null && world.getTileEntity(x,y,z) instanceof TileEntityEnergyBattery){
            TileEntityEnergyBattery tile = (TileEntityEnergyBattery)world.getTileEntity(x,y,z);

            String t = (tile.GetStoredEnergy() / tile.GetMaxEnergy() * 100) + "";
            int length = t.length();
            String[] g = null;


            if(length >= 6)
             g = t.split(t.charAt(6) + "", 6);


            String use;

            if(g != null && g[0] != null)
                use = g[0];
            else
            use = t;

            ChatMessageHandler.sendChatToPlayer(pl, "Energy stored: " + EnumChatFormatting.AQUA + use + "%");
        }

        return false;
    }

    @Override
    public void breakBlock(World World, int x, int y, int z, Block id, int meta)
    {

        TileEntity tile_e = World.getTileEntity(x, y, z);

        if(tile_e != null && tile_e instanceof TileEntityEnergyBattery){
            TileEntityEnergyBattery tile = (TileEntityEnergyBattery)tile_e;

            ItemStack stack = new ItemStack(ModBlocks.EnergyBattery);

            stack.setTagCompound(new NBTTagCompound());


            stack.stackTagCompound.setDouble("Power", tile.GetStoredEnergy());

            if(stack != null){
                float spawnX = x + World.rand.nextFloat();
                float spawnY = y + World.rand.nextFloat();
                float spawnZ = z + World.rand.nextFloat();


                EntityItem droppedItem = new EntityItem(World, spawnX, spawnY, spawnZ, stack);

                float mult = 0.05F;

                droppedItem.motionX = (-0.5 + World.rand.nextFloat()) * mult;
                droppedItem.motionY = (4 + World.rand.nextFloat()) * mult;
                droppedItem.motionZ = (-0.5 + World.rand.nextFloat()) * mult;


                World.spawnEntityInWorld(droppedItem);
                super.breakBlock(World, x, y, z, id, meta);
            }


        }

    }
}
