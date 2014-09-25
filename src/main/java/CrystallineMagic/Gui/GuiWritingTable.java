package CrystallineMagic.Gui;

import CrystallineApi.Elements.ElementBase;
import CrystallineApi.Elements.ElementRegistry;
import CrystallineApi.Recipes.WritingRecipe;
import CrystallineApi.Recipes.WritingRecipeHandler;
import CrystallineMagic.Container.ContainerWriting;
import CrystallineMagic.Gui.GuiObjects.Buttons.ElementButton;
import CrystallineMagic.Gui.GuiObjects.Buttons.WritingClearGrid;
import CrystallineMagic.Gui.GuiObjects.Buttons.WritingRecipeLoadButton;
import CrystallineMagic.Items.WritingRecipePage;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.CreateWriting;
import CrystallineMagic.TileEntities.TileEntitySpellWritingTable;
import CrystallineMagic.Utils.Ref;
import MiscUtils.Network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiWritingTable  extends GuiContainer {

    private final ResourceLocation Texture = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/gui/SpellWritingGui.png");


    /**
     * Recipe location slots
     * 1 = 78, 16
     * 2 = 41, 89
     * 3 = 115, 89
     * 4 = 41, 15
     * 5 = 115, 15
     * 6 = 78, 88
     * 7 = 42, 53
     * 8 = 114, 52
     */

    public int[] GetPos(int i){

        if (i == 1){
          return new int[]{78, 16};
        }else  if (i == 2){
            return new int[]{41, 89};
        }else  if (i == 3){
            return new int[]{115, 89};
        }else  if (i == 4){
            return new int[]{41, 15};
        }else  if (i == 5){
            return new int[]{115, 15};
        }else  if (i == 6){
            return new int[]{78, 88};
        }else  if (i == 7){
            return new int[]{42, 53};
        }else  if (i == 8){
            return new int[]{114, 52};
        }


        return new int[]{0,0};
    }

    ElementButton[] Elements = new ElementButton[8];
    WritingRecipe res = null;

    TileEntitySpellWritingTable tile;

    GuiButton Create;
    GuiButton Up, Down;
    WritingRecipeLoadButton Load;
    WritingClearGrid Clear;

    ItemStack stackReq;


    int posMin = 0, posMax = 8;
    int Els = ElementRegistry.Elements.size();

    boolean Valid = false;


    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;


        Create = new GuiButton(0, x + 63, y + 111, 46, 20, EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("gui.writingspell.create") + EnumChatFormatting.RESET);
        Up = new GuiButton(1, x + 171, y + 5, 38, 20, "/" + ("\\"));
        Down = new GuiButton(2, x + 171, y + 97, 38, 20, "\\" + "/");

        Load = new WritingRecipeLoadButton(Els * 2, x + 9, y + 75);
        Clear = new WritingClearGrid(Els * 2 + 1, x + 9, y + 95);


        if((Els < 8)) {
            Up.enabled = false;
            Down.enabled = false;

        }else {

            if (posMax < Els){
                Down.enabled = true;
            }else{
                Down.enabled = false;
            }

            if(posMin > 0){
                Up.enabled = true;

            }else{
                Up.enabled = false;
            }

        }

        Load.enabled = false;

        if(tile.getStackInSlot(1) != null && tile.getStackInSlot(1).getItem() instanceof WritingRecipePage){
            WritingRecipePage res = (WritingRecipePage)tile.getStackInSlot(1).getItem();
            Load.enabled = res.GetStoredRecipe(tile.getStackInSlot(1)) != null;


        }

        Create.enabled = Valid;



        buttonList.add(Up);
        buttonList.add(Down);
        buttonList.add(Create);
        buttonList.add(Load);
        buttonList.add(Clear);


        for(int i = posMin; i < posMax; i++){

            if(Els > i)
            buttonList.add(new ElementButton(10 + i, (x + 172 + ((i & 1) != 0 ? 20 : 0)), y + 26 + (((i - posMin) / 2) * 18), ElementRegistry.Elements.get(i)));
        }


        for(ElementButton bt : Elements){
            if(bt != null && bt.element != null){

                bt.xPosition = x + GetPos((bt.id - (Els + 50) + 1))[0];
                bt.yPosition = y + GetPos((bt.id - (Els + 50) + 1))[1];

                buttonList.add(bt);
            }
        }


    }



    @Override
    protected void actionPerformed(GuiButton bt) {
        int id = bt.id;


        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        if(bt instanceof WritingRecipeLoadButton){
            if(tile.getStackInSlot(1) != null && tile.getStackInSlot(1).getItem() instanceof WritingRecipePage){

                ItemStack stack = tile.getStackInSlot(1);
                WritingRecipePage red = (WritingRecipePage)tile.getStackInSlot(1).getItem();

                if(red.GetStoredRecipe(stack) != null){

                    WritingRecipe res = red.GetStoredRecipe(stack);


                    if(tile.getStackInSlot(0) != null){
                        if(tile.getStackInSlot(0).getItem() == res.InputItem.getItem()){
                            stackReq = res.InputItem;

                        }
                    }else if (tile.getStackInSlot(0) == null){
                        stackReq = res.InputItem;
                    }else{
                        stackReq = null;
                    }

                    for(int i = 0; i < Elements.length; i++){
                        Elements[i] = null;

                    }

                    for(ElementBase r : res.Elements){

                        for(int i = 0; i < Elements.length; i++){
                            if(Elements[i] == null){
                                Elements[i] = new ElementButton(i + (Els + 50), x + GetPos((i+1))[0], y + GetPos((i+1))[1], r);
                                break;
                            }

                        }
                    }


                }

            }


        }


        if(bt instanceof WritingClearGrid){

                    for(int i = 0; i < Elements.length; i++){
                        Elements[i] = null;

                    }

            stackReq = null;

        }

        if(bt instanceof ElementButton){
            if(id < Els + 50){
                ElementButton el = (ElementButton)bt;



                for(int i = 0; i < 8; i++){
                    if(Elements[i] == null){
                        Elements[i] = new ElementButton(i + (Els + 50), x + GetPos((i+1))[0], y + GetPos((i+1))[1], el.element);
                        break;

                    }
                }



            }else{


                int g = id - (Els + 50);


                if(Elements.length >= g){
                    if(Elements[g] != null){
                        Elements[g] = null;
                    }
                }
            }

        }


        if(bt.enabled) {
            if (id == 2) {
                if(posMax + 2 <= Els) {
                    posMin += 2;
                    posMax += 2;

                }else if (posMax + 1 <= Els){

                    posMin += 2;
                    posMax += 2;
                }

            }else if (id == 1){
                if(posMin - 2 >= 0) {
                    posMin -= 2;
                    posMax -= 2;

                }else if(posMin - 1 >= 0) {
                    posMin -= 1;
                    posMax -= 1;
                }

            }else if (id == 0 && Valid && res != null){

                PacketHandler.sendToServer(new CreateWriting(tile.xCoord, tile.yCoord, tile.zCoord, res.Elements, res.InputItem), CrystMagic.Utils.channels);

                stackReq = null;

                for(int i = 0; i < Elements.length; i++){
                   Elements[i] = null;
                }
            }
        }

    }


        public GuiWritingTable(InventoryPlayer InvPlayer, TileEntitySpellWritingTable tile) {
        super(new ContainerWriting(InvPlayer, tile));
        this.xSize = 216;
        this.ySize = 217;
        this.tile = tile;

            if(Els < 8)
                posMax = Els;

            else
                posMax = 8;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("tile.spellwritingtable.name"), 8, 3, 4210752);

    }



    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);


        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;


        if(stackReq != null && tile.getStackInSlot(0) != null && tile.getStackInSlot(0).getItem() != stackReq.getItem() || stackReq != null && tile.getStackInSlot(0) == null){

            GL11.glPushMatrix();

            float a = 0.4F;
            float c = 0.8F;

            GL11.glColor4f(c,c,c, a);

            itemRender.renderWithColor = false;

            itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), stackReq, x + 78, y + 52);

            itemRender.renderWithColor = true;


            GL11.glPopMatrix();

        }

        initGui();


        int h = 0;

        for(ElementButton b : Elements){
            if(b != null && b.element != null)
                h += 1;
        }


        ElementBase[] ResBes = new ElementBase[h];

        for(int i = 0; i < Elements.length; i++){
            boolean t = false;
            if(Elements[i] != null){

                if(t) {
                    t = false;
                    continue;
                }

                for(int g = 0; g < h; g++){

                    if(ResBes[g] == null) {
                        ResBes[g] = ElementRegistry.GetElement(Elements[i].element.Id);

                        t = true;
                        break;
                    }
                }

            }
        }
        if(tile.getStackInSlot(0) != null){
            WritingRecipe res = WritingRecipeHandler.GetRecipe(tile.getStackInSlot(0), ResBes);


            if(res != null){
                Valid = true;
                this.res = res;

            }else{
                Valid = false;
                this.res = null;
            }

        }else{
            Valid = false;
            this.res = null;
        }

    }



    @Override
    public void drawScreen(int x, int y, float f) {
        this.drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());

        super.drawScreen(x, y, f);



        for (int i = 0; i < buttonList.size(); i++) {


            if (buttonList.get(i) instanceof ElementButton) {

                GuiButton btn = (GuiButton) buttonList.get(i);
                if (btn.func_146115_a()) {
                    ElementButton el = (ElementButton) btn;

                    String[] desc = {el.element.Name};

                    List temp = Arrays.asList(desc);
                    drawHoveringText(temp, x, y, fontRendererObj);
                }


            }else if(buttonList.get(i) instanceof WritingRecipeLoadButton){
                    GuiButton btn = (GuiButton) buttonList.get(i);

                    if (btn.func_146115_a()){


                        if(tile.getStackInSlot(1) != null && tile.getStackInSlot(1).getItem() instanceof WritingRecipePage){

                            ItemStack stack = tile.getStackInSlot(1);

                            WritingRecipePage itm = (WritingRecipePage)stack.getItem();

                            if(itm.GetStoredRecipe(stack) != null){


                                List temp = new ArrayList();
                                temp.add(StatCollector.translateToLocal("gui.writingspell.load").replace("$load", itm.GetStoredRecipe(stack).Output.getDisplayName()));

                                drawHoveringText(temp, x, y, fontRendererObj);

                            }


                        }



                    }




            }else if(buttonList.get(i) instanceof WritingClearGrid){
                GuiButton btn = (GuiButton) buttonList.get(i);

                if (btn.func_146115_a()) {


                    List temp = new ArrayList();
                    temp.add(StatCollector.translateToLocal("gui.writingspell.clear"));

                    drawHoveringText(temp, x, y, fontRendererObj);

                }



            }else if (buttonList.get(i) instanceof GuiButton){

                GuiButton btn = (GuiButton) buttonList.get(i);
                if (btn.func_146115_a() && btn.id == 0 && btn.enabled && res != null) {

                    List temp = new ArrayList();
                    temp.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("gui.writingspell.create"));

                    temp.add(res.Output.getDisplayName());

                    res.Output.getItem().addInformation(res.Output, Minecraft.getMinecraft().thePlayer, temp, false);


                    drawHoveringText(temp, x, y, fontRendererObj);
                }

            }
        }
    }
}
