package com.hirakurai.hiracustomcrafts.util.recipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapelessRecipeDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShapelessRecipeConfigurator implements JsonConfigurator {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapelessRecipes/ShapelessRecipesData.json");
    private List<ShapelessRecipeDTO> itemShapelessRecipeDataList;

    public ShapelessRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ShapelessRecipeDTO> itemShapelessRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ShapelessRecipeDTO("test_shapeless_item_key1",
                                        Material.BEDROCK,
                                        "TestShapelessItemName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        ),
                                        Arrays.asList(
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("bedrock"), 6),
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("stick"), 3)
                                        )
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(itemShapelessRecipeDataListExample, writer);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFromJsonFile() {
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        itemShapelessRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ShapelessRecipeDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ShapelessRecipeDTO> getItemShapelessRecipeDataList() {
        return itemShapelessRecipeDataList;
    }
}
