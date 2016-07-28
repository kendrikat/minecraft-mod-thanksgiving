package de.endrikatz.thanksgiving.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;

public class GivingCommandExecutor extends AbstractCommandExecutor {

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
            int itemId = Integer.parseInt(args[0]);
            p.performCommand("give " + p.getDisplayName() + " " + itemId
                    + " 64");
            return true;
        } catch (NumberFormatException e) {
            sendMessageCrit(p, "this isn't a number... stupid");
        } catch (ArrayIndexOutOfBoundsException e) {
            sendMessageCrit(p, "usage: \"/g itemID\"");
        }
        return false;
    }

}
