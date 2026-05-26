package mod.crend.movesubtitles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoveSubtitlesConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static Path configPath;

	public static MoveSubtitlesConfig INSTANCE;

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
		return INSTANCE;
	}

	public static void save() {
		if (configPath == null) {
			return;
		}
		try {
			Files.createDirectories(configPath.getParent());
			Files.writeString(configPath, GSON.toJson(get()));
		} catch (IOException ignored) {
		}
	}
}
