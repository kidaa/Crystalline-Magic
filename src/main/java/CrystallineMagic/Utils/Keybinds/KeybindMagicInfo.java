package CrystallineMagic.Utils.Keybinds;

import CrystallineMagic.Gui.EnumGuis;
import CrystallineMagic.Main.CrystMagic;
import MiscUtils.Register.KeyBind.ModKeybind;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

public class KeybindMagicInfo extends ModKeybind {

    public KeybindMagicInfo() {
        super("key.crystmagic.magicinfo.name", Keyboard.KEY_I, "keyCategory.crystmagic.name");
    }
    @Override
    public void OnActivated(EntityPlayer player, Side side) {
        FMLNetworkHandler.openGui(player, CrystMagic.instance, EnumGuis.Magic_Info.Id, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
    }
}
