package rakaneth.wolfsden.view

import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView
import rakaneth.wolfsden.GameConfig

class PlayView(
    private val grid: TileGrid,
    theme: ColorTheme = GameConfig.THEME)
    : BaseView(grid, theme){
        init {
            val msgBox = Components.logArea()
                .withSize(GameConfig.MSG_W, GameConfig.MSG_H)
                .withDecorations(box(title="Messages"))
                .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_LEFT)
                .build()

            val skillBox = Components.panel()
                .withSize(GameConfig.SKIL_W, GameConfig.SKIL_H)
                .withDecorations(box(title="Skills"))
                .withAlignmentAround(msgBox, ComponentAlignment.RIGHT_CENTER)
                .build()

            val infoBox = Components.panel()
                .withSize(GameConfig.INFO_W, GameConfig.INFO_H)
                .withDecorations(box(title="Info"))
                .withAlignmentAround(skillBox, ComponentAlignment.RIGHT_CENTER)
                .build()

            val statBox = Components.panel()
                .withSize(GameConfig.STATS_W, GameConfig.STATS_H)
                .withDecorations(box(title="Stats"))
                .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
                .build()

            screen.addComponents(msgBox, skillBox, infoBox, statBox)
        }
}