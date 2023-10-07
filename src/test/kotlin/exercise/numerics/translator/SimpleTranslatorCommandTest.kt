package exercise.numerics.translator

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import org.jline.terminal.Terminal
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.shell.command.CommandContext
import java.io.PrintWriter
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class SimpleTranslatorCommandTest(
    @MockK private val commandContext: CommandContext,
    @MockK private val translator: NumericTranslator<Int>
) {
    private val command = SimpleTranslatorCommand(
        key = "test",
        numericType = Int::class.java,
        translator = translator
    )

    private val mockPrintWriter: PrintWriter = mockPrintWriter()

    @Test
    fun `translateTo() passes argument from context and writes it back`() {
        every { commandContext.getOptionValue<Int>(any()) } returns 1

        val serviceSlot = slot<Int>()
        every { translator.toText(capture(serviceSlot)) } returns "translated"

        val printWriterSlot = slot<String>()
        every { mockPrintWriter.println(capture(printWriterSlot)) } returns Unit

        command.translateTo(commandContext)

        assertEquals(1, serviceSlot.captured)
        assertEquals("translated", printWriterSlot.captured)
    }

    private fun mockPrintWriter(): PrintWriter {
        val mockTerminal: Terminal = mockk()
        every { commandContext.terminal } returns mockTerminal

        val mockPrintWriter: PrintWriter = mockk()
        every { mockTerminal.writer() } returns mockPrintWriter

        return mockPrintWriter
    }
}