package com.n4a.rng.commands.admin;

import com.n4a.rng.PluginMain;
import com.n4a.rng.config.EventItemsConfig;
import com.n4a.rng.config.MessageConfig;
import com.n4a.rng.config.PluginConfig;
import com.n4a.rng.config.domain.message.Message;
import com.n4a.rng.config.domain.message.MessageType;
import com.n4a.rng.config.domain.pet.Pet;
import com.n4a.rng.menus.menu.admin.EventItemsMenu;
import com.n4a.rng.persistence.bag.BagPersistence;
import com.n4a.rng.randomevent.RandomEvent;
import com.n4a.rng.randomevent.RandomEventManager;
import com.n4a.rng.randomevent.RandomEventType;
import com.n4a.rng.utils.ItemBuilder;
import com.n4a.rng.utils.ItemUtil;
import eu.okaeri.commands.annotation.Arg;
import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.response.BukkitResponse;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command(label = "mnplugin")
@Permission("quick.admin")
public class PluginCommand implements CommandService {

    private @Inject PluginConfig pluginConfig;
    private @Inject MessageConfig messageConfig;
    private @Inject EventItemsConfig eventItemsConfig;

    private @Inject BagPersistence bagPersistence;

    private @Inject RandomEventManager randomEventManager;

    private final NamespacedKey petTypeKey = new NamespacedKey(PluginMain.getInstance(), "pet-type");

    @Executor(pattern = "reload")
    public BukkitResponse reload(CommandSender commandSender) {
        pluginConfig.load();
        messageConfig.load();
        eventItemsConfig.load();

        messageConfig.getReloaded().send(commandSender);

        return null;
    }

    @Executor(pattern = "items")
    public BukkitResponse remove(CommandSender commandSender) {
        if (commandSender instanceof Player player) {
            EventItemsMenu eventItemsMenu = new EventItemsMenu("&8Itemy eventowe:", 6);
            eventItemsMenu.show(player);
        }

        return null;
    }

    @Executor(pattern = "givePet *")
    public BukkitResponse givePet(CommandSender commandSender, @Arg("pet") String name) {
        if (commandSender instanceof Player player) {
            Pet pet = getPet(name);

            if (pet != null) {
                ItemStack item = new ItemBuilder(pet.getItem().clone()).setStringPersistentData(petTypeKey, pet.getName()).setSkin(pet.getSkin()).toItemStack();

                ItemUtil.giveItem(player, item, 1);
            }
        }

        return null;
    }

    @Executor(pattern = "startEvent *")
    public BukkitResponse startEvent(CommandSender commandSender, @Arg("type") RandomEventType type) {
        if (commandSender instanceof Player player) {
            randomEventManager.start(pluginConfig, messageConfig, getRandomEvent(type));
        }

        return null;
    }

    @Executor(pattern = "reset bags")
    public BukkitResponse resetBags(CommandSender commandSender) {
        if (commandSender instanceof Player player) {
            bagPersistence.getBagRepository().deleteAll();

            new Message(MessageType.SUBTITLE, "&cUsunięto wszystkie sakiewki!").send(player);
        }

        return null;
    }

//    @Executor(pattern = "animacja")
//    public BukkitResponse animation(CommandSender commandSender) {
//        if (commandSender instanceof Player player) {
//            HologramManager manager = FancyHologramsPlugin.get().getHologramManager();
//
//            String name = UUID.randomUUID().toString();
//            ItemHologramData hologramData = new ItemHologramData(name, player.getLocation().add(player.getLocation().getDirection().multiply(4)).getBlock().getLocation().add(0, 2.5, 0));
//            hologramData.setBillboard(Display.Billboard.FIXED);
//            hologramData.setVisibility(Visibility.ALL);
//            hologramData.setVisibilityDistance(-1);
//            hologramData.setTranslation(new Vector3f(0));
//            hologramData.setPersistent(true);
//            hologramData.setItemStack(new ItemBuilder(Material.GOLD_BLOCK).toItemStack());
//            hologramData.setScale(new Vector3f(0));
//
//            Hologram hologram = manager.create(hologramData);
//            hologram.createHologram();
//            manager.addHologram(hologram);
//            hologram.forceShowHologram(player);
//
//            customAnimationManager.addAnimation(player, new CustomAnimation(2.5D * new Random().nextDouble(), 0.0D, 0, false, true, 0F, 0F, hologram, hologramData));
//
//            Bukkit.getScheduler().runTaskTimer(PluginMain.getInstance(), task -> {
//                CustomAnimation customAnimation = customAnimationManager.getAnimations().get(player.getUniqueId());
//
//                if (customAnimation == null || customAnimation.isComplete()) {
//                    if (customAnimation != null) {
//                        customAnimationManager.removeAnimation(player);
//                    }
//
//                    task.cancel();
//                    return;
//                }
//
//                customAnimation.updateTick();
//                customAnimation.updateDelay();
//                customAnimation.updateAxis();
//                customAnimation.updateSize();
//
//                if (customAnimation.getSize() < 5) {
//                    hologramData.setScale(new Vector3f(customAnimation.getSize()));
//                } else if (customAnimation.getSize() > 5 && customAnimation.getSize() < 6) {
//                    for (int i = 0; i < 4; i++) {
//                        spawnFirework(hologramData.getLocation());
//                    }
//
//                    hologramData.setItemStack(new ItemBuilder(Material.NETHERITE_SWORD).toItemStack());
//                }
//
//                if (customAnimation.getTick() > customAnimation.getDelay() * 10.0) {
//                    customAnimation.setTick(0);
//
//                    if (customAnimation.getDelay() >= 0.5) {
//                        customAnimation.setComplete(true);
//                        customAnimation.setSpinning(false);
//
//                        System.out.println("completed");
//
//                        if (manager.getHologram(name).isPresent()) {
//                            manager.getHologram(name).get().forceHideHologram(player);
//                            manager.getHologram(name).get().deleteHologram();
//                            manager.removeHologram(hologram);
//                        }
//
//                        customAnimationManager.removeAnimation(player);
//
//                        task.cancel();
//                    }
//                }
//            }, 0L, 10L);
//        }
//
//        return null;
//    }
//
//    @Executor(pattern = "setcooldown *")
//    public BukkitResponse setCooldown(CommandSender commandSender, @Arg("cooldown") Integer cooldown) {
//        if (commandSender instanceof Player player) {
//            if (player.getItemInHand().isEmpty()) {
//                new MessageUtil().message(player, MessageType.SUBTITLE, "&cMuisz trzymać w ręce &4PRZEDMIOT!");
//
//                return null;
//            }
//
//            player.setCooldown(player.getItemInHand().getType(), cooldown * 20);
//            new MessageUtil().message(player, MessageType.SUBTITLE, "&aPomyślnie nadano &2COOLDOWN!");
//        }
//
//        return null;
//    }
//
//    public static void spawnFirework(Location location) {
//        Firework firework = location.getWorld().spawn(location, Firework.class);
//        FireworkMeta fireworkMeta = firework.getFireworkMeta();
//
//        FireworkEffect effect = FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.WHITE).with(FireworkEffect.Type.BALL).trail(true).flicker(true).build();
//
//        fireworkMeta.addEffect(effect);
//        fireworkMeta.setPower(0);
//        firework.setFireworkMeta(fireworkMeta);
//        firework.setTicksToDetonate(1);
//    }

    private Pet getPet(String name) {
        return pluginConfig.getPets().getIndex().stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    private RandomEvent getRandomEvent(RandomEventType type) {
        return pluginConfig.getRandomEvents().stream().filter(event -> event.getType().equals(type)).findFirst().orElse(null);
    }
}
