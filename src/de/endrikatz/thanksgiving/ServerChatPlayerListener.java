package de.endrikatz.thanksgiving;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerChatPlayerListener extends PlayerListener {
	public static ThanksGiving plugin;

	private static KitCollection kitCollection = new KitCollection();

	public ServerChatPlayerListener(ThanksGiving instance) {
		plugin = instance;
		kitCollection.init();
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
				ArrayList<Kit> kits = kitCollection.getCollection();

				for (Kit kit : kits) {
					if (kitType.toLowerCase().contains(kit.getName())) {
						ArrayList<Integer> items = kit.getItems();

						for (int i = 0; i < kit.getItems().size(); i++) {
							p.performCommand("give " + p.getDisplayName() + " "
									+ items.get(i) + " 1");
						}
					}
				}
				chat.setCancelled(true);

			} catch (ArrayIndexOutOfBoundsException e) {
				sendMessageFormatted(p,
						"usage: \"#k kitname\" - see #l for list of all kits");
			}
		}

		if (msgLowCase.contains("#l")) {
			sendMessageFormatted(p, "kits: " + kitCollection.getNames());
			sendMessageFormatted(p, "example: \"#k tools\"");
			chat.setCancelled(true);
		}

	}
}
