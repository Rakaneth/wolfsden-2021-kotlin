package rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.zircon.api.data.Tile
import rakaneth.wolfsden.EntityTile
import rakaneth.wolfsden.attributes.EntityActions
import rakaneth.wolfsden.attributes.EntityPosition
import rakaneth.wolfsden.attributes.EntityVitals
import rakaneth.wolfsden.attributes.flags.BlockOccupier
import rakaneth.wolfsden.world.GameContext
import squidpony.squidmath.MathExtras.clamp
import java.lang.Integer.max
import kotlin.reflect.KClass

fun <T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}.")
}

var AnyGameEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.positionProperty
    get() = this.tryToFindAttribute(EntityPosition::class).positionProperty

val AnyGameEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile

val AnyGameEntity.occupiesBlock: Boolean
    get() = findAttribute(BlockOccupier::class).isPresent

var AnyGameEntity.curHP: Int
    get() = tryToFindAttribute(EntityVitals::class).curHp
    set(value) {
        findAttribute(EntityVitals::class).map {
            it.curHp = clamp(value, 0, it.maxHp)
        }
    }

var AnyGameEntity.maxHP: Int
    get() = tryToFindAttribute(EntityVitals::class).maxHp
    set(value) {
        findAttribute(EntityVitals::class).map {
            it.maxHp = value
            if (it.curHp > it.maxHp) {
                it.curHp = it.maxHp
            }
        }
    }

val AnyGameEntity.hpStringProp
    get() = tryToFindAttribute(EntityVitals::class).stringProp

suspend fun AnyGameEntity.tryActionsOn(context: GameContext, target: AnyGameEntity): Response {
    var result: Response = Pass
    findAttributeOrNull(EntityActions::class)?.let {
        it.createActionsFor(context, this, target).forEach { action ->
            if (target.receiveMessage(action) is Consumed) {
                result = Consumed
                return@forEach
            }
        }
    }
    return result
}