package rakaneth.wolfsden.builders

import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import rakaneth.wolfsden.EntityTile
import rakaneth.wolfsden.attributes.EntityActions
import rakaneth.wolfsden.attributes.EntityPosition
import rakaneth.wolfsden.attributes.EntityVitals
import rakaneth.wolfsden.attributes.flags.BlockOccupier
import rakaneth.wolfsden.attributes.types.Deep
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.attributes.types.Wall
import rakaneth.wolfsden.builders.GameTileRepository.PLAYER
import rakaneth.wolfsden.messages.Dig
import rakaneth.wolfsden.systems.CameraMover
import rakaneth.wolfsden.systems.InputReceiver
import rakaneth.wolfsden.systems.Movable
import rakaneth.wolfsden.world.GameContext

fun <T : EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            EntityPosition(),
            EntityTile(PLAYER),
            EntityActions(Dig::class),
            EntityVitals(10))
        behaviors(InputReceiver)
        facets(Movable, CameraMover)
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.WALL))
    }

    fun newDeep() = newGameEntityOfType(Deep) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.DEEP)
        )
    }
}