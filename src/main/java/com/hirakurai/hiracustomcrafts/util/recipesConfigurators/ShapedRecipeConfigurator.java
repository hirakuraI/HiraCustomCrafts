package com.hirakurai.hiracustomcrafts.util.recipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapedRecipeDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShapedRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapedRecipes/ShapedRecipesData.json");
    private List<ShapedRecipeDTO> itemShapedRecipeDataList;

    public ShapedRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ShapedRecipeDTO> itemShapedRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ShapedRecipeDTO("test_shaped_item_key1",
                                        "L L",
                                        "BBB",
                                        "BBB",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('L', NamespacedKey.minecraft("leather"));
                                        }},
                                        Material.BEDROCK,
                                        "TestShapedItemName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        )
                                ),
                                new ShapedRecipeDTO("test_shaped_item_key2",
                                        "LLL",
                                        "BLB",
                                        "BCB",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('L', NamespacedKey.minecraft("leather"));
                                            put('C', NamespacedKey.fromString("test_shaped_item_key1", HiraCustomCrafts.getPlugin()));
                                        }},
                                        Material.BEDROCK,
                                        "TestShapedItemName2",
                                        Arrays.asList(
                                                "TestItemLore3",
                                                "TestItemLore4"
                                        )
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(itemShapedRecipeDataListExample, writer);
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
        itemShapedRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ShapedRecipeDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ShapedRecipeDTO> getItemShapedRecipeDataList() {
        return itemShapedRecipeDataList;
    }
}
