package com.hirakurai.hiracustomcrafts.registration;

import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.ShapelessCraftIngredient;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.RecipeGeneralDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.ShapelessRecipeDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponShapelessRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.ShapelessRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators.ArmorShapelessRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators.ToolShapelessRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators.WeaponShapelessRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.tools.NBTHelper;
import com.hirakurai.hiracustomcrafts.util.tools.StringHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ShapelessRecipeRegistrationManager {

    private List<ShapelessRecipeDTO> shapelessRecipeDataList;
    private List<ArmorShapelessRecipeExtendedDTO> armorShapelessRecipeDataList;
    private List<ToolShapelessRecipeExtendedDTO> toolShapelessRecipeDataList;
    private List<WeaponShapelessRecipeExtendedDTO> weaponShapelessRecipeDataList;

    public ShapelessRecipeRegistrationManager() {
        this.shapelessRecipeDataList = new ShapelessRecipeConfigurator().getItemShapelessRecipeDataList();
        this.armorShapelessRecipeDataList = new ArmorShapelessRecipeConfigurator().getArmorShapelessRecipeDataList();
        this.toolShapelessRecipeDataList = new ToolShapelessRecipeConfigurator().getToolShapelessRecipeDataList();
        this.weaponShapelessRecipeDataList = new WeaponShapelessRecipeConfigurator().getWeaponShapelessRecipeDataList();
    }

    public void registerShapelessRecipes() {
        Integer countOfRecipes = shapelessRecipeDataList.size() + armorShapelessRecipeDataList.size() + toolShapelessRecipeDataList.size() + weaponShapelessRecipeDataList.size();
        for (ShapelessRecipeDTO shapelessRecipeDTO : shapelessRecipeDataList) {
            registerItemShapelessRecipe(shapelessRecipeDTO);
        }
        for (ArmorShapelessRecipeExtendedDTO armorShapelessRecipeExtendedDTO : armorShapelessRecipeDataList) {
            registerArmorShapelessRecipe(armorShapelessRecipeExtendedDTO);
        }
        for (ToolShapelessRecipeExtendedDTO toolShapelessRecipeExtendedDTO : toolShapelessRecipeDataList) {
            registerToolShapelessRecipe(toolShapelessRecipeExtendedDTO);
        }
        for (WeaponShapelessRecipeExtendedDTO weaponShapelessRecipeExtendedDTO : weaponShapelessRecipeDataList) {
            registerWeaponShapelessRecipe(weaponShapelessRecipeExtendedDTO);
        }
        HiraCustomCrafts.getPlugin().getLogger().info(countOfRecipes + " shapeless recipes found.");
    }

    private void registerItemShapelessRecipe(ShapelessRecipeDTO itemRecipeData) {
        ItemStack resultItem = prepareResultItem(itemRecipeData);
        ShapelessRecipe recipe = prepareShapelessRecipe(itemRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerArmorShapelessRecipe(ArmorShapelessRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = prepareResultArmor(armorRecipeData);
        ShapelessRecipe recipe = prepareShapelessRecipe(armorRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerToolShapelessRecipe(ToolShapelessRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = prepareResultTool(toolRecipeData);
        ShapelessRecipe recipe = prepareShapelessRecipe(toolRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerWeaponShapelessRecipe(WeaponShapelessRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = prepareResultWeapon(weaponRecipeData);
        ShapelessRecipe recipe = prepareShapelessRecipe(weaponRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private ShapelessRecipe prepareShapelessRecipe(ShapelessRecipeDTO itemRecipeData, ItemStack resultItem) {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()), resultItem);
        List<ShapelessCraftIngredient> craftIngredients = itemRecipeData.getCraftIngredients();
        for (ShapelessCraftIngredient craftIngredient : craftIngredients) {
            if (craftIngredient.getNamespacedKey().getNamespace().equals("minecraft")) {
                recipe.addIngredient(craftIngredient.getCount(), Material.valueOf(craftIngredient.getNamespacedKey().getKey().toUpperCase()));
            } else {
                recipe.addIngredient(craftIngredient.getCount(), Bukkit.getRecipe(craftIngredient.getNamespacedKey()).getResult().getData());
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

    private ItemStack prepareResultArmor(ArmorShapelessRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = new ItemStack(armorRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                armorRecipeData.getResultItemName(),
                armorRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        if (armorRecipeData.getProtectionToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.armor",
                    "generic.armor",
                    armorRecipeData.getSlot(),
                    0,
                    armorRecipeData.getProtectionToAdd().doubleValue(),
                    random));
        }
        if (armorRecipeData.getDurabilityToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.armor_toughness",
                    "generic.armor_toughness",
                    armorRecipeData.getSlot(),
                    0,
                    armorRecipeData.getDurabilityToAdd().doubleValue(),
                    random));
        }
        if (armorRecipeData.getKnockbackResistanceToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.knockback_resistance",
                    "generic.knockback_resistance",
                    armorRecipeData.getSlot(),
                    0,
                    armorRecipeData.getKnockbackResistanceToAdd().doubleValue(),
                    random));
        }

        NBTTag.put("AttributeModifiers", attributeModifiers);
        NMSResultItemCopy.setTag(NBTTag);

        return CraftItemStack.asBukkitCopy(NMSResultItemCopy);
    }

    private ItemStack prepareResultTool(ToolShapelessRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = new ItemStack(toolRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                toolRecipeData.getResultItemName(),
                toolRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        if (toolRecipeData.getAttackDamageToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_damage",
                    "generic.attack_damage",
                    toolRecipeData.getSlot(),
                    0,
                    toolRecipeData.getAttackDamageToAdd().doubleValue(),
                    random));
        }
        if (toolRecipeData.getAttackSpeedToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_speed ",
                    "generic.attack_speed ",
                    toolRecipeData.getSlot(),
                    0,
                    toolRecipeData.getAttackSpeedToAdd().doubleValue(),
                    random));
        }

        NBTTag.put("AttributeModifiers", attributeModifiers);
        NMSResultItemCopy.setTag(NBTTag);

        return CraftItemStack.asBukkitCopy(NMSResultItemCopy);
    }

    private ItemStack prepareResultWeapon(WeaponShapelessRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = new ItemStack(weaponRecipeData.getResultItemMaterial());
        prepareItemMeta(resultItem,
                weaponRecipeData.getResultItemName(),
                weaponRecipeData.getResultItemLore());

        net.minecraft.world.item.ItemStack NMSResultItemCopy = CraftItemStack.asNMSCopy(resultItem);
        CompoundTag NBTTag = NMSResultItemCopy.getOrCreateTag();

        ListTag attributeModifiers = new ListTag();
        Random random = new Random();
        if (weaponRecipeData.getAttackDamageToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_damage",
                    "generic.attack_damage",
                    weaponRecipeData.getSlot(),
                    0,
                    weaponRecipeData.getAttackDamageToAdd().doubleValue(),
                    random));
        }
        if (weaponRecipeData.getAttackSpeedToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.attack_speed ",
                    "generic.attack_speed ",
                    weaponRecipeData.getSlot(),
                    0,
                    weaponRecipeData.getAttackSpeedToAdd().doubleValue(),
                    random));
        }

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
