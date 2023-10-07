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
    private val printWriterSlot = slot<Any>()

    @Test
    fun `translateTo() passes argument from context and writes result`() {
        every { commandContext.getOptionValue<Int>(any()) } returns 1

        every { translator.toText(1) } returns "translated"
        every { mockPrintWriter.println(capture(printWriterSlot)) } returns Unit

        command.translateTo(commandContext)

        assertEquals("translated", printWriterSlot.captured)
    }

    @Test
    fun `translateFrom() passes argument from context and writes result`() {
        every { commandContext.getOptionValue<String>(any()) } returns "1"

        every { translator.fromText("1") } returns 1
        every { mockPrintWriter.println(capture(printWriterSlot)) } returns Unit

        command.translateFrom(commandContext)

        assertEquals(1, printWriterSlot.captured)
    }

    // TODO: Exceptional cases; omitted for exercise brevity

    private fun mockPrintWriter(): PrintWriter {
        val mockTerminal: Terminal = mockk()
        every { commandContext.terminal } returns mockTerminal

        val mockPrintWriter: PrintWriter = mockk()
        every { mockTerminal.writer() } returns mockPrintWriter

        return mockPrintWriter
    }
}