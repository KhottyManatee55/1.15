package cofh.thermal.core.util;

import cofh.lib.block.crops.CropsBlockCoFH;
import cofh.lib.block.crops.CropsBlockPerennial;
import cofh.lib.item.*;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_BLOCKS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_ITEMS;
import static net.minecraft.block.Block.Properties.create;

public class RegistrationHelper {

    public RegistrationHelper() {

    }

    // region BLOCKS
    public static void registerBlock(String name, Supplier<Block> sup) {

        registerBlock(name, sup, THERMAL_BLOCKS, Rarity.COMMON);
    }

    public static void registerBlock(String name, Supplier<Block> sup, Rarity rarity) {

        registerBlock(name, sup, THERMAL_BLOCKS, rarity);
    }

    public static void registerBlock(String name, Supplier<Block> sup, ItemGroup group) {

        registerBlock(name, sup, group, Rarity.COMMON);
    }

    public static void registerBlock(String name, Supplier<Block> sup, ItemGroup group, Rarity rarity) {

        BLOCKS.register(name, sup);
        ITEMS.register(name, (Supplier<Item>) () -> new BlockItemCoFH(BLOCKS.get(name), new Item.Properties().group(group).rarity(rarity)));
    }

    public static void registerBlockOnly(String name, Supplier<Block> sup) {

        BLOCKS.register(name, sup);
    }
    // endregion

    // AUGMENTABLE BLOCKS
    public static void registerAugBlock(String name, Supplier<Block> sup, IntSupplier numSlots, Predicate<ItemStack> validAugment) {

        registerAugBlock(name, sup, numSlots, validAugment, THERMAL_BLOCKS, Rarity.COMMON);
    }

    public static void registerAugBlock(String name, Supplier<Block> sup, IntSupplier numSlots, Predicate<ItemStack> validAugment, Rarity rarity) {

        registerAugBlock(name, sup, numSlots, validAugment, THERMAL_BLOCKS, rarity);
    }

    public static void registerAugBlock(String name, Supplier<Block> sup, IntSupplier numSlots, Predicate<ItemStack> validAugment, ItemGroup group) {

        registerAugBlock(name, sup, numSlots, validAugment, group, Rarity.COMMON);
    }

    public static void registerAugBlock(String name, Supplier<Block> sup, IntSupplier numSlots, Predicate<ItemStack> validAugment, ItemGroup group, Rarity rarity) {

        BLOCKS.register(name, sup);
        ITEMS.register(name, (Supplier<Item>) () -> new BlockItemAugmentable(BLOCKS.get(name), new Item.Properties().group(group).rarity(rarity)).setNumSlots(numSlots).setSetAugValidator(validAugment));
    }
    // endregion

    // region ITEMS
    public static RegistryObject<Item> registerItem(String name, Supplier<Item> sup) {

        return ITEMS.register(name, sup);
    }

    public static RegistryObject<Item> registerItem(String name) {

        return registerItem(name, THERMAL_ITEMS, Rarity.COMMON);
    }

    public static RegistryObject<Item> registerItem(String name, ItemGroup group) {

        return registerItem(name, group, Rarity.COMMON);
    }

    public static RegistryObject<Item> registerItem(String name, Rarity rarity) {

        return registerItem(name, THERMAL_ITEMS, rarity);
    }

