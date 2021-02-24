package rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.zircon.api.data.Tile
import rakaneth.wolfsden.EntityTile
import rakaneth.wolfsden.attributes.EntityPosition
import kotlin.reflect.KClass

fun<T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}.")
}

var AnyGameEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile