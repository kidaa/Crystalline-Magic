package CrystallineApi.Spells;

public interface SpellModifier {

    //The unlocolized name of the spell modifier
    public String GetName();
    //The id for the modifier(used for saving/loading)
    public String GetId();

    //A amount which multiplies the cost of the spell (can be used to increase/decrease cost of spell)
    public double EnergyMultiplier();

    public boolean IgnoreCompatibility();


}
