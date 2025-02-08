package com.n4a.minestaritems.cooldown;

import com.n4a.minestaritems.config.domain.item.CustomItemType;
import eu.okaeri.commons.cache.CacheMap;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class CooldownManager {
    private final CacheMap<UUID, HashMap<CustomItemType, Long>> cooldowns = new CacheMap<>();

    public boolean hasCooldown(Player player, CustomItemType type, long cooldown) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            return cooldowns.get(player.getUniqueId()).containsKey(type) && (System.currentTimeMillis() - cooldowns.get(player.getUniqueId()).get(type) < cooldown);
        }

        return false;
    }

    public long getPlayerItemCooldown(Player player, CustomItemType type, long cooldown)  {
        return (cooldown - (System.currentTimeMillis() - cooldowns.get(player.getUniqueId()).getOrDefault(type, 0L)));
    }
}