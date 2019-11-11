package cofh.test.item;

import cofh.lib.item.override.ArrowItemCoFH;
import cofh.test.entity.projectile.PrismarineArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PrismarineArrowItem extends ArrowItemCoFH {

    public PrismarineArrowItem(Properties builder) {

        super(builder);
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new PrismarineArrowEntity(worldIn, shooter);
    }

}
