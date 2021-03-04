package rakaneth.wolfsden.builders

interface Blueprint {
    val freq: Int
}

interface BlueprintHolder<T: Blueprint> {
    val table: Map<String, T>
}

data class CreatureHolder(
    override var table: Map<String, CreatureBlueprint> = mapOf()
) : BlueprintHolder<CreatureBlueprint>

data class EquipHolder(
    override var table: Map<String, EquipmentBlueprint> = mapOf()
) : BlueprintHolder<EquipmentBlueprint>

data class ItemHolder(
    override var table: Map<String, ItemBlueprint> = mapOf()
): BlueprintHolder<ItemBlueprint>
