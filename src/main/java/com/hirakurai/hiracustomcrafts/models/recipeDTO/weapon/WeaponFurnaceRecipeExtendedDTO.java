package com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.FurnaceRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public class WeaponFurnaceRecipeExtendedDTO extends FurnaceRecipeDTO {
    private String slot;
    private Integer attackDamageToAdd;
    private Integer attackSpeedToAdd;

    public WeaponFurnaceRecipeExtendedDTO(String recipeKey,
                                          Material resultItemMaterial,
                                          String resultItemName,
                                          List<String> resultItemLore,
                                          NamespacedKey craftIngredient,
                                          Integer experience,
                                          Integer cookingTime,
                                          String slot,
                                          Integer attackDamageToAdd,
                                          Integer attackSpeedToAdd) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore, craftIngredient, experience, cookingTime);
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
