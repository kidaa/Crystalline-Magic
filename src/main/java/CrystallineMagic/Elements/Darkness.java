package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Darkness extends ElementBase {

    public Darkness() {
        super("Darkness", "DARKNESS", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 192, 128, 64, 64));
    }
}
