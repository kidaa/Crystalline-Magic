package CrystallineMagic.Utils.Spells.SpellModifiers;

import CrystallineMagic.Utils.Spells.Utils.SpellModifier;

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
}
