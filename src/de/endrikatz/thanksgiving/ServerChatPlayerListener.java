package de.endrikatz.thanksgiving;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerChatPlayerListener extends PlayerListener {
	public static ThanksGiving plugin;

	private static int[] itemKitLeather = { 298, 299, 300, 301, 286 };
	private static int[] itemKitDiamond = { 310, 311, 312, 313, 276 };
	private static int[] itemKitTools = { 277, 278, 279 };

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
			sendMessageFormatted(p, " \"#h\" [this help screen]");
			sendMessageFormatted(p, " \"#k\" [get a kit of different items]");
			sendMessageFormatted(p, " \"#l\" [list of all available kits]");
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#g")) {
			try {
				int itemId = Integer.parseInt(message.split(" ")[1]);
				p.performCommand("give " + p.getDisplayName() + " " + itemId
						+ " 64");
			} catch (NumberFormatException e) {
				sendMessageFormatted(p, "this isn't a number... stupid");
			} catch (ArrayIndexOutOfBoundsException e) {
				sendMessageFormatted(p, "usage: \"#g itemID\"");
			}
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#k")) {
			try {
				String kitType = message.split(" ")[1];

				/* qnd :) */

				if (kitType.toLowerCase().contains("leather")) {
					for (int i = 0; i < itemKitLeather.length; i++) {
						p.performCommand("give " + p.getDisplayName() + " "
								+ itemKitLeather[i] + " 1");
					}
				}

				if (kitType.toLowerCase().contains("diamond")) {
					for (int i = 0; i < itemKitDiamond.length; i++) {
						p.performCommand("give " + p.getDisplayName() + " "
								+ itemKitDiamond[i] + " 1");
					}
				}

				if (kitType.toLowerCase().contains("tools")) {
					for (int i = 0; i < itemKitTools.length; i++) {
						p.performCommand("give " + p.getDisplayName() + " "
								+ itemKitTools[i] + " 1");
					}
				}

				chat.setCancelled(true);

			} catch (ArrayIndexOutOfBoundsException e) {
				sendMessageFormatted(p,
						"usage: \"#k kitname\" - see #l for list of all kits");
			}
		}

		if (msgLowCase.contains("#l")) {
			sendMessageFormatted(p, "kits: leather, diamond, tools");
			sendMessageFormatted(p, "example: \"#k tools\"");
			chat.setCancelled(true);
		}

	}
}
