package me.samcefalo.scash.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.samcefalo.scash.models.PlayerCash;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public class SendCashEvent extends Event {

    private PlayerCash sender, receiver;
    private int amount;

    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
    
}
