package rakaneth.wolfsden

import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import rakaneth.wolfsden.view.StartView
import java.awt.Toolkit

fun main(args: Array<String>) {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val tileSet = if (screenSize.width < 1600) {
        CP437TilesetResources.jolly12x12()
    } else {
        CP437TilesetResources.cooz16x16()
    }


    val config = AppConfig.newBuilder()
        .withDefaultTileset(tileSet)
        .withSize(100, 40)
        .build()
    val grid = SwingApplications.startTileGrid(config)
    StartView(grid).dock()

}