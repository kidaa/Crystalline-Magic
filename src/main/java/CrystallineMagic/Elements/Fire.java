package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Fire extends ElementBase {
    public Fire() {
        super("Fire", "FIRE", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 128, 0, 64, 64));
    }
}
