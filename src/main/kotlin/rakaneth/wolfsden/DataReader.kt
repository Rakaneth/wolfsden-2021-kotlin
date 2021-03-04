package rakaneth.wolfsden

import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import rakaneth.wolfsden.builders.*
import java.io.BufferedInputStream
import java.io.File

object DataReader {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private const val creatureFile = "creatures.yml"
    private const val itemFile = "items.yml"
    private const val equipFile = "equipment.yml"

    private fun <R, T: BlueprintHolder<R>> loadBlueprints(fileName: String, klass: Class<T>): T {
        val yaml = Yaml(Constructor(klass))
        val stream = javaClass.classLoader.getResourceAsStream(fileName)
        var result: T
        BufferedInputStream(stream!!).use {
            result = yaml.load(it)
        }
        if (result.table.isNotEmpty()) {
            logger.info("${result.table.size} items loaded from $fileName")
        }
        return result
    }

    fun loadCreatureBP() = loadBlueprints(creatureFile, CreatureHolder::class.java)
    fun loadItemBP() = loadBlueprints(itemFile, ItemHolder::class.java)
    fun loadEquipBP() = loadBlueprints(equipFile, EquipHolder::class.java)
}