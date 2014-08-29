package CrystallineMagic.Spells.SpellModifiers;

import CrystallineApi.Spells.SpellModifier;

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
        return 1.3;
    }
}
