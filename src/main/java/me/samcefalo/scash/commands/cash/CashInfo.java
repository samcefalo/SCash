package me.samcefalo.scash.commands.cash;

import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.commands.CommandHandler;
import me.samcefalo.scash.services.PlayerCashService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashInfo implements CommandHandler {

    private SCash plugin;
    private PlayerCashService playerCashService;

    public CashInfo(SCash plugin) {
        this.plugin = plugin;
        this.playerCashService = plugin.sqlManager.getPlayerCashService();
        register();
    }

    @Override
    public Command createCommand() {
        return Commands.create()
                .assertPermission("cash.admin")
                .handler(c -> {
                    //TODO
                });
    }

    @Override
    public void register() {
        createCommand().register("infocash");
    }
}
