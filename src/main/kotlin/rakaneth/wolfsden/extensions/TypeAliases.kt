package rakaneth.wolfsden.extensions

import org.hexworks.amethyst.api.Message
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import rakaneth.wolfsden.world.GameContext

typealias GameEntity<T> = Entity<T, GameContext>
typealias AnyGameEntity = GameEntity<EntityType>
typealias GameMessage = Message<GameContext>