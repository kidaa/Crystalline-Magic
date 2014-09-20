package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Earth extends ElementBase {

    public Earth() {
        super("Earth", "EARTH", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 192, 0, 64, 64));
    }
}
