package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ToolShapedRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapedRecipes/Tool/ToolShapedRecipesData.json");
    private List<ToolShapedRecipeExtendedDTO> toolShapedRecipeDataList;

    public ToolShapedRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ToolShapedRecipeExtendedDTO> toolShapedRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ToolShapedRecipeExtendedDTO("test_tool_key1",
                                        "BBB",
                                        " S ",
                                        " S ",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('S', NamespacedKey.minecraft("stick"));
                                        }},
                                        Material.WOODEN_AXE,
                                        "TestToolName1",
                                        Arrays.asList(
                                                "TestToolLore1",
                                                "TestToolLore2"
                                        ),
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(toolShapedRecipeDataListExample, writer);
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
        toolShapedRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ToolShapedRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ToolShapedRecipeExtendedDTO> getToolShapedRecipeDataList() {
        return toolShapedRecipeDataList;
    }
}
