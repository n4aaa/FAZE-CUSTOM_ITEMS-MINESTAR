package com.n4a.rng.config.domain.menu;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
@Setter
public class ItemMenu extends OkaeriConfig {
    private int slot;
    private ItemStack itemStack;
    private ItemType itemType;
    private int customModelData;
}