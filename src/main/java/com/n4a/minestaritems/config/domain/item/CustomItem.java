package com.n4a.godice.config.domain.item;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
@Setter
public class CustomItem extends OkaeriConfig {
    private ItemStack itemStack;
    private int customModelData;
}
