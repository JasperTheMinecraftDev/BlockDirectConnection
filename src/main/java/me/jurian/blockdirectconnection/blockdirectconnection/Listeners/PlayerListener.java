package me.jurian.blockdirectconnection.blockdirectconnection.Listeners;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener extends JavaPlugin implements Listener {
    private static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.YELLOW + "AntiDirectConnect" + ChatColor.GRAY + "] " + ChatColor.DARK_AQUA;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            saveDefaultConfig();
    }

    public void onDisable() {}

    @EventHandler
    public void onConnect(PlayerLoginEvent event) {
        if (getConfig().getStringList("allowed").contains(event.getAddress().getHostAddress().toLowerCase()))
            return;
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, getConfig().getString("settings.PlayerKickMessage").replaceAll("&",""));
    }
}