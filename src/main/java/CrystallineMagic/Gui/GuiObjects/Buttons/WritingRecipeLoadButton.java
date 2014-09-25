package CrystallineMagic.Gui.GuiObjects.Buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WritingRecipeLoadButton extends GuiButton {

    ResourceLocation res = new ResourceLocation("crystmagic", "textures/gui/icons.png");

    public WritingRecipeLoadButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, 16, 16, null);
    }



    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_)
    {
        if (this.visible)
        {
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;


            GL11.glPushMatrix();

            mc.renderEngine.bindTexture(res);



            if(enabled && getHoverState(field_146123_n) != 2){
                GL11.glColor4f(1,1,1,1);

                drawTexturedModalRect(xPosition, yPosition, 0,0, 16, 16);


            }else if(getHoverState(field_146123_n) == 2){
                GL11.glColor4f(1,1,1,1);

                drawTexturedModalRect(xPosition, yPosition, 32,0, 16, 16);


            }else if(!enabled){
                GL11.glColor4f(0.5F,0.5F,0.5F,1);

                drawTexturedModalRect(xPosition, yPosition, 16,0, 16, 16);


            }

            drawTexturedModalRect(xPosition + 1, yPosition + 1, 49,1, 14,14);


            GL11.glPopMatrix();

        }
    }

}
