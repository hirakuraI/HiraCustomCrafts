package com.hirakurai.hiracustomcrafts.util.recipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.BlastFurnaceRecipeDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlastFurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/BlastFurnaceRecipes/BlastFurnaceRecipesData.json");
    private List<BlastFurnaceRecipeDTO> itemBlastFurnaceRecipeDataList;

    public BlastFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<BlastFurnaceRecipeDTO> itemBlastFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new BlastFurnaceRecipeDTO("test_blast_furnace_item_key1",
                                        Material.BEDROCK,
                                        "TestBlastFurnaceItemName1",
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
                gson.toJson(itemBlastFurnaceRecipeDataListExample, writer);
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
        itemBlastFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, BlastFurnaceRecipeDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<BlastFurnaceRecipeDTO> getItemBlastFurnaceRecipeDataList() {
        return itemBlastFurnaceRecipeDataList;
    }
}
