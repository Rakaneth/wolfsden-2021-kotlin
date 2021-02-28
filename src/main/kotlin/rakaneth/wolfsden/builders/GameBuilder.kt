package rakaneth.wolfsden.builders

import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.GameState
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.extensions.GameEntity

class GameBuilder(val worldSize: Size3D) {

    val gmap = WorldBuilder(worldSize)
        .withBaseMap(0, 1)
        .withWaterPct(15, 2)
        .withWaterPct(30, 3)
        .withDoorPct(50, 0, true)
        .withDoorPct(75, 4, true)
        .build(GameConfig.GAME_AREA_SIZE)

    private fun prepareWorld() = also {
        gmap.scrollUpBy(gmap.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        val player = EntityFactory.newPlayer()
        val playerPositioned = gmap.addAtEmptyPosition(
            player,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1),
            size = gmap.visibleSize.copy(zLength = 0)
        )
        if (playerPositioned) {
            return player
        } else {
            throw Exception("player not positioned")
        }

    }

    fun buildGame(): GameState {
        prepareWorld()
        val player = addPlayer()
        return GameState.create(player = player, gmap = gmap)
    }

    companion object {
        fun create() = GameBuilder(GameConfig.WORLD_SIZE).buildGame()
    }


}