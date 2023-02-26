package realtimefabric.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import realtimefabric.RealTimeMod;

public class ForceSyncTimeCommand {
	
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("rtsync").requires(source -> source.hasPermissionLevel(4)).executes(ctx -> {
			RealTimeMod.tickListener.syncTime();
			return 1;
		}));
	}
}
