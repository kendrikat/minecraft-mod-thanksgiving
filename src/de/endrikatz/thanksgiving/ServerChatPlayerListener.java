package de.endrikatz.thanksgiving;

import java.util.Arrays;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
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
			sendMessageNorm(p, " \"#t name v1:COUNT .. vN\" [create kit]");
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
				Map<String, Object> kits = kitCollection.getCollection();

				if (kits.containsKey(kitType.toLowerCase())) {
					Map<String, Object> items = ((Kit) kits.get(kitType
							.toLowerCase())).getItems();

					for (Object object : items.values()) {
						p.performCommand("give " + p.getDisplayName() + " "
								+ ((Item) object).getId() + " "
								+ ((Item) object).getCount());
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
				FileConfiguration config = plugin.getConfig();
				config.set("kitCollection", kitCollection);
				plugin.saveConfig();

			} else {
				sendMessageCrit(p, "oh noes ... ");
			}
			chat.setCancelled(true);
		}

		if (msgLowCase.contains("#rm")) {
			String[] split = message.split(" ");

			if (split.length > 1) {
				Map<String, Object> kits = kitCollection.getCollection();

				if (kits.containsKey(split[1])) {
					kits.remove(split[1]);
					sendMessageNorm(p, "custom kit " + split[1] + " removed");
				}

			} else {
				sendMessageCrit(p, "sup? ... ");
			}
			chat.setCancelled(true);
		}
	}

	public void setCollection(Object object) {
		kitCollection = (KitCollection) object;
	}
}