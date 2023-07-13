package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import eventserver.teamwars.game.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;
import tw.teamwarsdonationevents.utils.RegionUtils;

import java.util.List;

public class AirDiamondsEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        final Game game = TeamWars.getInstance().getGame();
        for (Team team: game.getTeamManager().getTeams()) {
            final List<Location> locations = RegionUtils.getRandomAirLocations(team.getRegion(), eventserver.teamwars.Config.world, 40);
            locations.forEach(l -> l.getBlock().setType(Material.DIAMOND_ORE));
        }
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.AIR_DIAMONDS;
    }
}
