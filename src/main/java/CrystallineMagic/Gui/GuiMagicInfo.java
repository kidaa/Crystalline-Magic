package CrystallineMagic.Gui;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Keybinds.Keybinds;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiMagicInfo extends GuiScreen{
    private final ResourceLocation Texture =new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/SoulInfoGui.png");

    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    MagicInfoStorage storage = MagicInfoStorage.get(player);

    public static final int xSizeOfTexture = 138;
    public static final int ySizeOfTexture = 147;


    protected void keyTyped(char ch, int key)
    {
        super.keyTyped(ch, key);

        if(key == Keybinds.KeyBindMagicInfo.getKeyCode()){
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }

    }

    @Override
    public void initGui(){
        super.initGui();
        buttonList.clear();

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        if(storage != null) {
            if (storage.HasMagic()) {
                if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {

                    if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                        buttonList.add(new GuiButton(1, posX + 110, posY + 95, 20, 20, "++"));
                        buttonList.add(new GuiButton(2, posX + 110, posY + 115, 20, 20, "++"));
                    }else{
                        buttonList.add(new GuiButton(1, posX + 110, posY + 95, 20, 20, "+"));
                        buttonList.add(new GuiButton(2, posX + 110, posY + 115, 20, 20, "+"));
                    }

                }


            }
        }
    }


    @Override
    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

        initGui();



        fontRendererObj.drawString(StatCollector.translateToLocal("gui.magicinfo.titel"), posX + 7, posY + 6, new Color(105, 105, 105).getRGB());
        fontRendererObj.drawString("---------------------", posX + 5, posY + 14, new Color(105, 105, 105).getRGB());


        if(storage != null) {


            if(storage.HasMagic()) {
                fontRendererObj.drawString(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.soulenergy").replace("$energy", (int) storage.GetPlayerEnergy() + ""), posX + 7, posY + 22, 0);
                fontRendererObj.drawString(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.maxsoulenergy").replace("$energy", (int) storage.GetPlayerMaxEnergy() + ""), posX + 7, posY + 32, 0);

                fontRendererObj.drawString(EnumChatFormatting.DARK_GREEN + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.soullevel").replace("$level", storage.GetPlayerLevel() + ""), posX + 7, posY + 50, 0);
                fontRendererObj.drawString(EnumChatFormatting.GOLD + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.currentexp").replace("$exp", storage.GetPlayerXp() + ""), posX + 7, posY + 60, 0);
                fontRendererObj.drawString(EnumChatFormatting.GOLD + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.requiredexp").replace("$exp", storage.GetRequiredXp() + ""), posX + 7, posY + 70, 0);


                if(Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
                    fontRendererObj.drawString(EnumChatFormatting.UNDERLINE + StatCollector.translateToLocal("gui.magicinfo.creativemode"), posX + 7, posY + 85, new Color(138, 138, 138).getRGB());

                    fontRendererObj.drawString(StatCollector.translateToLocal("gui.magicinfo.creativemode.increaselevel"), posX + 7, posY + 100, new Color(125, 125, 125).getRGB());
                    fontRendererObj.drawString(StatCollector.translateToLocal("gui.magicinfo.creativemode.increaseenergy"), posX + 7, posY + 120, new Color(125, 125, 125).getRGB());
                }

            }else{
                fontRendererObj.drawSplitString(EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal("gui.magicinfo.nomagic"), posX + 7, posY + 26, 150, 0);

            }


        }

        super.drawScreen(x, y, f);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }



    @Override
    protected void actionPerformed(GuiButton button){
        int id = button.id;

        if(storage != null && storage.HasMagic()) {

            if (id == 1) {
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                    storage.IncreasePlayerLevelWithEngUpdate(10);
                }else{
                    storage.IncreasePlayerLevelWithEngUpdate(1);
                }


            }else if(id == 2){
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                    storage.SetPlayerMaxEnergy(storage.GetPlayerMaxEnergy() + 1000);
                }else{
                    storage.SetPlayerMaxEnergy(storage.GetPlayerMaxEnergy() + 100);
                }
            }

            PacketHandler.sendToServer(new SyncPlayerPropsPacket(player), CrystMagic.Utils.channels);

        }
    }
}
