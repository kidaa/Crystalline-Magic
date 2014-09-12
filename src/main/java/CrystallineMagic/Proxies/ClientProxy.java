package CrystallineMagic.Proxies;

import CrystallineMagic.Entity.EntitySpellProjectile;
import CrystallineMagic.Entity.FX.EntityMagicEffectFX;
import CrystallineMagic.Entity.Render.EntitySpellProjectileRender;
import CrystallineMagic.Keybinds.Keybinds;
import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.Rendering.Items.PowerCrystalItemRender;
import CrystallineMagic.Rendering.TileEntitys.TileEntityPowerCrystalRender;
import CrystallineMagic.TileEntities.TileEntityPowerCrystal;
import MiscUtils.Register.KeyBind.KeybindRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {

    public static boolean HasValidInvisibilityArmor = false;

    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPowerCrystal.class, new TileEntityPowerCrystalRender());

        MinecraftForgeClient.registerItemRenderer(new ItemStack(ModBlocks.PowerCrystal).getItem(), new PowerCrystalItemRender());

        RenderingRegistry.registerEntityRenderingHandler(EntitySpellProjectile.class, new EntitySpellProjectileRender());

    }



    public void RegisterKeybindings(){
        KeybindRegistry.RegisterKeybind(Keybinds.KeyBindMagicInfo);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int addArmor(String armor){
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }

    public void MagicEffect(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul)
    {
        if (!doParticle()) {
            return;
        }

        EntityMagicEffectFX effectFX = new EntityMagicEffectFX(world, x, y, z, size, r, g, b, maxAgeMul);
        effectFX.motionZ = motionx;
        effectFX.motionY = motiony;
        effectFX.motionZ = motionz;

        Minecraft.getMinecraft().effectRenderer.addEffect(effectFX);
    }

    private boolean doParticle()
    {

        float chance = 1.0F;
        if (Minecraft.getMinecraft().gameSettings.particleSetting == 1) {
            chance = 0.6F;
        } else if (Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
            chance = 0.2F;
        }
        return Math.random() < chance;
    }



}
