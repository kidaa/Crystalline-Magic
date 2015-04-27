package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Spells.SpellModifiers.RangeExtender;
import MiscUtils.Block.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;

public class ExplodeBlock implements SpellComponent {
    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer pp, int Side) {


        if(world.getBlock(x,y,z) != null && world.getBlock(x,y,z) != Blocks.air){

            if(SpellUtils.GetAmountOfAModifer(Spell, new RangeExtender()) <= 0) {
                BlockUtil.explodeBlock(world, x, y, z);
            }else{
                int range = SpellUtils.GetAmountOfAModifer(Spell, new RangeExtender()) + 1;

                Vec3 org = Vec3.createVectorHelper(x,y,z);

                ArrayList<ChunkPosition> pos = new ArrayList<ChunkPosition>();

                for(int yP = y - range; yP < y + range + 1; yP++){
                    for(int xP = x - range; xP < x + range + 1; xP++){
                        for(int zP = z - range; zP < z + range + 1; zP++){

                            Vec3 newPos = Vec3.createVectorHelper(xP, yP, zP);

                            if(org.distanceTo(newPos) <= range){
                                pos.add(new ChunkPosition(xP, yP, zP));
                            }

                        }
                    }
                }

                BlockUtil.explodeBlocks(world, x,y,z, pos);


            }

        }

        return false;
    }

    @Override
    public String GetName() {
        return "Explode Block";
    }

    @Override
    public double EnergyCost() {
        return 125;
    }

    @Override
    public String GetId() {
        return "EB";
    }

    @Override
    public SpellPartUsage GetUsage() {
        return SpellPartUsage.Block;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return new SpellModifier[]{new RangeExtender()};
    }

    @Override
    public Color GetComponentColor() {
        return new Color(59,0,0);
    }
}
