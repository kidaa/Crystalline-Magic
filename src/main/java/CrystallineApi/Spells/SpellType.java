package CrystallineApi.Spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SpellType {

    //The unlocolized name of the spell type
    public String GetName();

    //The id for the spell type(used for saving/loading)
    public String GetId();

    //The method the spell type is used
    public SpellUseType GetUseType();


    //Activated when the spell type is used
    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide);

    //The usage of the spell type
    public SpellPartUsage GetUsage();

    //Multiplies the the energy cost of the full spell
    public double GetEnergyMultiplier(ItemStack stack);

    //The different modifiers the component accepts (null for no compatible modifiers) (A modifier can still be applied if any of the components accepts it)
    public SpellModifier[] CompatibleModifiers();

}
