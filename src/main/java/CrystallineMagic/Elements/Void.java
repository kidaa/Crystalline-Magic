package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Void extends ElementBase {

    public Void() {
        super("Void", "VOID", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 0, 128, 64, 64));
    }
}
