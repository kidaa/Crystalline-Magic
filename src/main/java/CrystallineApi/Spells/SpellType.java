package CrystallineApi.Spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SpellType {

    public String GetName();
    public String GetId();

    public SpellUseType GetUseType();


    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide);

    public SpellPartUsage GetUsage();

    public double GetEnergyMultiplier(ItemStack stack);

}
