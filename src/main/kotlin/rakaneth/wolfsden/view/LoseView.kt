package rakaneth.wolfsden.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView
import rakaneth.wolfsden.GameConfig
import kotlin.system.exitProcess

class LoseView(
    private val grid: TileGrid,
    theme: ColorTheme = GameConfig.THEME)
    : BaseView(grid, theme) {
        init {
            val header = Components.header()
                .withAlignmentWithin(screen, ComponentAlignment.CENTER)
                .withText("You lose :(")
                .build()

            val restartButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_LEFT)
                .withText("Restart")
                .withDecorations(ComponentDecorations.box())
                .build()

            val exitButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_RIGHT)
                .withText("Quit")
                .withDecorations(ComponentDecorations.box())
                .build()

            restartButton.onActivated {
                replaceWith(PlayView(grid))
            }

            exitButton.onActivated {
                exitProcess(0)
            }

            screen.addComponents(header, restartButton, exitButton)
        }
}