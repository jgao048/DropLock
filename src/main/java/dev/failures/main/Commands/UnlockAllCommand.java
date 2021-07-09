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

public class UnlockAllCommand implements CommandExecutor {
    private DropLock main;

    public UnlockAllCommand(DropLock main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        int slot = 0;
        ItemStack itemHand = p.getInventory().getItemInMainHand().clone();
        PDUtil itemStatus = new PDUtil(DataKeys.LOCKED_STATUS);
        for(ItemStack item : p.getInventory()) {
            if(item == null) {
                slot++;
                continue;
            }
            //itemStatus.setItemDataString(p.getInventory().getItem(slot), "UNLOCKED");
            itemStatus.removeKeyItem(item);
            p.getInventory().setItem(slot, item);
            slot++;
        }
        p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-unlocked-all")));
        return false;
    }
}
