package rakaneth.wolfsden.builders

import rakaneth.wolfsden.blocks.GameBlock

object GameBlockFactory {
    fun floor() = GameBlock(GameTileRepository.FLOOR)
    fun wall() = GameBlock.createWith(EntityFactory.newWall())
    fun stairs_down() = GameBlock(GameTileRepository.STAIRS_DOWN)
    fun stairs_up() = GameBlock(GameTileRepository.STAIRS_UP)
    fun shallow() = GameBlock(GameTileRepository.SHALLOW)
    fun deep() = GameBlock.createWith(EntityFactory.newDeep())
    fun doorOpen() = GameBlock.createWith(EntityFactory.newOpenDoor())
    fun doorClosed() = GameBlock.createWith(EntityFactory.newClosedDoor())
}