package rakaneth.wolfsden.builders

import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols
import rakaneth.wolfsden.builders.GameColors.FLOOR_BG
import rakaneth.wolfsden.builders.GameColors.FLOOR_FG
import rakaneth.wolfsden.builders.GameColors.STAIR_BG
import rakaneth.wolfsden.builders.GameColors.STAIR_FG
import rakaneth.wolfsden.builders.GameColors.WALL_BG
import rakaneth.wolfsden.builders.GameColors.WALL_FG

object GameTileRepository {
    val EMPTY: CharacterTile = Tile.empty()
    val FLOOR: CharacterTile = Tile.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)
        .withForegroundColor(FLOOR_FG)
        .withBackgroundColor(FLOOR_BG)
        .buildCharacterTile()

    val WALL: CharacterTile = Tile.newBuilder()
        .withCharacter('#')
        .withForegroundColor(WALL_FG)
        .withBackgroundColor(WALL_BG)
        .buildCharacterTile()

    val STAIRS_DOWN: CharacterTile = Tile.newBuilder()
        .withCharacter('>')
        .withForegroundColor(STAIR_FG)
        .withBackgroundColor(STAIR_BG)
        .buildCharacterTile()

    val STAIRS_UP: CharacterTile = Tile.newBuilder()
        .withCharacter('<')
        .withForegroundColor(STAIR_FG)
        .withBackgroundColor(STAIR_BG)
        .buildCharacterTile()
}