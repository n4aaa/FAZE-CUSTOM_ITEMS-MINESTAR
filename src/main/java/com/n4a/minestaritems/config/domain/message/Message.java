package com.n4a.godice.config.domain.message;

import com.n4a.godice.utils.ChatUtil;
import com.n4a.godice.utils.MessageUtil;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message extends OkaeriConfig {
    private MessageType type;
    private String message;

    public void send(Player player) {
        new MessageUtil().message(player, type, ChatUtil.fixColor(message));
    }
    public void send(CommandSender commandSender) {
        new MessageUtil().message(commandSender, type, ChatUtil.fixColor(message));
    }

    public Message placeholder(String oldValue, String newValue) {
        return new Message(type, message.replace(oldValue, newValue));
    }
}
