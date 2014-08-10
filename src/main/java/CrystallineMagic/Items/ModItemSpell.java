package CrystallineMagic.Items;

import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.SpellComponent;
import CrystallineMagic.Utils.Spells.SpellComponents.Fire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ModItemSpell extends Item {



    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 25;
    }


    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }


    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

        return par1ItemStack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        EntitySpellProjectile EntSpell = new EntitySpellProjectile(world, player, 1 * 2.0F, new SpellComponent[]{new Fire()});

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(EntSpell);
        }

        return stack;
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

    }
}
