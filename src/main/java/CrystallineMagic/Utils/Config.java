package CrystallineMagic.Utils;

import MiscUtils.Utils.Config.ConfigBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config extends ConfigBase{

    protected Configuration config;

    public Config(String Loc){

        config = new Configuration(new File(Loc + "/tm1990's mods/CrystallineMagic.cfg"));
    }

    @Override
    public Configuration GetConfigFile() {
        return config;
    }

    @Override
    public void InitConfig(String t) {

        config.addCustomCategoryComment(CATEGORY_CLIENT_SETTINGS, "Client side only settings. Settings that does not affect gameplay");
        config.addCustomCategoryComment(CATEGORY_SERVER_SETTINGS, "Server side settings which can affect gameplay");

        config.addCustomCategoryComment(CATEGORY_BLOCKS, "This allows you to enabled/disable the different blocks from the mod");
        config.addCustomCategoryComment(CATEGORY_ITEMS, "This allows you to enabled/disable the different items from the mod");

        config.addCustomCategoryComment(CATEGORY_WORLDGEN, "This allows you to disable and change different world generation types");

        config.setCategoryRequiresMcRestart(CATEGORY_BLOCKS, true);
        config.setCategoryRequiresMcRestart(CATEGORY_ITEMS, true);

        config.setCategoryRequiresMcRestart(CATEGORY_WORLDGEN, true);

        LoadConfig();
    }

    @Override
    public void LoadConfig() {


        if(config.hasChanged())
            config.save();
    }

    public  boolean IsBlockEnabled(Block block){
        if(BlockConfigNames.get(block) == null)
            return false;


        boolean bl = GetConfigFile().get(CATEGORY_BLOCKS, "Enable " + BlockConfigNames.get(block).replace("tile.", "").replace(".name", ""), true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }



    public  boolean IsItemEnabled(Item item){
        if(ItemConfigNames.get(item) == null)
            return false;

        boolean bl = GetConfigFile().get(CATEGORY_ITEMS, "Enable " + ItemConfigNames.get(item).replace("item.", "").replace(".name", ""), true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }


    public  boolean IsWorldGeneratorEnabled(String WorldGen){
        boolean bl = GetConfigFile().get(CATEGORY_WORLDGEN, "Enable Worldgen: " + WorldGen, true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }

    public  int GetWorldGenerationChance(String WorldGen, int def){
        if(IsWorldGeneratorEnabled(WorldGen)){
            int t = GetConfigFile().get(CATEGORY_WORLDGEN, "The amount of times it will try to spawn in a chunk: " + WorldGen, def).getInt(def);

            if(GetConfigFile().hasChanged())
                GetConfigFile().save();

            return t;


        }
        return 0;
    }

    public boolean CanSpawnParticles(){
        return GetConfigFile().getBoolean("Can Spawn Particles", CATEGORY_CLIENT_SETTINGS, true, "Should particles be enabled?");
    }
}
