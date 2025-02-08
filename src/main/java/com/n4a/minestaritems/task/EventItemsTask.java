package com.n4a.minestaritems.task;

import com.n4a.minestaritems.Main;
import com.n4a.minestaritems.config.PluginConfig;
import com.n4a.minestaritems.config.domain.item.CustomItemType;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.List;

@Scheduled(rate = MinecraftTimeEquivalent.SECOND, async = true)
public class EventItemsTask implements Runnable {

    private @Inject PluginConfig pluginConfig;
    private @Inject NamespacedKey namespacedKey;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getInventory().getItemInOffHand().getType().isEmpty() ) {
                CustomItemType type = getItemType(player.getInventory().getItemInOffHand());

                if (type != null) {
                    if (pluginConfig.getOffhandItemsEffects().containsKey(type)) {
                        List<PotionEffect> effects = pluginConfig.getOffhandItemsEffects().get(type);

                        for (PotionEffect effect : effects) {
                            Runnable prepareAndExecuteSync = () -> {
                                player.addPotionEffect(effect);
                            };

                            Main.getMain().getServer().getScheduler().runTask(Main.getMain(), prepareAndExecuteSync);
                        }
                    }
                }
            }
        }
    }

    private CustomItemType getItemType(ItemStack item) {
        if (item.hasItemMeta()) {
            ItemMeta im = item.getItemMeta();

            if (im.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING)) {
                return CustomItemType.valueOf(im.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING));
            }
        }

        return null;
    }
}
