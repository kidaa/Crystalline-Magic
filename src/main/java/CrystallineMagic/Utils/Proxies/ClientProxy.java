package CrystallineMagic.Utils.Proxies;

import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Rendering.PowerCrystalItemRender;
import CrystallineMagic.Rendering.TileEntityPowerCrystalRender;
import CrystallineMagic.TileEntities.TileEntityPowerCrystal;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {

    public static boolean HasValidInvisibilityArmor = false;

    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPowerCrystal.class, new TileEntityPowerCrystalRender());

        RenderingRegistry.registerEntityRenderingHandler(EntitySpellProjectile.class, new RenderSnowball(ModItems.SpellIconItem));

        MinecraftForgeClient.registerItemRenderer(new ItemStack(ModBlocks.PowerCrystal).getItem(), new PowerCrystalItemRender());

    }



    @SideOnly(Side.CLIENT)
    @Override
    public int addArmor(String armor){
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }


    public void registerKeyBindings(){


    }

}
