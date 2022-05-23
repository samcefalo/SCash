package me.samcefalo.scash.commands.cash;

import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.lucko.helper.promise.Promise;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.commands.CommandHandler;
import me.samcefalo.scash.services.PlayerCashService;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashAdd implements CommandHandler {

    private SCash plugin;
    private PlayerCashService playerCashService;

    public CashAdd(SCash plugin) {
        this.plugin = plugin;
        this.playerCashService = plugin.sqlManager.getPlayerCashService();
        register();
    }

    @Override
    public Command createCommand() {
        return Commands.create()
                .assertPermission("cash.admin")
                .assertUsage("<player> <amount>")
                .handler(c -> {
                    Player other = c.arg(0).parseOrFail(Player.class);
                    CommandSender sender = c.sender();
                    int amount = c.arg(1).parseOrFail(Integer.class);

                    Promise.start()
                            .thenApplyAsync(p -> playerCashService.findOrCreate(other.getUniqueId().toString()))
                            .thenAcceptAsync(playerCash -> {
                                playerCash.setCash(playerCash.getCash() + amount);
                                playerCashService.save(playerCash);
                            })
                            .thenRunAsync(() -> other.sendMessage("You have been received " + amount + " cash."))
                            .thenRunAsync(() -> sender.sendMessage("You have send " + amount + " cash to " + other.getDisplayName()));
                    //TODO change message
                });
    }

    @Override
    public void register() {
        createCommand()
                .register("addcash");
    }
}
