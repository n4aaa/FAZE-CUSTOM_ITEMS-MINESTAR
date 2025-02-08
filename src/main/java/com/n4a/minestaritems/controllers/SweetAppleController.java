package com.n4a.minestaritems.controllers;

import com.n4a.minestaritems.config.PluginConfig;
import com.n4a.minestaritems.config.domain.item.CustomItemType;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class SweetAppleController implements Listener {

    private @Inject PluginConfig pluginConfig;

    private @Inject WorldGuard worldGuard;

    private @Inject NamespacedKey namespacedKey;

    private Random random = new Random();

    @EventHandler
    public void handleAction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand().clone();

        if (!itemStack.getType().equals(Material.AIR) || itemStack.hasItemMeta()) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING)) {
                String itemType = itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);

                if (itemType != null && itemType.equals(CustomItemType.SWEET_APPLE.toString())) {
                    if (worldGuard != null) {
                        RegionContainer regionContainer = worldGuard.getPlatform().getRegionContainer();
                        RegionQuery regionQuery = regionContainer.createQuery();

                        for (ProtectedRegion protectedRegion : regionQuery.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()))) {
                            if (pluginConfig.getItemsBlockedRegions().stream().anyMatch(string -> string.equalsIgnoreCase(protectedRegion.getId()))) {
                                return;
                            }
                        }

                        player.addPotionEffect(pluginConfig.getSweetAppleEffects().get(random.nextInt(pluginConfig.getSweetAppleEffects().size())));
                        pluginConfig.getSweetAppleUsage().send(player);
                    }
                }
            }
        }
    }
}
