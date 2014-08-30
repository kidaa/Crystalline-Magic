package CrystallineMagic.Spells.SpellTypes;

import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUseType;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Utils.MagicEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Touch implements SpellType {
    @Override
    public String GetName() {
        return "Touch";
    }

    @Override
    public String GetId() {
        return "Touch";
    }

    @Override
    public SpellUseType GetUseType() {
        return SpellUseType.Touch;
    }

    @Override
    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide) {
        SpellComponent[] Comps = SpellUtils.GetSpellComponents(SpellStack);
        Block bl = world.getBlock(x,y,z);

        boolean t = false;

        if(Comps != null && Comps.length > 0 && bl != null && bl != Blocks.air){
            for(int i = 0; i < Comps.length; i++){
                if(Comps[i].OnUseOnBlock(SpellStack, world, x, y, z, bl, player, BlockSide)) {
                    t = true;
                }
            }

        }else if(Comps != null && Comps.length > 0){
            for(int i = 0; i < Comps.length; i++){
                if(Comps[i].OnUseOnEntity(SpellStack, world, ent, player))
                    t = true;
            }
        }


        if(t){
            if(Comps[0].GetComponentColor() != null)
            MagicEffects.SpawnMagicEffect(world, (double)x - 0.8, (double)y, (double)z - 0.8, 12, 2, Comps[0].GetComponentColor());
        }

        return t;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Both;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return null;
    }
}
