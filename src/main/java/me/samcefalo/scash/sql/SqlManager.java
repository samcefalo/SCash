package me.samcefalo.scash.sql;

import io.github.eikefs.sql.provider.Provider;
import io.github.eikefs.sql.provider.database.Database;
import lombok.Getter;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.models.PlayerCash;
import me.samcefalo.scash.repositories.PlayerCashRepository;
import me.samcefalo.scash.services.PlayerCashService;

public class SqlManager {

    @Getter
    private SCash plugin;
    @Getter
    private PlayerCashService playerCashService;
    @Getter
    private Database database;

    public SqlManager(SCash plugin) {
        this.plugin = plugin;
        initDatabase();
        createTables();
        PlayerCashRepository playerCashRepository = new PlayerCashRepository(database);
        this.playerCashService = new PlayerCashService(playerCashRepository);
    }

    private void initDatabase() {
        this.database = Provider
                .getInstance()
                .submitHikari("localhost:3306", "spigot", "root", "");
    }

    private void createTables() {
        PlayerCash.create(database, PlayerCash.class);
    }

}
