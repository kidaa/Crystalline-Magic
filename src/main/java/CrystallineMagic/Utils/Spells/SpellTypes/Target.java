package CrystallineMagic.Utils.Spells.SpellTypes;

import CrystallineApi.Spells.SpellUtils;
import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import CrystallineApi.Spells.SpellType;
import CrystallineApi.Spells.SpellUseType;
import MiscUtils.Handlers.ChatMessageHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Target implements SpellType {
    @Override
    public String GetName() {
        return "Target";
    }

    @Override
    public String GetId() {
        return "TA";
    }

    @Override
    public SpellUseType GetUseType() {
        return SpellUseType.Self;
    }

    @Override
    public boolean OnUse(ItemStack SpellStack, EntityPlayer player, Entity ent, World world, int x, int y, int z, int BlockSide) {
        MagicInfoStorage storage = MagicInfoStorage.get(player);

        boolean t = false;

        if(storage.GetTargetX() != 0 || storage.GetTargetY() != 0 || storage.GetTargetZ() != 0){

                SpellComponent[] Components = SpellUtils.GetSpellComponents(SpellStack);

                if(Components != null && Components.length > 0) {
                    for (int i = 0; i < Components.length; i++) {
                        if (Components[i] != null) {
                            if(Components[i].OnUseOnBlock(SpellStack, world, storage.GetTargetX(), storage.GetTargetY(), storage.GetTargetZ(), world.getBlock(storage.GetTargetX(), storage.GetTargetY(), storage.GetTargetZ()), player, -1))
                                t = true;

                        }


                    }
                }

            }else{
                ChatMessageHandler.sendChatToPlayer(player, StatCollector.translateToLocal("chat.message.spell.noTarget"));
                return false;

            }



        return t;
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Block;
    }

    @Override
    public double GetEnergyMultiplier(ItemStack stack) {
        return 1;
    }
}
