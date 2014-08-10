package CrystallineMagic.ItemBlocks;

import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.TileEntities.TileEntityEnergyBattery;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ModItemBlockEnergyBattery extends ItemBlock {
    public ModItemBlockEnergyBattery(Block p_i45328_1_) {
        super(p_i45328_1_);
    }


    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {

        if(stack.stackTagCompound != null){
            world.setBlock(x, y, z, ModBlocks.EnergyBattery);
            if(world.getTileEntity(x, y, z) != null){
                TileEntityEnergyBattery tile = (TileEntityEnergyBattery)world.getTileEntity(x, y, z);

              tile.Power = stack.stackTagCompound.getDouble("Power");
            }


        }else{
            world.setBlock(x, y, z, ModBlocks.EnergyBattery);
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
    {

        if(itemstack.stackTagCompound != null){
            list.add("Energy stored: " + EnumChatFormatting.AQUA + (itemstack.stackTagCompound.getDouble("Power") / TileEntityEnergyBattery.MaxPower * 100) + "%");
        }

    }
}
