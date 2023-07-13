package tw.teamwarsdonationevents.event;

import org.bukkit.Bukkit;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;

public interface DonationEvent {
    boolean execute(Donation donation);
    default void notify(Donation donation) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (getConfig().getTitle() != null)
                p.showTitle(getConfig().getTitle());
            if (getConfig().getSound() != null)
                p.playSound(p.getLocation(), getConfig().getSound(), 0.7F, 1.0F);
            getConfig().getChatNotify().forEach(
                    str -> p.sendMessage(str.replace("%customer%", donation.getDonor())
                            .replace("%text%", donation.getMessage())));
        });
    }

    Config.EventConfig getConfig();
}
