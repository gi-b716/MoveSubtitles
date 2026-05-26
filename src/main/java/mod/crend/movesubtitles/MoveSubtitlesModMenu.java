package mod.crend.movesubtitles;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class MoveSubtitlesModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return MoveSubtitlesConfigScreenFactory::create;
	}
}
