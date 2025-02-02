package com.n4a.rng.utils;

import com.n4a.rng.config.domain.message.MessageType;
import eu.okaeri.commons.bukkit.UnsafeBukkitCommons;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    public void message(CommandSender sender, MessageType type, String message) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (type) {
                case NOTHING: {
                    break;
                }

                case CHAT: {
                    UnsafeBukkitCommons.sendMessage(player, ChatUtil.fixColor(message), UnsafeBukkitCommons.ChatTarget.CHAT);

                    break;
                }

                case ACTIONBAR: {
                    UnsafeBukkitCommons.sendMessage(player, ChatUtil.fixColor(message), UnsafeBukkitCommons.ChatTarget.ACTION_BAR);

                    break;
                }

                case TITLE: {
                    UnsafeBukkitCommons.sendTitle(player, ChatUtil.fixColor(message), "", 10, 60, 10);
                    break;
                }

                case SUBTITLE: {
                    UnsafeBukkitCommons.sendTitle(player, "", ChatUtil.fixColor(message), 10, 60, 10);

                    break;
                }

                case TITLE_SUBTITLE: {
                    String[] split = message.split("%NEWLINE%");

                    if (split.length < 2) {
                        throw new IllegalArgumentException("Notice with TITLE_SUBTITLE needs to have %NEWLINE% to include both title and subtitle.");
                    }

                    String component = split[0];
                    String subComponent = split[1];

                    UnsafeBukkitCommons.sendTitle(player, ChatUtil.fixColor(component), ChatUtil.fixColor(subComponent), 10, 60, 10);

                    break;
                }

                default: {
                    throw new IllegalArgumentException("Unknown message type: " + type);
                }
            }
        } else {
            sender.sendMessage(ChatUtil.fixColor(message));
        }
    }
}
