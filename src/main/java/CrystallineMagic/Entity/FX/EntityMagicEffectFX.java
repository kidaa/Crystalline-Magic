package CrystallineMagic.Entity.FX;

import CrystallineMagic.Utils.Ref;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.ArrayDeque;
import java.util.Queue;

public class EntityMagicEffectFX extends EntityFX {

    public static final ResourceLocation particles = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/items/MagicEffect.png");
    public static Queue<EntityMagicEffectFX> queuedRenders = new ArrayDeque();
    float f;
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;

    public EntityMagicEffectFX(World world, double d, double d1, double d2, float size, float red, float green, float blue, float maxAgeMul)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        this.particleRed = red;
        this.particleGreen = green;
        this.particleBlue = blue;
        this.particleGravity = 0.0F;
        this.motionX = (this.motionY = this.motionZ = 0.0D);
        this.particleScale *= size;
        this.moteParticleScale = this.particleScale;
        this.particleMaxAge = ((int)(28.0D / (Math.random() * 0.3D + 0.7D) * maxAgeMul));

        this.moteHalfLife = (this.particleMaxAge / 2);
        this.noClip = true;
        setSize(0.01F, 0.01F);
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().renderViewEntity;

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    public static void dispatchQueuedRenders(Tessellator tessellator)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
        Minecraft.getMinecraft().renderEngine.bindTexture(particles);

        tessellator.startDrawingQuads();
        for (EntityMagicEffectFX render : queuedRenders) {
            render.renderQueued(tessellator);
        }
        tessellator.draw();

        queuedRenders.clear();
    }

    private void renderQueued(Tessellator tessellator)
    {
        float agescale = 0.0F;
        agescale = this.particleAge / this.moteHalfLife;
        if (agescale > 1.0F) {
            agescale = 2.0F - agescale;
        }
        this.particleScale = (this.moteParticleScale * agescale);

        float f10 = 0.5F * this.particleScale;
        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * this.f - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * this.f - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * this.f - interpPosZ);

        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 0.5F);
        tessellator.addVertexWithUV(f11 - this.f1 * f10 - this.f4 * f10, f12 - this.f2 * f10, f13 - this.f3 * f10 - this.f5 * f10, 0.0D, 1.0D);
        tessellator.addVertexWithUV(f11 - this.f1 * f10 + this.f4 * f10, f12 + this.f2 * f10, f13 - this.f3 * f10 + this.f5 * f10, 1.0D, 1.0D);
        tessellator.addVertexWithUV(f11 + this.f1 * f10 + this.f4 * f10, f12 + this.f2 * f10, f13 + this.f3 * f10 + this.f5 * f10, 1.0D, 0.0D);
        tessellator.addVertexWithUV(f11 + this.f1 * f10 - this.f4 * f10, f12 - this.f2 * f10, f13 + this.f3 * f10 - this.f5 * f10, 0.0D, 0.0D);
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.f = f;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.f5 = f5;

        queuedRenders.add(this);

    }




    public void onUpdate()
    {

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            setDead();
        }
        this.motionY -= 0.04D * this.particleGravity;
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;
    }

    public void setGravity(float value)
    {
        this.particleGravity = value;
    }

    float moteParticleScale;
    int moteHalfLife;

}

