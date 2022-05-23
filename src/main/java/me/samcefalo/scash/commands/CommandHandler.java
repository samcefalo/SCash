package me.samcefalo.scash.commands;

import me.lucko.helper.command.Command;

public interface CommandHandler {

     Command createCommand();

     void register();

}
