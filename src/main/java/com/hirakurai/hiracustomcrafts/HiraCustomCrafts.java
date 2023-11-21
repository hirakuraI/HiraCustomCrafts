package com.hirakurai.hiracustomcrafts;

import net.minecraft.world.item.Item;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class HiraCustomCrafts extends JavaPlugin {
    private static HiraCustomCrafts plugin;
    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HiraCustomCrafts getPlugin() {
        return plugin;
    }
}
