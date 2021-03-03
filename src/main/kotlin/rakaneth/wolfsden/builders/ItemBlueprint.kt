package rakaneth.wolfsden.builders

data class ItemBlueprint(
    val name: String = "No name",
    val desc: String = "No desc",
    val glyph: Char = '@',
    val color: String = "white",
    val itemType: ItemType = ItemType.HEALING,
    override val freq: Int = 0,
    val pctAmt: Double = 0.0,
    val flatAmt: Int = 0
): Blueprint
