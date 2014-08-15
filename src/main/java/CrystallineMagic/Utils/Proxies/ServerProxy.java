package CrystallineMagic.Utils.Proxies;

import CrystallineMagic.Main.CrystMagic;
import CrystallineMagic.Main.ModItems;
import CrystallineMagic.Packets.SyncPlayerPropsPacket;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.MagicUtils;
import MiscUtils.Network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import java.util.HashMap;
import java.util.Map;

public class ServerProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public void registerRenderThings(){

    }


    public int addArmor(String armor){
        return 0;
    }


    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }



    private static final String getSaveKey(EntityPlayer player) {
        return player.getCommandSenderName() + ":" + MagicInfoStorage.EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player) {
        MagicInfoStorage playerData = MagicInfoStorage.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        ServerProxy.storeEntityData(getSaveKey(player), savedData);
    }


    public static final void loadProxyData(EntityPlayer player) {
        MagicInfoStorage playerData = MagicInfoStorage.get(player);
        NBTTagCompound savedData = ServerProxy.getEntityData(getSaveKey(player));
        if (savedData != null) { playerData.loadNBTData(savedData); }

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player, CrystMagic.channels);
    }

    public static void RegisterChestLoot(){

        int i1 = 0;
        int i2 = 1;
        int i3 = 4;

        for(int i = 0; i < MagicUtils.Components.size(); i++){
            ItemStack stack = new ItemStack(ModItems.SpellComponent);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Comp", MagicUtils.Components.get(i).GetName());
            stack.getTagCompound().setString("CompId", MagicUtils.Components.get(i).GetId());


            ChestGenHooks chestGen = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));



        }



        for(int i = 0; i < MagicUtils.Modifiers.size(); i++){
            ItemStack stack = new ItemStack(ModItems.SpellModifier);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Mod", MagicUtils.Modifiers.get(i).GetName());
            stack.getTagCompound().setString("ModId", MagicUtils.Modifiers.get(i).GetId());


            ChestGenHooks chestGen = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));



        }


        for(int i = 0; i < MagicUtils.Types.size(); i++){
            ItemStack stack = new ItemStack(ModItems.SpellType);

            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setString("Type", MagicUtils.Types.get(i).GetName());
            stack.getTagCompound().setString("TypeId", MagicUtils.Types.get(i).GetId());


            ChestGenHooks chestGen = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));

            chestGen = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
            chestGen.addItem(new WeightedRandomChestContent(stack, i1, i2, i3));



        }
    }
}
