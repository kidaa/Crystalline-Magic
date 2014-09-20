package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Motion extends ElementBase {

    public Motion() {
        super("Motion", "MOTION", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 64, 192, 64, 64));
    }
}
