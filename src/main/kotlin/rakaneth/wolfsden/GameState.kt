package rakaneth.wolfsden

import org.hexworks.zircon.api.data.Size3D
import rakaneth.wolfsden.builders.WorldBuilder
import rakaneth.wolfsden.world.GameMap

class GameState(val gmap: GameMap) {
    companion object {
        fun create(
            worldSize: Size3D = GameConfig.WORLD_SIZE,
            visibleSize: Size3D = GameConfig.GAME_AREA_SIZE
        ) = GameState(WorldBuilder(worldSize)
            .withBaseMap()
            .withDoorPct(15, 0)
            .withWaterPct(30, 1)
            .withWaterPct(50, 4)
            .build(visibleSize))
    }
}