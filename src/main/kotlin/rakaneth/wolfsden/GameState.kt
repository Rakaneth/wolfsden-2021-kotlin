package rakaneth.wolfsden

import org.hexworks.zircon.api.data.Size3D
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.builders.WorldBuilder
import rakaneth.wolfsden.extensions.GameEntity
import rakaneth.wolfsden.world.GameMap

class GameState(val gmap: GameMap, val player: GameEntity<Player>) {
    companion object {
        fun create(player: GameEntity<Player>,
                   gmap: GameMap) = GameState(gmap=gmap, player=player)

    }
}