package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Time extends ElementBase {
    public Time() {
        super("Time", "TIME", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 192, 192, 64, 64));
    }
}
