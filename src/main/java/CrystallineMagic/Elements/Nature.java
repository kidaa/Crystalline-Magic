package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Nature extends ElementBase{

    public Nature() {
        super("Nature", "NATURE", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 192, 64, 64, 64));
    }
}
