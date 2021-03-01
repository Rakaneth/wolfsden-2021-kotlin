package rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.zircon.api.data.Position3D

class EntityPosition(
    initialPosition: Position3D = Position3D.unknown()
) : BaseAttribute() {
    val positionProperty = initialPosition.toProperty()
    val stringProperty: Binding<String> = ComputedBinding(positionProperty) {
        "Floor B${it.z}F (${it.x}, ${it.y})"
    }
    var position: Position3D by positionProperty.asDelegate()
}