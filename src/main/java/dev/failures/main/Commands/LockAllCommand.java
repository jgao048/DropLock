package dev.failures.main.Commands;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.ColorUtil;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LockAllCommand implements CommandExecutor {
    private DropLock main;

    public LockAllCommand(DropLock main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        int slot = 0;
        PDUtil locked = new PDUtil(DataKeys.LOCKED_SLOT);
        for(ItemStack item : p.getInventory()) {
            if(item == null) {
                slot++;
                continue;
            }
            locked.setItemDataInteger(item,slot);
            slot++;
        }
        p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-locked-all")));
        return false;
    }
}
