package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ToolShapelessRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapelessRecipes/ToolShapelessRecipesData.json");
    private List<ToolShapelessRecipeExtendedDTO> toolShapelessRecipeDataList;

    public ToolShapelessRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ToolShapelessRecipeExtendedDTO> toolShapelessRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ToolShapelessRecipeExtendedDTO("test_shapeless_tool_key1",
                                        Material.WOODEN_AXE,
                                        "TestShapelessToolName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore1"
                                        ),
                                        Arrays.asList(
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("bedrock"), 3),
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("stick"), 3)
                                        ),
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(toolShapelessRecipeDataListExample, writer);
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
        toolShapelessRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ToolShapelessRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ToolShapelessRecipeExtendedDTO> getToolShapelessRecipeDataList() {
        return toolShapelessRecipeDataList;
    }
}
