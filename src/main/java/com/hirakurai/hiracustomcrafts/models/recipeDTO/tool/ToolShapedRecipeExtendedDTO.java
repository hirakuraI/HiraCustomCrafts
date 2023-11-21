package com.hirakurai.hiracustomcrafts.models.recipeDTO.tool;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapedRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ToolShapedRecipeExtendedDTO extends ShapedRecipeDTO {
    private Integer attackDamageToAdd;
    private Integer attackSpeedToAdd;

    public ToolShapedRecipeExtendedDTO(String recipeKey,
                                       String craftRecipeUpperLine,
                                       String craftRecipeMiddleLine,
                                       String craftRecipeBottomLine,
                                       HashMap<Character, NamespacedKey> craftIngredients,
                                       Material resultItemMaterial,
                                       String resultItemName,
                                       List<String> resultItemLore,
                                       Integer attackDamageToAdd,
                                       Integer attackSpeedToAdd) {
        super(recipeKey,
                craftRecipeUpperLine,
                craftRecipeMiddleLine,
                craftRecipeBottomLine,
                craftIngredients,
                resultItemMaterial,
                resultItemName,
                resultItemLore);
        this.attackDamageToAdd = attackDamageToAdd;
        this.attackSpeedToAdd = attackSpeedToAdd;
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
