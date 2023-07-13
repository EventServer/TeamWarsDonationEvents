package tw.teamwarsdonationevents;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeamWarsDonationEvents extends JavaPlugin {
    private DonationManager donationManager;
    @Getter
    private static TeamWarsDonationEvents instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Config.load(getConfig());

        this.donationManager = new DonationManager(this);
   }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
