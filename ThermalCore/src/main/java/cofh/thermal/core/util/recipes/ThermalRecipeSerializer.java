package cofh.thermal.core.util.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.RecipeJsonUtils.*;

public class ThermalRecipeSerializer<T extends ThermalRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    protected final int defaultEnergy;
    protected final ThermalRecipeSerializer.IFactory<T> recipeFactory;

    public ThermalRecipeSerializer(ThermalRecipeSerializer.IFactory<T> recipeFactory, int defaultEnergy) {

        this.recipeFactory = recipeFactory;
        this.defaultEnergy = defaultEnergy;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {

        int energy = defaultEnergy;
        float experience = 0.0F;
        int minTicks = -1;

        ArrayList<Ingredient> inputItems = new ArrayList<>();
        ArrayList<FluidStack> inputFluids = new ArrayList<>();
        ArrayList<ItemStack> outputItems = new ArrayList<>();
        ArrayList<Float> outputItemChances = new ArrayList<>();
        ArrayList<FluidStack> outputFluids = new ArrayList<>();

        /* INPUT */
        if (json.has(INGREDIENT)) {
            parseInputs(inputItems, inputFluids, json.get(INGREDIENT));
        } else if (json.has(INGREDIENTS)) {
            parseInputs(inputItems, inputFluids, json.get(INGREDIENTS));
        } else if (json.has(INPUT)) {
            parseInputs(inputItems, inputFluids, json.get(INPUT));
        } else if (json.has(INPUTS)) {
            parseInputs(inputItems, inputFluids, json.get(INPUTS));
        }

        /* OUTPUT */
        if (json.has(RESULT)) {
            parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RESULT));
        } else if (json.has(RESULTS)) {
            parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RESULTS));
        } else if (json.has(OUTPUT)) {
            parseOutputs(outputItems, outputItemChances, outputFluids, json.get(OUTPUT));
        } else if (json.has(OUTPUTS)) {
            parseOutputs(outputItems, outputItemChances, outputFluids, json.get(OUTPUTS));
        }

        /* ENERGY */
        if (json.has(ENERGY)) {
            energy = json.get(ENERGY).getAsInt();
        }
        if (json.has(ENERGY_MOD)) {
            energy *= json.get(ENERGY_MOD).getAsFloat();
        }
        /* EXPERIENCE */
        if (json.has(EXPERIENCE)) {
            experience = json.get(EXPERIENCE).getAsFloat();
        }
        /* MIN TICKS */
        if (json.has(MIN_TICKS)) {
            minTicks = json.get(MIN_TICKS).getAsInt();
        }
        return recipeFactory.create(recipeId, energy, experience, minTicks, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {

        int energy = buffer.readVarInt();
        float experience = buffer.readFloat();
        int minTicks = buffer.readVarInt();

        int numInputItems = buffer.readVarInt();
        ArrayList<Ingredient> inputItems = new ArrayList<>(numInputItems);
        for (int i = 0; i < numInputItems; ++i) {
            inputItems.add(Ingredient.read(buffer));
        }

        int numInputFluids = buffer.readVarInt();
        ArrayList<FluidStack> inputFluids = new ArrayList<>(numInputFluids);
        for (int i = 0; i < numInputFluids; ++i) {
            inputFluids.add(buffer.readFluidStack());
        }

        int numOutputItems = buffer.readVarInt();
        ArrayList<ItemStack> outputItems = new ArrayList<>(numOutputItems);
        ArrayList<Float> outputItemChances = new ArrayList<>(numOutputItems);
        for (int i = 0; i < numOutputItems; ++i) {
            outputItems.add(buffer.readItemStack());
            outputItemChances.add(buffer.readFloat());
        }

        int numOutputFluids = buffer.readVarInt();
        ArrayList<FluidStack> outputFluids = new ArrayList<>(numOutputFluids);
        for (int i = 0; i < numOutputFluids; ++i) {
            outputFluids.add(buffer.readFluidStack());
        }
        return recipeFactory.create(recipeId, energy, experience, minTicks, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {

        buffer.writeVarInt(recipe.energy);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.minTicks);

        int numInputItems = recipe.inputItems.size();
        buffer.writeVarInt(numInputItems);
        for (int i = 0; i < numInputItems; ++i) {
            recipe.inputItems.get(i).write(buffer);
        }
        int numInputFluids = recipe.inputFluids.size();
        buffer.writeVarInt(numInputFluids);
        for (int i = 0; i < numInputFluids; ++i) {
            buffer.writeFluidStack(recipe.inputFluids.get(i));
        }
        int numOutputItems = recipe.outputItems.size();
        buffer.writeVarInt(numOutputItems);
        for (int i = 0; i < numOutputItems; ++i) {
            buffer.writeItemStack(recipe.outputItems.get(i));
            buffer.writeFloat(recipe.outputItemChances.get(i));
        }
        int numOutputFluids = recipe.outputFluids.size();
        buffer.writeVarInt(numOutputFluids);
        for (int i = 0; i < numOutputFluids; ++i) {
            buffer.writeFluidStack(recipe.outputFluids.get(i));
        }
    }

    public interface IFactory<T extends ThermalRecipe> {

        T create(ResourceLocation recipeId, int energy, float experience, int minTicks, List<Ingredient> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids);

    }

}
