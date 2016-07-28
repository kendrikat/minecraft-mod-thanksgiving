package de.endrikatz.thanksgiving.commands;

import de.endrikatz.thanksgiving.ThanksGiving;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CreateCommandExecutor extends AbstractCommandExecutor {

    public CreateCommandExecutor(ThanksGiving plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (args.length > 1
                && plugin.getKitCollection().addCustomKit(
                Arrays.copyOfRange(args, 0, args.length))) {
            sendMessageNorm(p, "custom kit " + args[0] + " saved :)");
            FileConfiguration config = plugin.getConfig();
            config.set("kitCollection", plugin.getKitCollection());
            plugin.saveConfig();

        } else {
            sendMessageCrit(p, "oh noes ... ");
        }

        return true;
    }


}
