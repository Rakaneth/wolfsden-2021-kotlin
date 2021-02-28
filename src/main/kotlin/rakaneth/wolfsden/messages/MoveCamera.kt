package rakaneth.wolfsden.messages

import org.hexworks.zircon.api.data.Position3D
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.GameMessage
import rakaneth.wolfsden.world.GameContext

data class MoveCamera(
    override val context: GameContext,
    override val source: AnyGameEntity,
    val prevPos: Position3D): GameMessage
