package CrystallineMagic.Gui.Overlay;

import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.Ref;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class GuiOverlayMagicEnergy extends GuiIngame {
    public GuiOverlayMagicEnergy() {
        super(Minecraft.getMinecraft());
    }


    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
                renderOverlay();

        }
    }


    private ResourceLocation texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/MagicEnergyOverlay.png");


    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    public void renderOverlay()
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);

        EntityPlayer player = this.mc.thePlayer;
        MagicInfoStorage data = MagicInfoStorage.get(player);
        this.mc.renderEngine.bindTexture(this.texture);

        if(data != null){
            if(data.HasMagic()){
                double Prog = ((double)data.GetPlayerEnergy() / (double)data.GetPlayerMaxEnergy()) * 100;
                double Prog1 = ((double)data.GetPlayerXp() / (double)data.GetRequiredXp()) * 100;



                int x = width - (width / 4);
                int y = height - 14;

                int x1 = x;
                int y1 = y - 11;

                drawTexturedModalRect(x1, y1, 0, 0, 92, 11);
                drawTexturedModalRect(x1, y1, 0,33, (int)((Prog1 / 100) * 93), 11);

                drawTexturedModalRect(x, y, 0, 0, 92, 11);
                drawTexturedModalRect(x, y, 0,17, (int)((Prog / 100) * 93), 11);


                int xp = data.GetPlayerXp(), xpR = data.GetRequiredXp(), lev = data.GetPlayerLevel();
                drawCenteredString(mc.fontRenderer,  (EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC  + xp + EnumChatFormatting.WHITE) + "/" + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + xpR, x1 + 45, y1 + 2, 0xffffff);


                int lv = (int)data.GetPlayerEnergy(), lvm = (int)data.GetPlayerMaxEnergy();
                drawCenteredString(mc.fontRenderer,  (EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + lv + EnumChatFormatting.WHITE) + "/" + EnumChatFormatting.AQUA + EnumChatFormatting.ITALIC + lvm, x + 45, y + 2, 0xffffff);


            }
        }



        GL11.glDisable(GL11.GL_BLEND);
    }

}
