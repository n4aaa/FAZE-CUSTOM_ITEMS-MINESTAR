package com.n4a.minestaritems.config.domain.item;

import com.n4a.minestaritems.utils.ItemBuilder;
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

    public ItemStack toItemStack() {
        return new ItemBuilder(itemStack.clone()).setCustomModelData(customModelData).toItemStack();
    }
}
