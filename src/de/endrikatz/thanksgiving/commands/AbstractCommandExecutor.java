package de.endrikatz.thanksgiving.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCommandExecutor implements CommandExecutor, TabCompleter {

    protected ThanksGiving plugin;

    private void sendMessageFormated(Player p, String msg, ChatColor C1, ChatColor C2) {
        p.sendMessage(C1 + "[Server] " + C2 + msg);
    }

    protected void sendMessageNorm(Player p, String msg) {
        sendMessageFormated(p, msg, ChatColor.GREEN, ChatColor.WHITE);
    }

    protected void sendMessageLow(Player p, String msg) {
        sendMessageFormated(p, msg, ChatColor.GREEN, ChatColor.GRAY);
    }

    protected void sendMessageCrit(Player p, String msg) {
        sendMessageFormated(p, msg, ChatColor.RED, ChatColor.WHITE);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {

        // TODO: check class

        if (args.length > 0) {
            final String arg = args[args.length - 1];
            final Material[] materials = Material.values();

            List<String> materialStrings = new ArrayList<>();
            for (Material m : materials) {
                if (m != null)
                    materialStrings.add(m.toString());
            }
            List<String> completion = new ArrayList<>();

            final int size = materials.length;
            int i = Collections.binarySearch(materialStrings, arg, String.CASE_INSENSITIVE_ORDER);

            if (i < 0) {
                i = -1 - i;
            }
            for (; i < size; i++) {
                String material = materialStrings.get(i);
                if (StringUtil.startsWithIgnoreCase(material, arg)) {
                    completion.add(material);
                } else {
                    break;
                }
            }

            return Bukkit.getUnsafe().tabCompleteInternalMaterialName(arg, completion);
        }

        return null;
    }
}
