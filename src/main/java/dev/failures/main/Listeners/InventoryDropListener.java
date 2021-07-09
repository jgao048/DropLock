package dev.failures.main.Listeners;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryDropListener implements Listener {
    private DropLock main;

    public InventoryDropListener(DropLock main) {
        this.main = main;
    }

    @EventHandler
    private void dragDrop(InventoryClickEvent e) {
        PDUtil lockedSlot = new PDUtil(DataKeys.LOCKED_STATUS);
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) return;
        if(!e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
        if(e.getCurrentItem().getType() == Material.AIR) return;

        ItemStack itemClicked = e.getCurrentItem();
        if(!lockedSlot.getItemDataString(itemClicked).equals("LOCKED")) return;
        e.setCancelled(true);
    }
}
