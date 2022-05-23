package me.samcefalo.scash;

import lombok.Getter;
import me.samcefalo.scash.commands.cash.CashAdd;
import me.samcefalo.scash.commands.cash.CashInfo;
import me.samcefalo.scash.commands.cash.CashReset;
import me.samcefalo.scash.listeners.PlayerJoinListener;
import me.samcefalo.scash.sql.SqlManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SCash extends JavaPlugin {

    @Getter
    public SqlManager sqlManager;

    @Override
    public void onEnable() {
        this.sqlManager = new SqlManager(this);
        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        new PlayerJoinListener(this);
    }

    private void registerCommands() {
        new CashAdd(this);
        new CashInfo(this);
        new CashReset(this);
    }

}
