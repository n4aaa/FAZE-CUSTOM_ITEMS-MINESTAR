package com.n4a.minestaritems.config;

import com.n4a.minestaritems.config.domain.item.CustomItem;
import com.n4a.minestaritems.config.domain.item.CustomItemType;
import com.n4a.minestaritems.config.domain.message.Message;
import com.n4a.minestaritems.config.domain.message.MessageType;
import com.n4a.minestaritems.utils.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Header("##")
@Header("## FAZE-CUSTOM_ITEMS-MINESTAR (Plugin-Config) ##")
@Header("##")
public class PluginConfig extends OkaeriConfig {
    @Comment("Ustawienia itemów eventowych")
    private Map<CustomItemType, Integer> itemsCooldown = Map.ofEntries(
            Map.entry(CustomItemType.PUMPKIN_GRENADE, 15)
    );

    private List<String> itemsBlockedRegions = Arrays.asList("spawn", "afk");

    private Map<CustomItemType, List<PotionEffect>> offhandItemsEffects = Map.ofEntries(
            Map.entry(
                    CustomItemType.CANDY_CANE,
                    Collections.singletonList(
                            new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 3)
                    )
            )
    );

    @Comment("Dyniowy granat")
    private CustomItem pumpkinGrenade = new CustomItem(new ItemBuilder(Material.JACK_O_LANTERN).setName("&#F78F4A&lᴅ&#F38C46&lʏ&#EE8A42&lɴ&#EA873F&lɪ&#E6853B&lᴏ&#E18237&lɢ&#DD8033&lʟ&#D87D2F&lᴏ&#D47B2C&lᴡ&#D07828&lʏ &#C77320&lɢ&#C3701C&lʀ&#BE6E18&lᴀ&#BA6B15&lɴ&#B56911&lᴀ&#B1660D&lᴛ").setLore("&8× &fJednorazowy przedmiot rzucany, po użyciu hełmy", "&8× &fosób dotkniętych czarem zostają &6zmienione w dynie", "&8× &fna okres &akilku sekund&f!", "", "&8× &7Przedmiot pochodzi z eventu &6ʜᴀʟʟᴏᴡᴇᴇɴ 2024").toItemStack(), 0);
    @Comment("Dyniowy granat - czas trwania (sekundy)")
    private int pumpkinGrenadeDuration = 6;

    @Comment("Slodkie jablko")
    private CustomItem sweetApple =  new CustomItem(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).setName("&#F85AD4&lꜱ&#F860D5&lʟ&#F865D7&lᴏ&#F86BD8&lᴅ&#F971DA&lᴋ&#F976DB&lɪ&#F97CDC&lᴇ &#F988DF&lᴊ&#F98DE0&lᴀ&#FA93E2&lʙ&#FA99E3&lʟ&#FA9EE5&lᴋ&#FAA4E6&lᴏ").setLore("&8× &fPo zjedzeniu mamy szanse na", "&8× &fotrzymanie większej ilości &adodatkowych serc", "", "&8× &7Przedmiot pochodzi z eventu &6ʜᴀʟʟᴏᴡᴇᴇɴ 2024").toItemStack(), 0);
    @Comment("Slodkie jablko - losowe efekty")
    private List<PotionEffect> sweetAppleEffects = Arrays.asList(
            new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 0),
            new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 1),
            new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 2),
            new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 3)
    );

    @Comment("Laska cukrowa")
    private CustomItem candyCane = new CustomItem(new ItemBuilder(Material.SUGAR_CANE).setName("&#FD6F7C&lʟ&#FC7985&lᴀ&#FB828E&lꜱ&#FA8C96&lᴋ&#F9959F&lᴀ &#F7A9B1&lᴄ&#F5B2B9&lᴜ&#F4BCC2&lᴋ&#F3C5CB&lʀ&#F2CFD4&lᴏ&#F1D8DC&lᴡ&#F0E2E5&lᴀ").setLore("&8× &fTrzymając w drugiej ręce otrzymujesz &c2&4❤", "", "&8× &7Przedmiot pochodzi z eventu &bꜱᴡɪᴀᴛᴇᴄᴢɴᴇɢᴏ 2024").toItemStack(), 0);

    @Comment("Piernik")
    private CustomItem gingerbread = new CustomItem(new ItemBuilder(Material.SUGAR_CANE).setName("&#FD6F7C&lʟ&#FC7985&lᴀ&#FB828E&lꜱ&#FA8C96&lᴋ&#F9959F&lᴀ &#F7A9B1&lᴄ&#F5B2B9&lᴜ&#F4BCC2&lᴋ&#F3C5CB&lʀ&#F2CFD4&lᴏ&#F1D8DC&lᴡ&#F0E2E5&lᴀ").setLore("&8× &cPrzedmiot jest jednorazowy", "", "&8× &fPo użyciu otrzymasz silny &cefekt regeneracji &fzdrowia", "", "&8× &7Przedmiot pochodzi z eventu &bꜱᴡɪᴀᴛᴇᴄᴢɴᴇɢᴏ 2024").toItemStack(), 0);
    private PotionEffect gingerbreadEffect = new PotionEffect(PotionEffectType.REGENERATION, 100, 2);

    private Message reloadMessage = new Message(MessageType.SUBTITLE, "&aPomyślnie przeładowano!");
    private Message gingerbreadUsage = new Message(MessageType.SUBTITLE, "&aUżyłeś piernika!");
    private Message pumpkinGrenadeUsage = new Message(MessageType.SUBTITLE, "&aUżyłeś dyniowego granatu!");
    private Message sweetAppleUsage = new Message(MessageType.SUBTITLE, "&aZjedzono słodkie jabłko!");
}
