package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Control extends ElementBase {

    public Control() {
        super("Control", "CONTROL", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 128, 192, 64, 64));
    }
}
