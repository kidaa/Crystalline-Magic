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

        int Range = MagicUtils.GetAmountOfAModifer(SpellStack, new RangeExtender());

        int Expand = 1 + Range;


        int XNeg = x - Expand;
        int XPos = x + Expand;

        int YNeg = y - Expand;
        int YPos = y + Expand;

        int ZNeg = z - Expand;
        int ZPos = z + Expand;



        AxisAlignedBB ab = AxisAlignedBB.getBoundingBox(XNeg, YNeg, ZNeg, XPos, YPos, ZPos);
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




        int g = 0;

        int xg = 0, yg = 0, zg = 0;

        if(Ents.size() < 0 || BlockSide != -1){

            for (int yT = YNeg; yT <= YPos; yT++) {

                yg += 1;

                for (int xT = XNeg; xT <= XPos; xT++) {

                    xg += 1;

                      for (int zT = ZNeg; zT <= ZPos; zT++) {

                          zg += 1;

                          g += 1;

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

        int g = MagicUtils.GetAmountOfAModifer(stack, new RangeExtender());

        double x = g * ((55 * 1.267) + (43*g));


        if(g <= 0)
            return 27;



        return x;
    }
}
