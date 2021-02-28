package dev.failures.main.Listeners;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickListener implements Listener {
    private DropLock main;

    public ItemPickListener(DropLock main) {
        this.main = main;
    }

    @EventHandler
    private void itemPickup(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) return;

        PDUtil itemOwner = new PDUtil(DataKeys.ITEM_OWNER);
        ItemStack pickedItem = e.getItem().getItemStack();
        if(itemOwner.getItemDataString(pickedItem).equals(e.getEntity().getUniqueId().toString())) {
            return;
        }
        e.setCancelled(true);
    }
}
