package com.github.rootpi_src.commandviewer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Event implements Listener {

    @EventHandler
    public void blockDamage(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (player.isSneaking() && (Objects.requireNonNull(player.getEquipment()).getItemInMainHand().equals(new ItemStack(Material.DIAMOND_SWORD)))) {

                Block target = event.getClickedBlock();
                Material material = target.getBlockData().getMaterial();

                if (material == Material.COMMAND_BLOCK || material == Material.CHAIN_COMMAND_BLOCK || material == Material.REPEATING_COMMAND_BLOCK) {
                    CommandBlock commandBlock = (CommandBlock) target.getState();
                    Bukkit.dispatchCommand(player, commandBlock.getCommand());

                    if (player.getLocale().equalsIgnoreCase("ko_kr")) player.sendMessage("명령 블록의 내용이 실행되었습니다.");
                    else player.sendMessage("Executed the command in the command block.");


                }

            }
        }

    }
}
