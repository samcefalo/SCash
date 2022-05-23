package me.samcefalo.scash.playercash;

import io.github.eikefs.sql.provider.Provider;
import io.github.eikefs.sql.provider.database.Database;
import me.samcefalo.scash.models.PlayerCash;
import me.samcefalo.scash.repositories.PlayerCashRepository;
import me.samcefalo.scash.services.PlayerCashService;

import java.util.UUID;

public class MainTest {

    private static Database database;

    public static void main(String[] args) {
        initDatabase();
        tableConfigure();
        PlayerCashRepository playerCashRepository = new PlayerCashRepository(database);
        PlayerCashService playerCashService = new PlayerCashService(playerCashRepository);
        PlayerCash playerCash = new PlayerCash("teste", 0);
        playerCashRepository.insert(playerCash);
        playerCash.setCash(playerCash.getCash()+1050);
        playerCashRepository.save(playerCash);

        System.out.println(playerCashService.findOrCreate("teste"));

        //playerCashRepository.delete(new PlayerCash("teste", 0));
    }

    private static void initDatabase() {
        database = Provider.getInstance().submitMySql("localhost:3306", "test", "root", "");
    }

    //Create User Table
    private static void tableConfigure() {
        PlayerCash.create(database, PlayerCash.class);
    }
}
