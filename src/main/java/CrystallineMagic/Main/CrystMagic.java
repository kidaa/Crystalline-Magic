package CrystallineMagic.Main;

import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUtils;
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
import CrystallineMagic.Utils.Effects.EffectUpdate;
import CrystallineMagic.Utils.Keybinds.Keybinds;
import CrystallineMagic.Utils.MagicalMaterialUtils;
import CrystallineMagic.Utils.Proxies.ServerProxy;
import CrystallineMagic.Utils.Ref;
import CrystallineMagic.Utils.Spells.EntitySpellProjectile;
import CrystallineMagic.Utils.Spells.SpellComponents.AntiGravity;
import CrystallineMagic.Utils.Spells.SpellComponents.Damage;
import CrystallineMagic.Utils.Spells.SpellComponents.Dig;
import CrystallineMagic.Utils.Spells.SpellComponents.ExplodeBlock;
import CrystallineMagic.Utils.Spells.SpellComponents.Fire;
import CrystallineMagic.Utils.Spells.SpellComponents.Gravity;
import CrystallineMagic.Utils.Spells.SpellComponents.Heal;
import CrystallineMagic.Utils.Spells.SpellComponents.LightningBolt;
import CrystallineMagic.Utils.Spells.SpellComponents.LowGravity;
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
import MiscUtils.Register.KeyBind.KeybindRegistry;
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
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;
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
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }



        Utils = new ChannelUtils(Ref.ModChannel, Ref.ModId);
        RegisterPackets();
        channels = Utils.getNewChannelHandler();



        config = new Config(event.getModConfigurationDirectory() + "");


        MagicalMaterialUtils.RegisterManualValues();
        MagicalMaterialUtils.RegisterAutomaticValues();

        RegisterSpellParts();

        ModItems.RegisterItems();
        ModBlocks.RegisterBlocks();

        CraftingRecipes.RegisterRecipes();

        proxy.registerRenderThings();


        KeybindRegistry.RegisterKeybind(Keybinds.KeyBindMagicInfo);


        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());
        MinecraftForge.EVENT_BUS.register(new OnPlayerRespawn());

        MinecraftForge.EVENT_BUS.register(new InvisibilityEvents());
        MinecraftForge.EVENT_BUS.register(new SpellCastEvent());

        MinecraftForge.EVENT_BUS.register(new EffectUpdate());


        FMLCommonHandler.instance().bus().register(new InvisibilityEvents());
        FMLCommonHandler.instance().bus().register(new MagicRecharge());
        FMLCommonHandler.instance().bus().register(new LevelUpHandeling());


        EntityRegistry.registerGlobalEntityID(EntitySpellProjectile.class, "SpellProjectile", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySpellProjectile.class, "SpellProjectile", 2, this, 128, 1, true);


        if(event.getSide() == Side.CLIENT){
            MinecraftForge.EVENT_BUS.register(new GuiOverlayMagicEnergy());
        }




        Collections.sort(SpellUtils.Types, new Comparator<SpellType>() {
            @Override
            public int compare(SpellType comp1, SpellType comp2) {

                return comp1.GetName().compareTo(comp2.GetName());
            }
        });



        Collections.sort(SpellUtils.Components, new Comparator<SpellComponent>() {
            @Override
            public int compare(SpellComponent  comp1, SpellComponent  comp2)
            {

                return  comp1.GetName().compareTo(comp2.GetName());
            }
        });



        Collections.sort(SpellUtils.Modifiers, new Comparator<SpellModifier>() {
            @Override
            public int compare(SpellModifier  comp1, SpellModifier  comp2)
            {

                return  comp1.GetName().compareTo(comp2.GetName());
            }
        });


    }

    public static Potion AntiGravityEffect, GravityEffect, LowGravityEffect;

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){

       this.AntiGravityEffect = (new CrystallineMagic.Utils.Effects.GravityEffect(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.antiGravity");
       this.GravityEffect = (new CrystallineMagic.Utils.Effects.GravityEffect(33, false, 0)).setIconIndex(0, 0).setPotionName("potion.gravity");
        this.LowGravityEffect = (new CrystallineMagic.Utils.Effects.GravityEffect(34, false, 0)).setIconIndex(0, 0).setPotionName("potion.lowGravity");

        GameRegistry.registerWorldGenerator(new ModWorlGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        proxy.RegisterChestLoot();

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

    public static void RegisterSpellParts(){

        SpellUtils.RegisterTypes(new Projectile());
        SpellUtils.RegisterTypes(new Self());
        SpellUtils.RegisterTypes(new Touch());
        SpellUtils.RegisterTypes(new Target());
        SpellUtils.RegisterTypes(new Area());


        SpellUtils.RegisterComponents(new Fire());
        SpellUtils.RegisterComponents(new Heal());
        SpellUtils.RegisterComponents(new Regen());
        SpellUtils.RegisterComponents(new Dig());
        SpellUtils.RegisterComponents(new Damage());
        SpellUtils.RegisterComponents(new ExplodeBlock());
        SpellUtils.RegisterComponents(new LightningBolt());
        SpellUtils.RegisterComponents(new AntiGravity());
        SpellUtils.RegisterComponents(new Gravity());
        SpellUtils.RegisterComponents(new LowGravity());

        SpellUtils.RegisterComponents(new SetTarget());
        SpellUtils.RegisterComponents(new TeleportTarget());
        SpellUtils.RegisterComponents(new TeleportRandom());


        SpellUtils.RegisterModifiers(new StrengthUpgrade());
        SpellUtils.RegisterModifiers(new CostDecreaser());
        SpellUtils.RegisterModifiers(new RangeExtender());
        SpellUtils.RegisterModifiers(new AreaIncludePlayer());


    }


}
