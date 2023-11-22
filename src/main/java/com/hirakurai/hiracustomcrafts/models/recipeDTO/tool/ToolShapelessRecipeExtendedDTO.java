package com.hirakurai.hiracustomcrafts.models.recipeDTO.tool;

import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapelessRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ToolShapelessRecipeExtendedDTO extends ShapelessRecipeDTO {
    private String slot;
    private Integer attackDamageToAdd;
    private Integer attackSpeedToAdd;

    public ToolShapelessRecipeExtendedDTO(String recipeKey,
                                          Material resultItemMaterial,
                                          String resultItemName,
                                          List<String> resultItemLore,
                                          List<ShapelessCraftIngredient> craftIngredients,
                                          String slot,
                                          Integer attackDamageToAdd,
                                          Integer attackSpeedToAdd) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore, craftIngredients);
        this.slot = slot;
        this.attackDamageToAdd = attackDamageToAdd;
        this.attackSpeedToAdd = attackSpeedToAdd;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Integer getAttackDamageToAdd() {
        return attackDamageToAdd;
    }

    public void setAttackDamageToAdd(Integer attackDamageToAdd) {
        this.attackDamageToAdd = attackDamageToAdd;
    }

    public Integer getAttackSpeedToAdd() {
        return attackSpeedToAdd;
    }

    public void setAttackSpeedToAdd(Integer attackSpeedToAdd) {
        this.attackSpeedToAdd = attackSpeedToAdd;
    }
}
