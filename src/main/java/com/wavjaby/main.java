package com.wavjaby;

import com.wavjaby.commands.AboutPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
    final static ConsoleCommandSender console = Bukkit.getConsoleSender();//console
    final static String pluginPrefix = "[" + ChatColor.LIGHT_PURPLE + "WavJaby's Plugin" + ChatColor.RESET + "]";
    final static String errorPluginPrefix = "[" + ChatColor.RED + "WavJaby's Plugin" + ChatColor.RESET + "]";

    private ConfigManager cfgm;

    public void onEnable() {
        loadConfigManager();
        setupCommands();

        console.sendMessage(pluginPrefix + ChatColor.GREEN + "啟動完畢");
    }

    public void onDisable() {
        console.sendMessage(pluginPrefix + ChatColor.GREEN + "已停止");
    }

    public void setupCommands() {
        TabCompletion tabCompletion = new TabCompletion(this);

        getCommand("wavjabyplugin").setExecutor(new AboutPlugin(this));
        getCommand("wavjabyplugin").setTabCompleter(tabCompletion);
        getCommand("test").setTabCompleter(tabCompletion);
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();
    }
}