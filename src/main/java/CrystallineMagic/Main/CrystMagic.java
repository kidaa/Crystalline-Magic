package CrystallineMagic.Main;

import CrystallineMagic.Event.EntityConstructingEvent;
import CrystallineMagic.Event.InvisibilityEvents;
import CrystallineMagic.Event.JoinWorld;
import CrystallineMagic.Event.LevelUpHandeling;
import CrystallineMagic.Event.MagicRecharge;
import CrystallineMagic.Event.OnPlayerRespawn;
import CrystallineMagic.Event.SpellCastEvent;
import CrystallineMagic.Gui.GuiHandler;
import CrystallineMagic.Gui.Overlay.GuiOverlayMagicEnergy;
import CrystallineMagic.Packets.ClientSyncInvisPlayers;
import CrystallineMagic.Packets.MagicReciveParticleEffects;
import CrystallineMagic.Packets.ServerSyncInvisPlayers;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Utils.Config;
import CrystallineMagic.Utils.CraftingRecipes;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.MagicalMaterialUtils;
import CrystallineMagic.Utils.Proxies.ServerProxy;
import CrystallineMagic.Utils.Ref;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.SpellComponents.Damage;
import CrystallineMagic.Utils.Spells.SpellComponents.Dig;
import CrystallineMagic.Utils.Spells.SpellComponents.Fire;
import CrystallineMagic.Utils.Spells.SpellComponents.Heal;
import CrystallineMagic.Utils.Spells.SpellComponents.Regen;
import CrystallineMagic.Utils.Spells.SpellComponents.SetTarget;
import CrystallineMagic.Utils.Spells.SpellComponents.TeleportRandom;
import CrystallineMagic.Utils.Spells.SpellComponents.TeleportTarget;
import CrystallineMagic.Utils.Spells.SpellModifiers.AreaIncludePlayer;
import CrystallineMagic.Utils.Spells.SpellModifiers.CostDecreaser;
import CrystallineMagic.Utils.Spells.SpellModifiers.RangeExtender;
import CrystallineMagic.Utils.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Utils.Spells.SpellTypes.Area;
import CrystallineMagic.Utils.Spells.SpellTypes.Projectile;
import CrystallineMagic.Utils.Spells.SpellTypes.Self;
import CrystallineMagic.Utils.Spells.SpellTypes.Target;
import CrystallineMagic.Utils.Spells.SpellTypes.Touch;
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

    public static CreativeTabs SpellPart = new CreativeTabs("tabModSpell") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return config.GetCheckedItem(ModItems.Spell);
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

        RegisterComponents();

        ModItems.RegisterItems();
        ModBlocks.RegisterBlocks();

        CraftingRecipes.RegisterRecipes();

        proxy.registerRenderThings();

        proxy.RegisterChestLoot();

        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());
        MinecraftForge.EVENT_BUS.register(new OnPlayerRespawn());

        MinecraftForge.EVENT_BUS.register(new InvisibilityEvents());
        MinecraftForge.EVENT_BUS.register(new SpellCastEvent());


        FMLCommonHandler.instance().bus().register(new InvisibilityEvents());
        FMLCommonHandler.instance().bus().register(new MagicRecharge());
        FMLCommonHandler.instance().bus().register(new LevelUpHandeling());


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

    public static void RegisterComponents(){

        MagicUtils.RegisterTypes(new Projectile());
        MagicUtils.RegisterTypes(new Self());
        MagicUtils.RegisterTypes(new Touch());
        MagicUtils.RegisterTypes(new Target());
        MagicUtils.RegisterTypes(new Area());


        MagicUtils.RegisterComponents(new Fire());
        MagicUtils.RegisterComponents(new Heal());
        MagicUtils.RegisterComponents(new Regen());
        MagicUtils.RegisterComponents(new Dig());
        MagicUtils.RegisterComponents(new Damage());

        MagicUtils.RegisterComponents(new SetTarget());
        MagicUtils.RegisterComponents(new TeleportTarget());
        MagicUtils.RegisterComponents(new TeleportRandom());



        MagicUtils.RegisterModifiers(new StrengthUpgrade());
        MagicUtils.RegisterModifiers(new CostDecreaser());
        MagicUtils.RegisterModifiers(new RangeExtender());
        MagicUtils.RegisterModifiers(new AreaIncludePlayer());

    }


}
