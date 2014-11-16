package CrystallineMagic.Main.Guide;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Main.ModBlocks;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Utils.Ref;
import MiscUtils.GuideBase.Utils.GuideInstance;
import MiscUtils.GuideBase.Utils.GuideTab;
import MiscUtils.GuideBase.Utils.ModGuideText;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrystaMagicGuideIntegration extends GuideInstance {

    @Override
    public ResourceLocation BlockDescriptions() {
        return new ResourceLocation(Ref.ModId.toLowerCase(), "guide/blockinfo.txt");
    }

    @Override
    public ResourceLocation ItemDescriptions() {
        return new ResourceLocation(Ref.ModId.toLowerCase(), "guide/iteminfo.txt");
    }

    @Override
    public String ModPageName() {
        return "guide.crystmagic.name";
    }

    @Override
    public ItemStack ModPageDisplay() {
        return new ItemStack(CrystMagic.config.GetCheckedItem(ModItems.Spell));
    }

    @Override
    public String ModDescription() {
        return "Crsytalline Magic is a crystal based magic mod.";
    }

    ModGuideText MainTab;
    GuideTab BlocksTab;
    GuideTab ItemsTab;

    @Override
    public void RegisterInfo() {
        MainTab = new ModGuideText(this, Items.paper, "guide.crystmagic.tab.main");
        BlocksTab = new GuideTab(this, ModBlocks.PowerCrystal, "guide.crystmagic.tab.blocks");
        ItemsTab = new GuideTab(this, ModItems.BlueCrystal, "guide.crystmagic.tab.items");

        for(Object r : Block.blockRegistry) {
            Block bl = (Block) r;
            if (bl != null) {
                GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(bl);
                if (id != null & id.modId.equalsIgnoreCase(Ref.ModId)) {
                    if (bl.getCreativeTabToDisplayOn() != null) {
                        BlocksTab.Register(bl);
                    }
                }
            }
        }


        for(Object r : Item.itemRegistry) {
            Item itm = (Item) r;
            if (itm != null && !(itm instanceof ItemBlock)) {
                GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(itm);
                if (id != null & id.modId.equalsIgnoreCase(Ref.ModId)) {
                    if (itm.getCreativeTab() != null) {
                        ItemsTab.Register(itm);
                    }
               }
            }
        }



        RegisterTab(MainTab);
        RegisterTab(BlocksTab);
        RegisterTab(ItemsTab);
    }
}
