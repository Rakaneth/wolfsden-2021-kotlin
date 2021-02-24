package rakaneth.wolfsden.builders

import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import rakaneth.wolfsden.EntityTile
import rakaneth.wolfsden.attributes.EntityPosition
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.builders.GameTileRepository.PLAYER
import rakaneth.wolfsden.world.GameContext

fun <T: EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(PLAYER))
        behaviors()
        facets()
    }
}