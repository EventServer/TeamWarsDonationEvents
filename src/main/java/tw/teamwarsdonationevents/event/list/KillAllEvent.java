package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import org.bukkit.entity.Player;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class KillAllEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        if (TeamWars.getInstance().getGame().getState() == Game.State.BATTLE)
            return false;

        TeamWars.getInstance().getGame().getTeamManager().getTeams().forEach(
            team -> team.getMembers().forEach(member -> {
                final Player player = member.getBukkitInstance();
                if (player != null && member.isLife())
                    player.setHealth(0);
            }));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.KILL_ALL;
    }
}
