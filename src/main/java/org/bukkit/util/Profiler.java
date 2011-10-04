package org.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class Profiler {
    private final CommandSender commandSender;
    private final long timeThreshold;

    private String currentKey;
    private long startTime;
    private boolean profileThisTime;

    public Profiler(CommandSender commandSender, long timeThreshold) {
        this.commandSender = commandSender;
        this.timeThreshold = timeThreshold;
    }

    public void start(String key) {
        currentKey = key;

        startTime = System.currentTimeMillis();
    }

    public void end() {
        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;

        if (diff >= timeThreshold) {
            sendMessage(String.format("%s took %d ms", currentKey, diff));
        }
    }

    public void sendMessage(String message) {
        try {
            commandSender.sendMessage("[PROFILE] " + message);
        } catch (CommandException ex) {
            CommandSender ccs = new ConsoleCommandSender(Bukkit.getServer());
            ccs.sendMessage("[PROFILE] Error while running profiler:");
            ccs.sendMessage(ex.getMessage());
        }
    }
}
