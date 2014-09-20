package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Sky extends ElementBase {
    public Sky() {
        super("Sky", "SKY", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-2.png"), 0, 0, 64, 64));
    }
}
