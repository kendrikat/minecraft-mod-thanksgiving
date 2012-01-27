package de.endrikatz.thanksgiving.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.Item;
import de.endrikatz.thanksgiving.Kit;
import de.endrikatz.thanksgiving.ThanksGiving;

public class KitCommandExecutor extends AbstractCommandExecutor {

	public KitCommandExecutor(ThanksGiving plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String cmdLabel, String[] args) {

		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}
		try {
			String kitType = args[0];
			Map<String, Object> kits = plugin.getKitCollection()
					.getCollection();

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
					"usage: \"/k kitname\" - see /l for list of all kits");
		}

		return true;
	}
}
