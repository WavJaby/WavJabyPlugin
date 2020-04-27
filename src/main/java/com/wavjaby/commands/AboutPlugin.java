package com.wavjaby.commands;

import com.wavjaby.ConfigManager;
import com.wavjaby.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AboutPlugin implements CommandExecutor {

    public AboutPlugin(main main) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wavjabyplugin") && args.length != 0) {//第二個選項
            if (args[0].equals("info")) {
                sender.sendMessage(ChatColor.GREEN + "版本:" + ChatColor.RED + "1.0");
                return true;
            }
            if (args[0].equals("reload")) {
                new ConfigManager().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "reload done.");
                return true;
            }
            sender.sendMessage("command wrong");
            return true;
        }
        sender.sendMessage("command not finish");
        return false;
    }
}
