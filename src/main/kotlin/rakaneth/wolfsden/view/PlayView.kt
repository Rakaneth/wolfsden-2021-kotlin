package rakaneth.wolfsden.view

import org.hexworks.amethyst.api.Consumed
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.GameState
import rakaneth.wolfsden.builders.GameTileRepository

class PlayView(
    private val grid: TileGrid,
    private val gamestate: GameState = GameState.create(),
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

            val gameComponent = Components.panel()
                .withSize(gamestate.gmap.visibleSize.to2DSize())
                .withComponentRenderer(
                    GameAreaComponentRenderer(
                        gameArea = gamestate.gmap,
                        projectionMode = ProjectionMode.TOP_DOWN.toProperty(),
                        fillerTile = GameTileRepository.FLOOR))
                .withAlignmentWithin(screen, ComponentAlignment.TOP_LEFT)
                .build()

            val (curX, curY, curZ) = gamestate.gmap.visibleOffset

            val posLabel = Components.label()
                .withText("Current Position: ($curX, $curY, $curZ)")
                .withAlignmentWithin(statBox, ComponentAlignment.TOP_LEFT)
                .build()

            statBox.addComponents(posLabel)

            screen.handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { e, _ ->
                when (e.code) {
                    KeyCode.KEY_W -> gamestate.gmap.scrollOneBackward()
                    KeyCode.KEY_S -> gamestate.gmap.scrollOneForward()
                    KeyCode.KEY_A -> gamestate.gmap.scrollOneLeft()
                    KeyCode.KEY_D -> gamestate.gmap.scrollOneRight()
                    KeyCode.KEY_Q -> gamestate.gmap.scrollOneUp()
                    KeyCode.KEY_E -> gamestate.gmap.scrollOneDown()
                    else -> println("Unhandled key ${e.code}")
                }
                Processed
            }

            screen.addComponents(msgBox, skillBox, infoBox, statBox, gameComponent)
        }
}