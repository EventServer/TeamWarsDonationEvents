package tw.teamwarsdonationevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import tw.donationevents.donation.Currency;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Config {

    private Config() {
        throw new IllegalStateException("Utility class");
    }

    public static void load(FileConfiguration file) {
        final ConfigurationSection events = file.getConfigurationSection("events");
        if (events != null)
            parseEvents(events);

        NOTIFY_PREFIX = file.getString("notify-prefix");
    }

    private static void parseEvents(ConfigurationSection events) {
        final ConfigurationSection healAll = events.getConfigurationSection("heal-all");
        if (healAll != null)
            HEAL_ALL = parseEvent(healAll);

        final ConfigurationSection feedAll = events.getConfigurationSection("feed-all");
        if (feedAll != null)
            FEED_ALL = parseEvent(feedAll);

        final ConfigurationSection killAll = events.getConfigurationSection("kill-all");
        if (killAll != null)
            KILL_ALL = parseEvent(killAll);

        final ConfigurationSection day = events.getConfigurationSection("day");
        if (day != null)
            DAY = parseEvent(day);

        final ConfigurationSection night = events.getConfigurationSection("night");
        if (night != null)
            NIGHT = parseEvent(night);

        final ConfigurationSection additional = events.getConfigurationSection("additional");
        if (additional != null)
            ADDITIONAL = parseEvent(additional);

        final ConfigurationSection jumpAll = events.getConfigurationSection("jump-all");
        if (jumpAll != null)
            JUMP_ALL = parseEvent(jumpAll);

        final ConfigurationSection add5Min = events.getConfigurationSection("add-5-min");
        if (add5Min != null)
            ADD_5_MIN = parseEvent(add5Min);

        final ConfigurationSection remove5Min = events.getConfigurationSection("remove-5-min");
        if (remove5Min != null)
            REMOVE_5_MIN = parseEvent(remove5Min);

        final ConfigurationSection tntRain = events.getConfigurationSection("tnt-rain");
        if (tntRain != null)
            TNT_RAIN = parseEvent(tntRain);

        final ConfigurationSection fire = events.getConfigurationSection("fire");
        if (fire != null)
            FIRE = parseEvent(fire);

        final ConfigurationSection dirtRain = events.getConfigurationSection("dirt-rain");
        if (dirtRain != null)
            DIRT_RAIN = parseEvent(dirtRain);

        final ConfigurationSection diamonds = events.getConfigurationSection("diamonds");
        if (diamonds != null)
            DIAMONDS = parseEvent(diamonds);

        final ConfigurationSection airDiamonds = events.getConfigurationSection("air-diamonds");
        if (airDiamonds != null)
            AIR_DIAMONDS = parseEvent(airDiamonds);

        final ConfigurationSection smoupWither = events.getConfigurationSection("smoup-wither");
        if (smoupWither != null)
            SMOUP_WITHER = parseEvent(smoupWither);
    }

    private static EventConfig parseEvent(ConfigurationSection section) {
        final List<String> chatNotify = section.getStringList("chat-notify");
        final List<String> aliases = section.getStringList("text-aliases");
        Title t = null;
        if (section.contains("title")) {
            final String title = section.getString("title.title", "");
            final String sub = section.getString("title.sub", "");
            t = Title.title(LegacyComponentSerializer.legacySection().deserialize(title),
                    LegacyComponentSerializer.legacySection().deserialize(sub));
        }



        final ConfigurationSection priceSection = section.getConfigurationSection("price");
        final EnumMap<Currency, Double> prices = new EnumMap<>(Currency.class);
        if (priceSection != null) {
            for (String str: priceSection.getKeys(false)) {
                final Currency currency = Currency.valueOf(str);
                final double price = priceSection.getDouble(str);
                prices.put(currency, price);
            }
        }

        Sound sound = null;
        if (section.contains("sound")) {
            sound = Sound.valueOf(section.getString("sound"));
        }
        return new EventConfig(t,sound, chatNotify, prices, aliases);
    }

    public static String NOTIFY_PREFIX;
    public static EventConfig DAY;
    public static EventConfig NIGHT;
    public static EventConfig KILL_ALL;
    public static EventConfig DIAMONDS;
    public static EventConfig HEAL_ALL;
    public static EventConfig JUMP_ALL;
    public static EventConfig ADD_5_MIN;
    public static EventConfig AIR_DIAMONDS;
    public static EventConfig REMOVE_5_MIN;
    public static EventConfig TNT_RAIN;
    public static EventConfig ADDITIONAL;
    public static EventConfig DIRT_RAIN;
    public static EventConfig FIRE;
    public static EventConfig SMOUP_WITHER;
    public static EventConfig FEED_ALL;
    @AllArgsConstructor @Getter @ToString
    public static class EventConfig {
        private final Title title;
        private final Sound sound;
        private final List<String> chatNotify;
        private final Map<Currency, Double> price;
        private final List<String> aliases;
    }
}
