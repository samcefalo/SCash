package me.samcefalo.scash.repositories;

import io.github.eikefs.sql.provider.database.Database;
import io.github.eikefs.sql.provider.orm.Repository;
import io.github.eikefs.sql.provider.query.Query;
import lombok.Getter;
import me.samcefalo.scash.models.PlayerCash;

import java.util.UUID;

public class PlayerCashRepository implements Repository<PlayerCash, String> {

    @Getter
    private Database database;

    public PlayerCashRepository(Database database) {
        this.database = database;
    }

    @Override
    public PlayerCash get(String id) throws NullPointerException {
        return database.buildSync(PlayerCash.class, new Query()
                .selectAll()
                .from("player_cash")
                .where("uuid", id)
                .raw());
    }

    @Override
    public void insert(PlayerCash object) {
        Query query = new Query()
                .insert("player_cash", object.getUuid(), object.getCash())
                .onDuplicateUpdate()
                .value("cash", object.getCash()).end();
        database.updateSync(query);
    }

    @Override
    public void update(PlayerCash object) {
        Query query = new Query()
                .insert("player_cash", object.getUuid(), object.getCash())
                .onDuplicateUpdate()
                .value("cash", object.getCash()).end();
        database.updateSync(query);
    }

    @Override
    public void delete(PlayerCash object) {
        Query query = new Query()
                .delete()
                .from("player_cash")
                .where("uuid", object.getUuid());
        database.updateSync(query);
    }

    public void save(PlayerCash object) {
        update(object);
    }

    public PlayerCash findByUUID(UUID uuid) {
        return get(uuid.toString());
    }
}
