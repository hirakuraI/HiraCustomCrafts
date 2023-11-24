package com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.JsonConfigurator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmorFurnaceRecipeConfigurator implements JsonConfigurator {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file = new File("./plugins/HiraCustomCrafts/CraftRecipes/FurnaceRecipes/ArmorFurnaceRecipesData.json");
    private List<ArmorFurnaceRecipeExtendedDTO> armorFurnaceRecipeDataList;

    public ArmorFurnaceRecipeConfigurator() {
        reload();
        uploadFromJsonFile();
    }

    public void reload() {
        if (!file.exists()) {
            try {
                createDataFolder();

                file.createNewFile();
                List<ArmorFurnaceRecipeExtendedDTO> armorFurnaceRecipeDataListExample = new ArrayList<>(
                        Arrays.asList(
                                new ArmorFurnaceRecipeExtendedDTO("test_furnace_armor_key1",
                                        Material.LEATHER_CHESTPLATE,
                                        "TestFurnaceArmorName1",
                                        Arrays.asList(
                                                "TestItemLore1",
                                                "TestItemLore2"
                                        ),
                                        NamespacedKey.minecraft("iron_chestplate"),
                                        10,
                                        20,
                                        "chest",
                                        10,
                                        (short) 300,
                                        300,
                                        0.01F
                                )
                        )
                );
                Writer writer = new FileWriter(file, false);
                gson.toJson(armorFurnaceRecipeDataListExample, writer);
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
        armorFurnaceRecipeDataList = new ArrayList<>(Arrays.asList(gson.fromJson(reader, ArmorFurnaceRecipeExtendedDTO[].class)));
    }

    private void createDataFolder() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<ArmorFurnaceRecipeExtendedDTO> getArmorFurnaceRecipeDataList() {
        return armorFurnaceRecipeDataList;
    }
}
