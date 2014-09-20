package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Target extends ElementBase {

    public Target() {
        super("Target", "TARGET", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-2.png"), 128, 0, 64, 64));
    }
}
