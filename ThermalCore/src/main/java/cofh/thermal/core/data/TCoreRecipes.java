package cofh.thermal.core.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreRecipes extends RecipeProviderCoFH {

    public TCoreRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Core: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generateStorageRecipes(reg, consumer, reg.get(ID_CHARCOAL_BLOCK), Items.CHARCOAL);
        generateStorageRecipes(reg, consumer, reg.get(ID_BAMBOO_BLOCK), Items.BAMBOO);
        generateStorageRecipes(reg, consumer, reg.get(ID_SUGAR_CANE_BLOCK), Items.SUGAR_CANE);
        generateStorageRecipes(reg, consumer, reg.get(ID_GUNPOWDER_BLOCK), Items.GUNPOWDER);

        generateStorageRecipes(reg, consumer, reg.get(ID_APPLE_BLOCK), Items.APPLE);
        generateStorageRecipes(reg, consumer, reg.get(ID_BEETROOT_BLOCK), Items.BEETROOT);
        generateStorageRecipes(reg, consumer, reg.get(ID_CARROT_BLOCK), Items.CARROT);
        generateStorageRecipes(reg, consumer, reg.get(ID_POTATO_BLOCK), Items.POTATO);

        generateStorageRecipes(reg, consumer, reg.get(ID_APATITE_BLOCK), reg.get("apatite"));
        generateStorageRecipes(reg, consumer, reg.get(ID_CINNABAR_BLOCK), reg.get("cinnabar"));
        generateStorageRecipes(reg, consumer, reg.get(ID_NITER_BLOCK), reg.get("niter"));
        generateStorageRecipes(reg, consumer, reg.get(ID_SULFUR_BLOCK), reg.get("sulfur"));

        generateStorageRecipes(reg, consumer, "copper");
        generateStorageRecipes(reg, consumer, "tin");
        generateStorageRecipes(reg, consumer, "lead");
        generateStorageRecipes(reg, consumer, "silver");
        generateStorageRecipes(reg, consumer, "nickel");

        generateStorageRecipes(reg, consumer, "bronze");
        generateStorageRecipes(reg, consumer, "electrum");
        generateStorageRecipes(reg, consumer, "invar");
        generateStorageRecipes(reg, consumer, "constantan");

        //        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(dust), ingot, 0, 200)
        //                .addCriterion("has_" + dustName, hasItem(dust))
        //                .build(consumer, this.modid + ":" +ingotName + "_from_dust_smelting");
        //
        //        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(dust), ingot, 0, 100)
        //                .addCriterion("has_" + dustName, hasItem(dust))
        //                .build(consumer, this.modid + ":" +ingotName + "_from_dust_blasting");
        //
        //        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(dust), ingot, 0, 200)
        //                .addCriterion("has_" + dustName, hasItem(dust))
        //                .build(consumer, this.modid + ":" +ingotName + "_from_dust_smelting");
        //
        //        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(dust), ingot, 0, 100)
        //                .addCriterion("has_" + dustName, hasItem(dust))
        //                .build(consumer, this.modid + ":" +ingotName + "_from_dust_blasting");

        generateSmeltingAndBlastingRecipes(reg, consumer, "copper", 0.6F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "tin", 0.6F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "silver", 1.0F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "nickel", 1.0F);

        generateSmeltingAndBlastingRecipes(reg, consumer, "bronze", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "electrum", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "invar", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "constantan", 0);

        generateStorageRecipes(reg, consumer, "signalum");
        generateStorageRecipes(reg, consumer, "lumium");
        generateStorageRecipes(reg, consumer, "enderium");

        generateSmeltingAndBlastingRecipes(reg, consumer, "signalum", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "lumium", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "enderium", 0);

        // generateAlloyRecipes(consumer);
        generateWrenchRecipes(consumer);
    }

    // region HELPERS
    private void generateAlloyRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        ShapedRecipeBuilder.shapedRecipe(reg.get("enderium_ingot"))
                .key('B', Items.BLAZE_POWDER)
                .key('E', reg.get("electrum_ingot"))
                .key('L', reg.get("lead_ingot"))
                .key('P', Items.ENDER_PEARL)
                .patternLine("LLL")
                .patternLine("PBP")
                .patternLine("EEE")
                .addCriterion("has_ender_pearl", hasItem(Items.ENDER_PEARL))
                .build(consumer);
    }

    private void generateWrenchRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        ShapelessRecipeBuilder.shapelessRecipe(reg.get("redprint"))
                .addIngredient(Items.PAPER)
                .addIngredient(Items.PAPER)
                .addIngredient(Items.REDSTONE)
                .addIngredient(Items.REDSTONE)
                .addCriterion("has_redstone", hasItem(Items.REDSTONE))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(reg.get("phytogro"), 8)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(reg.get("apatite"))
                .addIngredient(reg.get("apatite"))
                .addIngredient(reg.get("niter"))
                .addCriterion("has_apatite", hasItem(reg.get("apatite")))
                .build(consumer, ID_THERMAL + ":phytogro_8");

        ShapelessRecipeBuilder.shapelessRecipe(reg.get("phytogro"), 4)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(Items.BONE_MEAL)
                .addIngredient(reg.get("apatite"))
                .addIngredient(reg.get("niter"))
                .addCriterion("has_apatite", hasItem(reg.get("apatite")))
                .build(consumer, ID_THERMAL + ":phytogro_4");

        ShapelessRecipeBuilder.shapelessRecipe(Items.GUNPOWDER, 4)
                .addIngredient(Items.CHARCOAL)
                .addIngredient(reg.get("niter"))
                .addIngredient(reg.get("niter"))
                .addIngredient(reg.get("sulfur"))
                .addCriterion("has_gunpowder", hasItem(Items.GUNPOWDER))
                .build(consumer, ID_THERMAL + ":gunpowder");

        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 4)
                .addIngredient(Items.PRISMARINE)
                .addIngredient(reg.get("wrench"))
                .addCriterion("has_prismarine", hasItem(Items.PRISMARINE))
                .build(consumer, ID_THERMAL + ":split_prismarine");

        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 9)
                .addIngredient(Items.PRISMARINE_BRICKS)
                .addIngredient(reg.get("wrench"))
                .addCriterion("has_prismarine_bricks", hasItem(Items.PRISMARINE_BRICKS))
                .build(consumer, ID_THERMAL + ":split_prismarine_bricks");

        ShapelessRecipeBuilder.shapelessRecipe(Items.QUARTZ, 4)
                .addIngredient(Items.QUARTZ_BLOCK)
                .addIngredient(reg.get("wrench"))
                .addCriterion("has_quartz_block", hasItem(Items.QUARTZ_BLOCK))
                .build(consumer, ID_THERMAL + ":split_quartz_block");
    }
    // endregion
}
