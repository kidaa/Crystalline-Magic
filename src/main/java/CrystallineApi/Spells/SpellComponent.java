package CrystallineApi.Spells;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SpellComponent{

    //Activated when the spell component is used on a entity
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player);

    //Activated when the spell component is used on a block
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side);

    //The unlocolized name of the component
    public String GetName();

    //The amount of energy the component costs to use
    public double EnergyCost();

    //The id for the component(used for saving/loading)
    public String GetId();


    //The usage for the component
    public SpellPartUsage GetUsage();

    //The different modifiers the component accepts (null for no compatible modifiers)
    public SpellModifier[] CompatibleModifiers();


}
