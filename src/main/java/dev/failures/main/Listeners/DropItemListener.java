package dev.failures.main.Listeners;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
        PDUtil lockedSlot = new PDUtil(DataKeys.lockedSlot);
        Player p = e.getPlayer();
        if(e.getItemDrop().getItemStack().getType() == Material.AIR) return;
        ItemStack itemHand = e.getItemDrop().getItemStack();

        if(!lockedSlot.itemDataContainsKey(itemHand)) return;
        e.setCancelled(true);

    }
}
