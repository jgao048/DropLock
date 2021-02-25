package dev.failures.main;

import dev.failures.main.Commands.LockItemCommand;
import dev.failures.main.Commands.UnlockItemCommand;
import dev.failures.main.Listeners.DropItemListener;
import dev.failures.main.Listeners.InventoryDropListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DropLock extends JavaPlugin {
    public static DropLock instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new DropItemListener(this),this);
        getServer().getPluginManager().registerEvents(new InventoryDropListener(this),this);

        getCommand("lock").setExecutor(new LockItemCommand(this));
        getCommand("unlock").setExecutor(new UnlockItemCommand(this));

    }

    public static DropLock getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
