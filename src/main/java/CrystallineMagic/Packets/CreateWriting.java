package CrystallineMagic.Packets;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementRegistry;
import CrystallineApi.Recipes.WritingRecipe;
import CrystallineApi.Recipes.WritingRecipeHandler;
import CrystallineMagic.TileEntities.TileEntitySpellWritingTable;
import MiscUtils.Network.AbstractPacket;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

public class CreateWriting extends AbstractPacket {

    int x,y,z;
    ArrayList<String> Els;
    ItemStack stack;


    public CreateWriting(){}
    public CreateWriting(int x, int y, int z, ElementBase[] Els, ItemStack input){
        this.x = x;
        this.y = y;
        this.z = z;

        this.Els = new ArrayList<String>();

        for(ElementBase r : Els){
            this.Els.add(r.Id);
        }

        this.stack = input;
    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {

        NBTTagCompound nbt = new NBTTagCompound();

        stack.writeToNBT(nbt);

        NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < Els.size(); i++)
        {
            String s = Els.get(i);
            if(s != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("Name", s);
                tagList.appendTag(tag);
            }
        }

        nbt.setTag("PLS", tagList);

        ByteBufUtils.writeTag(buffer, nbt);

        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);

    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {

        NBTTagCompound comp = ByteBufUtils.readTag(buffer);
        stack = ItemStack.loadItemStackFromNBT(comp);


        NBTTagList tagList = comp.getTagList("PLS", Constants.NBT.TAG_COMPOUND);


        for(int i = 0; i < tagList.tagCount(); i++)
        {

            NBTTagCompound tag = tagList.getCompoundTagAt(i);

            if(tag.hasKey("Name")) {

                String s = tag.getString("Name");

                if(Els == null)
                    Els = new ArrayList<String>();

                Els.add(i, s);
            }
        }


        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();


    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {


        ElementBase[] Ell = new ElementBase[Els.size()];

        for(int i = 0; i < Els.size(); i++){
            String t = Els.get(i);

            Ell[i] = ElementRegistry.GetElement(t.toUpperCase());

        }

        WritingRecipe res = WritingRecipeHandler.GetRecipe(stack, Ell);

        if(res != null){

            if(player.worldObj.getTileEntity(x,y,z) instanceof TileEntitySpellWritingTable){
                TileEntitySpellWritingTable tile = (TileEntitySpellWritingTable)player.worldObj.getTileEntity(x,y,z);
                tile.setInventorySlotContents(0, res.Output);



            }

        }else{

            System.err.println("Error in loading packet could not find recipe from loaded data");
        }

    }
}
