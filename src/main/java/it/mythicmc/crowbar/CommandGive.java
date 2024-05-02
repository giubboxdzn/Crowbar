package it.mythicmc.crowbar;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandGive implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("crowbar")) {
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage("Utilizzo corretto: /crowbar give <player>");
            return true;
        }

        if (!args[0].equalsIgnoreCase("give")) {
            return false;
        }

        Player targetPlayer = sender.getServer().getPlayer(args[1]);

        if (targetPlayer == null) {
            sender.sendMessage("Il giocatore specificato non è online!");
            return true;
        }

        ItemStack crowbar = new ItemStack(Material.GOLD_PICKAXE);
        crowbar.setDurability((short) 5);
        ItemMeta meta = crowbar.getItemMeta();
        meta.setDisplayName("§eCrowbar");
        List<String> lore = new ArrayList<>();
        lore.add("§7Usa questo piccone in un claim");
        lore.add("§7avversario");
        lore.add("§7per rubare gli spawner al suo");
        lore.add("§7interno.");
        meta.setLore(lore);
        crowbar.setItemMeta(meta);

        targetPlayer.getInventory().addItem(crowbar);
        sender.sendMessage("§fÈ stata givvata la crowbar a " + targetPlayer.getName());

        return true;
    }
}
