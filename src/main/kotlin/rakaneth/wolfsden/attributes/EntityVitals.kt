package rakaneth.wolfsden.attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

class EntityVitals(startHp: Int) : BaseAttribute() {
    private val maxHpProp = startHp.toProperty()
    private val curHpProp = startHp.toProperty()
    val stringProp: Binding<String> = ComputedDualBinding(maxHpProp, curHpProp) { mv, cv ->
        "HP: $cv / $mv"
    }
    val pctProp: Binding<Double> = ComputedDualBinding(curHpProp, maxHpProp) { cv, mv ->
        cv.toDouble() / mv.toDouble() * 100.0
    }

    var maxHp: Int by maxHpProp.asDelegate()
    var curHp: Int by curHpProp.asDelegate()
    val alive = curHp > 0
}