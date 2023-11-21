package com.hirakurai.hiracustomcrafts.util.recipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.ShapedRecipeData;
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
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapedRecipesData.json");
    private List<ShapedRecipeData> itemShapedRecipeDataList;

    public ShapedRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                if (!file.getParentFile().getParentFile().exists()) {
                    file.getParentFile().getParentFile().mkdir();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdir();
                }
                file.createNewFile();
                List<ShapedRecipeData> itemShapedRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ShapedRecipeData("test_item_key1",
                                        "L L",
                                        "BBB",
                                        "BBB",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('L', NamespacedKey.minecraft("leather"));
                                        }},
                                        Material.BEDROCK,
                                        "TestItemName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        )
                                ),
                                new ShapedRecipeData("test_item_key2",
                                        "LLL",
                                        "BLB",
                                        "BCB",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('L', NamespacedKey.minecraft("leather"));
                                            put('C', NamespacedKey.fromString("test_item_key1", HiraCustomCrafts.getPlugin()));
                                        }},
                                        Material.BEDROCK,
                                        "TestItemName2",
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
        itemShapedRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ShapedRecipeData[].class)));
    }

    public List<ShapedRecipeData> getItemShapedRecipeDataList() {
        return itemShapedRecipeDataList;
    }
}
