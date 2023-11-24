package com.hirakurai.hiracustomcrafts.models.recipeDTO.armor;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.FurnaceRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public class ArmorFurnaceRecipeExtendedDTO extends FurnaceRecipeDTO {
    private String slot;
    private Integer protectionToAdd;
    private Short durability;
    private Integer toughnessToAdd;
    private Float knockbackResistanceToAdd;

    public ArmorFurnaceRecipeExtendedDTO(String recipeKey,
                                         Material resultItemMaterial,
                                         String resultItemName,
                                         List<String> resultItemLore,
                                         NamespacedKey craftIngredient,
                                         Integer experience,
                                         Integer cookingTime,
                                         String slot,
                                         Integer protectionToAdd,
                                         Short durability,
                                         Integer toughnessToAdd,
                                         Float knockbackResistanceToAdd) {
        super(recipeKey, resultItemMaterial, resultItemName, resultItemLore, craftIngredient, experience, cookingTime);
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
