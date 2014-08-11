package CrystallineMagic.Utils.Spells.SpellModifiers;

import CrystallineMagic.Utils.Spells.Utils.SpellModifier;

public class RangeExtender implements SpellModifier {
    @Override
    public String GetName() {
        return "Range Extender";
    }

    @Override
    public String GetId() {
        return "RE";
    }

    @Override
    public double EnergyMultiplier() {
        return 1.1;
    }
}
