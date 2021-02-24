package rakaneth.wolfsden.builders

import org.hexworks.zircon.api.color.TileColor

object GameColors {
    val WALL_FG = TileColor.fromString("#75715E")
    val WALL_BG = TileColor.fromString("#3E3D32")
    val FLOOR_FG = TileColor.fromString("#75715E")
    val FLOOR_BG = TileColor.fromString("#1E2320")
    val STAIR_FG = TileColor.fromString("yellow")
    val STAIR_BG = FLOOR_BG
    val SHALLOW_FG = TileColor.fromString("#999999")
    val SHALLOW_BG = TileColor.fromString("cyan")
    val DEEP_FG = SHALLOW_FG
    val DEEP_BG = TileColor.fromString("blue")
    val PLAYER_FG = TileColor.fromString("#DDDDDD")
}