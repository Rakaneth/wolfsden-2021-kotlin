package rakaneth.wolfsden.builders

data class FactionBlueprint(
    val neutral: List<String> = listOf(),
    val hostile: List<String> = listOf(),
    val ally: List<String> = listOf()
)

data class CreatureBlueprint(
    val name: String = "No Name",
    val desc: String = "No Desc",
    val str: Int = 0,
    val stam: Int = 0,
    val spd: Int = 0,
    val skl: Int = 0,
    val glyph: Char = '@',
    val color: String = "white",
    val mh: String = "none",
    val oh: String = "none",
    val armor: String = "none",
    val trinket: String = "none",
    val vision: Int = 6,
    val xp: Int = 0,
    val ai: String = "none",
    val tags: List<String> = listOf(),
    val weakness: List<String> = listOf(),
    val vulnerability: List<String> = listOf(),
    val resistance: List<String> = listOf(),
    val immunity: List<String> = listOf(),
    val skills: List<String> = listOf(),
    val factions: FactionBlueprint = FactionBlueprint()
)
