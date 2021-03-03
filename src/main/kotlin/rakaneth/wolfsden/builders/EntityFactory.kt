package rakaneth.wolfsden.builders

import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import org.hexworks.cobalt.logging.api.LoggerFactory
import rakaneth.wolfsden.DataReader
import rakaneth.wolfsden.attributes.*
import rakaneth.wolfsden.attributes.flags.BlockOccupier
import rakaneth.wolfsden.attributes.types.Deep
import rakaneth.wolfsden.attributes.types.Door
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.attributes.types.Wall
import rakaneth.wolfsden.builders.GameTileRepository.PLAYER
import rakaneth.wolfsden.messages.Dig
import rakaneth.wolfsden.messages.Operate
import rakaneth.wolfsden.systems.CameraMover
import rakaneth.wolfsden.systems.InputReceiver
import rakaneth.wolfsden.systems.Movable
import rakaneth.wolfsden.systems.Openable
import rakaneth.wolfsden.world.GameContext

fun <T : EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {
    private val creatureBP = DataReader.loadCreatureBP()
    private val itemBP = DataReader.loadItemBP()
    private val equipBP = DataReader.loadEquipBP()

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            EntityPosition(),
            EntityTile(PLAYER),
            EntityActions(Dig::class, Operate::class),
            EntityVitals(10))
        behaviors(InputReceiver)
        facets(Movable, CameraMover)
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.WALL)
        )
    }

    fun newDeep() = newGameEntityOfType(Deep) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.DEEP)
        )
    }

    fun newOpenDoor() = newGameEntityOfType(Door) {
        attributes(
            EntityPosition(),
            TwoState(true),
            EntityTile(GameTileRepository.DOOR_OPEN)
        )
    }

    fun newClosedDoor() = newGameEntityOfType(Door) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            TwoState(false),
            EntityTile(GameTileRepository.DOOR_CLOSED)
        )
        facets(Openable)
    }
}