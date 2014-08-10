package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.SpellComponent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Fire implements SpellComponent {


    @Override
    public void OnHitEntity(World world, Entity entityHit, EntitySpellProjectile Projectile) {
        entityHit.setFire(1000);
    }

    @Override
    public void OnBlockHit(World world, int x, int y, int z, EntitySpellProjectile Projectile) {

    }


    @Override
    public String GetName() {
        return "Flame";
    }

    @Override
    public double EnergyCost() {
        return 50;
    }

    @Override
    public String GetId() {
        return "Fire";
    }
}
