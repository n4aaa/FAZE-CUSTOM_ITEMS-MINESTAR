package com.n4a.minestaritems.command;

import com.n4a.minestaritems.config.PluginConfig;
import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.response.BukkitResponse;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(label = "fazecustomitemsminestar")
@Permission("quick.admin")
public class PluginCommand implements CommandService {

    private @Inject PluginConfig pluginConfig;

    @Executor(pattern = "reload")
    public BukkitResponse reload(CommandSender commandSender) {
        pluginConfig.load();

        pluginConfig.getReloadMessage().send(commandSender);

        return null;
    }

    @Executor(pattern = "items")
    public BukkitResponse items(CommandSender commandSender) {
        if (commandSender instanceof Player player) {
//            EventItemsMenu eventItemsMenu = new EventItemsMenu("&8Itemy eventowe:", 6);
//            eventItemsMenu.show(player);
        }

        return null;
    }
}
