package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponBlastFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponBlastFurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/BlastFurnaceRecipes/WeaponBlastFurnaceRecipesData.json");
    private List<WeaponBlastFurnaceRecipeExtendedDTO> weaponBlastFurnaceRecipeDataList;

    public WeaponBlastFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<WeaponBlastFurnaceRecipeExtendedDTO> weaponBlastFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new WeaponBlastFurnaceRecipeExtendedDTO("test_blast_furnace_weapon_key1",
                                        Material.WOODEN_SWORD,
                                        "TestBlastFurnaceWeaponName1",
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
                gson.toJson(weaponBlastFurnaceRecipeDataListExample, writer);
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
        weaponBlastFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, WeaponBlastFurnaceRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<WeaponBlastFurnaceRecipeExtendedDTO> getWeaponBlastFurnaceRecipeDataList() {
        return weaponBlastFurnaceRecipeDataList;
    }
}
