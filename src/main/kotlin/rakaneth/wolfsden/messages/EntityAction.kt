package rakaneth.wolfsden.messages

import org.hexworks.amethyst.api.entity.EntityType
import rakaneth.wolfsden.extensions.GameEntity
import rakaneth.wolfsden.extensions.GameMessage

interface EntityAction<S: EntityType, T: EntityType> : GameMessage {
    val target: GameEntity<T>

    operator fun component1() = context
    operator fun component2() = source
    operator fun component3() = target
}