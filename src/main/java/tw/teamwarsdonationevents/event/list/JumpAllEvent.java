package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class JumpAllEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        TeamWars.getInstance().getGame().getTeamManager().getTeams().forEach(
            team -> team.getMembers().forEach(member -> {
                final Player player = member.getBukkitInstance();
                if (player != null && member.isLife())
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 49));
            }));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.JUMP_ALL;
    }
}
