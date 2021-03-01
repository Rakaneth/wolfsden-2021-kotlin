package rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import rakaneth.wolfsden.extensions.curHP
import rakaneth.wolfsden.extensions.maxHP
import rakaneth.wolfsden.extensions.position
import rakaneth.wolfsden.messages.MoveTo
import rakaneth.wolfsden.world.GameContext

object InputReceiver : BaseBehavior<GameContext>() {
    override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        val (gmap, _, uiEvent, player) = context
        val currentPos = player.position
        if (uiEvent is KeyboardEvent) {
            val newPosition = when (uiEvent.code) {
                KeyCode.KEY_W -> currentPos.withRelativeY(-1)
                KeyCode.KEY_A -> currentPos.withRelativeX(-1)
                KeyCode.KEY_S -> currentPos.withRelativeY(1)
                KeyCode.KEY_D -> currentPos.withRelativeX(1)
                KeyCode.KEY_Q -> currentPos.withRelativeX(-1).withRelativeY(-1)
                KeyCode.KEY_E -> currentPos.withRelativeX(1).withRelativeY(-1)
                KeyCode.KEY_Z -> currentPos.withRelativeX(-1).withRelativeY(1)
                KeyCode.KEY_C -> currentPos.withRelativeX(1).withRelativeY(1)
                KeyCode.ENTER -> {
                    val curBlock = gmap.fetchBlockAt(currentPos).get()
                    when {
                        curBlock.isStairsUp -> currentPos.withRelativeZ(-1)
                        curBlock.isStairsDown -> currentPos.withRelativeZ(1)
                        else -> currentPos
                    }
                }
                else -> {
                    when (uiEvent.code) {
                        KeyCode.RIGHT -> player.curHP += 1
                        KeyCode.LEFT -> player.curHP -= 1
                        KeyCode.UP -> player.maxHP += 1
                        KeyCode.DOWN -> player.maxHP -= 1
                        else -> {}
                    }
                    currentPos
                }
            }
            player.receiveMessage(MoveTo(context, player, newPosition))
        }
        return true
    }
}