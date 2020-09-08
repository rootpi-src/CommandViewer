package com.github.rootpi_src.commandviewer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA+"[CommandViewer] Plugin Enabled.");
        getServer().getPluginManager().registerEvents(new Event(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {

            for(Player player : Bukkit.getOnlinePlayers()){

                if(Objects.requireNonNull(player.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.DIAMOND_SWORD))) {
                    Block target = player.getTargetBlock(null, 100);
                    Material material = target.getBlockData().getMaterial();

                    if(material == Material.COMMAND_BLOCK || material == Material.CHAIN_COMMAND_BLOCK || material == Material.REPEATING_COMMAND_BLOCK) {
                        CommandBlock commandBlock = (CommandBlock) target.getState();
                        player.sendActionBar(commandBlock.getCommand());
                    }

                }

            }

        }, 0L, 0L);

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA+"[CommandViewer] Plugin Disabled.");
    }
}