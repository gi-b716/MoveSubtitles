package mod.crend.movesubtitles;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public final class MoveSubtitlesConfigScreen {
	private MoveSubtitlesConfigScreen() {
	}

	public static Screen create(Screen parent) {
		MoveSubtitlesConfig config = MoveSubtitlesConfig.get();
		return YetAnotherConfigLib.createBuilder()
			.title(Text.translatable("movesubtitles.config.title"))
			.category(ConfigCategory.createBuilder()
				.name(Text.translatable("movesubtitles.config.category.general"))
				.option(Option.<ScreenEdge>createBuilder()
					.name(Text.translatable("movesubtitles.config.edge"))
					.description(OptionDescription.of(Text.translatable("movesubtitles.config.edge.desc")))
					.binding(ScreenEdge.BOTTOM_RIGHT, () -> config.edge, value -> config.edge = value)
					.controller(option -> EnumControllerBuilder.create(option).enumClass(ScreenEdge.class))
					.build())
				.option(Option.<Float>createBuilder()
					.name(Text.translatable("movesubtitles.config.delta_x"))
					.description(OptionDescription.of(Text.translatable("movesubtitles.config.delta_x.desc")))
					.binding(0.0F, () -> config.deltaX, value -> config.deltaX = value)
					.controller(FloatFieldControllerBuilder::create)
					.build())
				.option(Option.<Float>createBuilder()
					.name(Text.translatable("movesubtitles.config.delta_y"))
					.description(OptionDescription.of(Text.translatable("movesubtitles.config.delta_y.desc")))
					.binding(0.0F, () -> config.deltaY, value -> config.deltaY = value)
					.controller(FloatFieldControllerBuilder::create)
					.build())
				.build())
			.save(MoveSubtitlesConfig::save)
			.build()
			.generateScreen(parent);
	}
}
