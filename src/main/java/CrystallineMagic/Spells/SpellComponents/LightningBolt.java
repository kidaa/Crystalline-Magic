package CrystallineMagic.Spells.SpellComponents;

import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellPartUsage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;

public class LightningBolt implements SpellComponent {

    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {

        EntityLightningBolt Lightning = new EntityLightningBolt(world, 1, 1, 1);
        Lightning.setPosition(entityHit.posX, entityHit.posY, entityHit.posZ);

        return world.spawnEntityInWorld(Lightning);
    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {

        EntityLightningBolt Lightning = new EntityLightningBolt(world, 1, 1, 1);
        Lightning.setPosition(x,y+1,z);

        return world.spawnEntityInWorld(Lightning);
    }

    @Override
    public String GetName() {
        return "Lightning Bolt";
    }

    @Override
    public double EnergyCost() {
        return 150;
    }

    @Override
    public String GetId() {
        return "LB";
    }

    @Override
    public SpellPartUsage GetUsage() {
        return SpellPartUsage.Both;
    }

    @Override
    public SpellModifier[] CompatibleModifiers() {
        return null;
    }

    @Override
    public Color GetComponentColor() {
        return new Color(34,0, 143);
    }
}
