package cofh.thermal.cultivation.inventory.container.device;

import cofh.lib.inventory.InvWrapperCoFH;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.core.init.TCoreReferences.DEVICE_HIVE_EXTRACTOR_CONTAINER;

public class DeviceSoilChargerContainer extends TileContainer {

    public final ThermalTileBase tile;

    public DeviceSoilChargerContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(DEVICE_HIVE_EXTRACTOR_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ThermalTileBase) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotRemoveOnly(tileInv, 0, 44, 35));

        bindAugmentSlots(tileInv, 1, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}