package rakaneth.wolfsden.extensions

import rakaneth.wolfsden.world.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

typealias GameEntity<T> = Entity<T, GameContext>
typealias AnyGameEntity = GameEntity<EntityType>