package it.mythicmc.crowbar;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class Crowbar extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("");
        System.out.println("\033[33m" + "_________                      ___.                 ");
        System.out.println("\033[33m" + "\\_   ___ \\_______  ______  _  _\\_ |__ _____ _______ ");
        System.out.println("\033[33m" + "/    \\  \\/\\_  __ \\/  _ \\ \\/ \\/ /| __ \\\\__  \\\\_  __ \\");
        System.out.println("\033[33m" + "\\     \\____|  | \\(  <_> )     / | \\_\\ \\/ __ \\|  | \\/");
        System.out.println("\033[33m" + "\\______  /|__|   \\____/ \\/\\_/  |___  (____  /__|   ");
        System.out.println("\033[33m" + "       \\/                          \\/     \\/       ");
        System.out.println("");
        System.out.println("\033[36m" + "Plugin sviluppato da: " + "\033[34m" + getDescription().getAuthors() + "\033[0m");
        System.out.println("");

        this.getCommand("crowbar").setExecutor(new CommandGive());

    }

    @Override
    public void onDisable() {
        System.out.println("Crowbar plugin disabilitato correttamente!");
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreackListener(BlockBreakEvent event){
        if(!event.getBlock().getType().equals(Material.MOB_SPAWNER))
            return;
        Player p = event.getPlayer();

        if(p.getItemInHand() == null)
            return;
        if(!p.getItemInHand().getType().equals(Material.GOLD_PICKAXE))
            return;

        if(p.getItemInHand().getDurability() != 5)
            return;

        Faction faction = Board.getInstance().getFactionAt(FLocation.wrap(event.getBlock()));
        if(faction.isWilderness())
            return;

        Collection<ItemStack> drops = event.getBlock().getDrops();
        for (ItemStack item : drops) {
            p.getInventory().addItem(item);
        }

        event.setCancelled(false);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crowbar give " + p.getName());
        
        
    }

}
