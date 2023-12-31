package com.hirakurai.hiracustomcrafts.models.recipeDTO.armor;

import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapelessRecipeDTO;
import org.bukkit.Material;

import java.util.List;

public class ArmorShapelessRecipeExtendedDTO extends ShapelessRecipeDTO {
    private String slot;
    private Integer protectionToAdd;
    private Short durability;
    private Integer toughnessToAdd;
    private Float knockbackResistanceToAdd;

    public ArmorShapelessRecipeExtendedDTO(String recipeKey,
                                           Material resultItemMaterial,
                                           String resultItemName,
                                           List<String> resultItemLore,
                                           List<ShapelessCraftIngredient> craftIngredients,
                                           String slot,
                                           Integer protectionToAdd,
                                           Short durability,
                                           Integer toughnessToAdd,
                                           Float knockbackResistanceToAdd) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore, craftIngredients);
        this.slot = slot;
        this.protectionToAdd = protectionToAdd;
        this.durability = durability;
        this.toughnessToAdd = toughnessToAdd;
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

    public Short getDurability() {
        return durability;
    }

    public void setDurability(Short durability) {
        this.durability = durability;
    }

    public Integer getToughnessToAdd() {
        return toughnessToAdd;
    }

    public void setToughnessToAdd(Integer toughnessToAdd) {
        this.toughnessToAdd = toughnessToAdd;
    }

    public Float getKnockbackResistanceToAdd() {
        return knockbackResistanceToAdd;
    }

    public void setKnockbackResistanceToAdd(Float knockbackResistanceToAdd) {
        this.knockbackResistanceToAdd = knockbackResistanceToAdd;
    }
}
