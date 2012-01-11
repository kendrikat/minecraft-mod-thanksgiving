package de.endrikatz.thanksgiving;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerChatPlayerListener extends PlayerListener {
	public static ThanksGiving plugin;

	public ServerChatPlayerListener(ThanksGiving instance) {
		plugin = instance;
	}

	private void sendMessageFormatted(Player p, String msg) {
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		p.sendMessage(RED + "[Server] " + WHITE + msg);
	}

	public void onPlayerChat(PlayerChatEvent chat) {
		Player p = chat.getPlayer();

		String message = chat.getMessage();
		String msgLowCase = message.toLowerCase();

		if (msgLowCase.contains("hi")) {
			sendMessageFormatted(p, "howdy, " + p.getName()
					+ " - type #h for a list of commands ");
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("help") || msgLowCase.contains("#h")) {
			sendMessageFormatted(p, " \"#g itemID\" [shortened /give command]");
			sendMessageFormatted(p, " \"#l\" [list of data values]");
			sendMessageFormatted(p, " \"#h\" [this help screen]");
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#g")) {
			try {
				int itemId = Integer.parseInt(message.split(" ")[1]);
				p.performCommand("give " + p.getDisplayName() + " " + itemId
						+ " 64");
			} catch (NumberFormatException e) {
				sendMessageFormatted(p, "this isn't a number... stupid");
			}
			chat.setCancelled(true);

		}

		if (msgLowCase.contains("#l")) {
			sendMessageFormatted(p, "TODO: list datavalues");
			chat.setCancelled(true);
		}

	}
}
