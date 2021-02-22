import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import rakaneth.wolfsden.view.StartView

fun main(args: Array<String>) {
    val config = AppConfig.newBuilder()
        .withDefaultTileset(CP437TilesetResources.rogueYun16x16())
        .withSize(100, 40)
        .build()
    val grid = SwingApplications.startTileGrid(config)
    StartView(grid).dock()
}