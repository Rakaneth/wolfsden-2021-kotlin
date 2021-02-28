package rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.MessageResponse
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import rakaneth.wolfsden.attributes.types.Player
import rakaneth.wolfsden.extensions.position
import rakaneth.wolfsden.extensions.tryActionsOn
import rakaneth.wolfsden.messages.MoveCamera
import rakaneth.wolfsden.messages.MoveTo
import rakaneth.wolfsden.world.GameContext

object Movable: BaseFacet<GameContext, MoveTo>(MoveTo::class) {
    override suspend fun receive(message: MoveTo): Response {
        val (context, entity, pos) = message
        val gmap = context.map
        val prevPos = entity.position
        var result: Response = Pass
        gmap.fetchBlockAtOrNull(pos)?.let { block ->
            if (block.isOccupied) {
                result = entity.tryActionsOn(context, block.occupier.get())
            } else {
                if (gmap.moveEntity(entity, pos)) {
                    result = Consumed
                    if (entity.type == Player) {
                        result = MessageResponse(MoveCamera(context, entity, prevPos))
                    }
                }
            }
        }
        return result
    }
}