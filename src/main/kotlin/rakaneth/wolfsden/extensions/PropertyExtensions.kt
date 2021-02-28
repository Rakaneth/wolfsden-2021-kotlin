package rakaneth.wolfsden.extensions

import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.data.Position3D

fun Property<Position3D>.toStringProperty(): Property<String> {
    val posProp = this
    return createPropertyFrom("").apply {
        this.updateFrom(posProp) {
            "(${it.x}, ${it.y}, ${it.z})"
        }
    }
}