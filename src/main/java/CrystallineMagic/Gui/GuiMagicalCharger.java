package CrystallineMagic.Gui;

import CrystallineMagic.Container.ContainerMagicalCharger;
import CrystallineMagic.TileEntities.TileEntityMagicalEnergyRecharger;
import CrystallineMagic.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiMagicalCharger extends GuiContainer {

    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/MagicalChargerGui.png");


    TileEntityMagicalEnergyRecharger tile;

    public GuiMagicalCharger(InventoryPlayer InvPlayer, TileEntityMagicalEnergyRecharger tile) {
        super(new ContainerMagicalCharger(InvPlayer, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("tile.magical_energy_re-charger.name"), 7, 3, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        double Prog = (tile.CurrentItemPower / tile.CurrentItemMaxPower) * 100;
        double Energy = ((tile.GetStoredEnergy() / tile.GetMaxEnergy())) * 100;
        int en = (int)((Energy/100)*73);

        drawString(fontRendererObj, "Energy: " + EnumChatFormatting.AQUA + (int)tile.GetStoredEnergy() + "/" + (int)tile.GetMaxEnergy() + EnumChatFormatting.RESET, x + 65, y + 70, 0xffffff);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        drawTexturedModalRect(x + 72, y + 54, 182, 80, (int) ((Prog/100) * 32), 5);

        drawTexturedModalRect(x + 152, y + 78 - en + 1, 182, 5, 17, en);


    }

}
