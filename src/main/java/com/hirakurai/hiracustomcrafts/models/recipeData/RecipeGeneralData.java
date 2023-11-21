package com.hirakurai.hiracustomcrafts.models.recipeData;

import org.bukkit.Material;

import java.util.List;

public abstract class RecipeGeneralData {
    private String recipeKey;
    private Material resultItemMaterial;
    private String resultItemName;
    private List<String> resultItemLore;
    private Boolean protectionChanged;
    public RecipeGeneralData(String recipeKey, Material resultItemMaterial, String resultItemName, List<String> resultItemLore) {
        this.recipeKey = recipeKey;
        this.resultItemMaterial = resultItemMaterial;
        this.resultItemName = resultItemName;
        this.resultItemLore = resultItemLore;
    }

    public String getRecipeKey() {
        return recipeKey;
    }

    public void setRecipeKey(String recipeKey) {
        this.recipeKey = recipeKey;
    }

    public Material getResultItemMaterial() {
        return resultItemMaterial;
    }

    public void setResultItemMaterial(Material resultItemMaterial) {
        this.resultItemMaterial = resultItemMaterial;
    }

    public String getResultItemName() {
        return resultItemName;
    }

    public void setResultItemName(String resultItemName) {
        this.resultItemName = resultItemName;
    }

    public List<String> getResultItemLore() {
        return resultItemLore;
    }

    public void setResultItemLore(List<String> resultItemLore) {
        this.resultItemLore = resultItemLore;
    }
}
