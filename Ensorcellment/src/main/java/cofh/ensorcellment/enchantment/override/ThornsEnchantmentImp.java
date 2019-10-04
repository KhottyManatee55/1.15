package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.Map;
import java.util.Random;

public class ThornsEnchantmentImp extends EnchantmentOverride {

    public static int chance = 15;

    public ThornsEnchantmentImp() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR, EquipmentSlotType.values());
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxEnchantability(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        if (!enable) {
            return stack.canApplyAtEnchantingTable(this);
        }
        Item item = stack.getItem();
        return enable && (item instanceof ArmorItem || item instanceof HorseArmorItem || item.isShield(stack, null) || supportsEnchantment(stack));
    }

    // region HELPERS
    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {

        Random rand = user.getRNG();
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.func_222189_b(Enchantments.THORNS, user);
        if (shouldHit(level, rand)) {
            if (attacker != null) {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float) ThornsEnchantment.getDamage(level, rand));
            }
            if (stack != null) {
                (stack.getValue()).damageItem(3, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
            }
        } else if (stack != null) {
            (stack.getValue()).damageItem(1, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
        }

    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}
