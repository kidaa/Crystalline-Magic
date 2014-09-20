package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Water extends ElementBase{
    public Water() {
        super("Water", "WATER", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 64, 0, 64, 64));
    }
}
