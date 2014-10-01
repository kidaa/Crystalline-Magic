package CrystallineMagic.Main;

import CrystallineApi.Elements.ElementRegistry;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellModifier;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Effects.EffectUpdate;
import CrystallineMagic.Elements.*;
import CrystallineMagic.Elements.Void;
import CrystallineMagic.Entity.EntitySpellProjectile;
import CrystallineMagic.Event.EntityConstructingEvent;
import CrystallineMagic.Event.InvisibilityEvents;
import CrystallineMagic.Event.JoinWorld;
import CrystallineMagic.Event.LevelUpHandeling;
import CrystallineMagic.Event.MagicRecharge;
import CrystallineMagic.Event.MagicSendParticleEvent;
import CrystallineMagic.Event.OnPlayerRespawn;
import CrystallineMagic.Event.SpellCastEvent;
import CrystallineMagic.Gui.GuiHandler;
import CrystallineMagic.Gui.Overlay.GuiOverlayMagicEnergy;
import CrystallineMagic.Items.ModItemMagicArmor;
import CrystallineMagic.Packets.ClientSyncInvisPlayers;
import CrystallineMagic.Packets.CreateWriting;
import CrystallineMagic.Packets.MagicSendParticleSync;
import CrystallineMagic.Packets.ServerSyncInvisPlayers;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Proxies.ServerProxy;
import CrystallineMagic.Spells.SpellComponents.Damage;
import CrystallineMagic.Spells.SpellComponents.Dig;
import CrystallineMagic.Spells.SpellComponents.ExplodeBlock;
import CrystallineMagic.Spells.SpellComponents.Fire;
import CrystallineMagic.Spells.SpellComponents.Gravity;
import CrystallineMagic.Spells.SpellComponents.Heal;
import CrystallineMagic.Spells.SpellComponents.LightningBolt;
import CrystallineMagic.Spells.SpellComponents.LowGravity;
import CrystallineMagic.Spells.SpellComponents.Regen;
import CrystallineMagic.Spells.SpellComponents.SetTarget;
import CrystallineMagic.Spells.SpellComponents.TeleportRandom;
import CrystallineMagic.Spells.SpellComponents.TeleportTarget;
import CrystallineMagic.Spells.SpellModifiers.AreaIncludePlayer;
import CrystallineMagic.Spells.SpellModifiers.CostDecreaser;
import CrystallineMagic.Spells.SpellModifiers.RangeExtender;
import CrystallineMagic.Spells.SpellModifiers.StrengthUpgrade;
import CrystallineMagic.Spells.SpellTypes.Area;
import CrystallineMagic.Spells.SpellTypes.Projectile;
import CrystallineMagic.Spells.SpellTypes.Self;
import CrystallineMagic.Spells.SpellTypes.Target;
import CrystallineMagic.Spells.SpellTypes.Touch;
import CrystallineMagic.Utils.Config;
import CrystallineMagic.Utils.CraftingRecipes;
import CrystallineMagic.Utils.MagicalMaterialUtils;
import CrystallineMagic.Utils.RecipeUtils.WritingRecipePageCreation;
import CrystallineMagic.Utils.Ref;
import CrystallineMagic.WorldGen.ModWorlGen;
import MiscUtils.Network.ChannelUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.RecipeSorter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;



@Mod(modid = Ref.ModId, name = Ref.ModName, version = Ref.ModVersion, dependencies = "required-after:MiscUtils;after:NEI")
public class CrystMagic {


    @Mod.Instance(Ref.ModId)
    public static CrystMagic instance = new CrystMagic();

    @SidedProxy(clientSide = "CrystallineMagic.Proxies.ClientProxy", serverSide = "CrystallineMagic.Proxies.ServerProxy")
    public static ServerProxy proxy;

    public static Config config;

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



        config = new Config(event.getModConfigurationDirectory() + "");


        MagicalMaterialUtils.RegisterManualValues();
        MagicalMaterialUtils.RegisterAutomaticValues();

        RegisterSpellParts();
        RegisterElements();

        ModItems.RegisterItems();
        ModBlocks.RegisterBlocks();

        CraftingRecipes.RegisterRecipes();

        proxy.registerRenderThings();

