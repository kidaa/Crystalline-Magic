package CrystallineMagic.Entity.FX;

import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMagicEffectFX extends EntityFX {


    public static final ResourceLocation particles = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/items/MagicEffect.png");


    public EntityMagicEffectFX(World world, double d, double d1, double d2, float size, float red, float green, float blue, float maxAgeMul)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);

        setParticleIcon(ModItems.SpellIconItem.getIconFromDamage(0));

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


        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;


    }


    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {



        Minecraft.getMinecraft().renderEngine.bindTexture(particles);

        float agescale = 0.0F;
        agescale = this.particleAge / this.moteHalfLife;
        if (agescale > 1.0F) {
            agescale = 2.0F - agescale;
        }
        this.particleScale = (this.moteParticleScale * agescale);

        float f10 = 0.5F * this.particleScale;
        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);


        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 0.5F);
        tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, 0.0D, 1.0D);
        tessellator.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, 1.0D, 1.0D);
        tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, 1.0D, 0.0D);
        tessellator.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, 0.0D, 0.0D);


    }


    @Override
    public int getFXLayer() {
        return 1;
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

