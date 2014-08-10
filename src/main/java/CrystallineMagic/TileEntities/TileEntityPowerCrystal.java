package CrystallineMagic.TileEntities;


import CrystallineApi.Magic.IMagicReceiver;
import CrystallineApi.Magic.IMagicSender;
import CrystallineApi.Magic.MagicEnergyUtils;
import CrystallineMagic.Main.CrystMagic;
import MiscUtils.TileEntity.ModTileEntity;
import MiscUtils.Utils.Handlers.ParticleHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPowerCrystal extends ModTileEntity implements IMagicSender {


    public int Red, Green, Blue, Rotation, Height, Mode;

    int Increase = 3;
    public int MaxHeight = 50;
    public int MaxRotate = 360;

    boolean Down = false;

    public boolean Bottom, Middle, Top;

    public double Power;
    public double PowerIncrease = 0.01;

    public static double MaxPower = 100;


    double i = 0;
    int g = 0;

    public void updateEntity(){

        ParticleHelper helper = new ParticleHelper(worldObj, CrystMagic.config.CanSpawnParticles());

        if(Bottom) {
            if (Mode == 1 || Mode == 0) {
                if (Red < 255) {
                    Red += Increase;

                } else if (Red >= 255 && Green == 0) {
                    Mode = 2;
                }

            } else if (Mode == 2) {
                if (Red > 0 && Green < 255) {
                    Green += Increase;

                } else if (Red > 0 && Green >= 255) {
                    Red -= Increase;

                } else if (Red == 0 && Green >= 255) {
                    Mode = 3;
                }

            } else if (Mode == 3) {
                if (Green > 0 && Blue < 255) {
                    Blue += Increase;

                } else if (Green > 0 && Blue >= 255) {
                    Green -= Increase;

                } else if (Green == 0 && Blue >= 255) {
                    Mode = 4;
                }

            } else if (Mode == 4) {
                if (Blue > 0 && Red < 255) {
                    Red += Increase;

                } else if (Blue > 0 && Red >= 255) {
                    Blue -= Increase;

                } else if (Blue == 0 && Red >= 255) {
                    Mode = 1;
                }
            }


            if (Height < MaxHeight && !Down) {
                Height += 1;

            } else if (Down)
                Height -= 1;

            if (Height >= MaxHeight)
                Down = true;

            else if (Height <= 0)
                Down = false;


            if (Rotation < MaxRotate)
                Rotation += 1;
            else if (Rotation >= MaxRotate)
                Rotation = 0;


            if (Power < MaxPower) {
                Power += PowerIncrease;
            }


            if (Power > 0) {
                if (i >= (100 / Power)) {
                    i = 0;
                    helper.SpawnParticleRandomDr("happyVillager", xCoord, yCoord, zCoord, 3, 3, 1);

                } else
                    i += 0.1;
            } else
                i = 0;




            if (g >= 50) {
                g = 0;

                MagicEnergyUtils.SendPowerToNearbyReceivers(this);
            }else
                g += 1;


        }
    }




    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);


        Red = nbtTagCompound.getInteger("Red");
        Green = nbtTagCompound.getInteger("Green");
        Blue = nbtTagCompound.getInteger("Blue");

        Rotation = nbtTagCompound.getInteger("Rotation");
        Height = nbtTagCompound.getInteger("Height");

        Mode = nbtTagCompound.getInteger("Mode");

        Down = nbtTagCompound.getBoolean("Down");

        Bottom = nbtTagCompound.getBoolean("Bottom");
        Middle = nbtTagCompound.getBoolean("Middle");
        Top = nbtTagCompound.getBoolean("Top");

        Power = nbtTagCompound.getDouble("Power");

        g = nbtTagCompound.getInteger("G");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Red", Red);
        nbtTagCompound.setInteger("Green", Green);
        nbtTagCompound.setInteger("Blue", Blue);

        nbtTagCompound.setInteger("Rotation", Rotation);
        nbtTagCompound.setInteger("Height", Height);

        nbtTagCompound.setInteger("Mode", Mode);

        nbtTagCompound.setBoolean("Down", Down);

        nbtTagCompound.setBoolean("Bottom", Bottom);
        nbtTagCompound.setBoolean("Middle", Middle);
        nbtTagCompound.setBoolean("Top", Top);

        nbtTagCompound.setDouble("Power", Power);

        nbtTagCompound.setInteger("G", g);
    }


    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }


    @Override
    public double GetStoredEnergy() {
        return Power;
    }

    @Override
    public double GetEnergyOffered() {
        return Power;
    }

    @Override
    public double GetEnergyPacketSize() {
        return 1;
    }

    @Override
    public boolean CanSendEnergy() {
        return Power > 0;
    }

    @Override
    public boolean CanSendEnergyAmount(double i) {
        return Power >= i && CanSendEnergy();
    }

    @Override
    public void DecreaseEnergy(double i) {
        if(Power >= i)
            Power -= i;
        else
            Power = 0;
    }

    @Override
    public void SendEnergy(IMagicReceiver receiver, double i) {
          if(CanSendEnergyAmount(i)){
             if(receiver.CanReceiveEnergyAmount(i)){
                 DecreaseEnergy(i);
                 receiver.ReceiveEnergy(i);

             }
          }

    }

    @Override
    public void OnSendEnergyPacket(TileEntity tile, IMagicReceiver receiver) {
        if(receiver.CanReceiveEnergy() && CanSendEnergyAmount(GetEnergyPacketSize())){

            receiver.ReceiveEnergy(GetEnergyPacketSize());
            DecreaseEnergy(GetEnergyPacketSize());

        }
    }
}
