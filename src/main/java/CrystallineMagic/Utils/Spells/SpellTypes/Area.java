package CrystallineMagic.Utils.Spells.SpellTypes;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.RangeExtender;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellType;
import CrystallineMagic.Utils.Spells.Utils.SpellUseType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Area implements SpellType{

    @Override
    public String GetName() {
        return "Area";
    }

    @Override
    public String GetId() {
        return "AR";
    }

    @Override
    public SpellUseType GetUseType() {
        return SpellUseType.Touch;
    }

    @Override
    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide) {
        boolean t = false;

        double Expand = 3 + (MagicUtils.GetAmountOfAModifer(SpellStack, new RangeExtender()) * 2);

        Expand /= 2;

        if(Expand < 3)
            Expand = 3;


        AxisAlignedBB ab = AxisAlignedBB.getBoundingBox(x - Expand, y - Expand, z - Expand, x + Expand, y + Expand, z + Expand);
        List list = world.getEntitiesWithinAABBExcludingEntity(player, ab);
        ArrayList<Entity> Ents = new ArrayList<Entity>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) != null && list.get(i) instanceof Entity){
                Ents.add((Entity)list.get(i));
            }
        }
        SpellComponent[] Comps = MagicUtils.GetSpellComponents(SpellStack);

        if(Ents.size() <= 0)
            return false;

        for(int g = 0; g < Ents.size(); g++)
        for(int i = 0; i < Comps.length; i++) {
            if (Comps != null && Comps.length > 0) {
                if(Comps[i].OnUseOnEntity(SpellStack, world, Ents.get(g), player))
                    t = true;
            }

        }





        return t;
    }
}
