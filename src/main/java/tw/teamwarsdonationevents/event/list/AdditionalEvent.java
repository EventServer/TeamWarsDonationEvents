package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import eventserver.teamwars.game.Team;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AdditionalEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        final Game game = TeamWars.getInstance().getGame();
        if (game.getState() != Game.State.ACTIVE)
            return false;

        final Team team = getRandomTeam(game.getTeamManager().getTeams());
        if (team == null) return false;
        team.setAdditionalMembers(2);
        final String text = eventserver.teamwars.Config.MESSAGES.SET_ADDITIONAL
                .replace("%team%", team.getPrefix())
                .replace("%amount%", "2")
                .replace("%team-id%", team.getId());
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(text));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.ADDITIONAL;
    }

    @Nullable
    private Team getRandomTeam(Set<Team> teams) {
        int size = teams.size();
        int item = ThreadLocalRandom.current().nextInt(size);
        int i = 0;
        for(Team obj : teams)
        {
            if (i == item)
                return obj;
            i++;
        }
        return null;
    }
}
