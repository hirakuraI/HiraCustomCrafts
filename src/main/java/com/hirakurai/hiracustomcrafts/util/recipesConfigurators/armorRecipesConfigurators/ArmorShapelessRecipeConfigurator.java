package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmorShapelessRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapelessRecipes/ArmorShapelessRecipesData.json");
    private List<ArmorShapelessRecipeExtendedDTO> armorShapelessRecipeDataList;

    public ArmorShapelessRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ArmorShapelessRecipeExtendedDTO> armorShapelessRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ArmorShapelessRecipeExtendedDTO("test_shapeless_armor_key1",
                                        Material.LEATHER_CHESTPLATE,
                                        "TestShapelessArmorName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore1"
                                        ),
                                        Arrays.asList(
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("bedrock"), 8),
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("leather"), 1)
                                        ),
                                        "chest",
                                        10,
                                        (short) 300,
                                        3,
                                        0.01F

                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(armorShapelessRecipeDataListExample, writer);
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
        armorShapelessRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ArmorShapelessRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ArmorShapelessRecipeExtendedDTO> getArmorShapelessRecipeDataList() {
        return armorShapelessRecipeDataList;
    }
}
