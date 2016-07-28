package de.endrikatz.thanksgiving;

import java.util.logging.Logger;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.endrikatz.thanksgiving.commands.CreateCommandExecutor;
import de.endrikatz.thanksgiving.commands.GivingCommandExecutor;
import de.endrikatz.thanksgiving.commands.HelpCommandExecutor;
import de.endrikatz.thanksgiving.commands.KitCommandExecutor;
import de.endrikatz.thanksgiving.commands.ListCommandExecutor;
import de.endrikatz.thanksgiving.commands.RemoveCommandExecutor;

public class ThanksGiving extends JavaPlugin {

    public static ThanksGiving plugin;
    public final Logger logger = Logger.getLogger("Minecraft");

    private GivingCommandExecutor giveExecutor = new GivingCommandExecutor(this);
    private HelpCommandExecutor helpExecutor = new HelpCommandExecutor(this);
    private ListCommandExecutor listExecutor = new ListCommandExecutor(this);
    private KitCommandExecutor kitExecutor = new KitCommandExecutor(this);
    private CreateCommandExecutor createExecutor = new CreateCommandExecutor(this);
    private RemoveCommandExecutor removeExecutor = new RemoveCommandExecutor(this);

    private String conf = "kitCollection";
    private KitCollection kitCollection = new KitCollection();

    static {
        ConfigurationSerialization.registerClass(KitCollection.class);
        ConfigurationSerialization.registerClass(Kit.class);
        ConfigurationSerialization.registerClass(Item.class);
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(pdfFile.getName() + " is now disabled.");
    }

    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is now enabled.");

        if (this.getConfig().contains(conf)) {
            this.setKitCollection((KitCollection) this.getConfig().get(conf));
        }

        getCommand("g").setExecutor(giveExecutor);
        getCommand("h").setExecutor(helpExecutor);
        getCommand("l").setExecutor(listExecutor);
        getCommand("k").setExecutor(kitExecutor);
        getCommand("c").setExecutor(createExecutor);
        getCommand("rm").setExecutor(removeExecutor);

    }

    public KitCollection getKitCollection() {
        return kitCollection;
    }

    public void setKitCollection(KitCollection kitCollection) {
        this.kitCollection = kitCollection;
    }
}
