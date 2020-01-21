package US.bittiez.Jack;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class main extends JavaPlugin implements Listener {
    private static Logger log;

    @Override
    public void onEnable() {
        log = getLogger();
        getServer().getPluginManager().registerEvents(this, this);
    }
    public boolean onCommand(CommandSender who, Command cmd, String label, String[] args) {
        return false;
    }
}
