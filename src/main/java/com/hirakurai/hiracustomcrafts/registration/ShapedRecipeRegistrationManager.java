package com.hirakurai.hiracustomcrafts.registration;

import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.RecipeGeneralDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapedRecipeDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.tools.NBTHelper;
import com.hirakurai.hiracustomcrafts.util.tools.StringHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ShapedRecipeRegistrationManager {
    private List<ShapedRecipeDTO> shapedRecipeDataList;
    private List<ArmorShapedRecipeExtendedDTO> armorShapedRecipeDataList;
    private List<ToolShapedRecipeExtendedDTO> toolShapeRecipeDataList;
    private List<WeaponShapedRecipeExtendedDTO> weaponShapedRecipeDataList;

    public ShapedRecipeRegistrationManager(List<ShapedRecipeDTO> shapedRecipeDataList,
                                           List<ArmorShapedRecipeExtendedDTO> armorShapedRecipeDataList,
                                           List<ToolShapedRecipeExtendedDTO> toolShapeRecipeDataList,
                                           List<WeaponShapedRecipeExtendedDTO> weaponShapedRecipeDataList) {
        this.shapedRecipeDataList = shapedRecipeDataList;
        this.armorShapedRecipeDataList = armorShapedRecipeDataList;
        this.toolShapeRecipeDataList = toolShapeRecipeDataList;
        this.weaponShapedRecipeDataList = weaponShapedRecipeDataList;
    }

    public void registerShapedRecipes() {
        for (ShapedRecipeDTO shapedRecipeData : shapedRecipeDataList) {
            registerItemShapedRecipe(shapedRecipeData);
        }
        for (ArmorShapedRecipeExtendedDTO armorShapedRecipeExtendedDTO : armorShapedRecipeDataList) {
            registerArmorShapedRecipe(armorShapedRecipeExtendedDTO);
        }
        for (ToolShapedRecipeExtendedDTO toolShapedRecipeExtendedDTO : toolShapeRecipeDataList) {
            registerToolShapedRecipe(toolShapedRecipeExtendedDTO);
        }
        for (WeaponShapedRecipeExtendedDTO weaponShapedRecipeExtendedDTO : weaponShapedRecipeDataList) {
            registerWeaponShapedRecipe(weaponShapedRecipeExtendedDTO);
        }
    }

    private void registerItemShapedRecipe(ShapedRecipeDTO itemRecipeData) {
        ItemStack resultItem = prepareResultItem(itemRecipeData);
        ShapedRecipe recipe = prepareShapeRecipe(itemRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerArmorShapedRecipe(ArmorShapedRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = prepareResultArmor(armorRecipeData);
        ShapedRecipe recipe = prepareShapeRecipe(armorRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerToolShapedRecipe(ToolShapedRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = prepareResultTool(toolRecipeData);
        ShapedRecipe recipe = prepareShapeRecipe(toolRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerWeaponShapedRecipe(WeaponShapedRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = prepareResultWeapon(weaponRecipeData);
        ShapedRecipe recipe = prepareShapeRecipe(weaponRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private ShapedRecipe prepareShapeRecipe(ShapedRecipeDTO itemRecipeData, ItemStack resultItem) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()), resultItem);
        if (itemRecipeData.getItemCraftRecipeBottomLine().equals("   ")) {
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
            if (itemNamespacedKey.getNamespace().equals("minecraft")) {
                recipe.setIngredient(ingredientsKey, Material.valueOf(itemNamespacedKey.getKey().toUpperCase()));
            } else {
                recipe.setIngredient(ingredientsKey, Bukkit.getRecipe(craftIngredients.get(ingredientsKey)).getResult().getData());
            }
        }
        return recipe;
    }

    private ItemStack prepareResultItem(RecipeGeneralDTO itemRecipeData) {
        ItemStack resultItem = new ItemStack(itemRecipeData.getResultItemMaterial());
        return prepareItemMeta(resultItem,
                itemRecipeData.getResultItemName(),
                itemRecipeData.getResultItemLore());
    }

    private ItemStack prepareResultArmor(ArmorShapedRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = new ItemStack(armorRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                armorRecipeData.getResultItemName(),
                armorRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.armor",
                "generic.armor",
                armorRecipeData.getSlot(),
                0,
                armorRecipeData.getProtectionToAdd().doubleValue(),
                random));
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.armor_toughness",
                "generic.armor_toughness",
                armorRecipeData.getSlot(),
                0,
                armorRecipeData.getDurabilityToAdd().doubleValue(),
                random));
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.knockback_resistance",
                "generic.knockback_resistance",
                armorRecipeData.getSlot(),
                0,
                armorRecipeData.getKnockbackResistanceToAdd().doubleValue(),
                random));

        NBTTag.put("AttributeModifiers", attributeModifiers);
        NMSResultItemCopy.setTag(NBTTag);

        return CraftItemStack.asBukkitCopy(NMSResultItemCopy);
    }

    private ItemStack prepareResultTool(ToolShapedRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = new ItemStack(toolRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                toolRecipeData.getResultItemName(),
                toolRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_damage",
                "generic.attack_damage",
                toolRecipeData.getSlot(),
                0,
                toolRecipeData.getAttackDamageToAdd().doubleValue(),
                random));
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_speed ",
                "generic.attack_speed ",
                toolRecipeData.getSlot(),
                0,
                toolRecipeData.getAttackSpeedToAdd().doubleValue(),
                random));

        NBTTag.put("AttributeModifiers", attributeModifiers);
        NMSResultItemCopy.setTag(NBTTag);

        return CraftItemStack.asBukkitCopy(NMSResultItemCopy);
    }

    private ItemStack prepareResultWeapon(WeaponShapedRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = new ItemStack(weaponRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                weaponRecipeData.getResultItemName(),
                weaponRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_damage",
                "generic.attack_damage",
                weaponRecipeData.getSlot(),
                0,
                weaponRecipeData.getAttackDamageToAdd().doubleValue(),
                random));
        attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_speed ",
                "generic.attack_speed ",
                weaponRecipeData.getSlot(),
                0,
                weaponRecipeData.getAttackSpeedToAdd().doubleValue(),
                random));

        NBTTag.put("AttributeModifiers", attributeModifiers);
        NMSResultItemCopy.setTag(NBTTag);

        return CraftItemStack.asBukkitCopy(NMSResultItemCopy);
    }

    private ItemStack prepareItemMeta(ItemStack resultItem, String resultItemName, List<String> resultItemLore) {
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        resultItemMeta.setDisplayName(StringHelper.color(resultItemName));
        List<String> coloredLore = new ArrayList<>();
        for (int i = 0; i < resultItemLore.size(); i++) {
            coloredLore.add(i, StringHelper.color(resultItemLore.get(i)));
        }
        resultItemMeta.setLore(coloredLore);
        resultItem.setItemMeta(resultItemMeta);
        return resultItem;
    }
}