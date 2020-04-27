package com.wavjaby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompletion implements TabCompleter {

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    public TabCompletion(main main) {
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = null;//player
        if (sender instanceof Player)//如果是玩家傳的
            player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("wavjabyplugin")  && args.length == 1) {//第1個選項
            List<String> list = new ArrayList<>();

            if (player != null)//如果是玩家w傳
                if (!player.hasPermission("wavjabyPlugin.tabComplete")) {//玩家沒權限
                    sender.sendMessage("You don't have tab permission");
                    return list;
                }
            if ("info".contains(args[0]))
                list.add("info");
            if ("reload".contains(args[0]))
                list.add("reload");
            return list;
        }else
            return Collections.singletonList("");
    }

}
