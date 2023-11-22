package com.hirakurai.hiracustomcrafts.models.recipeDTO.armor;

import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapelessRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ArmorShapelessRecipeExtendedDTO extends ShapelessRecipeDTO {
    private String slot;
    private Integer protectionToAdd;
    private Integer durabilityToAdd;
    private Float knockbackResistanceToAdd;

    public ArmorShapelessRecipeExtendedDTO(String recipeKey,
                                           Material resultItemMaterial,
                                           String resultItemName,
                                           List<String> resultItemLore,
                                           List<ShapelessCraftIngredient> craftIngredients,
                                           String slot,
                                           Integer protectionToAdd,
                                           Integer durabilityToAdd,
                                           Float knockbackResistanceToAdd) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore, craftIngredients);
        this.slot = slot;
        this.protectionToAdd = protectionToAdd;
        this.durabilityToAdd = durabilityToAdd;
        this.knockbackResistanceToAdd = knockbackResistanceToAdd;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Integer getProtectionToAdd() {
        return protectionToAdd;
    }

    public void setProtectionToAdd(Integer protectionToAdd) {
        this.protectionToAdd = protectionToAdd;
    }

    public Integer getDurabilityToAdd() {
        return durabilityToAdd;
    }

    public void setDurabilityToAdd(Integer durabilityToAdd) {
        this.durabilityToAdd = durabilityToAdd;
    }

    public Float getKnockbackResistanceToAdd() {
        return knockbackResistanceToAdd;
    }

    public void setKnockbackResistanceToAdd(Float knockbackResistanceToAdd) {
        this.knockbackResistanceToAdd = knockbackResistanceToAdd;
    }
}
