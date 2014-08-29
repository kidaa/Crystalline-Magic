package CrystallineApi.Events;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EventSpellCast extends Event {

    public EntityPlayer player;
    public ItemStack stack;

    public EventSpellCast(EntityPlayer player, ItemStack stack){
        this.stack = stack;
        this.player = player;
    }


}
