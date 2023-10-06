package exercise.numerics.translator.roman

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class RomanNumeralTranslatorTest {
    private val translator: RomanNumeralTranslator = RomanNumeralTranslator()

    @Test
    fun `Rejects zero`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "0 is not supported by the roman numeral system",
            block = { translator.toText(0) }
        )
    }

    @Test
    fun `Rejects negative values`() {
        assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            message = "Negative numbers are not supported by the roman numeral system",
            block = { translator.toText(-1) }
        )
    }

    @Test
    fun `Translates 1000 to M`() {
        assertEquals("M", translator.toText(1000))
    }
}