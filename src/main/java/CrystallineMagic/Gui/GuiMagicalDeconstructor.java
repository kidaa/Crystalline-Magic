package CrystallineMagic.Gui;

import CrystallineMagic.Container.ContainerMagicalDeconstructor;
import CrystallineMagic.TileEntities.TileEntityMagicalDecontructor;
import CrystallineMagic.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiMagicalDeconstructor extends GuiContainer {

    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/MagicalDeconstructorGui.png");


    TileEntityMagicalDecontructor tile;

    public GuiMagicalDeconstructor(InventoryPlayer InvPlayer, TileEntityMagicalDecontructor tile) {
        super(new ContainerMagicalDeconstructor(InvPlayer, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("tile.magical_material_de-constructor.name"), 0, 3, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        double Prog = ((double)tile.GenTimer / (double)tile.GenTime) * 100;
        double Energy = ((tile.GetStoredEnergy() / tile.GetMaxEnergy())) * 100;
        int en = (int) ((Energy / 100) * 73);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        drawTexturedModalRect(x + 44, y + 31, 0, 168, (int) ((Prog / 100) * 96), 12);
        drawTexturedModalRect(x + 152, y + 78 - en + 1, 182, 5, 17, en);



        drawString(fontRendererObj, "Energy: " + EnumChatFormatting.AQUA + (int) tile.GetStoredEnergy() + "/" + (int) tile.GetMaxEnergy(), x + 8, y + 60, 0xffffff);


    }

}