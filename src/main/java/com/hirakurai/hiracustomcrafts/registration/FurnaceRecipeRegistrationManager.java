package com.hirakurai.hiracustomcrafts.registration;

import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.FurnaceRecipeDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.RecipeGeneralDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.FurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators.ArmorFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators.ToolFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators.WeaponFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.tools.NBTHelper;
import com.hirakurai.hiracustomcrafts.util.tools.StringHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FurnaceRecipeRegistrationManager {
    private List<FurnaceRecipeDTO> furnaceRecipeDataList;
    private List<ArmorFurnaceRecipeExtendedDTO> armorFurnaceRecipeDataList;
    private List<ToolFurnaceRecipeExtendedDTO> toolFurnaceRecipeDataList;
    private List<WeaponFurnaceRecipeExtendedDTO> weaponFurnaceRecipeDataList;

    public FurnaceRecipeRegistrationManager() {
        this.furnaceRecipeDataList = new FurnaceRecipeConfigurator().getItemFurnaceRecipeDataList();
        this.armorFurnaceRecipeDataList = new ArmorFurnaceRecipeConfigurator().getArmorFurnaceRecipeDataList();
        this.toolFurnaceRecipeDataList = new ToolFurnaceRecipeConfigurator().getToolFurnaceRecipeDataList();
        this.weaponFurnaceRecipeDataList = new WeaponFurnaceRecipeConfigurator().getWeaponFurnaceRecipeDataList();
    }

    public void registerFurnaceRecipes() {
        Integer countOfRecipes = furnaceRecipeDataList.size() + armorFurnaceRecipeDataList.size() + toolFurnaceRecipeDataList.size() + weaponFurnaceRecipeDataList.size();
        for (FurnaceRecipeDTO furnaceRecipeDTO : furnaceRecipeDataList) {
            registerItemFurnaceRecipe(furnaceRecipeDTO);
        }
        for (ArmorFurnaceRecipeExtendedDTO armorFurnaceRecipeExtendedDTO : armorFurnaceRecipeDataList) {
            registerArmorFurnaceRecipe(armorFurnaceRecipeExtendedDTO);
        }
        for (ToolFurnaceRecipeExtendedDTO toolFurnaceRecipeExtendedDTO : toolFurnaceRecipeDataList) {
            registerToolFurnaceRecipe(toolFurnaceRecipeExtendedDTO);
        }
        for (WeaponFurnaceRecipeExtendedDTO weaponFurnaceRecipeExtendedDTO : weaponFurnaceRecipeDataList) {
            registerWeaponFurnaceRecipe(weaponFurnaceRecipeExtendedDTO);
        }
        HiraCustomCrafts.getPlugin().getLogger().info(countOfRecipes + " furnace recipes found.");
    }

    private void registerItemFurnaceRecipe(FurnaceRecipeDTO itemRecipeData) {
        ItemStack resultItem = prepareResultItem(itemRecipeData);
        FurnaceRecipe recipe = prepareFurnaceRecipe(itemRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerArmorFurnaceRecipe(ArmorFurnaceRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = prepareResultArmor(armorRecipeData);
        FurnaceRecipe recipe = prepareFurnaceRecipe(armorRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerToolFurnaceRecipe(ToolFurnaceRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = prepareResultTool(toolRecipeData);
        FurnaceRecipe recipe = prepareFurnaceRecipe(toolRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerWeaponFurnaceRecipe(WeaponFurnaceRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = prepareResultWeapon(weaponRecipeData);
        FurnaceRecipe recipe = prepareFurnaceRecipe(weaponRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private FurnaceRecipe prepareFurnaceRecipe(FurnaceRecipeDTO itemRecipeData, ItemStack resultItem) {
        FurnaceRecipe recipe;
        if (itemRecipeData.getCraftIngredient().getNamespace().equals("minecraft")) {
            recipe = new FurnaceRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()),
                    resultItem,
                    Material.valueOf(itemRecipeData.getCraftIngredient().getKey().toUpperCase()),
                    itemRecipeData.getExperience(),
                    itemRecipeData.getCookingTime());
        } else {
            recipe = new FurnaceRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()),
                    resultItem,
                    new RecipeChoice.ExactChoice(Bukkit.getRecipe(itemRecipeData.getCraftIngredient()).getResult()),
                    itemRecipeData.getExperience(),
                    itemRecipeData.getCookingTime());
        }
        return recipe;
    }

    private ItemStack prepareResultItem(RecipeGeneralDTO itemRecipeData) {
        ItemStack resultItem = new ItemStack(itemRecipeData.getResultItemMaterial());
        return prepareItemMeta(resultItem,
                itemRecipeData.getResultItemName(),
                itemRecipeData.getResultItemLore());
    }

    private ItemStack prepareResultArmor(ArmorFurnaceRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = new ItemStack(armorRecipeData.getResultItemMaterial());
        resultItem.setDurability((short) -armorRecipeData.getDurability());
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
        if (armorRecipeData.getToughnessToAdd() != 0) {
            attributeModifiers.add(NBTHelper.createAttributeModifier("generic.armor_toughness",
                    "generic.armor_toughness",
                    armorRecipeData.getSlot(),
                    0,
                    armorRecipeData.getToughnessToAdd().doubleValue(),
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

    private ItemStack prepareResultTool(ToolFurnaceRecipeExtendedDTO toolRecipeData) {
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

    private ItemStack prepareResultWeapon(WeaponFurnaceRecipeExtendedDTO weaponRecipeData) {
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
