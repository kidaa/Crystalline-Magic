package CrystallineMagic.Utils.Spells.SpellModifiers;

import CrystallineMagic.Utils.Spells.Utils.SpellModifier;

public class StrengthUpgrade implements SpellModifier {
    @Override
    public String GetName() {
        return "Strength Upgrade";
    }

    @Override
    public String GetId() {
        return "SU";
    }

    @Override
    public double EnergyMultiplier() {
        return 1.4;
    }
}
