package CrystallineMagic.Gui;

import CrystallineMagic.Container.ContainerSpellCreation;
import CrystallineMagic.TileEntities.TileEntitySpellCreationTable;
import CrystallineMagic.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiSpellCreationTable extends GuiContainer {

    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/SpellCreationGui.png");

    TileEntitySpellCreationTable tile;


    public GuiSpellCreationTable(InventoryPlayer InvPlayer, TileEntitySpellCreationTable tile) {
        super(new ContainerSpellCreation(InvPlayer, tile));
        this.xSize = 236;
        this.ySize = 190;
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("tile.spell_creation_table.name"), 8, 3, 4210752);

        fontRendererObj.drawString(StatCollector.translateToLocal("gui.spellcreation.components"), 48, 15, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.spellcreation.modifiers"), 124, 15, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        int Error = tile.ErrorCode;

        if(Error  > 0){
            drawCenteredString(fontRendererObj, EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.spellcreation.error." + Error), x + 130, y + 86, 0xFFFFFF);
        }



    }

}