package realtimefabric.listeners;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import realtimefabric.RealTimeMod;

public class RealTimeServerWorldLoadListener implements Load {

	@Override
	public void onWorldLoad(MinecraftServer server, ServerWorld world) {
		RealTimeMod.tickListener.syncTime(world);	
	}

}
