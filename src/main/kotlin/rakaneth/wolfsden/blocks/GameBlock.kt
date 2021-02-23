package rakaneth.wolfsden.blocks

import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock
import rakaneth.wolfsden.builders.GameTileRepository.DEEP
import rakaneth.wolfsden.builders.GameTileRepository.EMPTY
import rakaneth.wolfsden.builders.GameTileRepository.FLOOR
import rakaneth.wolfsden.builders.GameTileRepository.WALL

class GameBlock(content: Tile = FLOOR) : BaseBlock<Tile>(
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to content)) {

        val isFloor: Boolean
            get() = content == FLOOR

        val isWall: Boolean
            get() = content == WALL || content == DEEP
}