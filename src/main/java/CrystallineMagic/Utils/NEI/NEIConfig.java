package CrystallineMagic.Utils.NEI;

import CrystallineMagic.Utils.NEI.CustomRecipes.InfusionRecipeHandler;
import codechicken.nei.api.*;


public class NEIConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {


        API.registerRecipeHandler(new InfusionRecipeHandler());

        API.registerUsageHandler(new InfusionRecipeHandler());

    }

    @Override
    public String getName() {
        return "Crystalline Magic NEI";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
