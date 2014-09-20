package CrystallineMagic.Elements;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementIcon;
import net.minecraft.util.ResourceLocation;

public class Metal extends ElementBase{

        public Metal() {
            super("Metal", "METAL", new ElementIcon(new ResourceLocation("crystmagic", "textures/effects/Elements-1.png"), 128, 64, 64, 64));
        }
}
