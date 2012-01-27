package de.endrikatz.thanksgiving.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;

public abstract class AbstractCommandExecutor implements CommandExecutor {

	protected ThanksGiving plugin;
	
	private void sendMessageFormated(Player p, String msg, ChatColor C1,
			ChatColor C2) {
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
}
