package tw.teamwarsdonationevents.event.list;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class SmoupWitherEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        eventserver.teamwars.Config.world.spawnEntity(new Location(eventserver.teamwars.Config.world, 653, 71, 143), EntityType.WITHER, CreatureSpawnEvent.SpawnReason.COMMAND, e -> {
            e.setCustomNameVisible(true);
            e.customName(Component.text("Smoup"));
        });
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.SMOUP_WITHER;
    }
}
