package realtimefabric.listeners;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Level;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartTick;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import realtimefabric.RealTimeMod;

public class RealTimeTickListener implements StartTick {
	
	private int tickCount = 0;
	private int syncTick = 1200;
	private ServerWorld serverWorld;
	private int offset = 1;
	
	public RealTimeTickListener() {
		RealTimeMod.log(Level.INFO, "RealTime mod tick listener created!");
	}
	
	@Override
	public void onStartTick(MinecraftServer server) {
		this.serverWorld = server.getOverworld();
		if(this.tickCount == 0) {
			syncTime(this.serverWorld);
		}
		
		this.tickCount++;	
		if(this.tickCount % this.syncTick == 0) {
			RealTimeMod.log(Level.INFO, "syncing at tick:" + this.tickCount);
			syncTime(this.serverWorld);
		}
	
	}
	
	
	public void syncTime() {
		this.syncTime(this.serverWorld);
	}
	
	public void syncTime(ServerWorld world) {
		if(world == null && this.serverWorld == null) {
			RealTimeMod.log(Level.ERROR, "syncTime called with NULL ServerWorld world. Cannot sync time.");
			return;
		}

		world.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(false, (MinecraftServer)null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        String hour = dateFormat.format(date);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("mm");
        Date date1 = new Date();
        String minute = dateFormat1.format(date1);
        float offset_sec = offset * 60 * 60;
        float offset_goal = offset_sec / 86400.0f * 24000.0f;
        int hour_int = Integer.parseInt(hour);
        int minute_int = Integer.parseInt(minute);
        float total_sec = hour_int * 60 * 60 + minute_int * 60;
        float goal_time = total_sec / 86400.0f * 24000.0f;
        long value = Math.round(goal_time) + 17000 + Math.round(offset_goal);
        world.setTimeOfDay(value);
        this.tickCount = 1;
        RealTimeMod.log(Level.INFO, "SYNCING TIME: " + value);
	}

}
