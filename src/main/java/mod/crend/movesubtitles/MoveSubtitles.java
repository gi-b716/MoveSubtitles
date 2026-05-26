package mod.crend.movesubtitles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MoveSubtitles implements ClientModInitializer {
	public static final String MOD_ID = "movesubtitles";

	@Override
	public void onInitializeClient() {
		MoveSubtitlesConfig.load(FabricLoader.getInstance().getConfigDir().resolve(MoveSubtitlesConfig.CONFIG_FILE));
	}
}