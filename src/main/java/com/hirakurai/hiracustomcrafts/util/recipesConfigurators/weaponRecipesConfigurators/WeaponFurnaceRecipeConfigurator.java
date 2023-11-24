package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponFurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/FurnaceRecipes/WeaponFurnaceRecipesData.json");
    private List<WeaponFurnaceRecipeExtendedDTO> weaponFurnaceRecipeDataList;

    public WeaponFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<WeaponFurnaceRecipeExtendedDTO> weaponFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new WeaponFurnaceRecipeExtendedDTO("test_furnace_weapon_key1",
                                        Material.WOODEN_SWORD,
                                        "TestFurnaceWeaponName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        ),
                                        NamespacedKey.minecraft("iron_sword"),
                                        10,
                                        20,
                                        "mainhand",
                                        1000,
                                        1000
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(weaponFurnaceRecipeDataListExample, writer);
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
        weaponFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, WeaponFurnaceRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<WeaponFurnaceRecipeExtendedDTO> getWeaponFurnaceRecipeDataList() {
        return weaponFurnaceRecipeDataList;
    }
}
