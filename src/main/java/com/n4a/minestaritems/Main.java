package com.n4a.minestaritems;

import com.n4a.minestaritems.config.PluginConfig;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.NamespacedKey;

import java.io.File;

@Scan(exclusions = "com.n4a.minestaritems.libs", deep = true)
public final class Main extends OkaeriBukkitPlugin {

    private static @Getter Main main;
    private @Getter PluginConfig pluginConfig;
    private final @Getter NamespacedKey namespacedKey = new NamespacedKey(this, "eventItemsType");

    @Planned(ExecutionPhase.PRE_SETUP)
    public void onPreSetup() {
        main = this;

        this.pluginConfig = ConfigManager.create(PluginConfig.class, it -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withBindFile(new File(main.getDataFolder(), "config.yml"));
            it.saveDefaults();
            it.load(true);
        });
    }

    @Planned(ExecutionPhase.STARTUP)
    public void onStartup() {
        this.getLogger().info("Enabled!");
    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    public void onPreShutdown() {
    }

    @Planned(ExecutionPhase.SHUTDOWN)
    public void onShutdown() {
        this.getLogger().info("Disabled!");
    }

    @Bean(value = "pluginConfig")
    public PluginConfig pluginConfig() {
        return this.pluginConfig;
    }

    @Bean(value = "namespacedKey")
    public NamespacedKey namespacedKey() {
        return this.namespacedKey;
    }
}
