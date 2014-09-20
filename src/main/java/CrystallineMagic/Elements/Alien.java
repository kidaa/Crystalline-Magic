package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Alien extends ElementBase {

    public Alien() {
        super("Alien", "ALIEN", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-2.png"), 64, 0, 64, 64));
    }
}
