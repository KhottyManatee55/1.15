package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

public class MagicEdgeEnchantment extends DamageEnchantmentCoFH {

    public MagicEdgeEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    // region HELPERS
    public static float getExtraDamage(int level) {

        return level * 0.5F;
    }

    public static void onHit(LivingEntity entity, int level) {

        for (int i = 0; i < 2 * level; ++i) {
            ((ServerWorld) entity.world).spawnParticle(ParticleTypes.ENCHANT, entity.posX + entity.world.rand.nextDouble(), entity.posY + 1.0D + entity.world.rand.nextDouble(), entity.posZ + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            ((ServerWorld) entity.world).spawnParticle(ParticleTypes.ENCHANTED_HIT, entity.posX + entity.world.rand.nextDouble(), entity.posY + 1.0D + entity.world.rand.nextDouble(), entity.posZ + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
        }
    }
    // endregion
}