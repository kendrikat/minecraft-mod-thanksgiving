package de.endrikatz.thanksgiving;

import java.util.ArrayList;
import java.util.Arrays;

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

	private void sendMessageFormated(Player p, String msg, ChatColor C1,
			ChatColor C2) {
		p.sendMessage(C1 + "[Server] " + C2 + msg);
	}

	private void sendMessageNorm(Player p, String msg) {
		sendMessageFormated(p, msg, ChatColor.GREEN, ChatColor.WHITE);
	}

	private void sendMessageLow(Player p, String msg) {
		sendMessageFormated(p, msg, ChatColor.GREEN, ChatColor.GRAY);
	}

	private void sendMessageCrit(Player p, String msg) {
		sendMessageFormated(p, msg, ChatColor.RED, ChatColor.WHITE);
	}

	public void onPlayerChat(PlayerChatEvent chat) {
		Player p = chat.getPlayer();

		String message = chat.getMessage();
		String msgLowCase = message.toLowerCase();

		if (msgLowCase.contains("help") || msgLowCase.contains("#h")) {
			sendMessageLow(p, "***********************************************");
			sendMessageNorm(p, " Hi, this is the mighty ThanksGiving-MOD(tm)!");
			sendMessageNorm(p, "COMMANDS:");
			sendMessageNorm(p, " \"#g itemID\" [shortened /give command]");
			sendMessageNorm(p, " \"#h\" [this help screen]");
			sendMessageNorm(p, " \"#k\" [get a kit of different items]");
			sendMessageNorm(p, " \"#l\" [list all available kits]");
			sendMessageNorm(p, " \"#t name v1 .. vN\" [create a custom kit]");
			sendMessageNorm(p, " \"#rm name \" [remove kit]");
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#g")) {
			try {
				int itemId = Integer.parseInt(message.split(" ")[1]);
				p.performCommand("give " + p.getDisplayName() + " " + itemId
						+ " 64");
			} catch (NumberFormatException e) {
				sendMessageCrit(p, "this isn't a number... stupid");
			} catch (ArrayIndexOutOfBoundsException e) {
				sendMessageCrit(p, "usage: \"#g itemID\"");
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

			} catch (ArrayIndexOutOfBoundsException e) {
				sendMessageCrit(p,
						"usage: \"#k kitname\" - see #l for list of all kits");
			}
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#l")) {
			sendMessageNorm(p, "kits: " + kitCollection.getNames());
			sendMessageNorm(p, "example: \"#k tools\"");
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#t")) {

			String[] split = message.split(" ");

			if (split.length > 1
					&& kitCollection.addCustomKit(Arrays.copyOfRange(split, 1,
							split.length))) {
				sendMessageNorm(p, "custom kit " + split[1] + " saved :)");
			} else {
				sendMessageCrit(p, "oh noes ... ");
			}

			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#rm")) {
			String[] split = message.split(" ");
			Kit rm = null;

			if (split.length > 1) {
				ArrayList<Kit> kits = kitCollection.getCollection();

				for (Kit kit : kits) {
					if (kit.getName().contains(split[1])) {
						rm = kit;
						sendMessageNorm(p, "custom kit " + split[1]
								+ " removed");
					}
				}
				kits.remove(rm);
			} else {
				sendMessageCrit(p, "sup? ... ");
			}
			chat.setCancelled(true);
		}

	}
}
