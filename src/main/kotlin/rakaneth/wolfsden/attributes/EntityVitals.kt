package rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.binding.bindPlusWith
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

class EntityVitals(startHp: Int) : BaseAttribute() {
    val maxHpProp = startHp.toProperty()
    val curHpProp = startHp.toProperty()
    val stringProp: Binding<String> = ComputedDualBinding(maxHpProp, curHpProp) { mv, cv ->
        "$cv / $mv"
    }

    var maxHp: Int by maxHpProp.asDelegate()
    var curHp: Int by curHpProp.asDelegate()
}