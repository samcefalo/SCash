package me.samcefalo.scash.models;

import io.github.eikefs.sql.provider.orm.ORM;
import io.github.eikefs.sql.provider.orm.annotations.Field;
import io.github.eikefs.sql.provider.orm.annotations.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "player_cash", primary = "uuid", unique = true)
public class PlayerCash extends ORM implements Serializable {

    @Field(size = 64, nullable = false)
    private String uuid;
    @Field(type = "int", size = 16, nullable = false)
    private int cash;

    public PlayerCash(String uuid, int cash) {
        this.uuid = uuid;
        this.cash = cash;
    }

    public static void create() {
        create(PlayerCash.class);
    }
}
