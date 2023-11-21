package com.hirakurai.hiracustomcrafts.registration;

import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.ShapedRecipeData;
import com.hirakurai.hiracustomcrafts.models.recipeData.RecipeGeneralData;
import com.hirakurai.hiracustomcrafts.util.tools.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RecipeRegistrationManager {
    public static void registerShapedRecipes(List<ShapedRecipeData> shapedRecipeDataList){
        for (ShapedRecipeData shapedRecipeData : shapedRecipeDataList) {
            registerShapedRecipe(shapedRecipeData);
        }
    }
    public static void registerShapedRecipe(ShapedRecipeData itemRecipeData){
        ItemStack resultItem = prepareResultItem(itemRecipeData);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()), resultItem);
        if(itemRecipeData.getItemCraftRecipeBottomLine().equals("   ")){
            recipe.shape(
                    itemRecipeData.getItemCraftRecipeUpperLine(),
                    itemRecipeData.getItemCraftRecipeMiddleLine());
        } else {
            recipe.shape(
                    itemRecipeData.getItemCraftRecipeUpperLine(),
                    itemRecipeData.getItemCraftRecipeMiddleLine(),
                    itemRecipeData.getItemCraftRecipeBottomLine());
        }
        HashMap<Character, NamespacedKey> craftIngredients = itemRecipeData.getCraftIngredients();
        Set<Character> ingredientsKeys = craftIngredients.keySet();
        for (Character ingredientsKey : ingredientsKeys) {
            NamespacedKey itemNamespacedKey = craftIngredients.get(ingredientsKey);
            if(itemNamespacedKey.getNamespace().equals("minecraft")){
                recipe.setIngredient(ingredientsKey, Material.valueOf(itemNamespacedKey.getKey().toUpperCase()));
            } else {
                recipe.setIngredient(ingredientsKey, Bukkit.getRecipe(craftIngredients.get(ingredientsKey)).getResult().getData());
            }
        }

        Bukkit.getServer().addRecipe(recipe);
    }

    public static ItemStack prepareResultItem(RecipeGeneralData itemRecipeData){
        ItemStack resultItem = new ItemStack(itemRecipeData.getResultItemMaterial());
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        resultItemMeta.setDisplayName(StringHelper.color(itemRecipeData.getResultItemName()));
        List<String> coloredLore = new ArrayList<>();
        List<String> resultItemLore = itemRecipeData.getResultItemLore();
        for (int i = 0; i < resultItemLore.size(); i++) {
            coloredLore.add(i, StringHelper.color(resultItemLore.get(i)));
        }
        resultItemMeta.setLore(coloredLore);
        resultItem.setItemMeta(resultItemMeta);
        return resultItem;
    }
}
