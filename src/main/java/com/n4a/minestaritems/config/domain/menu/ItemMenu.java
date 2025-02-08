package com.n4a.minestaritems.config.domain.menu;

import com.n4a.minestaritems.config.domain.item.CustomItem;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemMenu extends OkaeriConfig {
    private int slot;
    private CustomItem customItem;
    private ItemType itemType;
}