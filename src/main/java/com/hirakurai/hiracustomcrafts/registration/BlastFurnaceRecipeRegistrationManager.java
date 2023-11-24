package com.hirakurai.hiracustomcrafts.registration;

import com.hirakurai.hiracustomcrafts.HiraCustomCrafts;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.BlastFurnaceRecipeDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.RecipeGeneralDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.armor.ArmorBlastFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolBlastFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.models.recipeDTO.weapon.WeaponBlastFurnaceRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.BlastFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators.ArmorBlastFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators.ToolBlastFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators.WeaponBlastFurnaceRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.tools.NBTHelper;
import com.hirakurai.hiracustomcrafts.util.tools.StringHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlastFurnaceRecipeRegistrationManager {
    private List<BlastFurnaceRecipeDTO> blastFurnaceRecipeDataList;
    private List<ArmorBlastFurnaceRecipeExtendedDTO> armorBlastFurnaceRecipeDataList;
    private List<ToolBlastFurnaceRecipeExtendedDTO> toolBlastFurnaceRecipeDataList;
    private List<WeaponBlastFurnaceRecipeExtendedDTO> weaponBlastFurnaceRecipeDataList;

    public BlastFurnaceRecipeRegistrationManager() {
        this.blastFurnaceRecipeDataList = new BlastFurnaceRecipeConfigurator().getItemBlastFurnaceRecipeDataList();
        this.armorBlastFurnaceRecipeDataList = new ArmorBlastFurnaceRecipeConfigurator().getArmorBlastFurnaceRecipeDataList();
        this.toolBlastFurnaceRecipeDataList = new ToolBlastFurnaceRecipeConfigurator().getToolBlastFurnaceRecipeDataList();
        this.weaponBlastFurnaceRecipeDataList = new WeaponBlastFurnaceRecipeConfigurator().getWeaponBlastFurnaceRecipeDataList();
    }

    public void registerBlastFurnaceRecipes() {
        Integer countOfRecipes = blastFurnaceRecipeDataList.size() + armorBlastFurnaceRecipeDataList.size() + toolBlastFurnaceRecipeDataList.size() + weaponBlastFurnaceRecipeDataList.size();
        for (BlastFurnaceRecipeDTO blastFurnaceRecipeDTO : blastFurnaceRecipeDataList) {
            registerItemBlastFurnaceRecipe(blastFurnaceRecipeDTO);
        }
        for (ArmorBlastFurnaceRecipeExtendedDTO armorBlastFurnaceRecipeExtendedDTO : armorBlastFurnaceRecipeDataList) {
            registerArmorBlastFurnaceRecipe(armorBlastFurnaceRecipeExtendedDTO);
        }
        for (ToolBlastFurnaceRecipeExtendedDTO toolBlastFurnaceRecipeExtendedDTO : toolBlastFurnaceRecipeDataList) {
            registerToolBlastFurnaceRecipe(toolBlastFurnaceRecipeExtendedDTO);
        }
        for (WeaponBlastFurnaceRecipeExtendedDTO weaponBlastFurnaceRecipeExtendedDTO : weaponBlastFurnaceRecipeDataList) {
            registerWeaponBlastFurnaceRecipe(weaponBlastFurnaceRecipeExtendedDTO);
        }
        HiraCustomCrafts.getPlugin().getLogger().info(countOfRecipes + " blast furnace recipes found.");
    }

    private void registerItemBlastFurnaceRecipe(BlastFurnaceRecipeDTO itemRecipeData) {
        ItemStack resultItem = prepareResultItem(itemRecipeData);
        BlastingRecipe recipe = prepareBlastFurnaceRecipe(itemRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerArmorBlastFurnaceRecipe(ArmorBlastFurnaceRecipeExtendedDTO armorRecipeData) {
        ItemStack resultItem = prepareResultArmor(armorRecipeData);
        BlastingRecipe recipe = prepareBlastFurnaceRecipe(armorRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerToolBlastFurnaceRecipe(ToolBlastFurnaceRecipeExtendedDTO toolRecipeData) {
        ItemStack resultItem = prepareResultTool(toolRecipeData);
        BlastingRecipe recipe = prepareBlastFurnaceRecipe(toolRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private void registerWeaponBlastFurnaceRecipe(WeaponBlastFurnaceRecipeExtendedDTO weaponRecipeData) {
        ItemStack resultItem = prepareResultWeapon(weaponRecipeData);
        BlastingRecipe recipe = prepareBlastFurnaceRecipe(weaponRecipeData, resultItem);
        Bukkit.getServer().addRecipe(recipe);
    }

    private BlastingRecipe prepareBlastFurnaceRecipe(BlastFurnaceRecipeDTO itemRecipeData, ItemStack resultItem) {
        BlastingRecipe recipe;
        if (itemRecipeData.getCraftIngredient().getNamespace().equals("minecraft")) {
            recipe = new BlastingRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()),
                    resultItem,
                    Material.valueOf(itemRecipeData.getCraftIngredient().getKey().toUpperCase()),
                    itemRecipeData.getExperience(),
                    itemRecipeData.getCookingTime());
        } else {
            recipe = new BlastingRecipe(new NamespacedKey(HiraCustomCrafts.getPlugin(), itemRecipeData.getRecipeKey()),
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

    private ItemStack prepareResultArmor(ArmorBlastFurnaceRecipeExtendedDTO armorRecipeData) {
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

    private ItemStack prepareResultTool(ToolBlastFurnaceRecipeExtendedDTO toolRecipeData) {
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

    private ItemStack prepareResultWeapon(WeaponBlastFurnaceRecipeExtendedDTO weaponRecipeData) {
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
