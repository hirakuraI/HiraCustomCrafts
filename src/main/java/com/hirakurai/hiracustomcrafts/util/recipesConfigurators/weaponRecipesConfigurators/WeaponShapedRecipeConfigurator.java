package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WeaponShapedRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/ShapedRecipes/Weapon/WeaponShapedRecipesData.json");
    private List<WeaponShapedRecipeExtendedDTO> weaponShapedRecipeDataList;

    public WeaponShapedRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<WeaponShapedRecipeExtendedDTO> weaponShapedRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new WeaponShapedRecipeExtendedDTO("test_weapon_key1",
                                        " B ",
                                        " B ",
                                        " S ",
                                        new HashMap<Character, NamespacedKey>() {{
                                            put('B', NamespacedKey.minecraft("bedrock"));
                                            put('S', NamespacedKey.minecraft("stick"));
                                        }},
                                        Material.WOODEN_SWORD,
                                        "TestWeaponName1",
                                        Arrays.asList(
                                                "TestWeaponLore1",
                                                "TestWeaponLore2"
                                        ),
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(weaponShapedRecipeDataListExample, writer);
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
        weaponShapedRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, WeaponShapedRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<WeaponShapedRecipeExtendedDTO> getWeaponShapedRecipeDataList() {
        return weaponShapedRecipeDataList;
    }
}
