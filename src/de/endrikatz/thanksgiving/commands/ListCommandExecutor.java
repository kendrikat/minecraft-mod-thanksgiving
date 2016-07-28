package de.endrikatz.thanksgiving.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;

public class ListCommandExecutor extends AbstractCommandExecutor {

    public ListCommandExecutor(ThanksGiving plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        sendMessageNorm(p, "kits: " + plugin.getKitCollection().getNames());
        sendMessageNorm(p, "example: \"/k tools\"");

        return true;
    }
}
