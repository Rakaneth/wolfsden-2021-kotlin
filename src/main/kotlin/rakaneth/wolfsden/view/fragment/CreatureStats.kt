package rakaneth.wolfsden.view.fragment

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.attributes.EntityPosition
import rakaneth.wolfsden.attributes.EntityVitals
import rakaneth.wolfsden.extensions.AnyGameEntity
import rakaneth.wolfsden.extensions.tryToFindAttribute


class CreatureStats(val entity: AnyGameEntity): Fragment {
    companion object {
        private const val WIDTH = GameConfig.STATS_W - 2
    }
    override val root = Components.vbox()
        .withSize(GameConfig.STATS_W-2, 10)
        .build().apply {
            val vitals = entity.tryToFindAttribute(EntityVitals::class)
            val pos = entity.tryToFindAttribute(EntityPosition::class)
            val hpLabel = Components.label()
                .withSize(WIDTH, 1)
                .build().apply {
                    textProperty.updateFrom(vitals.stringProp)
                }

            val positionLabel = Components.label()
                .withSize(WIDTH, 1)
                .build().apply {
                    textProperty.updateFrom(pos.stringProperty)
                }

            val hpBar = Components.progressBar()
                .withSize(WIDTH, 3)
                .withDecorations(box())
                .withDisplayPercentValueOfProgress(true)
                .withColorTheme(ColorThemes.afterglow())
                .build().apply {
                progressProperty.updateFrom(vitals.pctProp)
            }

            val paragraph = Components.paragraph()
                .withText("This is a long paragraph meant to test word wrapping.")
                .withSize(WIDTH, 3)
                .build()

            addComponents(positionLabel, hpLabel, hpBar, paragraph)
        }
}