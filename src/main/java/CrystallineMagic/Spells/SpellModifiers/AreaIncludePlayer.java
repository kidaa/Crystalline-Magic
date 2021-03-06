package CrystallineMagic.Spells.SpellModifiers;

import CrystallineApi.Spells.SpellModifier;

public class AreaIncludePlayer implements SpellModifier {
    @Override
    public String GetName() {
        return "Area Include Player";
    }

    @Override
    public String GetId() {
        return "AIP";
    }

    @Override
    public double EnergyMultiplier() {
        return 1;
    }

    @Override
    public boolean IgnoreCompatibility() {
        return false;
    }
}
