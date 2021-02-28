package dev.failures.main.Commands;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.ColorUtil;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnlockItemCommand implements CommandExecutor {
    private DropLock main;

    public UnlockItemCommand(DropLock main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand().getType() == Material.AIR) return false;

        ItemStack itemHand = p.getInventory().getItemInMainHand();
        PDUtil itemOwner = new PDUtil(DataKeys.ITEM_OWNER);

        if(itemOwner.itemDataContainsKey(itemHand)) {
            itemOwner.removeKeyItem(itemHand);
            p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-unlocked-success")));
            return true;
        }
        p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-unlocked-already")));
        return false;
    }
}
