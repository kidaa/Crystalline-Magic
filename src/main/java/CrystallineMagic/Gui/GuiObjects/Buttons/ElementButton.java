package CrystallineMagic.Gui.GuiObjects.Buttons;

import CrystallineApi.Elements.ElementBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;


public class ElementButton extends GuiButton {


    public ElementBase element;

    public ElementButton(int id, int x, int y, ElementBase el) {
        super(id, x, y, 16, 16, "");
        this.element = el;
    }

    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible && element != null)
        {
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;

            float f = 0.8F;

            if(field_146123_n)
                GL11.glColor4f(1,1,1,1);
            else{
                GL11.glColor4f(f, f, f, 1);
            }

            GL11.glPushMatrix();

            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            p_146112_1_.getTextureManager().bindTexture(element.Icon.iconL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            int size = 16;

            float scX = (float)size / element.Icon.Xsize, scY = (float)size / element.Icon.Ysize;

            GL11.glScalef(scX, scY, scX);

            drawTexturedModalRect((int)(xPosition * ((double)element.Icon.Xsize / (double)size)), (int)(yPosition * ((double)element.Icon.Ysize / (double)size)), element.Icon.Xpos, element.Icon.Ypos, element.Icon.Xsize, element.Icon.Ysize);


            GL11.glPopMatrix();

        }
    }



}

