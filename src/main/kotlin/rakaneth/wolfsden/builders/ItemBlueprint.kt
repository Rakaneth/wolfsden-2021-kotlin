package rakaneth.wolfsden.builders

data class ItemBlueprint(
    var name: String = "No name",
    var desc: String = "No desc",
    var glyph: Char = '@',
    var color: String = "white",
    var itemType: ItemType = ItemType.healing,
    override var freq: Int = 0,
    var pctAmt: Double = 0.0,
    var flatAmt: Int = 0
): Blueprint
