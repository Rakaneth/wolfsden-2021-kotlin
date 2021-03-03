package rakaneth.wolfsden

import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import rakaneth.wolfsden.builders.Blueprint
import rakaneth.wolfsden.builders.CreatureBlueprint
import rakaneth.wolfsden.builders.EquipmentBlueprint
import rakaneth.wolfsden.builders.ItemBlueprint
import java.io.BufferedInputStream
import java.io.File

object DataReader {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private const val creatureFile = "creatures.yml"
    private const val itemFile = "items.yml"
    private const val equipFile = "equipment.yml"

    private fun  loadBlueprints(fileName: String): Map<String, Blueprint> {
        val yaml = Yaml(Constructor())
        val stream = javaClass.classLoader.getResourceAsStream(fileName)
        var result: Map<String, Blueprint>
        BufferedInputStream(stream!!).use {
            result = yaml.loadAs(it, )
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