    public static RegistryObject<Item> registerItem(String name, ItemGroup group, Rarity rarity) {

        return ITEMS.register(name, () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
    }
    // endregion

    // region METAL SETS
    public static void registerMetalSet(String prefix) {

        registerMetalSet(prefix, THERMAL_ITEMS, Rarity.COMMON, false);
    }

    public static void registerMetalSet(String prefix, ItemGroup group) {

        registerMetalSet(prefix, group, Rarity.COMMON, false);
    }

    public static void registerMetalSet(String prefix, Rarity rarity) {

        registerMetalSet(prefix, THERMAL_ITEMS, rarity, false);
    }

    public static void registerMetalSet(String prefix, ItemGroup group, Rarity rarity) {

        registerMetalSet(prefix, group, rarity, false);
    }

    public static void registerMetalSet(String prefix, ItemGroup group, boolean vanilla) {

        registerMetalSet(prefix, group, Rarity.COMMON, vanilla);
    }

    public static void registerMetalSet(String prefix, ItemGroup group, Rarity rarity, boolean vanilla) {

        if (!vanilla) {
            ITEMS.register(prefix + "_ingot", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
            ITEMS.register(prefix + "_nugget", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        }
        ITEMS.register(prefix + "_dust", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_gear", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_plate", () -> new CountedItem(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_coin", () -> new CoinItem(new Item.Properties().group(group).rarity(rarity)));
    }
    // endregion

    // region GEM SETS
    public static void registerGemSet(String prefix) {

        registerGemSet(prefix, THERMAL_ITEMS, Rarity.COMMON, false);
    }

    public static void registerGemSet(String prefix, ItemGroup group) {

        registerGemSet(prefix, group, Rarity.COMMON, false);
    }

    public static void registerGemSet(String prefix, Rarity rarity) {

        registerGemSet(prefix, THERMAL_ITEMS, rarity, false);
    }

    public static void registerGemSet(String prefix, ItemGroup group, Rarity rarity) {

        registerGemSet(prefix, group, rarity, false);
    }

    public static void registerGemSet(String prefix, ItemGroup group, boolean vanilla) {

        registerGemSet(prefix, group, Rarity.COMMON, vanilla);
    }

    public static void registerGemSet(String prefix, ItemGroup group, Rarity rarity, boolean vanilla) {

        if (!vanilla) {
            ITEMS.register(prefix + "_gem", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        }
        // ITEMS.register(prefix + "_nugget", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_dust", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_gear", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        // ITEMS.register(prefix + "_plate", () -> new CountedItem(new Item.Properties().group(group).rarity(rarity)));
        // ITEMS.register(prefix + "_coin", () -> new CoinItem(new Item.Properties().group(group).rarity(rarity)));
    }
    // endregion

    // region TOOL SETS
    public static void registerStandardToolSet(String prefix, IItemTier tier, ItemGroup toolGroup, ItemGroup combatGroup) {

        ITEMS.register(prefix + "_shovel", () -> new ShovelItem(tier, 1.5F, -3.0F, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_pickaxe", () -> new PickaxeItem(tier, 1, -2.8F, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_axe", () -> new AxeItem(tier, tier.getAttackDamage() > 0 ? 8.0F - tier.getAttackDamage() : 6.0F, MathHelper.clamp(-3.7F + tier.getEfficiency() / 10, -3.2F, -3.0F), new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_hoe", () -> new HoeItem(tier, -3.0F + tier.getHarvestLevel(), new Item.Properties().group(toolGroup)));

        ITEMS.register(prefix + "_sword", () -> new SwordItem(tier, 3, -2.4F, new Item.Properties().group(combatGroup)));
    }

    public static void registerExtraToolSet(String prefix, IItemTier tier, ItemGroup toolGroup) {

        ITEMS.register(prefix + "_excavator", () -> new ExcavatorItem(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_hammer", () -> new HammerItem(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_sickle", () -> new SickleItem(tier, new Item.Properties().group(toolGroup)));
    }

    public static void registerSpecial(String prefix, IItemTier tier, ItemGroup toolGroup) {

        ITEMS.register(prefix + "_shears", () -> new ShearsItemCoFH(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_bow", () -> new BowItemCoFH(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_fishing_rod", () -> new FishingRodItemCoFH(tier, new Item.Properties().group(toolGroup)));
    }
    // endregion

    // region CROPS
    public static void registerAnnual(String id) {

        BLOCKS.register(id, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(ITEMS.getSup(id)).seed(ITEMS.getSup(seeds(id))));
    }

    public static void registerPerennial(String id) {

        BLOCKS.register(id, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(ITEMS.getSup(id)).seed(ITEMS.getSup(seeds(id))));
    }

    public static void registerCropAndSeed(String id) {

        registerCropAndSeed(id, THERMAL_ITEMS);
    }

    public static void registerCropAndSeed(String id, Food food) {

        registerCropAndSeed(id, THERMAL_ITEMS, food);
    }

    public static void registerCropAndSeed(String id, ItemGroup group) {

        registerCropAndSeed(id, group, null);
    }

    public static void registerCropAndSeed(String id, ItemGroup group, Food food) {

        if (food != null) {
            ITEMS.register(id, () -> new ItemCoFH(new Item.Properties().group(group).food(food)));
        } else {
            ITEMS.register(id, () -> new ItemCoFH(new Item.Properties().group(group)));
        }
        ITEMS.register(seeds(id), () -> new BlockNamedItemCoFH(BLOCKS.get(id), new Item.Properties().group(group)));
    }

    public static String block(String id) {

        return id + "_block";
    }

    public static String seeds(String id) {

        return id + "_seeds";
    }
    // endregion
}
