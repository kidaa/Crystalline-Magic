package CrystallineMagic.Utils.Spells.SpellTypes;

import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import CrystallineMagic.Utils.Spells.Utils.SpellType;
import CrystallineMagic.Utils.Spells.Utils.SpellUseType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Self implements SpellType {
    @Override
    public String GetName() {
        return "Self";
    }

    @Override
    public String GetId() {
        return "Self";
    }

    @Override
    public SpellUseType GetUseType() {
        return SpellUseType.Self;
    }

    @Override
    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide) {
        SpellComponent[] Comps = MagicUtils.GetSpellComponents(SpellStack);

        boolean t = false;

        if(Comps != null && Comps.length > 0){
            for(int i = 0; i < Comps.length; i++){
              if(Comps[i].OnUseOnEntity(SpellStack, world, player, player))
                  t = true;



            }
        }

        return t;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Entity;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1;
    }
}
