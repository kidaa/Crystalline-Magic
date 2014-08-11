package CrystallineMagic.Utils.Spells.SpellComponents;

import CrystallineMagic.Utils.MagicInfoStorage;
import CrystallineMagic.Utils.Spells.Utils.SpellComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TeleportTarget implements SpellComponent {


    @Override
    public void OnUseOnEntity(ItemStack Spell, World world, Entity entityHit, EntityPlayer player) {
           MagicInfoStorage st = MagicInfoStorage.get(player);

            int x = st.GetTargetX();
            int y = st.GetTargetY();
            int z = st.GetTargetZ();

            if(x != 0 || y != 0 || z != 0){
                entityHit.setPosition((double)x,(double)y+1,(double)z);

            }


    }

    @Override
    public void OnUseOnBlock(ItemStack Spell, World world, int x, int y, int z, Block block, EntityPlayer player, int Side) {

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
}
