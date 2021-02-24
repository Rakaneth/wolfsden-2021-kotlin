package rakaneth.wolfsden

import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.data.Size3D
import squidpony.squidmath.IRNG
import squidpony.squidmath.StatefulRNG
import java.awt.Toolkit

object GameConfig {

    private val screenSize = Toolkit.getDefaultToolkit().screenSize

    const val GAME_W = 100
    const val GAME_H = 40
    const val DUNGEON_LEVELS = 5
    const val DUNGEON_W = 150
    const val DUNGEON_H = 100
    const val MAP_W = 70
    const val MAP_H = 30
    const val MSG_W = 40
    const val MSG_H = 10
    const val SKIL_W = 30
    const val SKIL_H = 10
    const val INFO_W = 30
    const val INFO_H = 10
    const val STATS_W = 30
    const val STATS_H = 30

    val TILESET = if (screenSize.width < 1600) {
        CP437TilesetResources.jolly12x12()
    } else {
        CP437TilesetResources.cooz16x16()
    }

    val THEME = ColorThemes.zenburnVanilla()

    val WORLD_SIZE = Size3D.create(DUNGEON_W, DUNGEON_H, DUNGEON_LEVELS)
    val GAME_AREA_SIZE = Size3D.create(MAP_W, MAP_H, 1)

    val MapRNG: IRNG = StatefulRNG(0xDEADBEEF)
    val GameRNG: IRNG = StatefulRNG()

    fun buildAppConfig() = AppConfig.newBuilder()
        .withDefaultTileset(TILESET)
        .withSize(GAME_W, GAME_H)
        .build()
}