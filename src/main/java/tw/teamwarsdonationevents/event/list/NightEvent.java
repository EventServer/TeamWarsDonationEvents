package tw.teamwarsdonationevents.event.list;

import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class NightEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        eventserver.teamwars.Config.world.setTime(20000);
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.NIGHT;
    }
}
