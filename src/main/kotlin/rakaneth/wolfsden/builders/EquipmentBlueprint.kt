package rakaneth.wolfsden.builders

data class EquipmentBlueprint(
    var name: String = "No name",
    var desc: String = "No desc",
    var glyph: Char = '@',
    var color: String = "white",
    override var freq: Int = 0,
    var slot: EquipSlot = EquipSlot.mh,
    var atk: Int = 0,
    var dfp: Int = 0,
    var sav: Int = 0,
    var dly: Int = 0,
    var dmg: Int = 0,
    var prot: Int = 0,
    var tags: List<String> = listOf(),
    var weakness: List<String> = listOf(),
    var vulnerability: List<String> = listOf(),
    var resistance: List<String> = listOf(),
    var immunity: List<String> = listOf()
): Blueprint
