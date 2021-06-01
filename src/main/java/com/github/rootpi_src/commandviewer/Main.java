package com.github.rootpi_src.commandviewer;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    Scheduler scheduler = new Scheduler();

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA+"[CommandViewer] Plugin Enabled.");
        getServer().getPluginManager().registerEvents(new Event(), this);

        scheduler.getPlugin(this);
        scheduler.run();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA+"[CommandViewer] Plugin Disabled.");
    }
}