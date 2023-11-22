package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WeaponShapelessRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapelessRecipes/WeaponShapelessRecipesData.json");
    private List<WeaponShapelessRecipeExtendedDTO> weaponShapelessRecipeDataList;

    public WeaponShapelessRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<WeaponShapelessRecipeExtendedDTO> weaponShapelessRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new WeaponShapelessRecipeExtendedDTO("test_shapeless_weapon_key1",
                                        Material.WOODEN_SWORD,
                                        "TestShapelessWeaponName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore1"
                                        ),
                                        Arrays.asList(
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("bedrock"), 2),
                                                new ShapelessCraftIngredient(NamespacedKey.minecraft("stick"), 3)
                                        ),
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(weaponShapelessRecipeDataListExample, writer);
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
        weaponShapelessRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, WeaponShapelessRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<WeaponShapelessRecipeExtendedDTO> getWeaponShapelessRecipeDataList() {
        return weaponShapelessRecipeDataList;
    }
}
