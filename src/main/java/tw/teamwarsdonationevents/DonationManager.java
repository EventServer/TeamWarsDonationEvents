package tw.teamwarsdonationevents;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tw.donationevents.donation.Donation;
import tw.donationevents.event.DonationAlertsEvent;
import tw.teamwarsdonationevents.event.DonationEvents;

import java.util.concurrent.ConcurrentLinkedDeque;

public class DonationManager {
    private final JavaPlugin plugin;
    @Getter
    final ConcurrentLinkedDeque<DonationTask> tasks = new ConcurrentLinkedDeque<>();

    public DonationManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new EventListener(), plugin);
        runExecuteTask();
    }


    private class EventListener implements Listener {

        @EventHandler
        private void onDonation(DonationAlertsEvent event) {
            final Donation donation = event.getDonation();
            final DonationEvents events = DonationEvents.getEvent(donation.getCash(), donation.getCurrency());
            if (events == null) return;

            final DonationTask task = new DonationTask(events.getEvent(), donation);
            tasks.add(task);
        }
    }

    private void runExecuteTask() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (tasks.isEmpty()) return;
            DonationTask task = tasks.element();
            task.execute();
            tasks.remove(task);
        }, 0, 100L);
    }
}
