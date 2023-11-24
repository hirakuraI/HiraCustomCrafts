package com.hirakurai.hiracustomcrafts.models.recipeDTO;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public class BlastFurnaceRecipeDTO extends RecipeGeneralDTO {
    private NamespacedKey craftIngredient;
    private Integer experience;
    private Integer cookingTime;

    public BlastFurnaceRecipeDTO(String recipeKey,
                                 Material resultItemMaterial,
                                 String resultItemName,
                                 List<String> resultItemLore,
                                 NamespacedKey craftIngredient,
                                 Integer experience,
                                 Integer cookingTime) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore);
        this.craftIngredient = craftIngredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public NamespacedKey getCraftIngredient() {
        return craftIngredient;
    }

    public void setCraftIngredient(NamespacedKey craftIngredient) {
        this.craftIngredient = craftIngredient;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }
}
