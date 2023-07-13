package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import org.bukkit.entity.Player;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class FeedAllEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        TeamWars.getInstance().getGame().getTeamManager().getTeams().forEach(
            team -> team.getMembers().forEach(member -> {
                final Player player = member.getBukkitInstance();
                if (player != null)
                    player.setFoodLevel(20);
            }));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.FEED_ALL;
    }
}
