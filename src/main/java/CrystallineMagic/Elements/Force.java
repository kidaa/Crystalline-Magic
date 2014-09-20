package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Force extends ElementBase {

    public Force() {
        super("Force", "FORCE", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 0, 192, 64, 64));
    }
}
