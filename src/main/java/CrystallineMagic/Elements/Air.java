package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Air extends ElementBase{
    public Air() {
        super("Air", "AIR", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 0, 0, 64, 64));
    }
}
