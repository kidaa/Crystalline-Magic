package CrystallineMagic.Utils.Spells;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface SpellComponent {

    public void OnHitEntity(World world, Entity entityHit, EntitySpellProjectile Projectile);
    public void OnBlockHit(World world, int x, int y, int z, EntitySpellProjectile Projectile);

    public String GetName();
    public double EnergyCost();
    public String GetId();


}
