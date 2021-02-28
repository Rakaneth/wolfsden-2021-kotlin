package rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import rakaneth.wolfsden.messages.Dig
import rakaneth.wolfsden.world.GameContext

object Diggable : BaseFacet<GameContext, Dig>(Dig::class) {
    override suspend fun receive(message: Dig): Response {
        val (context, _, target) = message
        context.map.removeEntity(target)
        return Consumed
    }
}
