package CrystallineMagic.Rendering.Items;

import CrystallineMagic.Utils.Ref;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PowerCrystalItemRender implements IItemRenderer
{

    private final IModelCustom model;
    ResourceLocation rs;

    public PowerCrystalItemRender() {
        model = AdvancedModelLoader.loadModel(new ResourceLocation(Ref.ModId.toLowerCase(), "Models/BigCrystal.obj"));
        rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/BigCrystalAlphaTexture.png");
    }



    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
                return true;
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslated(0.3, 0.2, 0.3);

        if(type == ItemRenderType.INVENTORY){
            GL11.glScalef(0.50F, 0.50F, 0.50F);
            GL11.glTranslated(0.3, -0.6, 0.3);

        }else {
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glTranslated(0.3, 0.2, 0.3);
    }


        Minecraft.getMinecraft().renderEngine.bindTexture(rs);

        GL11.glPushMatrix();
        GL11.glColor4f(0.01F, 0, 0, 1F);
        model.renderAll();


        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}
