package dev.failures.main.Commands;

import dev.failures.main.DataKeys;
import dev.failures.main.DropLock;
import dev.failures.main.Utils.ColorUtil;
import dev.failures.main.Utils.PDUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LockItemCommand implements CommandExecutor {
    private DropLock main;

    public LockItemCommand(DropLock main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand().getType() == Material.AIR) return false;

        ItemStack itemHand = p.getInventory().getItemInMainHand().clone();
        PDUtil itemOwner = new PDUtil(DataKeys.ITEM_OWNER);

        if(itemOwner.itemDataContainsKey(itemHand)) {
            p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-locked-already")));
            return true;
        }
        p.sendMessage(ColorUtil.colorize(main.getConfig().getString("item-locked-success")));
        itemOwner.setItemDataString(itemHand,p.getUniqueId().toString());
        p.getInventory().setItemInMainHand(itemHand);

        return true;
    }
}
