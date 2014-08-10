package CrystallineMagic.Gui;

import CrystallineMagic.Container.ContainerMagicalInfuser;
import CrystallineMagic.TileEntities.TileEntityMagicalInfuser;
import CrystallineMagic.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiMagicalInfuser extends GuiContainer {

    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase() , "textures/gui/MagicalInfuserGui.png");


    TileEntityMagicalInfuser tile;

    public GuiMagicalInfuser(InventoryPlayer InvPlayer, TileEntityMagicalInfuser tile) {
        super(new ContainerMagicalInfuser(InvPlayer, tile));
        this.tile = tile;
        this.xSize = 223;
        this.ySize = 226;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        double Prog = ((double)tile.GetProgress() / (double)tile.GetMaxProg()) * 100;
        double Energy = ((tile.GetStoredEnergy() / tile.GetMaxEnergy())) * 100;
        int en = (int)((Energy/100)*118);

        drawString(fontRendererObj, "Energy: " + EnumChatFormatting.AQUA + (int)tile.GetStoredEnergy() + "/" + (int)tile.GetMaxEnergy(), x + 70, y + 133, 0xffffff);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        drawTexturedModalRect(x + 135, y + 60, 226, 123, (int) ((Prog/100) * 24), 6);

        drawTexturedModalRect(x + 191, y + 207 - en + 1, 226, 1, 17, en);


    }

}
