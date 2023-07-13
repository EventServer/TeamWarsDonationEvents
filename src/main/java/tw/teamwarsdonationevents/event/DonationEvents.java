package tw.teamwarsdonationevents.event;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import tw.donationevents.donation.Currency;
import tw.teamwarsdonationevents.event.list.*;

public enum DonationEvents {
    FEED_ALL(new FeedAllEvent()),
    KILL_ALL(new KillAllEvent()),
    NIGHT(new NightEvent()),
    DAY(new DayEvent()),
    ADDITIONAL(new AdditionalEvent()),
    JUMP_ALL(new JumpAllEvent()),
    FIRE(new FireEvent()),
    AIR_DIAMONDS(new AirDiamondsEvent()),
    DIRT_RAIN(new DirtRain()),
    DIAMONDS(new DiamondsEvent()),
    ADD_5_MIN(new Add5MinEvent()),
    REMOVE_5_MIN(new Remove5MinEvent()),
    SMOUP_WITHER(new SmoupWitherEvent()),
    TNT_RAIN(new TntRainEvent()),
    HEAL_ALL(new HealAllEvent());

    @Getter
    private final DonationEvent event;
    DonationEvents(DonationEvent event) {
        this.event = event;
    }

    @Nullable
    public static DonationEvents getEvent(double price, Currency currency) {
        for (DonationEvents e: values()) {
            final DonationEvent event = e.getEvent();
            if (!event.getConfig().getPrice().containsKey(currency)) {
                continue;
            }
            if (event.getConfig().getPrice().get(currency) == price) {
                return e;
            }
        }
        return null;
    }
}
