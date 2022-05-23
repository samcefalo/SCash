package me.samcefalo.scash.commands.cash;

import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.lucko.helper.promise.Promise;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.commands.CommandHandler;
import me.samcefalo.scash.services.PlayerCashService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashReset implements CommandHandler {

    private SCash plugin;
    private PlayerCashService playerCashService;

    public CashReset(SCash plugin) {
        this.plugin = plugin;
        this.playerCashService = plugin.getSqlManager().getPlayerCashService();
        register();
    }

    @Override
    public Command createCommand() {
        return Commands.create()
                .assertPermission("cash.admin")
                .assertUsage("<player>")
                .handler(c -> {
                    Player other = c.arg(0).parseOrFail(Player.class);
                    CommandSender sender = c.sender();
                    Promise.start()
                            .thenApplyAsync(p -> playerCashService.findOrCreate(other.getUniqueId().toString()))
                            .thenAcceptAsync(playerCash -> {
                                playerCash.setCash(0);
                                playerCashService.save(playerCash);
                            })
                            .thenRunAsync(() -> other.sendMessage("Your cash has been reset."))
                            .thenRunAsync(() -> sender.sendMessage(""+ other.getDisplayName() + "'s cash has been reset."));
                    //TODO change message
                });
    }

    @Override
    public void register() {
        createCommand()
                .register("resetcash");
    }
}
