package rakaneth.wolfsden.messages

import org.hexworks.amethyst.api.entity.EntityType
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.world.GameContext

data class Operate(
    override val context: GameContext,
    override val source: AnyGameEntity,
    override val target: AnyGameEntity) : EntityAction<EntityType, EntityType>
