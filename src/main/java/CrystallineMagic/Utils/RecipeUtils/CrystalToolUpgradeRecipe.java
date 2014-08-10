package CrystallineMagic.Utils.RecipeUtils;

import CrystallineMagic.Main.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class CrystalToolUpgradeRecipe implements IRecipe {

    ItemStack UpgradeItem;
    ItemStack Tool;
    Enchantment encahantment;
    int lv;

    public CrystalToolUpgradeRecipe(ItemStack Tool, ItemStack UpgradeItem, Enchantment encahantment, int Level){
        this.UpgradeItem = UpgradeItem;
        this.encahantment = encahantment;
        this.Tool = Tool;
        this.lv = Level;

    }



    @Override
    public boolean matches(InventoryCrafting inv, World var2) {



        boolean HasUpgradeItem = false, HasCrystals = false, HasTool = false;
        int Crystals = 0;


        ItemStack tmpStack = null;


        for(int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) != null) {
                if (inv.getStackInSlot(i) instanceof ItemStack)
                    tmpStack = inv.getStackInSlot(i);
                else
                    tmpStack = new ItemStack(inv.getStackInSlot(i).getItem());




                if (tmpStack.getItem() == Tool.getItem()) {
                    HasTool = true;
                    Tool = tmpStack;
                }

                if (tmpStack.getItem() == UpgradeItem.getItem())
                    HasUpgradeItem = true;

                if (tmpStack.getItem() == ModItems.ChargedCrystal)
                    Crystals++;



                if (HasUpgradeItem && Crystals == 3 && HasTool) {

                            return  !EnchantmentHelper.getEnchantments(tmpStack).containsValue(encahantment);
                }

            }
        }

        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {


        ItemStack CurrentTool = null;

        for(int i = 0; i < inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i) != null && inv.getStackInSlot(i).getItem() == Tool.getItem())
                CurrentTool = inv.getStackInSlot(i).copy();
        }

        if(CurrentTool != null) {
            ItemStack newItem = new ItemStack(CurrentTool.getItem(), CurrentTool.stackSize, CurrentTool.getItemDamage());
            newItem.setTagCompound(CurrentTool.getTagCompound());

                    newItem.addEnchantment(encahantment, lv);



            return newItem;

        }

        return Tool;

    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return Tool.copy();
    }
}
