package de.endrikatz.thanksgiving.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;

public class HelpCommandExecutor extends AbstractCommandExecutor {

	public HelpCommandExecutor(ThanksGiving plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String cmdLabel, String[] args) {

		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}

		sendMessageLow(p, "***********************************************");
		sendMessageNorm(p, " Hi, this is the mighty ThanksGiving-MOD(tm)!");
		sendMessageNorm(p, "COMMANDS:");
		sendMessageNorm(p, " \"/g itemID\" [shortened /give command]");
		sendMessageNorm(p, " \"/h\" [this help screen]");
		sendMessageNorm(p, " \"/k\" [get a kit of different items]");
		sendMessageNorm(p, " \"/l\" [list all available kits]");
		sendMessageNorm(p, " \"/t name v1:COUNT .. vN\" [create kit]");
		sendMessageNorm(p, " \"/rm name \" [remove kit]");

		return true;
	}
}
