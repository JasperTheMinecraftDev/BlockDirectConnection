package me.jurian.blockdirectconnection.blockdirectconnection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.jurian.blockdirectconnection.blockdirectconnection.bstats.Metrics;

public class Blockdirectconnection extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        int pluginId = 13869;
        Metrics metrics = new Metrics(this, pluginId);
        System.out.println("BlockDirectConnection is now succesfully enabled!");
    }

    public void onDisable() {}
        @EventHandler
        public void onConnect(PlayerLoginEvent event) {
            if (getConfig().getStringList("allowed").contains(event.getAddress().getHostAddress().toLowerCase()))
                return;
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, getConfig().getString("PlayerKickMessage"));

            ;}
}
