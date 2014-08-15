package CrystallineMagic.Utils.Spells.SpellTypes;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.SpellModifiers.AreaIncludePlayer;
import CrystallineMagic.Utils.Spells.SpellModifiers.RangeExtender;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
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

        int Expand = 1 + (MagicUtils.GetAmountOfAModifer(SpellStack, new RangeExtender()));

        if(Expand > 1)
            Expand /= 2;

        AxisAlignedBB ab = AxisAlignedBB.getBoundingBox(x - Expand, y - Expand, z - Expand, x + Expand, y + Expand, z + Expand);
        List list;

        if(MagicUtils.GetAmountOfAModifer(SpellStack, new AreaIncludePlayer()) <= 0)
        list = world.getEntitiesWithinAABBExcludingEntity(player, ab);
        else{
            list = world.getEntitiesWithinAABB(Entity.class, ab);
        }


        ArrayList<Entity> Ents = new ArrayList<Entity>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) != null && list.get(i) instanceof Entity){
                Ents.add((Entity)list.get(i));
            }
        }

        SpellComponent[] Comps = MagicUtils.GetSpellComponents(SpellStack);

        if(Ents.size() > 0) {
            for (int g = 0; g < Ents.size(); g++)
                for (int i = 0; i < Comps.length; i++) {
                    if (Comps != null && Comps.length > 0) {
                        if (Comps[i].OnUseOnEntity(SpellStack, world, Ents.get(g), player))
                            t = true;
                    }

                }
        }

        if(Ents.size() < 0 || BlockSide != -1){
            for (int yT = y - Expand; yT < y + Expand * 2; yT++) {
                for (int xT = x - Expand; xT < x + Expand * 2; xT++) {
                      for (int zT = z - Expand; zT < z + Expand * 2; zT++) {
                            for (int i = 0; i < Comps.length; i++) {
                            if (Comps[i].OnUseOnBlock(SpellStack, world, xT, yT, zT, world.getBlock(xT, yT, zT), player, BlockSide))
                                t = true;



                        }
                    }
                }
            }

            }





        return t;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Both;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1 + ((MagicUtils.GetAmountOfAModifer(stack, new RangeExtender())+1) * 4);
    }
}
