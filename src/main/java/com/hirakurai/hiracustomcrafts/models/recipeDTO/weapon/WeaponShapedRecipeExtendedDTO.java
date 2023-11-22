package com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapedRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class WeaponShapedRecipeExtendedDTO extends ShapedRecipeDTO {
    private String slot;
    private Integer attackDamageToAdd;
    private Integer attackSpeedToAdd;

    public WeaponShapedRecipeExtendedDTO(String recipeKey,
                                         String craftRecipeUpperLine,
                                         String craftRecipeMiddleLine,
                                         String craftRecipeBottomLine,
                                         HashMap<Character, NamespacedKey> craftIngredients,
                                         Material resultItemMaterial,
                                         String resultItemName,
                                         List<String> resultItemLore,
                                         String slot,
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
