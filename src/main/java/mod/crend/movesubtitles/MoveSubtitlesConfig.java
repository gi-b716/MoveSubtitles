package mod.crend.movesubtitles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoveSubtitlesConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Logger LOGGER = LoggerFactory.getLogger(MoveSubtitlesConfig.class);
	private static Path configPath;

	public static MoveSubtitlesConfig INSTANCE;
	public static final String CONFIG_FILE = MoveSubtitles.MOD_ID + ".json";

	public ScreenEdge edge = ScreenEdge.BOTTOM_RIGHT;
	public float deltaX = 0.0F;
	public float deltaY = 0.0F;

	public static void load(Path configFile) {
		configPath = configFile;
		boolean needsSave = false;
		if (Files.exists(configFile)) {
			try {
				INSTANCE = GSON.fromJson(Files.readString(configFile), MoveSubtitlesConfig.class);
				if (INSTANCE == null) {
					INSTANCE = new MoveSubtitlesConfig();
					needsSave = true;
				}
			} catch (Exception e) {
				LOGGER.warn("Failed to load config, using defaults.", e);
				INSTANCE = new MoveSubtitlesConfig();
				needsSave = true;
			}
		} else {
			INSTANCE = new MoveSubtitlesConfig();
			needsSave = true;
		}

		if (needsSave) {
			save();
		}
	}

	public static MoveSubtitlesConfig get() {
		if (INSTANCE == null) {
			INSTANCE = new MoveSubtitlesConfig();
		}
		if (configPath == null) {
			configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE);
		}
		return INSTANCE;
	}

	public static void save() {
		if (configPath == null) {
			return;
		}
		try {
			Files.createDirectories(configPath.getParent());
			Files.writeString(configPath, GSON.toJson(get()));
		} catch (IOException e) {
			LOGGER.warn("Failed to save config.", e);
		}
	}
}
