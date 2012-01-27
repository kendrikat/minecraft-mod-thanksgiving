package de.endrikatz.thanksgiving.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.endrikatz.thanksgiving.ThanksGiving;

public class RemoveCommandExecutor extends AbstractCommandExecutor {

	public RemoveCommandExecutor(ThanksGiving plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String cmdLabel, String[] args) {

		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}

		if (args.length > 0) {
			Map<String, Object> kits = plugin.getKitCollection()
					.getCollection();

			if (kits.containsKey(args[0])) {
				kits.remove(args[0]);
				sendMessageNorm(p, "custom kit " + args[0] + " removed");
			}

		} else {
			sendMessageCrit(p, "sup? ... ");
		}

		return true;
	}
}
