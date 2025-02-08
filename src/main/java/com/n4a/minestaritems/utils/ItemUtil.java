package com.n4a.minestaritems.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class ItemUtil {

    public static void giveItem(Player player, ItemStack itemStack) {
        if (ItemUtil.hasSpace(player.getInventory(), itemStack)) {
            player.getInventory().addItem(itemStack);

            return;
        }

        player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemStack);
    }

    public static void giveItem(Player player, ItemStack itemStack, int amount) {
        if (ItemUtil.hasSpace(player.getInventory(), itemStack)) {
            itemStack.setAmount(amount);
            player.getInventory().addItem(itemStack);

            return;
        }

        player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemStack);
    }

    public static void takeItem(Player player, ItemStack itemStack, int amount) {
        Inventory inventory = player.getInventory();
        int remaining = amount;

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.isSimilar(itemStack)) {
                if (item.getAmount() > remaining) {
                    item.setAmount(item.getAmount() - remaining);
                    return;
                } else {
                    remaining -= item.getAmount();
                    inventory.removeItem(item);
                    if (remaining <= 0) {
                        return;
                    }
                }
            }
        }
    }

    public static boolean hasItem(Player player, ItemStack itemStack, int amount) {
        Inventory inventory = player.getInventory();
        int totalAmount = 0;

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.isSimilar(itemStack)) {
                totalAmount += item.getAmount();
                if (totalAmount >= amount) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasSpace(Inventory inventory, ItemStack itemStack) {
        if (inventory.firstEmpty() != -1) {
            return true;
        }

        for (ItemStack itemInv : inventory.getContents()) {
            if (itemInv == null || !itemInv.isSimilar(itemStack) || itemInv.getMaxStackSize() <= itemInv.getAmount()) continue;
            return true;
        }

        return false;
    }
}