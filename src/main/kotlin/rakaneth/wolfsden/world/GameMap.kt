package rakaneth.wolfsden.world

import org.hexworks.amethyst.api.Engine
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.internal.TurnBasedEngine
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.GameState
import rakaneth.wolfsden.blocks.GameBlock
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.position

class GameMap(
    startingBlocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .build() {

    private val engine: TurnBasedEngine<GameContext> = Engine.create()

    init {
        startingBlocks.forEach { (pos, block) ->
            setBlockAt(pos, block)
            block.entities.forEach { entity ->
                engine.addEntity(entity)
                entity.position = pos
            }
        }
    }

    fun addEntity(entity: Entity<EntityType, GameContext>, position: Position3D) {
        entity.position = position
        engine.addEntity(entity)
        fetchBlockAt(position).map {
            it.addEntity(entity)
        }
    }

    fun removeEntity(entity: Entity<EntityType, GameContext>) {
        fetchBlockAt(entity.position).map {
            it.removeEntity(entity)
        }
        engine.removeEntity(entity)
        entity.position = Position3D.unknown()
    }

    fun findEmptyLocationOnFloor(z: Int): Position3D {
        val cands = blocks.filter { (pos, block) ->
            block.isEmptyFloor && pos.z == z
        }
        return GameConfig.MapRNG.getRandomElement(cands.keys)
    }

    fun findEmptyLocationWithin(offset: Position3D, size: Size3D): Maybe<Position3D> {
        val cands = blocks.filter { (pos, block) ->
            block.isEmptyFloor
                    && pos.x in offset.x..offset.x + size.xLength
                    && pos.y in offset.y..offset.y + size.yLength
                    && pos.z in offset.z..offset.z + size.zLength
        }
        return if (cands.keys.isEmpty()) {
            Maybe.empty()
        } else {
            Maybe.of(GameConfig.MapRNG.getRandomElement(cands.keys))
        }
        /*
        var position = Maybe.empty<Position3D>()
        val maxTries = 10
        var currentTry = 0
        val (sx, sy, sz) = size

        while (position.isPresent.not() && currentTry < maxTries) {
            val pos = Position3D.create(
                x = GameConfig.MapRNG.nextInt(size.xLength) + offset.x,
                y = GameConfig.MapRNG.nextInt(size.yLength) + offset.y,
                z = GameConfig.MapRNG.nextInt(size.zLength) + offset.z
            )

            fetchBlockAt(pos).map {
                if (it.isEmptyFloor) {
                    position = Maybe.of(pos)
                }
            }
            currentTry++
        }
        return position
         */
    }

    fun addAtEmptyPosition(
        entity: AnyGameEntity,
        offset: Position3D = Position3D.create(0, 0, 0),
        size: Size3D = actualSize
    ): Boolean {
        return findEmptyLocationWithin(offset, size).fold(
            whenEmpty = { false },
            whenPresent = { location ->
                addEntity(entity, location)
                true
            }
        )
    }

    fun update(screen: Screen, uiEvent: UIEvent, gamestate: GameState) {
        engine.executeTurn(GameContext(
            map = this,
            screen = screen,
            uiEvent = uiEvent,
            player = gamestate.player))
    }

    fun moveEntity(entity: AnyGameEntity, position: Position3D): Boolean {
        var success = false
        val oldBlock = fetchBlockAt(entity.position)
        val newBlock = fetchBlockAt(position)

        if (oldBlock.isPresent && newBlock.isPresent) {
            success = true
            oldBlock.get().removeEntity(entity)
            entity.position = position
            newBlock.get().addEntity(entity)
        }

        return success
    }
}