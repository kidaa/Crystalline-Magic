package CrystallineMagic.Event.Custom;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@Cancelable
public class EventSpellCast extends Event {

    EntityPlayer player;
    ItemStack stack;

    public EventSpellCast(EntityPlayer player, ItemStack stack){
        this.stack = stack;
        this.player = player;
    }


}
