package exercise.numerics.translator

import exercise.numerics.translator.config.CommandConfig
import exercise.numerics.translator.roman.RomanNumeralTranslator
import exercise.numerics.translator.roman.TranslationError.ZERO_NOT_SUPPORTED
import org.awaitility.Awaitility.await
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.shell.test.ShellTestClient
import org.springframework.shell.test.autoconfigure.ShellTest
import org.springframework.test.annotation.DirtiesContext
import java.util.concurrent.TimeUnit
import kotlin.test.Test

@ShellTest
@Import(value = [CommandConfig::class, RomanNumeralTranslator::class])
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RomanNumeralCommandLineIntegrationTest {

    @Autowired
    lateinit var client: ShellTestClient

    @Test
    fun `Interactive shell gracefully handles invalid values in to-roman-numeral command`() {
        assertShellCommandResult("to-roman-numeral --value 0", ZERO_NOT_SUPPORTED.message)
    }

    @Test
    fun `Interactive shell handles to-roman-numeral command`() {
        assertShellCommandResult("to-roman-numeral --value 123", "CXXIII")
    }

    @Test
    fun `Interactive shell handles from-roman-numeral command`() {
        assertShellCommandResult("from-roman-numeral --value XIV", "14")
    }

    private fun assertShellCommandResult(command: String, expected: String) {
        val session = client.interactive().run()
        session.write(session.writeSequence().text(command).carriageReturn().build());

        await().atMost(1, TimeUnit.SECONDS).until {
            val output =
                client.screen().lines()
                    .filter { it.isNotBlank() }
                    .map { it.trim() }

            if (!output.contains(expected)) {
                throw AssertionError("Expected screen to contain '$expected' but was $output")
            }
            true
        }
    }
}