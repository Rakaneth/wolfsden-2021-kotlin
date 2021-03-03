package rakaneth.wolfsden

import org.yaml.snakeyaml.Yaml
import rakaneth.wolfsden.builders.CreatureBlueprint
import java.io.BufferedInputStream
import java.io.File

object DataReader {
    private val yaml = Yaml()
    private const val creatureFile = "creatures.yml"
    private const val itemFile = "items.yml"
    private const val equipFile = "equipment.yml"

    private fun <T> loadBlueprints(fileName: String): Map<String, T> {
        val stream = javaClass.classLoader.getResourceAsStream(fileName)
        var result: Map<String, T>
        BufferedInputStream(stream).use {
            result = yaml.load(it)
        }
        return result
    }

    fun loadCreatureBP() = loadBlueprints<CreatureBlueprint>(creatureFile)
    //fun loadItemBP() = loadBlueprints<ItemBlueprint>(itemFile)
    //fun loadEquipBP() = loadBlueprints<EquipBlueprint>(equipFile)
}