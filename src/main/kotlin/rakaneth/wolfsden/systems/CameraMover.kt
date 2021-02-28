package rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import rakaneth.wolfsden.extensions.position
import rakaneth.wolfsden.messages.MoveCamera
import rakaneth.wolfsden.world.GameContext

object CameraMover: BaseFacet<GameContext, MoveCamera>(MoveCamera::class) {
    override suspend fun receive(message: MoveCamera): Response {
        val (context, source, prevPos) = message
        val gmap = context.map
        val screenPos = source.position - gmap.visibleOffset
        val halfH = gmap.visibleSize.yLength / 2
        val halfW = gmap.visibleSize.xLength / 2
        val currentPos = source.position
        when {
            prevPos.y > currentPos.y && screenPos.y < halfH -> {
                gmap.scrollOneBackward()
            }
            prevPos.y < currentPos.y && screenPos.y > halfH -> {
                gmap.scrollOneForward()
            }
            prevPos.x > currentPos.x && screenPos.x < halfW -> {
                gmap.scrollOneLeft()
            }
            prevPos.x < currentPos.x && screenPos.x > halfW -> {
                gmap.scrollOneRight()
            }
        }
        return Consumed
    }
}