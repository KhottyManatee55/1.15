package cofh.thermal.core.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalReferences.*;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreItemModels extends ItemModelProviderCoFH {

    public TCoreItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Core: Item Models";
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        DeferredRegisterCoFH<Item> reg = ITEMS;

        registerVanilla(reg);
        registerResources(reg);
        registerTools(reg);

        registerAugments(reg);
        registerParts(reg);
        registerArmor(reg);
    }

    // region ITEM HELPERS
    private void registerVanilla(DeferredRegisterCoFH<Item> reg) {

        metalSet(reg, "iron", true);
        metalSet(reg, "gold", true);

        gemSet(reg, "diamond", true);
        gemSet(reg, "emerald", true);
    }

    private void registerResources(DeferredRegisterCoFH<Item> reg) {

        generated(reg.getSup("wood_dust"), DUSTS);

        generated(reg.getSup("apatite"), RESOURCES);
        generated(reg.getSup("cinnabar"), RESOURCES);
        generated(reg.getSup("niter"), RESOURCES);
        generated(reg.getSup("sulfur"), RESOURCES);

        generated(reg.getSup("basalz_rod"), RESOURCES);
        generated(reg.getSup("basalz_powder"), RESOURCES);
        generated(reg.getSup("blitz_rod"), RESOURCES);
        generated(reg.getSup("blitz_powder"), RESOURCES);
        generated(reg.getSup("blizz_rod"), RESOURCES);
        generated(reg.getSup("blizz_powder"), RESOURCES);

        metalSet(reg, "copper");
        metalSet(reg, "lead");
        metalSet(reg, "nickel");
        metalSet(reg, "silver");
        metalSet(reg, "tin");

        metalSet(reg, "bronze");
        metalSet(reg, "constantan");
        metalSet(reg, "electrum");
        metalSet(reg, "invar");

        gemSet(reg, "ruby");
        gemSet(reg, "sapphire");

        metalSet(reg, "signalum");
        metalSet(reg, "lumium");
        metalSet(reg, "enderium");
    }

    private void registerAugments(DeferredRegisterCoFH<Item> reg) {

        for (int i = 1; i <= 4; ++i) {
            generated(reg.getSup("upgrade_augment_" + i), AUGMENTS);
            generated(reg.getSup("fluid_tank_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_stor_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_xfer_augment_" + i), AUGMENTS);
        }
        generated(reg.getSup("area_radius_augment"), AUGMENTS);
        generated(reg.getSup("potion_amplifier_augment"), AUGMENTS);
        generated(reg.getSup("potion_duration_augment"), AUGMENTS);

        generated(reg.getSup("machine_speed_augment"), AUGMENTS);
        generated(reg.getSup("machine_output_augment"), AUGMENTS);
        generated(reg.getSup("machine_catalyst_augment"), AUGMENTS);
        generated(reg.getSup("dynamo_output_augment"), AUGMENTS);
        generated(reg.getSup("dynamo_fuel_augment"), AUGMENTS);
    }

    private void registerParts(DeferredRegisterCoFH<Item> reg) {

        generated(reg.getSup("redstone_servo"), CRAFTING);
        generated(reg.getSup("rf_coil"), CRAFTING);
        generated(reg.getSup("drill_head"), CRAFTING);
        generated(reg.getSup("laser_diode"), CRAFTING);
        generated(reg.getSup("saw_blade"), CRAFTING);
    }

    private void registerTools(DeferredRegisterCoFH<Item> reg) {

        handheld(reg.getSup("wrench"), TOOLS);
        generated(reg.getSup("redprint"), TOOLS);
        generated(reg.getSup("lock"), TOOLS);
        generated(reg.getSup("phytogro"), TOOLS);
    }

    private void registerArmor(DeferredRegisterCoFH<Item> reg) {

        generated(reg.getSup(ID_BEEKEEPER_HELMET), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_CHESTPLATE), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_LEGGINGS), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_BOOTS), ARMOR);

        generated(reg.getSup(ID_HAZMAT_HELMET), ARMOR);
        generated(reg.getSup(ID_HAZMAT_CHESTPLATE), ARMOR);
        generated(reg.getSup(ID_HAZMAT_LEGGINGS), ARMOR);
        generated(reg.getSup(ID_HAZMAT_BOOTS), ARMOR);
    }
    // endregion

    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        registerVanillaBlocks(reg);
        registerResourceBlocks(reg);
        registerStorageBlocks(reg);
        registerBuildingBlocks(reg);
    }

    // region BLOCK HELPERS
    private void registerVanillaBlocks(DeferredRegisterCoFH<Block> reg) {

        blockItem(reg.getSup(ID_CHARCOAL_BLOCK));
        blockItem(reg.getSup(ID_BAMBOO_BLOCK));
        blockItem(reg.getSup(ID_SUGAR_CANE_BLOCK));
        blockItem(reg.getSup(ID_GUNPOWDER_BLOCK));

        blockItem(reg.getSup(ID_APPLE_BLOCK));
        blockItem(reg.getSup(ID_BEETROOT_BLOCK));
        blockItem(reg.getSup(ID_CARROT_BLOCK));
        blockItem(reg.getSup(ID_POTATO_BLOCK));
    }

    private void registerResourceBlocks(DeferredRegisterCoFH<Block> reg) {

        blockItem(reg.getSup(ID_APATITE_ORE));
        blockItem(reg.getSup(ID_CINNABAR_ORE));
        blockItem(reg.getSup(ID_NITER_ORE));
        blockItem(reg.getSup(ID_SULFUR_ORE));

        blockItem(reg.getSup(ID_COPPER_ORE));
        blockItem(reg.getSup(ID_LEAD_ORE));
        blockItem(reg.getSup(ID_NICKEL_ORE));
        blockItem(reg.getSup(ID_SILVER_ORE));
        blockItem(reg.getSup(ID_TIN_ORE));

        blockItem(reg.getSup(ID_RUBY_ORE));
        blockItem(reg.getSup(ID_SAPPHIRE_ORE));
    }

    private void registerStorageBlocks(DeferredRegisterCoFH<Block> reg) {

        blockItem(reg.getSup(ID_APATITE_BLOCK));
        blockItem(reg.getSup(ID_CINNABAR_BLOCK));
        blockItem(reg.getSup(ID_NITER_BLOCK));
        blockItem(reg.getSup(ID_SULFUR_BLOCK));

        blockItem(reg.getSup(ID_COPPER_BLOCK));
        blockItem(reg.getSup(ID_LEAD_BLOCK));
        blockItem(reg.getSup(ID_NICKEL_BLOCK));
        blockItem(reg.getSup(ID_SILVER_BLOCK));
        blockItem(reg.getSup(ID_TIN_BLOCK));

        blockItem(reg.getSup(ID_BRONZE_BLOCK));
        blockItem(reg.getSup(ID_ELECTRUM_BLOCK));
        blockItem(reg.getSup(ID_INVAR_BLOCK));
        blockItem(reg.getSup(ID_CONSTANTAN_BLOCK));

        blockItem(reg.getSup(ID_RUBY_BLOCK));
        blockItem(reg.getSup(ID_SAPPHIRE_BLOCK));

        blockItem(reg.getSup(ID_ENDERIUM_BLOCK));
        blockItem(reg.getSup(ID_LUMIUM_BLOCK));
        blockItem(reg.getSup(ID_SIGNALUM_BLOCK));
    }

    private void registerBuildingBlocks(DeferredRegisterCoFH<Block> reg) {

        blockItem(reg.getSup(ID_ENDERIUM_GLASS));
        blockItem(reg.getSup(ID_LUMIUM_GLASS));
        blockItem(reg.getSup(ID_SIGNALUM_GLASS));
    }
    // endregion
}
