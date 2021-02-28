package rakaneth.wolfsden.world

import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.extensions.GameEntity

data class GameContext(
    val map: GameMap,
    val screen: Screen,
    val uiEvent: UIEvent,
    val player: GameEntity<Player>
) : Context
