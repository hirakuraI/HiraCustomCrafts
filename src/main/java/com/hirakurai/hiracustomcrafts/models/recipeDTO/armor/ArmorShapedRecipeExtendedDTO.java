package com.hirakurai.hiracustomcrafts.models.recipeDTO.armor;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapedRecipeDTO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public class ArmorShapedRecipeExtendedDTO extends ShapedRecipeDTO {
    private Integer protectionToAdd;
    private Integer durabilityToAdd;
    private Float knockbackResistanceToAdd;

    public ArmorShapedRecipeExtendedDTO(String recipeKey,
                                        String craftRecipeUpperLine,
                                        String craftRecipeMiddleLine,
                                        String craftRecipeBottomLine,
                                        HashMap<Character, NamespacedKey> craftIngredients,
                                        Material resultItemMaterial,
                                        String resultItemName,
                                        List<String> resultItemLore,
                                        Integer protectionToAdd,
                                        Integer durabilityToAdd,
                                        Float knockbackResistanceToAdd) {
        super(recipeKey,
                craftRecipeUpperLine,
                craftRecipeMiddleLine,
                craftRecipeBottomLine,
                craftIngredients,
                resultItemMaterial,
                resultItemName,
                resultItemLore);
        this.protectionToAdd = protectionToAdd;
        this.durabilityToAdd = durabilityToAdd;
        this.knockbackResistanceToAdd = knockbackResistanceToAdd;
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
