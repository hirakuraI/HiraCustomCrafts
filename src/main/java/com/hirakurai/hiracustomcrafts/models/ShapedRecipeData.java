package com.hirakurai.hiracustomcrafts.models;

import com.hirakurai.hiracustomcrafts.models.recipeData.RecipeGeneralData;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ShapedRecipeData extends RecipeGeneralData {
    private String craftRecipeUpperLine;
    private String craftRecipeMiddleLine;
    private String craftRecipeBottomLine;
    private HashMap<Character, NamespacedKey> craftIngredients;

    public ShapedRecipeData(String recipeKey,
                                String craftRecipeUpperLine,
                                String craftRecipeMiddleLine,
                                String craftRecipeBottomLine,
                                HashMap<Character, NamespacedKey> craftIngredients,
                                Material resultItemMaterial,
                                String resultItemName,
                                List<String> resultItemLore) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore);
        this.craftRecipeUpperLine = craftRecipeUpperLine;
        this.craftRecipeMiddleLine = craftRecipeMiddleLine;
        this.craftRecipeBottomLine = craftRecipeBottomLine;
        this.craftIngredients = craftIngredients;
    }

    public String getItemCraftRecipeUpperLine() {
        return craftRecipeUpperLine;
    }

    public void setItemCraftRecipeUpperLine(String craftRecipeUpperLine) {
        this.craftRecipeUpperLine = craftRecipeUpperLine;
    }

    public String getItemCraftRecipeMiddleLine() {
        return craftRecipeMiddleLine;
    }

    public void setItemCraftRecipeMiddleLine(String craftRecipeMiddleLine) {
        this.craftRecipeMiddleLine = craftRecipeMiddleLine;
    }

    public String getItemCraftRecipeBottomLine() {
        return craftRecipeBottomLine;
    }

    public void setItemCraftRecipeBottomLine(String craftRecipeBottomLine) {
        this.craftRecipeBottomLine = craftRecipeBottomLine;
    }

    public HashMap<Character, NamespacedKey> getCraftIngredients() {
        return craftIngredients;
    }

    public void setCraftIngredients(HashMap<Character, NamespacedKey> craftIngredients) {
        this.craftIngredients = craftIngredients;
    }
}
