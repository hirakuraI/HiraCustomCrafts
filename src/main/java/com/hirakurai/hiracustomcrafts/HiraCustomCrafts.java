package com.hirakurai.hiracustomcrafts;

import com.hirakurai.hiracustomcrafts.registration.ShapedRecipeRegistrationManager;
import com.hirakurai.hiracustomcrafts.registration.ShapelessRecipeRegistrationManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HiraCustomCrafts extends JavaPlugin {
    private static HiraCustomCrafts plugin;

    @Override
    public void onEnable() {
        plugin = this;
        ShapedRecipeRegistrationManager shapedRecipeRegistrationManager = new ShapedRecipeRegistrationManager();
        shapedRecipeRegistrationManager.registerShapedRecipes();
        ShapelessRecipeRegistrationManager shapelessRecipeRegistrationManager = new ShapelessRecipeRegistrationManager();
        shapelessRecipeRegistrationManager.registerShapelessRecipes();
        this.getLogger().info("HiraCustomCrafts successfully enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("HiraCustomCrafts has been disabled!");
    }

    public static HiraCustomCrafts getPlugin() {
        return plugin;
    }
}
