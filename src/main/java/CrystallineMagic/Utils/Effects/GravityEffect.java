package CrystallineMagic.Utils.Effects;

import net.minecraft.potion.Potion;

public class GravityEffect extends Potion {
    public GravityEffect(int id, boolean IsBadEffect, int color) {
        super(id, IsBadEffect, color);
    }

    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }


}