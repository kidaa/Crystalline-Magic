package CrystallineMagic.Utils.Spells.SpellModifiers;

import CrystallineApi.Spells.SpellModifier;

public class CostDecreaser implements SpellModifier {
    @Override
    public String GetName() {
        return "Cost Decreaser";
    }

    @Override
    public String GetId() {
        return "CD";
    }

    @Override
    public double EnergyMultiplier() {
        return 0.9;
    }
}
