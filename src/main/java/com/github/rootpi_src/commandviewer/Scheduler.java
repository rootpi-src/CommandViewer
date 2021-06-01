package com.github.rootpi_src.commandviewer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public class Scheduler implements Runnable{

    Plugin plugin = null;

    public void getPlugin(Plugin p){
        plugin = p;
    }

    @Override
    public void run() {

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {

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
}
