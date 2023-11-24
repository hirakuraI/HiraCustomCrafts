package com.hirakurai.hiracustomcrafts.util.recipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.FurnaceRecipeDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/FurnaceRecipes/FurnaceRecipesData.json");
    private List<FurnaceRecipeDTO> itemFurnaceRecipeDataList;

    public FurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<FurnaceRecipeDTO> itemFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new FurnaceRecipeDTO("test_furnace_item_key1",
                                        Material.BEDROCK,
                                        "TestFurnaceItemName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        ),
                                        NamespacedKey.minecraft("bedrock"),
                                        10,
                                        20
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(itemFurnaceRecipeDataListExample, writer);
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
        itemFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, FurnaceRecipeDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<FurnaceRecipeDTO> getItemFurnaceRecipeDataList() {
        return itemFurnaceRecipeDataList;
    }
}
