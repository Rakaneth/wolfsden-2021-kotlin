package rakaneth.wolfsden.messages

import org.hexworks.zircon.api.data.Position3D
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.GameMessage
import rakaneth.wolfsden.world.GameContext

data class MoveTo(
    override val context: GameContext,
    override val source: AnyGameEntity,
    val position: Position3D) : GameMessage
