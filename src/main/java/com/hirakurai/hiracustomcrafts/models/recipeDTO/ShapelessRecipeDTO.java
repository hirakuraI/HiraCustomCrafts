package com.hirakurai.hiracustomcrafts.models.recipeDTO;

import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import org.bukkit.Material;

import java.util.List;

public class ShapelessRecipeDTO extends RecipeGeneralDTO {
    private List<ShapelessCraftIngredient> craftIngredients;

    public ShapelessRecipeDTO(String recipeKey,
                              Material resultItemMaterial,
                              String resultItemName,
                              List<String> resultItemLore,
                              List<ShapelessCraftIngredient> craftIngredients) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore);
        this.craftIngredients = craftIngredients;
    }

    public List<ShapelessCraftIngredient> getCraftIngredients() {
        return craftIngredients;
    }

    public void setCraftIngredients(List<ShapelessCraftIngredient> craftIngredients) {
        this.craftIngredients = craftIngredients;
    }
}
