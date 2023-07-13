package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import eventserver.teamwars.game.Game;
import eventserver.teamwars.game.Team;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.TeamWarsDonationEvents;
import tw.teamwarsdonationevents.event.DonationEvent;
import tw.teamwarsdonationevents.utils.RegionUtils;

import java.util.List;

public class DirtRain implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        final Game game = TeamWars.getInstance().getGame();
        final ItemStack stack = new ItemStack(Material.COARSE_DIRT, 64);
        final ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.RED+donation.getDonor());
        stack.setItemMeta(meta);
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
                    locations.forEach(l -> l.getWorld().dropItem(l, stack.clone()));
                }
            }
        }.runTaskTimer(TeamWarsDonationEvents.getInstance(), 20, 20);

        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.DIRT_RAIN;
    }
}
