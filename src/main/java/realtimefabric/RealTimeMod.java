package realtimefabric;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import realtimefabric.commands.ForceSyncTimeCommand;
import realtimefabric.commands.ToggleRtCommand;
import realtimefabric.listeners.BedListener;
import realtimefabric.listeners.RealTimeServerWorldLoadListener;
import realtimefabric.listeners.RealTimeTickListener;
import realtimefabric.listeners.ServerStoppingListener;

public class RealTimeMod implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "realtimefabric";
    public static final String MOD_NAME = "RealTimeFabric";
    public static RealTimeTickListener tickListener = new RealTimeTickListener();
    private static boolean isDebug = false;

	@Override
	public void onInitialize() {
        log(Level.INFO, "Initializing");

        ServerTickEvents.START_SERVER_TICK.register(tickListener);
        ServerWorldEvents.LOAD.register(new RealTimeServerWorldLoadListener());
        ServerLifecycleEvents.SERVER_STOPPING.register(new ServerStoppingListener());
        EntitySleepEvents.ALLOW_SLEEPING.register(new BedListener());

		net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback.EVENT.register((dispatcher, reg, env) -> {
			ForceSyncTimeCommand.register(dispatcher);
            ToggleRtCommand.register(dispatcher);
		});


	}

    public static void log(Level level, String message){
    	if(!isDebug) return;
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}
