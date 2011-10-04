package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ProfileCommand extends Command{
    public ProfileCommand(String name) {
        super(name);
        this.description = "Enables or disables profiling.";
        this.usageMessage = "/profile <on [time" +
                " threshold] [invocation threshold]|off>";
        this.setPermission("bukkit.command.profile");
        this.setAliases(Arrays.asList("prof"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player && !testPermission(sender)) return true;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("on")) {
                long timeThreshold = 500;

                if (args.length > 1)
                    timeThreshold = Long.parseLong(args[1]);

                Bukkit.getPluginManager().enableProfiling(sender, timeThreshold);
            } else if (args[0].equalsIgnoreCase("off")) {
                Bukkit.getPluginManager().disableProfiling();
            }
        }

        return true;
    }
}
