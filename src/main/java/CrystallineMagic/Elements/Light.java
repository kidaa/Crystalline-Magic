package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Light extends ElementBase {

    public Light() {
        super("Light", "LIGHT", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 128, 128, 64, 64));
    }
}