        proxy.RegisterKeybindings();


        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());
        MinecraftForge.EVENT_BUS.register(new OnPlayerRespawn());

        MinecraftForge.EVENT_BUS.register(new InvisibilityEvents());
        MinecraftForge.EVENT_BUS.register(new SpellCastEvent());

        MinecraftForge.EVENT_BUS.register(new EffectUpdate());
        MinecraftForge.EVENT_BUS.register(new MagicSendParticleEvent());


        FMLCommonHandler.instance().bus().register(new InvisibilityEvents());
        FMLCommonHandler.instance().bus().register(new MagicRecharge());
        FMLCommonHandler.instance().bus().register(new LevelUpHandeling());


       RegisterEntities();


        if(event.getSide() == Side.CLIENT){
            MinecraftForge.EVENT_BUS.register(new GuiOverlayMagicEnergy());
        }


        RecipeSorter.register("CrystMagic:SpellPartCopy", WritingRecipePageCreation.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");


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

    public static Potion levitation, GravityEffect, LowGravityEffect;

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){

        levitation = (new CrystallineMagic.Effects.GravityEffect(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.levitation");
        GravityEffect = (new CrystallineMagic.Effects.GravityEffect(33, false, 0)).setIconIndex(0, 0).setPotionName("potion.gravity");
        this.LowGravityEffect = (new CrystallineMagic.Effects.GravityEffect(34, false, 0)).setIconIndex(0, 0).setPotionName("potion.lowGravity");

        GameRegistry.registerWorldGenerator(new ModWorlGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        proxy.RegisterChestLoot();

    }



    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {


    }


    public static void RegisterPackets(){

        Utils.handler.RegisterPacket(SyncPlayerPropsPacket.class);
        Utils.handler.RegisterPacket(ClientSyncInvisPlayers.class);
        Utils.handler.RegisterPacket(ServerSyncInvisPlayers.class);
        Utils.handler.RegisterPacket(MagicSendParticleSync.class);
        Utils.handler.RegisterPacket(CreateWriting.class);

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
        SpellUtils.RegisterComponents(new CrystallineMagic.Spells.SpellComponents.levitation());
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


    public void RegisterElements(){

        ElementRegistry.RegisterElement(new Air());
        ElementRegistry.RegisterElement(new Water());
        ElementRegistry.RegisterElement(new Earth());
        ElementRegistry.RegisterElement(new CrystallineMagic.Elements.Fire());

        ElementRegistry.RegisterElement(new Nature());
        ElementRegistry.RegisterElement(new Sky());
        ElementRegistry.RegisterElement(new Ice());
        ElementRegistry.RegisterElement(new Metal());

        ElementRegistry.RegisterElement(new Energy());
        ElementRegistry.RegisterElement(new Force());
        ElementRegistry.RegisterElement(new Lightning());

        ElementRegistry.RegisterElement(new Void());
        ElementRegistry.RegisterElement(new Alien());

        ElementRegistry.RegisterElement(new Light());
        ElementRegistry.RegisterElement(new Darkness());

        ElementRegistry.RegisterElement(new Control());
        ElementRegistry.RegisterElement(new CrystallineMagic.Elements.Target());
        ElementRegistry.RegisterElement(new Motion());
        ElementRegistry.RegisterElement(new Time());



    }

    public void RegisterEntities(){


        EntityRegistry.registerGlobalEntityID(EntitySpellProjectile.class, "SpellProjectile", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySpellProjectile.class, "SpellProjectile", 0, this, 128, 1, true);


    }


    public static boolean HasMagicRoobes(EntityPlayer player){
        if(player != null && player.inventory.armorInventory != null) {

            if (player.inventory.armorInventory[0] != null
                    && player.inventory.armorInventory[1] != null
                    && player.inventory.armorInventory[2] != null
                    && player.inventory.armorInventory[3] != null) {
                if (

                        player.inventory.armorInventory[0].getItem() instanceof ModItemMagicArmor
                                && player.inventory.armorInventory[1].getItem() instanceof ModItemMagicArmor
                                && player.inventory.armorInventory[2].getItem() instanceof ModItemMagicArmor
                                && player.inventory.armorInventory[3].getItem() instanceof ModItemMagicArmor) {

                    return true;
                } else {
                    return false;
                }

            }else {
                return false;

            }
        }


        return true;
    }

}
