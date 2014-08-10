package CrystallineMagic.Items;

import CrystallineApi.Magic.IEnergyStorageItem;
import CrystallineMagic.Utils.MagicInfoStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ModItemSoulOrb extends Item implements IEnergyStorageItem {


    public ModItemSoulOrb(){
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Deprecated
    public boolean hasEffect(ItemStack stack, int pass)
    {

        return (MagicInfoStorage.GetPlayerFromStack(stack) != null);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            list.add(StatCollector.translateToLocal("items.desc.soulorb.bound").replace("$player", MagicInfoStorage.GetPlayerFromStack(stack).getDisplayName()));
        }else{
            list.add(StatCollector.translateToLocal("items.desc.soulorb.unbound"));
        }
    }


    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {

        if(player.isSneaking()) {
            if (stack.getTagCompound() == null) {
                stack.setTagCompound(new NBTTagCompound());

                stack.getTagCompound().setString("Player", player.getCommandSenderName());

                MagicInfoStorage.get(player).SetHasMagic(true);

            } else {
                if (MagicInfoStorage.GetPlayerFromStack(stack) == null) {
                    stack.getTagCompound().setString("Player", player.getCommandSenderName());

                    MagicInfoStorage.get(player).SetHasMagic(true);

                }

            }

        }else{
            if (MagicInfoStorage.GetPlayerFromStack(stack) != null) {
                MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).SetPlayerMaxEnergy(MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).GetPlayerMaxEnergy() + 100);
            }
        }


        return stack;
    }

    @Override
    public double GetEnergyStored(ItemStack stack) {

        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            if(MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)) != null){
                return MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).GetPlayerEnergy();

            }
        }

        return 0;
    }


    public EntityPlayer GetOwner(ItemStack stack){
        EntityPlayer player = null;


        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            player = MagicInfoStorage.GetPlayerFromStack(stack);
        }

        return player;
    }

    @Override
    public double GetMaxEnergy(ItemStack stack) {
        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            if(MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)) != null){
                return MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).GetPlayerMaxEnergy();

            }
        }

        return 0;
    }

    @Override
    public void SetEnergy(ItemStack stack, double i) {
        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            if(MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)) != null){
                 MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).SetPlayerEnergy(i);

            }
        }

    }

    @Override
    public void AddEnergy(ItemStack stack, double i) {
        if(MagicInfoStorage.GetPlayerFromStack(stack) != null){
            if(MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)) != null){
                MagicInfoStorage.get(MagicInfoStorage.GetPlayerFromStack(stack)).IncreasePlayerEnergy(i);

            }
        }

    }

    @Override
    public void RemoveEnergy(ItemStack stack, double i) {
        if( GetOwner(stack) != null){
            if(MagicInfoStorage.get(GetOwner(stack)) != null){
                if(MagicInfoStorage.get(GetOwner(stack)).GetPlayerEnergy() >= i)
                MagicInfoStorage.get(GetOwner(stack)).DecreasePlayerEnergy(i);

                else
                    GetOwner(stack).attackEntityFrom(new DamageSource("damage.soulorb.overuse"), 1F);

            }
        }
    }
}
