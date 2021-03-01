package rakaneth.wolfsden.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import rakaneth.wolfsden.attributes.EntityTile
import rakaneth.wolfsden.attributes.TwoState
import rakaneth.wolfsden.attributes.types.Door
import rakaneth.wolfsden.builders.EntityFactory
import rakaneth.wolfsden.extensions.position
import rakaneth.wolfsden.extensions.tryToFindAttribute
import rakaneth.wolfsden.messages.Operate
import rakaneth.wolfsden.world.GameContext

object Openable: BaseFacet<GameContext, Operate>(Operate::class) {
    override suspend fun receive(message: Operate): Response {
        val (context, _, target) = message
        val pos = target.position
        val binaryState = target.tryToFindAttribute(TwoState::class)
        binaryState.state = !binaryState.state
        if (binaryState.state && target.type is Door) {
            //on = open
            context.map.removeEntity(target)
            context.map.addEntity(EntityFactory.newOpenDoor(), pos)
        }
        return Consumed
    }
}