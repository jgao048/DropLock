package dev.failures.main;

import org.bukkit.NamespacedKey;

public class DataKeys {
    public static NamespacedKey lockedSlot = new NamespacedKey(DropLock.getInstance(),"lockedSlot");
    public static NamespacedKey ITEM_OWNER = new NamespacedKey(DropLock.getInstance(), "itemOwner");
    public static NamespacedKey ITEM_TIMER = new NamespacedKey(DropLock.getInstance(), "timeLeft");
}
