package CrystallineMagic.Utils.Spells.Utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SpellComponent{

    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player);
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side);

    public String GetName();
    public double EnergyCost();
    public String GetId();

    public SpellPartUsage GetUsage();



}
