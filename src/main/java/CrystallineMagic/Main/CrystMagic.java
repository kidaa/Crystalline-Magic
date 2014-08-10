package CrystallineMagic.Main;

import CrystallineMagic.Event.EntityConstructingEvent;
import CrystallineMagic.Event.InvisibilityEvents;
import CrystallineMagic.Event.JoinWorld;
import CrystallineMagic.Event.MagicRecharge;
import CrystallineMagic.Event.OnPlayerRespawn;
import CrystallineMagic.Gui.GuiHandler;
import CrystallineMagic.Gui.Overlay.GuiOverlayMagicEnergy;
import CrystallineMagic.Packets.ClientSyncInvisPlayers;
import CrystallineMagic.Packets.MagicReciveParticleEffects;
import CrystallineMagic.Packets.ServerSyncInvisPlayers;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Utils.Config;
import CrystallineMagic.Utils.CraftingRecipes;
import CrystallineMagic.Utils.MagicalMaterialUtils;
import CrystallineMagic.Utils.Proxies.ServerProxy;
import CrystallineMagic.Utils.Ref;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.WorldGen.ModWorlGen;
import MiscUtils.Network.ChannelUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import java.util.EnumMap;



@Mod(modid = Ref.ModId, name = Ref.ModName, version = Ref.ModVersion, dependencies = "required-after:MiscUtils;after:NEI")
public class CrystMagic {



    @Mod.Instance(Ref.ModId)
    public static CrystMagic instance = new CrystMagic();

    @SidedProxy(clientSide = "CrystallineMagic.Utils.Proxies.ClientProxy", serverSide = "CrystallineMagic.Utils.Proxies.ServerProxy")
    public static ServerProxy proxy;

    public static Config config;

    public static EnumMap<Side, FMLEmbeddedChannel> channels;
    public static ChannelUtils Utils;

    public static CreativeTabs CreativeTab = new CreativeTabs("tabMod") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return config.GetCheckedItem(ModItems.SoulOrb);
        }

    };


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Utils = new ChannelUtils(Ref.ModChannel, Ref.ModId);
        RegisterPackets();
        channels = Utils.getNewChannelHandler();



        config = new Config(event.getModConfigurationDirectory() + "");


        MagicalMaterialUtils.RegisterManualValues();
        MagicalMaterialUtils.RegisterAutomaticValues();

        ModItems.RegisterItems();
        ModBlocks.RegisterBlocks();

        CraftingRecipes.RegisterRecipes();


        proxy.registerRenderThings();

        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());
        MinecraftForge.EVENT_BUS.register(new OnPlayerRespawn());

        MinecraftForge.EVENT_BUS.register(new InvisibilityEvents());
        FMLCommonHandler.instance().bus().register(new InvisibilityEvents());

        FMLCommonHandler.instance().bus().register(new MagicRecharge());


        EntityRegistry.registerGlobalEntityID(EntitySpellProjectile.class, "SpellProjectile", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySpellProjectile.class, "SpellProjectile", 2, this, 128, 1, true);


        if(event.getSide() == Side.CLIENT){
            MinecraftForge.EVENT_BUS.register(new GuiOverlayMagicEnergy());


        }

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){

        GameRegistry.registerWorldGenerator(new ModWorlGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

    }



    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {


    }


    public static void RegisterPackets(){


        Utils.handler.RegisterPacket(MagicReciveParticleEffects.class);
        Utils.handler.RegisterPacket(SyncPlayerPropsPacket.class);
        Utils.handler.RegisterPacket(ClientSyncInvisPlayers.class);
        Utils.handler.RegisterPacket(ServerSyncInvisPlayers.class);

    }


}
