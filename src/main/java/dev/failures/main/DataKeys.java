package dev.failures.main;

import org.bukkit.NamespacedKey;

public class DataKeys {
    public static NamespacedKey LOCKED_SLOT = new NamespacedKey(DropLock.getInstance(),"lockedSlot");
    public static NamespacedKey ITEM_OWNER = new NamespacedKey(DropLock.getInstance(), "itemOwner");
}
