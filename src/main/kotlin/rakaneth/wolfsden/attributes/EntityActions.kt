package rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.amethyst.api.entity.EntityType
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.messages.EntityAction
import rakaneth.wolfsden.world.GameContext
import kotlin.reflect.KClass

class EntityActions(
    private vararg val actions: KClass<out EntityAction<out EntityType, out EntityType>>
) : BaseAttribute() {
    fun createActionsFor(
        context: GameContext,
        source: AnyGameEntity,
        target: AnyGameEntity): Iterable<EntityAction<out EntityType, out EntityType>> {
        return actions.map {
            try {
                it.constructors.first().call(context, source, target)
            } catch (e: Exception) {
                throw IllegalArgumentException("Can't create EntityAction. Does it have the right constructor?")
            }
        }
    }
}