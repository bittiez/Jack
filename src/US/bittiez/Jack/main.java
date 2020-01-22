package US.bittiez.Jack;

import US.bittiez.Config.ConfigAccessor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public class main extends JavaPlugin implements Listener {
    private static Logger log;
    private ConfigAccessor mainConfig;
    private ConfigAccessor chatConfig;
    private String chatPrefix;
    private Random randomGenerator = new Random();

    @Override
    public void onEnable() {
        log = getLogger();
        getServer().getPluginManager().registerEvents(this, this);

        mainConfig = new ConfigAccessor(this, "config.yml");
        chatConfig = new ConfigAccessor(this, "chat.yml");
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        mainConfig.saveDefaultConfig();
        chatConfig.saveDefaultConfig();
        loadJack();
    }

    private void loadJack() {
        chatPrefix = mainConfig.getConfig().getString("require_prefix", "Hey Jack");
    }

    public boolean onCommand(CommandSender who, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Jack") && who.hasPermission(PERMISSION.RELOAD)) {
            if (args[0].equalsIgnoreCase("reload")) {
                chatConfig.reloadConfig();
                mainConfig.reloadConfig();
                loadJack();
                who.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Jack &7has been reloaded!"));
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!e.isCancelled() && !e.getMessage().isEmpty()) {
            if (e.getPlayer().hasPermission(PERMISSION.RESPOND)) {
                String message = e.getMessage();
                if (!chatPrefix.isEmpty()) {
                    //Prefix defined
                    if (message.toLowerCase().startsWith(chatPrefix.toLowerCase())) {
                        //Prefix found in this message
                        Bukkit.getScheduler().runTaskAsynchronously(this, () -> handleMessage(e.getPlayer(), message));
                    }
                } else {
                    //No prefix defined
                    Bukkit.getScheduler().runTaskAsynchronously(this, () -> handleMessage(e.getPlayer(), message));
                }
            }
        }
    }

    private void handleMessage(Player from, String message) {
        message = ChatColor.stripColor(message);
        for (String listen : chatConfig.getConfig().getConfigurationSection("listen").getKeys(false)) {
            if (message.toLowerCase().contains(listen.toLowerCase())) {
                String response = mainConfig.getConfig().getString("response", "&3Jack: &7[RESPONSE]");
                response = response.replace("[RESPONSE]", getResponse(chatConfig.getConfig().getString("listen." + listen)));
                response = response.replace("[PLAYER]", from.getName());
                response = ChatColor.translateAlternateColorCodes('&', response);
                from.sendMessage(response);
                break;
            }
        }
    }

    private String getResponse(String answerPath) {
        List<String> responses = chatConfig.getConfig().getStringList("answers." + answerPath);
        if (responses.size() < 2)
            return responses.get(0);
        return responses.get(randomGenerator.nextInt(responses.size()));
    }
}
