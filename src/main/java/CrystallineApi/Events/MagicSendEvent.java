package CrystallineApi.Events;

import CrystallineApi.Magic.IMagicReceiver;
import CrystallineApi.Magic.IMagicSender;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.tileentity.TileEntity;

@Cancelable
public class MagicSendEvent extends Event {

    public final IMagicReceiver rec;
    public final IMagicSender Sender;
    public final double Amount;

    public final TileEntity recTile;

    public MagicSendEvent(TileEntity Rec, IMagicSender Sender, double Amount){
        this.rec = (IMagicReceiver)Rec;
        this.Sender = Sender;
        this.Amount = Amount;
        this.recTile = Rec;

    }
}
