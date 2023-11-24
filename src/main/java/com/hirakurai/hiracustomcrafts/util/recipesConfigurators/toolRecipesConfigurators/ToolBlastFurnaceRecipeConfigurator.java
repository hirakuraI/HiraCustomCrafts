package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolBlastFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolBlastFurnaceRecipeConfigurator implements JsonConfigurator {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/BlastFurnaceRecipes/ToolBlastFurnaceRecipesData.json");
    private List<ToolBlastFurnaceRecipeExtendedDTO> toolBlastFurnaceRecipeDataList;

    public ToolBlastFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ToolBlastFurnaceRecipeExtendedDTO> toolBlastFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ToolBlastFurnaceRecipeExtendedDTO("test_blast_furnace_tool_key1",
                                        Material.WOODEN_AXE,
                                        "TestBlastFurnaceToolName1",
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
                gson.toJson(toolBlastFurnaceRecipeDataListExample, writer);
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
        toolBlastFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ToolBlastFurnaceRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ToolBlastFurnaceRecipeExtendedDTO> getToolBlastFurnaceRecipeDataList() {
        return toolBlastFurnaceRecipeDataList;
    }
}
