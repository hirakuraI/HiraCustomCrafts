package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArmorShapedRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapedRecipes/Armor/ArmorShapedRecipesData.json");
    private List<ArmorShapedRecipeExtendedDTO> armorShapedRecipeDataList;

    public ArmorShapedRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ArmorShapedRecipeExtendedDTO> armorShapedRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ArmorShapedRecipeExtendedDTO("test_armor_key1",
                                        "B B",
                                        "BCB",
                                        "BBB",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('C', NamespacedKey.minecraft("cobblestone"));
                                        }},
                                        Material.LEATHER_CHESTPLATE,
                                        "TestArmorName1",
                                        Arrays.asList(
                                                "TestArmorLore1",
                                                "TestArmorLore2"
                                        ),
                                        "chest",
                                        10,
                                        300,
                                        0.01F
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(armorShapedRecipeDataListExample, writer);
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
        armorShapedRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ArmorShapedRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ArmorShapedRecipeExtendedDTO> getArmorShapedRecipeDataList() {
        return armorShapedRecipeDataList;
    }
}
