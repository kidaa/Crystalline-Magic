package CrystallineApi.Magic;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

import java.util.List;

public class MagicEnergyUtils {


    public static void SendPowerToNearbyReceivers(TileEntity tile){
        SendPower((IMagicSender)tile, tile, 8);
    }

    public static void SendPowerToNearbyReceiversWithRadius(TileEntity tile, int Radius){
        SendPower((IMagicSender)tile, tile, Radius);
    }

    private static void SendPower(IMagicSender sender, TileEntity tile, int Radius){

        if (!tile.getWorldObj().isRemote) {
            WorldServer world = (WorldServer) tile.getWorldObj();

            double Rad = 0;
            IMagicReceiver recc = null;

            List list = world.func_147486_a(tile.xCoord - Radius, tile.yCoord - Radius, tile.zCoord - Radius, tile.xCoord + Radius, tile.yCoord + Radius, tile.zCoord + Radius);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof TileEntity) {
                    TileEntity tilee = (TileEntity) list.get(i);

                    if (tilee instanceof IMagicReceiver) {
                        IMagicReceiver rec = (IMagicReceiver) tilee;

                        if (rec.CanReceiveEnergyAmount(sender.GetEnergyPacketSize())) {


                                 if(tilee.xCoord != tile.xCoord || tilee.yCoord != tile.yCoord || tilee.zCoord != tile.zCoord) {

                                    if (Rad == 0) {
                                        Rad = tile.getDistanceFrom(tilee.xCoord, tilee.yCoord, tilee.zCoord);
                                        recc = rec;

                                    } else if (Rad > 0 && tilee.getDistanceFrom(tilee.xCoord, tilee.yCoord, tilee.zCoord) < Rad) {
                                        Rad = tile.getDistanceFrom(tilee.xCoord, tilee.yCoord, tilee.zCoord);
                                        recc = rec;

                                    }




                            }else
                                continue;

                            break;

                        }else
                            continue;


                    } else
                        continue;

                }

            }



            if(recc != null){
                if(sender.CanSendEnergyAmount(sender.GetEnergyPacketSize())) {
                    sender.SendEnergy(recc, sender.GetEnergyPacketSize());


                }
            }


        }
    }
}
