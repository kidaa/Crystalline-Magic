package CrystallineMagic.Event;

import CrystallineMagic.Entity.FX.EntityMagicEffectFX;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderWorldLastEvent {

    @SubscribeEvent
    public void onRenderWorldLast(net.minecraftforge.client.event.RenderWorldLastEvent event)
    {

        GL11.glPushMatrix();
        Tessellator tessellator = Tessellator.instance;


    GL11.glDepthMask(false);
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(770, 1);
    GL11.glAlphaFunc(516, 0.003921569F);
    EntityMagicEffectFX.dispatchQueuedRenders(tessellator);
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glDisable(GL11.GL_BLEND);
    GL11.glDepthMask(true);

        GL11.glPopMatrix();

    }
}
