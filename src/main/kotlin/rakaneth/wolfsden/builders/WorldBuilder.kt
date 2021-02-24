package rakaneth.wolfsden.builders

import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.blocks.GameBlock
import rakaneth.wolfsden.world.GameMap
import squidpony.squidgrid.mapping.DungeonGenerator
import squidpony.squidgrid.mapping.SerpentDeepMapGenerator

class WorldBuilder(private val worldSize: Size3D) {
    private var base: Array<Array<CharArray>> = arrayOf()
    val deco = DungeonGenerator(worldSize.xLength, worldSize.yLength, GameConfig.MapRNG)

    fun withBaseMap(caveCarve: Int = 1, roomCarve: Int = 0) = also {
        val (w, h, d) = worldSize
        val gen = SerpentDeepMapGenerator(w, h, d, GameConfig.MapRNG, 0.2)
        gen.putCaveCarvers(caveCarve)
        gen.putRoundRoomCarvers(roomCarve)
        base = gen.generate()
    }

    fun withWaterPct(pct: Int, z: Int) = also {
        val floorTiles = base[z]
        deco.addWater(pct)
        base[z] = deco.generateRespectingStairs(floorTiles)
    }

    fun withDoorPct(pct: Int, z: Int, doubleDoors: Boolean = false) = also {
        val floorTiles = base[z]
        deco.addDoors(pct, doubleDoors)
        base[z] = deco.generateRespectingStairs(floorTiles)
    }

    fun build(visibleSize: Size3D): GameMap {
        val blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()
        base.forEachIndexed { z, level ->
            level.forEachIndexed { x, cols ->
                cols.forEachIndexed { y, c ->
                    val pos = Position3D.create(x, y, z)
                    blocks[pos] = when (c) {
                        '#' -> GameBlockFactory.wall()
                        '.' -> GameBlockFactory.floor()
                        '>' -> GameBlockFactory.stairs_down()
                        '<' -> GameBlockFactory.stairs_up()
                        ',' -> GameBlockFactory.shallow()
                        '~' -> GameBlockFactory.deep()
                        else -> GameBlockFactory.floor() //TODO: bridge, door tiles
                    }
                }
            }
        }
        return GameMap(blocks, visibleSize, worldSize)
    }
}