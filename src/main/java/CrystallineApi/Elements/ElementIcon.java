package CrystallineApi.Elements;

import net.minecraft.util.ResourceLocation;

public class ElementIcon {

    public ResourceLocation iconL;
    public int Xpos, Ypos, Xsize, Ysize;


    public ElementIcon(ResourceLocation lc, int X, int Y, int xsize, int ysize){
        this.iconL = lc;

        this.Xpos = X;
        this.Ypos = Y;

        this.Xsize = xsize;
        this.Ysize = ysize;
    }
}
