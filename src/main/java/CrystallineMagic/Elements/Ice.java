package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Ice extends ElementBase {

    public Ice() {
        super("Ice", "ICE", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 64, 64, 64, 64));
    }
}
