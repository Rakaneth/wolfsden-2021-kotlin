package rakaneth.wolfsden.blocks

import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock
import rakaneth.wolfsden.builders.GameTileRepository.DEEP
import rakaneth.wolfsden.builders.GameTileRepository.EMPTY
import rakaneth.wolfsden.builders.GameTileRepository.FLOOR
import rakaneth.wolfsden.builders.GameTileRepository.PLAYER
import rakaneth.wolfsden.builders.GameTileRepository.WALL
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.occupiesBlock
import rakaneth.wolfsden.extensions.tile

class GameBlock(
    private var defaultTile: Tile = FLOOR,
    private val currentEntities: MutableList<AnyGameEntity> = mutableListOf()
) : BaseBlock<Tile>(
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to defaultTile)
) {
    init {
        updateContent()
    }

    val isFloor: Boolean
        get() = defaultTile == FLOOR

    val isWall: Boolean
        get() = defaultTile == WALL || defaultTile == DEEP

    val isEmptyFloor: Boolean
        get() = currentEntities.isEmpty()

    val entities: Iterable<AnyGameEntity>
        get() = currentEntities.toList()

    val occupier: Maybe<AnyGameEntity>
        get() = Maybe.ofNullable(currentEntities.firstOrNull { it.occupiesBlock })

    val isOccupied: Boolean
        get() = occupier.isPresent

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

    companion object {
        fun createWith(entity: AnyGameEntity) = GameBlock(
            currentEntities = mutableListOf(entity))
    }
}