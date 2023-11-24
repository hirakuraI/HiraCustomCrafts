package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolFurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/FurnaceRecipes/ToolFurnaceRecipesData.json");
    private List<ToolFurnaceRecipeExtendedDTO> toolFurnaceRecipeDataList;

    public ToolFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ToolFurnaceRecipeExtendedDTO> toolFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ToolFurnaceRecipeExtendedDTO("test_furnace_tool_key1",
                                        Material.WOODEN_AXE,
                                        "TestFurnaceToolName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        ),
                                        NamespacedKey.minecraft("iron_axe"),
                                        10,
                                        20,
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(toolFurnaceRecipeDataListExample, writer);
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
        toolFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ToolFurnaceRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ToolFurnaceRecipeExtendedDTO> getToolFurnaceRecipeDataList() {
        return toolFurnaceRecipeDataList;
    }
}
