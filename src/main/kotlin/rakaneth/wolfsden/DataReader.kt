package rakaneth.wolfsden

import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import rakaneth.wolfsden.builders.CreatureBlueprint
import rakaneth.wolfsden.builders.EquipmentBlueprint
import rakaneth.wolfsden.builders.ItemBlueprint
import java.io.BufferedInputStream
import java.io.File

object DataReader {
    private val yaml = Yaml()
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private const val creatureFile = "creatures.yml"
    private const val itemFile = "items.yml"
    private const val equipFile = "equipment.yml"

    private fun <T> loadBlueprints(fileName: String): Map<String, T> {
        val stream = javaClass.classLoader.getResourceAsStream(fileName)
        var result: Map<String, T>
        BufferedInputStream(stream!!).use {
            result = yaml.load(it)
        }
        if (result.isNotEmpty()) {
            logger.info("${result.size} items loaded from $fileName")
        }
        return result
    }

    fun loadCreatureBP() = loadBlueprints<CreatureBlueprint>(creatureFile)
    fun loadItemBP() = loadBlueprints<ItemBlueprint>(itemFile)
    fun loadEquipBP() = loadBlueprints<EquipmentBlueprint>(equipFile)
}