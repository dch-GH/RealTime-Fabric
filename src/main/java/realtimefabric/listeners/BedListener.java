package realtimefabric.listeners;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents.AllowResettingTime;
import net.minecraft.entity.player.PlayerEntity;
import realtimefabric.ModConfig;

public class BedListener implements AllowResettingTime {
	@Override
	public boolean allowResettingTime(PlayerEntity player) {
	// Disallow time resetting while the mod is enabled.
	// This still sets the Time Since Last Rest stat, so insomnia won't occur!
		return !ModConfig.Enabled;
	}
}
