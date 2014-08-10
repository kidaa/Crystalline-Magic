package CrystallineMagic.Utils;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Packets.MagicReciveParticleEffects;
import CrystallineMagic.Utils.Spells.SpellComponent;
import MiscUtils.Network.PacketHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class MagicUtils {

    public static void ReceiveEnergy(TileEntity tile){
        PacketHandler.sendToDimension(new MagicReciveParticleEffects(tile.xCoord, tile.yCoord, tile.zCoord), tile.getWorldObj().provider.dimensionId, CrystMagic.channels);
    }

    public static ArrayList<SpellComponent> Components = new ArrayList<SpellComponent>();
    public static HashMap<String, SpellComponent> Comps = new HashMap<String, SpellComponent>();


    public static void RegisterComponents(SpellComponent comp){
        Components.add(comp);
        Comps.put(comp.GetId(), comp);
    }

    public static SpellComponent GetCompFromSpellComp(ItemStack stack){
        SpellComponent comp = null;

        if(stack.getTagCompound() != null){
            if(Comps.get(stack.getTagCompound().getString("CompId")) != null)
                comp = Comps.get(stack.getTagCompound().getString("CompId"));
        }


        return comp;
    }

    public static SpellComponent[] GetSpellComponents(ItemStack stack){

        ArrayList<SpellComponent> list = new ArrayList<SpellComponent>();

        if(stack.getTagCompound() != null){
            String t = stack.getTagCompound().getString("Comps");

            String[] comps = t.split(":");

            if(comps.length > 0){
                for(int i = 0; i < comps.length; i++){
                    SpellComponent spellComp = MagicUtils.Comps.get(comps[i]);
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

    public static void SetSpellComponents(ItemStack stack, SpellComponent[] Comps){

        if(stack == null || stack.getItem() == null)
            return;

        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        String t = "";

        for(int i = 0; i < Comps.length; i++){
            System.out.println(Comps[i] + ":");
            if(Comps[i] != null){
                t = t + Comps[i].GetId() + ":";
            }

        }


        stack.getTagCompound().setString("Comps", t);

    }

    public static double GetSpellCost(ItemStack stack){
        double x = 0;

        SpellComponent[] Comps = GetSpellComponents(stack);

        for(int i = 0; i < Comps.length; i++) {
            if (Comps[i] != null) {
                x += Comps[i].EnergyCost();

            }

        }

        return x;
    }

}
