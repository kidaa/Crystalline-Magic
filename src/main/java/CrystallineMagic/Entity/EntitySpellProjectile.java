package CrystallineMagic.Entity;

import CrystallineApi.Spells.SpellComponent;
import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Utils.MagicEffects;
import MiscUtils.Handlers.ParticleHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class EntitySpellProjectile extends Entity implements IProjectile
{
    EntityPlayer Shooter;
    public SpellComponent[] Components;
    ParticleHelper helper;
    ItemStack stack;

    private int x = -1;
    private int y = -1;
    private int z = -1;
    private Block field_145790_g;

    private int inData;
    private boolean inGround;

    public Entity shootingEntity;
    private int ticksInAir;


    public EntitySpellProjectile(World p_i1753_1_)
    {
        super(p_i1753_1_);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);

        SpawnParticles();
    }

    public EntitySpellProjectile(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_)
    {
        super(p_i1754_1_);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(p_i1754_2_, p_i1754_4_, p_i1754_6_);
        this.yOffset = 0.0F;

        SpawnParticles();
    }

    public EntitySpellProjectile(World p_i1755_1_, EntityPlayer p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
    {
        super(p_i1755_1_);
        this.renderDistanceWeight = 10.0D;
        this.Shooter = p_i1755_2_;

        SpawnParticles();

        this.posY = p_i1755_2_.posY + (double)p_i1755_2_.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_i1755_3_.posX - p_i1755_2_.posX;
        double d1 = p_i1755_3_.boundingBox.minY + (double)(p_i1755_3_.height / 3.0F) - this.posY;
        double d2 = p_i1755_3_.posZ - p_i1755_2_.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(p_i1755_2_.posX + d4, this.posY, p_i1755_2_.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
        }
        SpawnParticles();
    }

    public EntitySpellProjectile(World p_i1756_1_, EntityPlayer p_i1756_2_, float p_i1756_3_, SpellComponent[] Components, ItemStack stack)
    {
        super(p_i1756_1_);
        this.renderDistanceWeight = 10.0D;
        this.Shooter = p_i1756_2_;
        this.stack = stack;


        this.Components = Components;
        this.helper = new ParticleHelper(worldObj, CrystMagic.config.CanSpawnParticles());


        SpawnParticles();


        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(p_i1756_2_.posX, p_i1756_2_.posY + (double)p_i1756_2_.getEyeHeight(), p_i1756_2_.posZ, p_i1756_2_.rotationYaw, p_i1756_2_.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
        SpawnParticles();

    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));

        SpawnParticles();
    }


    public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
    {

        SpawnParticles();

        float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_1_ *= (double)p_70186_7_;
        p_70186_3_ *= (double)p_70186_7_;
        p_70186_5_ *= (double)p_70186_7_;
        this.motionX = p_70186_1_;
        this.motionY = p_70186_3_;
        this.motionZ = p_70186_5_;
        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);


       SpawnParticles();
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        SpawnParticles();

        this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        this.setRotation(p_70056_7_, p_70056_8_);
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        SpawnParticles();

        this.motionX = p_70016_1_;
        this.motionY = p_70016_3_;
        this.motionZ = p_70016_5_;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }

        SpawnParticles();
    }


    public void onEntityUpdate()
    {

        SpawnParticles();

        super.onEntityUpdate();
    }

    public void onUpdate()
    {

        onEntityUpdate();

        super.onUpdate();


        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }




        Block block = this.worldObj.getBlock(this.x, this.y, this.z);

        if (block.getMaterial() != Material.air)
        {
            block.setBlockBoundsBasedOnState(this.worldObj, this.x, this.y, this.z);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.x, this.y, this.z);

            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }



        if(ticksInAir >= 40)
            this.setDead();


            ++this.ticksInAir;


        int Side = 0;

        if(ticksInAir > 1) {
            Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
            vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null) {
                vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }


            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int i;
            float f1;


            for (i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity) list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
                    f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double) f1, (double) f1, (double) f1);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (movingobjectposition1 != null) {
                        double d1 = vec31.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.entityHit;

                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer) || entityplayer == this.Shooter) {

                    movingobjectposition = null;
                }
            }

            float f2;
            float f4;


            if (movingobjectposition != null) {
                if (movingobjectposition.entityHit != null) {

                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    if (Components != null && Components.length > 0)
                        for (int g = 0; g < Components.length; g++) {
                            Components[g].OnUseOnEntity(stack, worldObj, movingobjectposition.entityHit, Shooter);
                        }

                    this.setDead();

                } else {

                    this.x = movingobjectposition.blockX;
                    this.y = movingobjectposition.blockY;
                    this.z = movingobjectposition.blockZ;
                    this.field_145790_g = this.worldObj.getBlock(this.x, this.y, this.z);
                    this.inData = this.worldObj.getBlockMetadata(this.x, this.y, this.z);
                    this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.posX)) / 2;
                    this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.posY)) / 2;
                    this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.posZ)) / 2;
                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
                    this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
                    this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
                    this.inGround = true;
                    Side = movingobjectposition.sideHit;

                    if (this.field_145790_g.getMaterial() != Material.air) {
                        this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.x, this.y, this.z, this);
                    }


                }

            }
        }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);


            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f3 = 0.99F;




            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;

        SpawnParticles();

            this.setPosition(this.posX, this.posY, this.posZ);

        SpawnParticles();

        this.func_145775_I();

        if(inGround){
                                if(Components != null && Components.length > 0)
                                for (int g = 0; g < Components.length; g++)
                                    Components[g].OnUseOnBlock(stack, worldObj, x, y, z, field_145790_g, Shooter, Side);

                            this.setDead();


        }

    }

    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setString("PL", Shooter.getCommandSenderName());

        nbt.setShort("xTile", (short)this.x);
        nbt.setShort("yTile", (short)this.y);
        nbt.setShort("zTile", (short)this.z);
        nbt.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
        nbt.setByte("inData", (byte)this.inData);
        nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));

        stack.writeToNBT(nbt);

    }


    public void readEntityFromNBT(NBTTagCompound nbt)
    {

        Shooter = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(nbt.getString("PL"));

        this.x = nbt.getShort("xTile");
        this.y = nbt.getShort("yTile");
        this.z = nbt.getShort("zTile");
        this.field_145790_g = Block.getBlockById(nbt.getByte("inTile") & 255);
        this.inData = nbt.getByte("inData") & 255;
        this.inGround = nbt.getByte("inGround") == 1;

        stack = ItemStack.loadItemStackFromNBT(nbt);


    }


    protected boolean canTriggerWalking()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }



    public boolean canAttackWithItem()
    {
        return false;
    }


    public void SpawnParticles(){


        if(Components != null && Components.length > 0){
            Color c = new Color(255,255,255);

            if(Components[0].GetComponentColor() != null)
                c = Components[0].GetComponentColor();

            MagicEffects.SpawnMagicEffect(worldObj, (posX - 0.8) + motionX, posY + motionY, (posZ - 0.8) + motionZ, 2, 1, c);
        }

    }

}