package exercise.numerics.translator.roman

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test


class RomanNumeralTranslatorTest {
    private val translator: RomanNumeralTranslator = RomanNumeralTranslator()

    @Test
    fun `Translates 1000 to M`() {
        assertThat(translator.toText(1000)).isEqualTo("M")
    }
}