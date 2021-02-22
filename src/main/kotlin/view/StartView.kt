package view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.ComponentDecorations.shadow
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class StartView(private val grid: TileGrid)
    : BaseView(grid, ColorThemes.arc()) {
        init {
            val msg = "Welcome to Wolf's Den Zircon Edition."
            val header = Components.textBox(contentWidth = msg.length)
                .addHeader(msg)
                .addNewLine()
                .withAlignmentWithin(screen, ComponentAlignment.CENTER)
                .build()

            val startButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
                .withText("Start!")
                .withDecorations(box(), shadow())
                .build()

            screen.addComponents(header, startButton)
        }
}