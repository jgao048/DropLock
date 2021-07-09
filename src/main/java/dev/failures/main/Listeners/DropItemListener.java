package dev.failures.main.Listeners;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropItemListener implements Listener {
    private DropLock main;

    public DropItemListener(DropLock main) {
        this.main = main;
    }

    @EventHandler
    private void preventDropping(PlayerDropItemEvent e) {
        PDUtil itemStatus = new PDUtil(DataKeys.LOCKED_STATUS);
        Player p = e.getPlayer();
        if (e.getItemDrop().getItemStack().getType() == Material.AIR) return;
        ItemStack itemHand = e.getItemDrop().getItemStack().clone();

        if (!itemStatus.getItemDataString(itemHand).equals("LOCKED")) return;
        e.setCancelled(true);
    }
}
