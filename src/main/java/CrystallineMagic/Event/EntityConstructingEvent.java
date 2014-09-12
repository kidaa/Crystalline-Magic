package CrystallineMagic.Event;

import CrystallineMagic.Utils.MagicInfoStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityConstructingEvent
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {


        if (event.entity instanceof EntityPlayer && MagicInfoStorage.get((EntityPlayer) event.entity) == null) {
            MagicInfoStorage.register((EntityPlayer) event.entity);
        }


        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(MagicInfoStorage.EXT_PROP_NAME) == null)
            event.entity.registerExtendedProperties(MagicInfoStorage.EXT_PROP_NAME, new MagicInfoStorage((EntityPlayer) event.entity));
    }

}