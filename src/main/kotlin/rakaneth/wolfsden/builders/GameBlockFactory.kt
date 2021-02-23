package rakaneth.wolfsden.builders

import rakaneth.wolfsden.blocks.GameBlock

object GameBlockFactory {
    fun floor() = GameBlock(GameTileRepository.FLOOR)
    fun wall() = GameBlock(GameTileRepository.WALL)
    fun stairs_down() = GameBlock(GameTileRepository.STAIRS_DOWN)
    fun stairs_up() = GameBlock(GameTileRepository.STAIRS_UP)
}