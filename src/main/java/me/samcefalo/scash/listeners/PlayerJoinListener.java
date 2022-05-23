package me.samcefalo.scash.listeners;

import me.lucko.helper.Events;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.models.PlayerCash;
import me.samcefalo.scash.services.PlayerCashService;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener {

    private SCash plugin;
    private PlayerCashService playerCashService;

    public PlayerJoinListener(SCash plugin) {
        this.plugin = plugin;
        joinMessage();
        playerCashLoad();
        playerCashService = plugin.getSqlManager().getPlayerCashService();
    }

    private void joinMessage() {
        Events.subscribe(PlayerJoinEvent.class)
                .handler(e -> {
                    e.setJoinMessage("");
                });
    }

    private void playerCashLoad() {
        Events.subscribe(PlayerJoinEvent.class)
                .handler(e -> {
                    Player player = e.getPlayer();
                    //PlayerCash playerCash = new PlayerCash(player.getUniqueId().toString(), 100);
                    //plugin.getSqlManager().getPlayerCashService().save(playerCash);
                });
    }

}
