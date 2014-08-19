package CrystallineApi.Spells;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellUtils {


    public static ArrayList<SpellComponent> Components = new ArrayList<SpellComponent>();
    public static HashMap<String, SpellComponent> Comps = new HashMap<String, SpellComponent>();

    public static ArrayList<SpellType> Types = new ArrayList<SpellType>();
    public static HashMap<String, SpellType> Type = new HashMap<String, SpellType>();

    public static ArrayList<SpellModifier> Modifiers = new ArrayList<SpellModifier>();
    public static HashMap<String, SpellModifier> Mods = new HashMap<String, SpellModifier>();


    public static void RegisterComponents(SpellComponent comp){
        Components.add(comp);
        Comps.put(comp.GetId(), comp);
    }


    public static void RegisterTypes(SpellType comp){
        Types.add(comp);
        Type.put(comp.GetId(), comp);
    }


    public static void RegisterModifiers(SpellModifier comp){
        Modifiers.add(comp);
        Mods.put(comp.GetId(), comp);
    }

    public static SpellComponent GetCompFromSpellComp(ItemStack stack){
        SpellComponent comp = null;

        if(stack.getTagCompound() != null){
            if(Comps.get(stack.getTagCompound().getString("CompId")) != null)
                comp = Comps.get(stack.getTagCompound().getString("CompId"));
        }


        return comp;
    }


    public static SpellType GetTypeFromSpellType(ItemStack stack){
        SpellType comp = null;

        if(stack.getTagCompound() != null){
            if(Type.get(stack.getTagCompound().getString("TypeId")) != null)
                comp = Type.get(stack.getTagCompound().getString("TypeId"));
        }


        return comp;
    }


    public static SpellModifier GetModifierFromSpellModifier(ItemStack stack){
        SpellModifier comp = null;

        if(stack.getTagCompound() != null){
            if(Mods.get(stack.getTagCompound().getString("ModId")) != null)
                comp = Mods.get(stack.getTagCompound().getString("ModId"));
        }


        return comp;
    }


    public static SpellType GetSpellType(ItemStack stack){
        SpellType Comps = null;

        if(stack.getTagCompound() != null){
            String id = stack.getTagCompound().getString("Type");

            Comps = Type.get(id);
        }

        return Comps;
    }

    public static int GetAmountOfAModifer(ItemStack stack, SpellModifier mod){
        if(mod != null) {
            int g = 0;

            SpellModifier[] Mods = GetSpellModifiersRaw(stack);

            for (int i = 0; i < Mods.length; i++) {
                if (Mods[i] != null) {
                    if (Mods[i].GetId().equals(mod.GetId()))
                        g += 1;
                }
            }

            return  g;
        }

        return 0;
    }

    public static SpellModifier[] GetSpellModifiers(ItemStack stack){

        ArrayList<SpellModifier> list = new ArrayList<SpellModifier>();

        if(stack.getTagCompound() != null){
            String t = stack.getTagCompound().getString("Mods");

            String[] comps = t.split(":");

            if(comps.length > 0){
                for(int i = 0; i < comps.length; i++){
                    SpellModifier spellComp = Mods.get(comps[i]);
                    if(spellComp != null && !list.contains(spellComp)){
                        list.add(spellComp);


                    }

                }
            }

        }

        SpellModifier[] Comps = new SpellModifier[list.size()];

        for(int i = 0; i < list.size(); i++){
            Comps[i] = list.get(i);
        }

        return Comps;
    }


    private static SpellModifier[] GetSpellModifiersRaw(ItemStack stack){

        ArrayList<SpellModifier> list = new ArrayList<SpellModifier>();

        if(stack.getTagCompound() != null){
            String t = stack.getTagCompound().getString("Mods");

            String[] comps = t.split(":");

            if(comps.length > 0){
                for(int i = 0; i < comps.length; i++){
                    SpellModifier spellComp = Mods.get(comps[i]);
                    if(spellComp != null){
                        list.add(spellComp);


                    }

                }
            }

        }

        SpellModifier[] Comps = new SpellModifier[list.size()];

        for(int i = 0; i < list.size(); i++){
            Comps[i] = list.get(i);
        }

        return Comps;
    }


    public static SpellComponent[] GetSpellComponents(ItemStack stack){

        ArrayList<SpellComponent> list = new ArrayList<SpellComponent>();

        if(stack.getTagCompound() != null){
            String t = stack.getTagCompound().getString("Comps");

            String[] comps = t.split(":");

            if(comps.length > 0){
                for(int i = 0; i < comps.length; i++){
                    SpellComponent spellComp = Comps.get(comps[i]);
                    if(spellComp != null){
                        list.add(spellComp);


                    }

                }
            }

        }

        SpellComponent[] Comps = new SpellComponent[list.size()];

        for(int i = 0; i < list.size(); i++){
            Comps[i] = list.get(i);
        }

        return Comps;
    }

    public static void SetSpellType(ItemStack stack, SpellType type){
        if(stack == null || stack.getItem() == null)
            return;

        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setString("Type", type.GetId());

    }

    public static void SetSpellComponents(ItemStack stack, SpellComponent[] Comps){

        if(stack == null || stack.getItem() == null)
            return;

        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        String t = "";

        for(int i = 0; i < Comps.length; i++){
            if(Comps[i] != null){
                t = t + Comps[i].GetId() + ":";
            }

        }


        stack.getTagCompound().setString("Comps", t);

    }


    public static void SetSpellModifiers(ItemStack stack, SpellModifier[] Comps){

        if(stack == null || stack.getItem() == null)
            return;

        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        String t = "";

        for(int i = 0; i < Comps.length; i++){
            if(Comps[i] != null){
                t = t + Comps[i].GetId() + ":";
            }

        }


        stack.getTagCompound().setString("Mods", t);

    }

    public static double GetSpellCost(ItemStack stack){
        double x = 0;

        SpellComponent[] Comps = GetSpellComponents(stack);

        for(int i = 0; i < Comps.length; i++) {
            if (Comps[i] != null) {
                x += Comps[i].EnergyCost();
            }
        }

        SpellModifier[] Mods = GetSpellModifiersRaw(stack);

        for(int i = 0; i < Mods.length; i++) {
            if (Mods[i] != null) {
                x *= Mods[i].EnergyMultiplier();
            }
        }

        SpellType type = GetSpellType(stack);

        x *= type.GetEnergyMultiplier(stack);

        return x;
    }

}
