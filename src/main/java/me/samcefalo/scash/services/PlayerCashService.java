package me.samcefalo.scash.services;

import io.github.eikefs.sql.provider.database.Database;
import lombok.Getter;
import me.samcefalo.scash.SCash;
import me.samcefalo.scash.models.PlayerCash;
import me.samcefalo.scash.repositories.PlayerCashRepository;

import java.util.List;
import java.util.UUID;

public class PlayerCashService {

    private PlayerCashRepository playerCashRepository;

    public PlayerCashService(PlayerCashRepository playerCashRepository) {
        this.playerCashRepository = playerCashRepository;
    }

    public PlayerCash findOrCreate(String uuid) {
        PlayerCash playerCash;
        try {
            playerCash = this.playerCashRepository.get(uuid);
        } catch (NullPointerException e) {
            playerCash = new PlayerCash(uuid, 0);
        }
        return playerCash;
    }

    public void insert(PlayerCash playerCash) {
        this.playerCashRepository.insert(playerCash);
    }

    public void update(PlayerCash playerCash) {
        this.playerCashRepository.update(playerCash);
    }

    public void saveAll(List<PlayerCash> playersCash) {
        playersCash.stream().forEach(playerCash -> save(playerCash));
    }

    public void save(PlayerCash playerCash) {
        this.playerCashRepository.save(playerCash);
    }

    public void deleteAll(List<PlayerCash> playersCash) {
        playersCash.stream().forEach(playerCash -> delete(playerCash));
    }

    public void delete(PlayerCash playerCash) {
        this.playerCashRepository.delete(playerCash);
    }

}
