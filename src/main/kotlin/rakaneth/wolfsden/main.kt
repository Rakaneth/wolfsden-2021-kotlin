package rakaneth.wolfsden

import org.hexworks.zircon.api.SwingApplications
import rakaneth.wolfsden.view.StartView

fun main(args: Array<String>) {
    val grid = SwingApplications.startTileGrid(GameConfig.buildAppConfig())
    StartView(grid).dock()
}