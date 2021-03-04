package rakaneth.wolfsden.builders

data class FactionBlueprint(
    var neutral: List<String> = listOf(),
    var hostile: List<String> = listOf(),
    var ally: List<String> = listOf()
)

data class CreatureBlueprint(
    var name: String = "No Name",
    var desc: String = "No Desc",
    var str: Int = 0,
    var stam: Int = 0,
    var spd: Int = 0,
    var skl: Int = 0,
    override var freq: Int = 0,
    var glyph: Char = '@',
    var color: String = "white",
    var mh: String = "none",
    var oh: String = "none",
    var armor: String = "none",
    var trinket: String = "none",
    var vision: Int = 6,
    var xp: Int = 0,
    var ai: String = "none",
    var tags: List<String> = listOf(),
    var weakness: List<String> = listOf(),
    var vulnerability: List<String> = listOf(),
    var resistance: List<String> = listOf(),
    var immunity: List<String> = listOf(),
    var skills: List<String> = listOf(),
    var factions: FactionBlueprint = FactionBlueprint(),
    var gainsXP: Boolean = false
): Blueprint
