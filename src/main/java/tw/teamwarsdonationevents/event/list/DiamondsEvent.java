package tw.teamwarsdonationevents.event.list;

import eventserver.teamwars.TeamWars;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tw.donationevents.donation.Donation;
import tw.teamwarsdonationevents.Config;
import tw.teamwarsdonationevents.event.DonationEvent;

public class DiamondsEvent implements DonationEvent {
    @Override
    public boolean execute(Donation donation) {
        final ItemStack stack = new ItemStack(Material.DIAMOND, 1);
        final ItemMeta meta = stack.getItemMeta();
        meta.displayName(LegacyComponentSerializer.legacySection().deserialize(donation.getDonor()));
        stack.setItemMeta(meta);
        TeamWars.getInstance().getGame().getTeamManager().getTeams().forEach(
            team -> team.getMembers().forEach(member -> {
                final Player player = member.getBukkitInstance();
                if (player != null && member.isLife())
                    player.getInventory().addItem(stack.clone()).forEach((i, st)
                            -> player.getLocation().getWorld().dropItem(player.getLocation(), st));
            }));
        return true;
    }

    @Override
    public Config.EventConfig getConfig() {
        return Config.DIAMONDS;
    }
}
