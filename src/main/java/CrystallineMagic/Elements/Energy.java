package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Energy extends ElementBase {

    public Energy() {
        super("Energy", "ENERGY", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 64, 128, 64, 64));
    }
}
