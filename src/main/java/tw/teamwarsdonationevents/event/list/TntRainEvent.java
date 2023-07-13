package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import eventserver.teamwars.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.TeamWarsDonationEvents;
import tw.teamwarsdonationevents.event.DonationEvent;
import tw.teamwarsdonationevents.utils.RegionUtils;

import java.util.List;

public class TntRainEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        final Game game = TeamWars.getInstance().getGame();
        new BukkitRunnable() {
            int count = 1;
            @Override
            public void run() {
                if (count == 20) {
                    cancel();
                }
                count++;
                for (Team team: game.getTeamManager().getTeams()) {
                    final List<Location> locations = RegionUtils.getRandomAirLocations(team.getRegion(), eventserver.teamwars.Config.world, 10);
                    locations.forEach(l -> l.getWorld().spawnEntity(l, EntityType.PRIMED_TNT, CreatureSpawnEvent.SpawnReason.CUSTOM, e -> {
                        final TNTPrimed tnt = (TNTPrimed) e;
                        tnt.setFuseTicks(200);
                    }));
                }
            }
        }.runTaskTimer(TeamWarsDonationEvents.getInstance(), 20, 20);

        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.TNT_RAIN;
    }
}
