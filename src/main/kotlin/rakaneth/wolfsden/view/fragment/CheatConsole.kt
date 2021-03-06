package rakaneth.wolfsden.view.fragment

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.component.Fragment
import org.hexworks.zircon.api.component.modal.Modal
import org.hexworks.zircon.api.component.modal.ModalFragment
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.uievent.*
import org.hexworks.zircon.internal.component.impl.textedit.cursor.Cursor
import org.hexworks.zircon.internal.component.modal.EmptyModalResult
import rakaneth.wolfsden.GameConfig
import rakaneth.wolfsden.GameState
import rakaneth.wolfsden.builders.EntityFactory.getRandomCreature
import rakaneth.wolfsden.builders.EntityFactory.getRandomEquip
import rakaneth.wolfsden.builders.EntityFactory.getRandomItem

class CheatConsole(val gameState: GameState): ModalFragment<EmptyModalResult> {
    val logger = LoggerFactory.getLogger(this::class)

    companion object {
        private const val WIDTH = GameConfig.GAME_W - 2
        private const val HEIGHT = GameConfig.GAME_H - 1
        private val FULL_SIZE = Size3D.create(WIDTH, HEIGHT, 0).to2DSize()
    }

    private val component = Components.vbox()
        .withSize(WIDTH, HEIGHT)
        .build().apply {
            val console = Components.logArea()
                .withSize(WIDTH-2, HEIGHT-4)
                .withDecorations(box())
                .build()

            val inputArea = Components.panel()
                .withSize(WIDTH-2, 3)
                .withDecorations(box())
                .build().apply {
                    val textBox = Components.textArea()
                        .withSize(WIDTH-14, 1)
                        .withAlignmentWithin(this, ComponentAlignment.LEFT_CENTER)
                        .build()
                    val sendButton = Components.button()
                        .withSize(10, 1)
                        .withText("Send")
                        .withAlignmentAround(textBox, ComponentAlignment.RIGHT_CENTER)
                        .build().apply {
                            onActivated {
                                val cmd = textBox.text.trim()
                                val result = parseCommand(cmd)
                                result.ifPresent {
                                    console.addParagraph(it, false)
                                }
                                textBox.text = ""
                                textBox.requestFocus()
                            }
                        }
                    addComponents(textBox, sendButton)
                }
            addComponents(console, inputArea)
        }

    private fun parseCommand(cmd: String): Maybe<String> {
        //TODO: parse command
        logger.info("Processing cmd $cmd")
        return when (cmd.toLowerCase()) {
            "item" -> Maybe.of(getRandomItem().name)
            "creature" -> Maybe.of(getRandomCreature().name)
            "equip" -> Maybe.of(getRandomEquip().name)
            else -> Maybe.of("Unknown command")
        }
    }

    override val root = ModalBuilder.newBuilder<EmptyModalResult>()
        .withComponent(component)
        .withParentSize(FULL_SIZE)
        .build().apply {
            handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { e, _ ->
                when (e.code) {
                    KeyCode.ESCAPE -> {
                        close(EmptyModalResult)
                        Processed
                    }
                    else -> Pass
                }
            }
        }
}