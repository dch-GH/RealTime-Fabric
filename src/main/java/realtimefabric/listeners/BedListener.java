package realtimefabric.listeners;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.text.*;

import org.jetbrains.annotations.Nullable;
import realtimefabric.ModConfig;

public class BedListener implements EntitySleepEvents.AllowSleeping {

    @Override
    public PlayerEntity.@Nullable SleepFailureReason allowSleep(PlayerEntity player, BlockPos sleepingPos) {
        if (ModConfig.Enabled && !player.getEntityWorld().isClient) {
            player.sendMessage(MutableText.of(new TranslatableTextContent("realtimefabric.cannot.sleep")));
            return PlayerEntity.SleepFailureReason.NOT_POSSIBLE_NOW;
        } else {
            return null;
        }

    }
}
