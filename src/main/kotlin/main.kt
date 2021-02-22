import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import view.StartView

fun main(args: Array<String>) {
    val config = AppConfig.newBuilder()
        .withDefaultTileset(CP437TilesetResources.cooz16x16())
        .build()
    val grid = SwingApplications.startTileGrid(config)
    StartView(grid).dock()
}