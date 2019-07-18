package cofh.lib.inventory.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * A slot that can only be used to display an item and disallows editing.
 */
public class SlotLocked extends SlotCoFH {

    public SlotLocked(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean canTakeStack(PlayerEntity player) {

        return false;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return false;
    }

}
