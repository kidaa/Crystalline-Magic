package CrystallineMagic.TileEntities;

import CrystallineMagic.Items.ModItemSoulOrb;
import CrystallineMagic.Items.ModItemSpell;
import CrystallineMagic.Items.ModItemSpellComponent;
import CrystallineMagic.Items.ModItemSpellModifier;
import CrystallineMagic.Items.ModItemSpellType;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import CrystallineMagic.Utils.Spells.Utils.SpellModifier;
import CrystallineMagic.Utils.Spells.Utils.SpellPartUsage;
import CrystallineMagic.Utils.Spells.Utils.SpellType;
import MiscUtils.TileEntity.TileEntityInvBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class TileEntitySpellCreationTable extends TileEntityInvBase{


    /**
     * Input type = 0
     * Input spell = 1
     * Input soul orb = 2
     *
     * Input components = 2-11
     * Input modifiers = 11-20
     *
     * Output spell = 21
     *
     */


    public int ErrorCode = 0;


    public TileEntitySpellCreationTable() {
        super(22, "SpellCreationTable", 1);
    }


    public ItemStack GetResult(){
        if(getStackInSlot(0) != null){
            if(getStackInSlot(0).getItem() instanceof ModItemSpellType){
                if(getStackInSlot(1) != null && getStackInSlot(1).getItem() instanceof ModItemSpell && getStackInSlot(1).getTagCompound() == null) {
                    if (getStackInSlot(2) != null && getStackInSlot(2).getItem() instanceof ModItemSoulOrb && ((ModItemSoulOrb) getStackInSlot(2).getItem()).hasEffect(getStackInSlot(2), 0)) {


                        SpellType type = MagicUtils.GetTypeFromSpellType(getStackInSlot(0));

                            ArrayList<SpellComponent> Components = new ArrayList<SpellComponent>();

                            for (int i = 0; i < 9; i++) {
                                ItemStack tmp = this.getStackInSlot(i + 3);

                                if (tmp != null && tmp.getItem() instanceof ModItemSpellComponent) {
                                    SpellComponent comp = MagicUtils.GetCompFromSpellComp(tmp);

                                    if(type.GetUsage() == SpellPartUsage.Both || comp.GetUsage() == type.GetUsage() || comp.GetUsage() == SpellPartUsage.Both){

                                    if (comp != null) {
                                        if (Components.contains(comp))
                                            return null;

                                        else
                                            Components.add(comp);

                                    }

                                    }else{
                                        ErrorCode = 1;
                                        return null;
                                    }


                                }

                            }


                            if (Components.size() <= 0) {
                                return null;
                            }


                            ArrayList<SpellModifier> Modifiers = new ArrayList<SpellModifier>();

                            for (int i = 0; i < 21; i++) {
                                ItemStack tmp = this.getStackInSlot(i);

                                if (tmp != null && tmp.getItem() instanceof ModItemSpellModifier) {
                                    SpellModifier comp = MagicUtils.GetModifierFromSpellModifier(tmp);


                                    if (comp != null) {
                                        Modifiers.add(comp);

                                    }


                                }

                            }

                            ItemStack stack = getStackInSlot(1).copy();

                            SpellComponent[] Comps = new SpellComponent[Components.size()];

                            for (int i = 0; i < Comps.length; i++)
                                Comps[i] = Components.get(i);


                            SpellModifier[] Mods = new SpellModifier[Modifiers.size()];

                            for (int i = 0; i < Mods.length; i++)
                                Mods[i] = Modifiers.get(i);


                            MagicUtils.SetSpellComponents(stack, Comps);
                            MagicUtils.SetSpellModifiers(stack, Mods);
                            MagicUtils.SetSpellType(stack, type);

                             ErrorCode = 0;
                            return stack;


                        }
                    }

            }
        }
        return null;
    }

    public void updateEntity(){

        if(getStackInSlot(2) != null && getStackInSlot(2).getItem() instanceof ModItemSoulOrb && getStackInSlot(21) != null) {
            ItemStack st = getStackInSlot(21);
            ModItemSoulOrb orb = (ModItemSoulOrb) getStackInSlot(2).getItem();

            EntityPlayer player = null;

            if (orb.GetOwner(getStackInSlot(2)) != null) {
                player = orb.GetOwner(getStackInSlot(2));
            }


            if(player != null)
            if (player.capabilities.isCreativeMode || MagicInfoStorage.get(player) != null && MagicInfoStorage.get(player).HasMagic() && MagicInfoStorage.get(player).GetPlayerMaxEnergy() >= MagicUtils.GetSpellCost(st)) {
            }else{
                ErrorCode = 2;
            }
        }
    }

    public boolean IsValid(){
        return GetResult() != null;
    }


    public void OnSlotChanged(){
        ErrorCode = 0;

        if(IsValid()){
            setInventorySlotContents(21, GetResult());
        }else{
            setInventorySlotContents(21, null);
        }


    }

    public void OnTakeOutput(){

        for(int i = 0; i < getSizeInventory(); i++){
            if(i != 2 && i != 21){

                if(i == 1)
                    decrStackSize(1, 1);
                else{
                    if(getStackInSlot(i) != null && getStackInSlot(i).getTagCompound() != null)
                        decrStackSize(i, 1);

                }

            }

        }

    }
}
