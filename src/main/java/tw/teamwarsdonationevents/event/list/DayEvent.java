package tw.teamwarsdonationevents.event.list;

import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class DayEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        eventserver.teamwars.Config.world.setTime(0);
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.DAY;
    }
}
