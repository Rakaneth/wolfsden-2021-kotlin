package rakaneth.wolfsden.blocks

import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock
import rakaneth.wolfsden.builders.GameTileRepository.DEEP
import rakaneth.wolfsden.builders.GameTileRepository.EMPTY
import rakaneth.wolfsden.builders.GameTileRepository.FLOOR
import rakaneth.wolfsden.builders.GameTileRepository.PLAYER
import rakaneth.wolfsden.builders.GameTileRepository.WALL
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.GameEntity
import rakaneth.wolfsden.extensions.tile

class GameBlock(
    private var defaultTile: Tile = FLOOR,
    private val currentEntities: MutableList<AnyGameEntity> = mutableListOf())
    : BaseBlock<Tile>(
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to defaultTile)) {
    val isFloor: Boolean
        get() = defaultTile == FLOOR

    val isWall: Boolean
        get() = defaultTile == WALL || defaultTile == DEEP

    val isEmptyFloor: Boolean
        get() = currentEntities.isEmpty()

    val entities: Iterable<AnyGameEntity>
        get() = currentEntities.toList()

    private fun updateContent() {
        val entityTiles = currentEntities.map { it.tile }
        content = when {
            entityTiles.contains(PLAYER) -> PLAYER
            entityTiles.isNotEmpty() -> entityTiles.first()
            else -> defaultTile
        }
    }

    fun addEntity(entity: AnyGameEntity) {
        currentEntities.add(entity)
        updateContent()
    }

    fun removeEntity(entity: AnyGameEntity) {
        currentEntities.remove(entity)
        updateContent()
    }
}