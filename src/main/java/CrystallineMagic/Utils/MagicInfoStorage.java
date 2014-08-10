package CrystallineMagic.Utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MagicInfoStorage implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "MiscItemsMagicPlayerStorage";

    private final EntityPlayer player;


    double MagicEnergy;
    double MaxMagicEnergy;

    boolean HasMagic;

    public int RechargeTime;
    public int Recharge;



    public static EntityPlayer GetPlayerFromStack(ItemStack stack){
        EntityPlayer player = null;


        if(stack.getTagCompound() != null){
            if(stack.getTagCompound().getString("Player") != null){

                if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
                    player = Minecraft.getMinecraft().thePlayer;

                }else{
                    player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(stack.getTagCompound().getString("Player"));


                }

            }
        }

        return player;
    }

    public MagicInfoStorage(EntityPlayer player)
    {
        this.player = player;

        MagicEnergy = MagicRef.BaseEnergy;
        MaxMagicEnergy = MagicRef.BaseMaxEnergy;

        Recharge = MagicRef.BaseRechargeTime;

        HasMagic = false;


    }


    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(MagicInfoStorage.EXT_PROP_NAME, new MagicInfoStorage(player));
    }


    public static final MagicInfoStorage get(EntityPlayer player)
    {
        return (MagicInfoStorage) player.getExtendedProperties(EXT_PROP_NAME);
    }


    @Override
    public void init(Entity entity, World world)
    {
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {

        NBTTagCompound properties = new NBTTagCompound();


        properties.setDouble("En", MagicEnergy);
        properties.setDouble("MaxEn", MaxMagicEnergy);

        properties.setBoolean("HM", HasMagic);

        properties.setInteger("Recharge", Recharge);
        properties.setInteger("RechargeTime", RechargeTime);

        compound.setTag(EXT_PROP_NAME, properties);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

        MagicEnergy = properties.getDouble("En");
        MaxMagicEnergy = properties.getDouble("MaxEn");

        HasMagic = properties.getBoolean("HM");

        Recharge = properties.getInteger("Recharge");
        RechargeTime = properties.getInteger("RechargeTime");
    }


    public double GetPlayerEnergy(){
        return MagicEnergy;
    }

    public void SetPlayerEnergy(double i){
        MagicEnergy = i;

        if(MagicEnergy > MaxMagicEnergy)
            MagicEnergy = MaxMagicEnergy;
    }

    public void IncreasePlayerEnergy(double i){
        MagicEnergy += i;

        if(MagicEnergy > MaxMagicEnergy)
            MagicEnergy = MaxMagicEnergy;

    }

    public void DecreasePlayerEnergy(double i){
        MagicEnergy -= i;

        if(MagicEnergy < 0)
            MagicEnergy = 0;
    }


    public double GetPlayerMaxEnergy(){
        return MaxMagicEnergy;
    }

    public void SetPlayerMaxEnergy(double i){
        MaxMagicEnergy = i;
    }

    public void SetHasMagic(boolean t){
        HasMagic = t;
    }

    public boolean HasMagic(){
        return HasMagic;
    }






}