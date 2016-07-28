package de.endrikatz.thanksgiving.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import de.endrikatz.thanksgiving.ThanksGiving;

public class GivingCommandExecutor extends AbstractCommandExecutor implements TabCompleter {

    public GivingCommandExecutor(ThanksGiving plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        try {
            p.performCommand("give " + p.getDisplayName() + " " + args[0] + " " + (args.length > 1 ? args[1] : 0));
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            sendMessageCrit(p, "usage: \"/g itemID\"");
        }
        return false;
    }

}
