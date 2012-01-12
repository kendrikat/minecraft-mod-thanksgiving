package de.endrikatz.thanksgiving;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ThanksGiving extends JavaPlugin {

	public static ThanksGiving plugin;	

	public final Logger logger = Logger.getLogger("Minecraft");

	public final ServerChatPlayerListener playerListener = new ServerChatPlayerListener(
			this);

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled.");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion()
				+ " is now enabled.");

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener,
				Event.Priority.Normal, this);

	}

}
