package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

import java.util.concurrent.TimeUnit;

public class Remove5MinEvent implements DonationEvent {

    @Override
    public boolean execute(Donation donation) {
        final Game game = TeamWars.getInstance().getGame();
        if (game.getState() != Game.State.ACTIVE)
            return false;

        final long startDate = game.getStartBattleDate();
        if (startDate - System.currentTimeMillis() < TimeUnit.MINUTES.toMillis(5))
            return false;

        game.setStartBattleDate(game.getStartBattleDate() + TimeUnit.MINUTES.toMillis(5));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.REMOVE_5_MIN;
    }
}
