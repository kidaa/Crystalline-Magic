package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Lightning extends ElementBase {

    public Lightning() {
        super("Lightning", "LIGHTNING", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 0, 64, 64, 64));
    }
}
