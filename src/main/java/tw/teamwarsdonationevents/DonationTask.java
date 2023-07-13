package tw.teamwarsdonationevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.event.DonationEvent;

@AllArgsConstructor @ToString @Getter
public class DonationTask {
    private final DonationEvent event;
    private final Donation donation;

    public void execute() {
        if (event.execute(donation))
            event.notify(donation);
    }
}
