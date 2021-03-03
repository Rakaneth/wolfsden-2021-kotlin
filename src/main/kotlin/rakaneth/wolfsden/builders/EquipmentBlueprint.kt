package rakaneth.wolfsden.builders

data class EquipmentBlueprint(
    val name: String = "No name",
    val desc: String = "No desc",
    val glyph: Char = '@',
    val color: String = "white",
    override val freq: Int = 0,
    val slot: EquipSlot = EquipSlot.MH,
    val atk: Int = 0,
    val dfp: Int = 0,
    val sav: Int = 0,
    val dly: Int = 0,
    val dmg: Int = 0,
    val prot: Int = 0,
    val tags: List<String> = listOf(),
    val weakness: List<String> = listOf(),
    val vulnerability: List<String> = listOf(),
    val resistance: List<String> = listOf(),
    val immunity: List<String> = listOf()
): Blueprint
