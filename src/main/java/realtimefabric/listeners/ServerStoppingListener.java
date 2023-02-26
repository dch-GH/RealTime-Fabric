package realtimefabric.listeners;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public class ServerStoppingListener implements ServerLifecycleEvents.ServerStopping {

    @Override
    public void onServerStopping(MinecraftServer server) {
        server.getOverworld().getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(true, server);
    }
}
