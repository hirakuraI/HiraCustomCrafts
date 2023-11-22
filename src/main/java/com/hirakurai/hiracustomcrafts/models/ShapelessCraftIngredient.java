package com.hirakurai.hiracustomcrafts.models;

import org.bukkit.NamespacedKey;

public class ShapelessCraftIngredient {
    private NamespacedKey namespacedKey;
    private Integer count;

    public ShapelessCraftIngredient(NamespacedKey namespacedKey, Integer count) {
        this.namespacedKey = namespacedKey;
        this.count = count;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public void setNamespacedKey(NamespacedKey namespacedKey) {
        this.namespacedKey = namespacedKey;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
