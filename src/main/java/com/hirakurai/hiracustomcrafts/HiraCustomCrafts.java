package com.hirakurai.hiracustomcrafts;

import com.hirakurai.hiracustomcrafts.models.recipeDTO.tool.ToolShapedRecipeExtendedDTO;
import com.hirakurai.hiracustomcrafts.registration.ShapedRecipeRegistrationManager;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.ShapedRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.armorRecipesConfigurators.ArmorShapedRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.toolRecipesConfigurators.ToolShapedRecipeConfigurator;
import com.hirakurai.hiracustomcrafts.util.recipesConfigurators.weaponRecipesConfigurators.WeaponShapedRecipeConfigurator;
import org.bukkit.plugin.java.JavaPlugin;

public final class HiraCustomCrafts extends JavaPlugin {
    private static HiraCustomCrafts plugin;

    @Override
    public void onEnable() {
        plugin = this;
        ShapedRecipeConfigurator shapedRecipeConfigurator = new ShapedRecipeConfigurator();
        ArmorShapedRecipeConfigurator armorShapedRecipeConfigurator = new ArmorShapedRecipeConfigurator();
        ToolShapedRecipeConfigurator toolShapedRecipeConfigurator = new ToolShapedRecipeConfigurator();
        WeaponShapedRecipeConfigurator weaponShapedRecipeConfigurator = new WeaponShapedRecipeConfigurator();
        ShapedRecipeRegistrationManager shapedRecipeRegistrationManager = new ShapedRecipeRegistrationManager(shapedRecipeConfigurator.getItemShapedRecipeDataList(),
                armorShapedRecipeConfigurator.getArmorShapedRecipeDataList(),
                toolShapedRecipeConfigurator.getToolShapedRecipeDataList(),
                weaponShapedRecipeConfigurator.getWeaponShapedRecipeDataList());
        shapedRecipeRegistrationManager.registerShapedRecipes();
        this.getLogger().info("HiraCustomCrafts successfully enabled!");
        int countOfRecipes = shapedRecipeConfigurator.getItemShapedRecipeDataList().size() +
                armorShapedRecipeConfigurator.getArmorShapedRecipeDataList().size() +
                toolShapedRecipeConfigurator.getToolShapedRecipeDataList().size() +
                weaponShapedRecipeConfigurator.getWeaponShapedRecipeDataList().size();
        this.getLogger().info(countOfRecipes + " shaped recipes found.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("HiraCustomCrafts has been disabled!");
    }

    public static HiraCustomCrafts getPlugin() {
        return plugin;
    }
}
