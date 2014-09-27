package CrystallineMagic.Rendering.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class EnergyBatteryModel extends ModelBase
{
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer CoreSmall;
    ModelRenderer CoreBig;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape13;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;
    ModelRenderer Shape33;
    ModelRenderer Shape34;
    ModelRenderer Shape35;
    ModelRenderer Shape36;

    public EnergyBatteryModel()
    {
        textureWidth = 128;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 123, 0);
        Shape1.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape1.setRotationPoint(-8F, 8F, -8F);
        Shape1.setTextureSize(128, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 123, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape2.setRotationPoint(-8F, 8F, 7F);
        Shape2.setTextureSize(128, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 123, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape3.setRotationPoint(7F, 8F, 7F);
        Shape3.setTextureSize(128, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 123, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape4.setRotationPoint(7F, 8F, -8F);
        Shape4.setTextureSize(128, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 87, 0);
        Shape5.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape5.setRotationPoint(-7F, 8F, -8F);
        Shape5.setTextureSize(128, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 54, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape6.setRotationPoint(-8F, 8F, -7F);
        Shape6.setTextureSize(128, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 54, 0);
        Shape7.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape7.setRotationPoint(7F, 8F, -7F);
        Shape7.setTextureSize(128, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 87, 0);
        Shape8.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape8.setRotationPoint(-7F, 8F, 7F);
        Shape8.setTextureSize(128, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 54, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape9.setRotationPoint(7F, 23F, -7F);
        Shape9.setTextureSize(128, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 87, 0);
        Shape10.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape10.setRotationPoint(-7F, 23F, 7F);
        Shape10.setTextureSize(128, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 87, 0);
        Shape11.addBox(0F, 0F, 0F, 14, 1, 1);
        Shape11.setRotationPoint(-7F, 23F, -8F);
        Shape11.setTextureSize(128, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 54, 0);
        Shape12.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape12.setRotationPoint(-8F, 23F, -7F);
        Shape12.setTextureSize(128, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        CoreSmall = new ModelRenderer(this, 40, 16);
        CoreSmall.addBox(0F, 0F, 0F, 8, 8, 8);
        CoreSmall.setRotationPoint(-4F, 12F, -4F);
        CoreSmall.setTextureSize(128, 32);
        CoreSmall.mirror = true;
        setRotation(CoreSmall, 0F, 0F, 0F);
        CoreBig = new ModelRenderer(this, 0, 12);
        CoreBig.addBox(0F, 0F, 0F, 10, 10, 10);
        CoreBig.setRotationPoint(-5F, 11F, -5F);
        CoreBig.setTextureSize(128, 32);
        CoreBig.mirror = true;
        setRotation(CoreBig, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 0, 0);
        Shape14.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape14.setRotationPoint(-7F, 8F, -7F);
        Shape14.setTextureSize(128, 32);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new ModelRenderer(this, 0, 0);
        Shape15.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape15.setRotationPoint(6F, 8F, -7F);
        Shape15.setTextureSize(128, 32);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 0, 0);
        Shape16.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape16.setRotationPoint(-7F, 8F, 6F);
        Shape16.setTextureSize(128, 32);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 0, 0);
        Shape17.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape17.setRotationPoint(6F, 8F, 6F);
        Shape17.setTextureSize(128, 32);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 0, 0);
        Shape13.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape13.setRotationPoint(6F, 9F, -8F);
        Shape13.setTextureSize(128, 32);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape18 = new ModelRenderer(this, 0, 0);
        Shape18.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape18.setRotationPoint(-7F, 9F, -8F);
        Shape18.setTextureSize(128, 32);
        Shape18.mirror = true;
        setRotation(Shape18, 0F, 0F, 0F);
        Shape19 = new ModelRenderer(this, 0, 0);
        Shape19.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape19.setRotationPoint(-8F, 9F, 6F);
        Shape19.setTextureSize(128, 32);
        Shape19.mirror = true;
        setRotation(Shape19, 0F, 0F, 0F);
        Shape20 = new ModelRenderer(this, 0, 0);
        Shape20.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape20.setRotationPoint(-8F, 9F, -7F);
        Shape20.setTextureSize(128, 32);
        Shape20.mirror = true;
        setRotation(Shape20, 0F, 0F, 0F);
        Shape21 = new ModelRenderer(this, 0, 0);
        Shape21.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape21.setRotationPoint(-7F, 9F, 7F);
        Shape21.setTextureSize(128, 32);
        Shape21.mirror = true;
        setRotation(Shape21, 0F, 0F, 0F);
        Shape22 = new ModelRenderer(this, 0, 0);
        Shape22.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape22.setRotationPoint(6F, 9F, 7F);
        Shape22.setTextureSize(128, 32);
        Shape22.mirror = true;
        setRotation(Shape22, 0F, 0F, 0F);
        Shape23 = new ModelRenderer(this, 0, 0);
        Shape23.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape23.setRotationPoint(7F, 9F, 6F);
        Shape23.setTextureSize(128, 32);
        Shape23.mirror = true;
        setRotation(Shape23, 0F, 0F, 0F);
        Shape24 = new ModelRenderer(this, 0, 0);
        Shape24.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape24.setRotationPoint(7F, 9F, -7F);
        Shape24.setTextureSize(128, 32);
        Shape24.mirror = true;
        setRotation(Shape24, 0F, 0F, 0F);
        Shape25 = new ModelRenderer(this, 0, 0);
        Shape25.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape25.setRotationPoint(-7F, 23F, -7F);
        Shape25.setTextureSize(128, 32);
        Shape25.mirror = true;
        setRotation(Shape25, 0F, 0F, 0F);
        Shape26 = new ModelRenderer(this, 0, 0);
        Shape26.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape26.setRotationPoint(6F, 23F, -7F);
        Shape26.setTextureSize(128, 32);
        Shape26.mirror = true;
        setRotation(Shape26, 0F, 0F, 0F);
        Shape27 = new ModelRenderer(this, 0, 0);
        Shape27.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape27.setRotationPoint(6F, 23F, 6F);
        Shape27.setTextureSize(128, 32);
        Shape27.mirror = true;
        setRotation(Shape27, 0F, 0F, 0F);
        Shape28 = new ModelRenderer(this, 0, 0);
        Shape28.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape28.setRotationPoint(-7F, 23F, 6F);
        Shape28.setTextureSize(128, 32);
        Shape28.mirror = true;
        setRotation(Shape28, 0F, 0F, 0F);
        Shape29 = new ModelRenderer(this, 0, 0);
        Shape29.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape29.setRotationPoint(6F, 22F, -8F);
        Shape29.setTextureSize(128, 32);
        Shape29.mirror = true;
        setRotation(Shape29, 0F, 0F, 0F);
        Shape30 = new ModelRenderer(this, 0, 0);
        Shape30.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape30.setRotationPoint(-7F, 22F, 7F);
        Shape30.setTextureSize(128, 32);
        Shape30.mirror = true;
        setRotation(Shape30, 0F, 0F, 0F);
        Shape31 = new ModelRenderer(this, 0, 0);
        Shape31.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape31.setRotationPoint(6F, 22F, 7F);
        Shape31.setTextureSize(128, 32);
        Shape31.mirror = true;
        setRotation(Shape31, 0F, 0F, 0F);
        Shape32 = new ModelRenderer(this, 0, 0);
        Shape32.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape32.setRotationPoint(-7F, 22F, -8F);
        Shape32.setTextureSize(128, 32);
        Shape32.mirror = true;
        setRotation(Shape32, 0F, 0F, 0F);
        Shape33 = new ModelRenderer(this, 0, 0);
        Shape33.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape33.setRotationPoint(-8F, 22F, 6F);
        Shape33.setTextureSize(128, 32);
        Shape33.mirror = true;
        setRotation(Shape33, 0F, 0F, 0F);
        Shape34 = new ModelRenderer(this, 0, 0);
        Shape34.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape34.setRotationPoint(-8F, 22F, -7F);
        Shape34.setTextureSize(128, 32);
        Shape34.mirror = true;
        setRotation(Shape34, 0F, 0F, 0F);
        Shape35 = new ModelRenderer(this, 0, 0);
        Shape35.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape35.setRotationPoint(7F, 22F, -7F);
        Shape35.setTextureSize(128, 32);
        Shape35.mirror = true;
        setRotation(Shape35, 0F, 0F, 0F);
        Shape36 = new ModelRenderer(this, 0, 0);
        Shape36.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape36.setRotationPoint(7F, 22F, 6F);
        Shape36.setTextureSize(128, 32);
        Shape36.mirror = true;
        setRotation(Shape36, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean Powered)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape14.render(f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape13.render(f5);
        Shape18.render(f5);
        Shape19.render(f5);
        Shape20.render(f5);
        Shape21.render(f5);
        Shape22.render(f5);
        Shape23.render(f5);
        Shape24.render(f5);
        Shape25.render(f5);
        Shape26.render(f5);
        Shape27.render(f5);
        Shape28.render(f5);
        Shape29.render(f5);
        Shape30.render(f5);
        Shape31.render(f5);
        Shape32.render(f5);
        Shape33.render(f5);
        Shape34.render(f5);
        Shape35.render(f5);
        Shape36.render(f5);


        float u = 0.3F;
        float o = 1F;
        float a = 0.8F;
        float a2 = 0.9F;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);


        if(Powered)
            GL11.glColor4f(o,o,o, a2);
        else
            GL11.glColor4f(u,u,u, a2);

        CoreSmall.render(f5);



        if(Powered)
        GL11.glColor4f(o,o,o, a);
        else
            GL11.glColor4f(u,u,u, a);

        CoreBig.render(f5);



        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();


    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}