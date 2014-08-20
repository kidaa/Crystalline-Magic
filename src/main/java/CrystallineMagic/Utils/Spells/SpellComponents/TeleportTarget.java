package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineApi.Spells.SpellComponent;
import CrystallineApi.Spells.SpellPartUsage;
import MiscUtils.Handlers.ChatMessageHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class TeleportTarget implements SpellComponent {


    @Override
    public boolean OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
           MagicInfoStorage st = MagicInfoStorage.get(player);

            int x = st.GetTargetX();
            int y = st.GetTargetY();
            int z = st.GetTargetZ();

            if(x != 0 || y != 0 || z != 0){
                if(entityHit instanceof EntityPlayer) {
                    EntityPlayer pl = (EntityPlayer)entityHit;
                    pl.setPositionAndUpdate((double)x + 0.5,(double)y+1,(double)z + 0.5);
                }else{
                    entityHit.setPosition(x + 0.5, y+1, z + 0.5);
                }

                world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
                return true;

            }else{
                ChatMessageHandler.sendChatToPlayer(player, StatCollector.translateToLocal("chat.message.spell.noTarget"));
                return false;
            }


    }

    @Override
    public boolean OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {

        return false;
    }

    @Override
    public String GetName() {
        return "Teleport Target";
    }

    @Override
    public double EnergyCost() {
        return 90;
    }

    @Override
    public String GetId() {
        return "TT";
    }

    public SpellPartUsage GetUsage(){
        return SpellPartUsage.Entity;
    }
}
