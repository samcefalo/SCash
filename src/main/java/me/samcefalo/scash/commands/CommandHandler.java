package me.samcefalo.scash.commands;

import me.lucko.helper.command.Command;

public interface CommandHandler {

    public Command createCommand();
    public void register();

}
